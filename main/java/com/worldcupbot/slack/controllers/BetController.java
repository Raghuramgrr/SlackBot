

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
        	System.out.println(verb);
            
            }
            
            else if(tokens.length ==3){
                  verb = tokens[0];
                  match = tokens[1];
                  score = tokens[2];
                  String[] scores = score.toString().split(":");          
                  forScore = scores[0];
                  againstScore = scores[1];
            }

        
        //Spliting the for And againstscores
       

        if (verb.equalsIgnoreCase("bet")) {
            return placeBet(user, worldCupBet, Integer.valueOf(match));
        }

        if (verb.equalsIgnoreCase("set-score")) {
            return calculatePoints(user);

        }
        if(verb.equalsIgnoreCase("ranking")) {
        String resp= ranking();
        
        System.out.println(resp);
        return resp;
        }
        
        else {
            response = "Invalid option Try /worldcup set-score 1 1:0 or /worldcup bet 1 1:0 or /worldcup ranking";
        }

        return response;
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
        Map<Integer, WorldCupBet> InnerMap = new HashMap<>();
        System.out.println(""+user+""+match);  
        if (!userMap.containsKey(user)) {
        	   System.out.println("If UserMap does not contains"+user+""+match);  
        	     
            worldCupBet.setForscore(Integer.valueOf(forScore));
            worldCupBet.setAgainstscore(Integer.valueOf(againstScore));

          
            InnerMap.put(match, worldCupBet);
            
            userMap.put(user, InnerMap);
            response = " bet placed on match--" + match + "-----predicted scores --- " + forScore + ":" + againstScore;
            	response="First bet for the user "+user;
        } 
        else if (userMap.containsKey(user) && !(isAlreadyBet(match, user)))  {
        	   System.out.println("Second Bet if userMap contains "+user+""+match);  
        	     
            worldCupBet.setForscore(Integer.valueOf(forScore));
            worldCupBet.setAgainstscore(Integer.valueOf(againstScore));
          
            InnerMap.put(match, worldCupBet);
            
            userMap.put(user, InnerMap);
            response = " bet placed on match--" + match + "----predicted scores--- " + forScore + ":" + againstScore;
            	response = "Second Bet for the user "+user;
         	} else if (userMap.containsKey(user) && (isAlreadyBet(match, user))) {
            response = " Bet already exists for match --" + match + "----your predicted scores--- " + forScore + ":" + againstScore;
        }
        

        return response;
    }
    
    
    private String calculatePoints(String user){
        int bet = 0;
        int original = 0;
        int points = 0;
        String response="";
        
        System.out.println("User to Calculate points -- "+user);
        //        if (userMap.get(userKey).equals(user)) {
          // System.out.println("User to Calculate points -- "+user);
    	    
                    Map<Integer, WorldCupBet> InnerMap = userMap.get(user);
                    for (Integer matchKey : InnerMap.keySet()) {
                        
                        WorldCupBet wcBet = InnerMap.get(matchKey);

                        bet = Math.abs(wcBet.getAgainstscore()
                                - wcBet.getForscore());
                        original = Math.abs(Integer.valueOf(forScore)
                                - Integer.valueOf(againstScore));

                        
                        if (wcBet.getForscore() == Integer.valueOf(forScore) && wcBet.getAgainstscore() == Integer.valueOf(againstScore)) {
                        	points =3;
                        	pushPoints(user,points);
                        	return String.valueOf(points);
                            
                        }
                         
                        else 
                        if (bet == original) {
                        	points =2;
                        	pushPoints(user,points);
                        	System.out.println("Scenario - 2 gets 2 point" + bet + original);
                        	return String.valueOf(points);
                                
                        }
                        
                        
                        
                        else 
                        	if ((wcBet.getAgainstscore() > wcBet.getForscore()) && (Integer.valueOf(againstScore) > Integer.valueOf(forScore))) 
                        	{
                        
                        		points =1;
                        		pushPoints(user,points);
                        		//wcBet.setPointsWon(points);
                            return String.valueOf(points);
                        }

                        	else  if ((wcBet.getForscore() > wcBet.getAgainstscore()) && (Integer.valueOf(forScore) > Integer.valueOf(againstScore))) {
                        		points =1;
                        		pushPoints(user,points);
                            	System.out.println("Scenario - 1B gets 1 point" + wcBet.getAgainstscore() + wcBet.getForscore());
                          return String.valueOf(points);
                        } 
                    }
                    System.out.println("User"+user);
                    System.out.println("userkey"+user);
                   // pointsMap.put(user,points);
                    response = user + "" + Integer.valueOf(pointsMap.get(user));
                    
       
        return response;
       
    
	
    }
 
    private String pushPoints(String user,int points) {
    	int value;
    	int totalpoint = 0;
    				if(pointsMap.isEmpty() || null==pointsMap) {
                        	pointsMap.put(user, points);
                        	return "pushed values";
                        	}
                       else {
                        	for(String key : pointsMap.keySet()) {
                        		if(key.equalsIgnoreCase(user))
                        		{
                        	 value= pointsMap.get(user);
                        	 totalpoint = points + value;
                        	System.out.println(points+"----"+value );
                        	pointsMap.put(user, totalpoint);
                        		}
                        	
                        	else {
                        		pointsMap.put(user,totalpoint);
                        	}
                        	}
                        	}
                        	System.out.println("points ++"+points+"points from hash map---"+pointsMap.get(user)+""+user);
							
                        	return null;
    }
   
    private String ranking() {
    	String resp="";
    	if(pointsMap.isEmpty() || null==pointsMap) {
    	 	System.out.println("PointsMap is empty");
    	 	resp+= "Points Map is Empty - Real Scores are yet to come.....";
    	}
    	
    	for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {
    		System.out.println("User : " + entry.getKey() + " Points : " + entry.getValue());
    		resp += entry.getKey()+"----------"+entry.getValue()+"\n";
    	}
         
		return resp;
    }
    
    
}
        
   



