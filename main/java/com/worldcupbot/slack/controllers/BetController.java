

package com.worldcupbot.slack.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldcupbot.slack.model.WorldCupBet;


@RestController
public class BetController {

    private static Map< String, Map<Integer, WorldCupBet>> userMap = new HashMap<>();
    private static Map<String,Integer> pointsMap =new HashMap();
    private String forScore;
    private String againstScore;

    @RequestMapping("/bet")
    public String placeBet(@RequestParam("text") String text,
            @RequestParam("user_name") String user) {

        WorldCupBet worldCupBet = new WorldCupBet();

        String response = "";
        
        String match = "";
        String score = "";
        String verb = "";
        String[] tokens = text.toString().split(" ");
        //Splitting the test response from slackbot to tokens 

        if(tokens.length == 1){
            verb = tokens[0];
            }
            
            else if(tokens.length ==3){
              for (int i = 0; i < tokens.length; i++) {
                  verb = tokens[0];
                  match = tokens[1];
                  score = tokens[2];
              }
            }

        String[] scores = score.toString().split(":");

        //Spliting the for And againstscores
        forScore = scores[0];
        againstScore = scores[1];

        if (verb.equalsIgnoreCase("bet")) {
            return placeBet(user, worldCupBet, Integer.valueOf(match));
        }

        if (verb.equalsIgnoreCase("set-score")) {
            return calculatePoints(user);

        }
        if(verb.equalsIgnoreCase("ranking")) {
        	return ranking();
        }
        
        else {
            response = "Invalid option Try /worldcup set-score 1 1:0 or /worldcup bet 1 1:0 or /worldcup ranking";
        }

        return response;
    }

    private String ranking() {
    	String resp="";
    	Iterator i = pointsMap.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry)i.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
             resp = pair.getKey()+"+----------"+pair.getValue();
        } 
		return resp;
    }
	private boolean isAlreadyBet(Integer match, String user) {
        Map<Integer, WorldCupBet> InnerMap = userMap.get(user);
        if (InnerMap.containsKey(match)) {
            return true;
        } else {
            return false;
        }
    }

    private String placeBet(String user, WorldCupBet worldCupBet, Integer match) {
        String response="";
        if (!userMap.containsKey(user)) {
            worldCupBet.setForscore(Integer.valueOf(forScore));
            worldCupBet.setAgainstscore(Integer.valueOf(againstScore));

            Map<Integer, WorldCupBet> InnerMap = new HashMap<>();
            InnerMap.put(match, worldCupBet);
            
            userMap.put(user, InnerMap);
            response = " bet placed on match--" + match + "predicted scores --- " + forScore + ":" + againstScore;
        } 
        else if (userMap.containsKey(user) && !(isAlreadyBet(match, user)))  {
            Map<Integer, WorldCupBet> InnerMap = userMap.get(user);
            
            worldCupBet.setForscore(Integer.valueOf(forScore));
            worldCupBet.setAgainstscore(Integer.valueOf(againstScore));

            InnerMap.put(match, worldCupBet);

            response = " bet placed on match--" + match + "predicted scores--- " + forScore + ":" + againstScore;
        } else if (userMap.containsKey(user) && (isAlreadyBet(match, user))) {
            response = " Bet already exists for match --" + match + " your predicted scores " + forScore + ":" + againstScore;
        }
        

        return response;
    }
    
    
    private String calculatePoints(String user){
        int bet = 0;
        int original = 0;
        int points = 0;
        String response="";
        for (String userKey : userMap.keySet()) {
                if (userKey.equals(user)) {
                    Map<Integer, WorldCupBet> InnerMap = userMap.get(user);
                    for (Integer matchKey : InnerMap.keySet()) {
                        
                        WorldCupBet wcBet = InnerMap.get(matchKey);

                        bet = Math.abs(wcBet.getAgainstscore()
                                - wcBet.getForscore());
                        original = Math.abs(Integer.valueOf(forScore)
                                - Integer.valueOf(againstScore));

                        
                        
                        if (wcBet.getForscore() == Integer.valueOf(forScore) && wcBet.getAgainstscore() == Integer.valueOf(againstScore)) {
                            points = 3;
                            System.out.println("Scenario - 3 gets 1 point" + wcBet.getAgainstscore() + forScore);
                        } 
                        
                        if (bet == original) {
                            points = 2;
                            System.out.println("Scenario - 2 gets 2 point" + bet + original);

                        }

                        if ((wcBet.getAgainstscore() > wcBet.getForscore()) && (Integer.valueOf(againstScore) > Integer.valueOf(forScore))) {
                            points = 1;
                            System.out.println("Scenario - 1A gets 1 point" + wcBet.getAgainstscore() + againstScore);
                        }

                        if ((wcBet.getForscore() > wcBet.getAgainstscore()) && (Integer.valueOf(forScore) > Integer.valueOf(againstScore))) {
                            points = 1;
                            System.out.println("Scenario - 1B gets 1 point" + wcBet.getAgainstscore() + wcBet.getForscore());

                        }

                       
                       
                        wcBet.setPointsWon(points);
                        pointsMap.put(userKey, points);
                        response = userKey + "" + points;
                        
                    }
                }
            }
        return response;
    }
    
    
   
}


