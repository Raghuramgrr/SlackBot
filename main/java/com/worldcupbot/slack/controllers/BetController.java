package com.worldcupbot.slack.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldcupbot.slack.model.WorldCupBet;


@RestController
public class BetController {

 private static Map < String, Map < String, WorldCupBet >> userMap = new HashMap < > ();
 private static Map < String, Integer > pointsMap = new HashMap();
 private static Map < String, WorldCupBet > matchMap = new HashMap < > ();

 private String forScore;
 private String againstScore;
 private boolean flag = false;

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

  if (tokens.length == 1) {
   verb = tokens[0];
   System.out.println(verb);

  } else if (tokens.length == 3) {
   verb = tokens[0];
   match = tokens[1];
   score = tokens[2];
   String[] scores = score.toString().split(":|-");
   forScore = scores[0];
   againstScore = scores[1];
  }
  /*for(int i=0;i<tokens.length;i++) {
  	if(!(Pattern.matches("[a-ZA-Z0-9:-]+",tokens[i])))
  			{
  		response ="Bad Input - Pls Try in this format/worldcup bet 1 1:0 or  /worldcup set-score 1 1:0 or /worldcup ranking ";
  	}
  }
  */

  if (verb.equalsIgnoreCase("bet")) {
   return placeBet(user, worldCupBet, match);
  }

  if (verb.equalsIgnoreCase("set-score")) {
   return calculatePoints(user, match);

  }
  if (verb.equalsIgnoreCase("ranking")) {
   String resp = ranking();

   System.out.println(resp);
   return resp;
  } else {
   response = "Invalid option Try /worldcup set-score 1 1:0 or /worldcup bet 1 1:0 or /worldcup ranking";
  }

  return response;
 }


 private boolean isAlreadyBet(String match, String user) {
  Map < String, WorldCupBet > matchMap = userMap.get(user);
  if (matchMap.containsKey(match)) {
   return true;
  } else {
   return false;
  }
 }









 private String placeBet(String user, WorldCupBet worldCupBet, String match) {
  String response = "";

  System.out.println("" + user + "" + match);
  if (!userMap.containsKey(user)) {
   System.out.println("If UserMap does not contains" + user + "" + match);

   worldCupBet.setForscore(Integer.valueOf(forScore));
   worldCupBet.setAgainstscore(Integer.valueOf(againstScore));


   matchMap.put(match, worldCupBet);

   userMap.put(user, matchMap);
   response = " bet placed on match--" + match + "\tpredicted scores\t" + forScore + ":" + againstScore;
   response = "Welcome This is your first bet " + user;
  }

  if (userMap.containsKey(user) && !(isAlreadyBet(match, user))) {
   System.out.println("Second Bet if userMap contains " + user + "" + match);

   worldCupBet.setForscore(Integer.valueOf(forScore));
   worldCupBet.setAgainstscore(Integer.valueOf(againstScore));

   matchMap.put(match, worldCupBet);

   //userMap.put(user, matchMap);
   response = " bet placed on match\t" + match + "\tpredicted scores\t" + forScore + ":" + againstScore;
   response = " Good luck on the bet \t" + user;
  } else if (userMap.containsKey(user) && (isAlreadyBet(match, user))) {
   response = " Bet Placed for match \t" + match + "\tyour predicted scores\t" + forScore + ":" + againstScore;
  }
  for (Entry < String, WorldCupBet > entry: matchMap.entrySet()) {
   String key = entry.getKey();
   WorldCupBet value = entry.getValue();

   System.out.println(key + "\t" + value.getForscore() + "\t" + value.getAgainstscore());

  }

  return response;
 }


 private String calculatePoints(String user, String match) {
  int bet = 0;
  int original = 0;
  int points = 0;
  String response = "";

  for (String userKey: userMap.keySet()) {
   if (userKey.equals(user)) {
    Map < String, WorldCupBet > matchMap = userMap.get(user);
    for (String matchKey: matchMap.keySet()) {
     System.out.print("\t MatchKey \t" + matchKey);
     if (matchKey.equals(match)) {
      WorldCupBet wcBet = matchMap.get(matchKey);


      System.out.print("\tForScore\t" + wcBet.getForscore() + "\tAgainstScore\t" + wcBet.getAgainstscore());



      bet = Math.abs(wcBet.getAgainstscore() - wcBet.getForscore());
      original = Math.abs(Integer.valueOf(forScore) - Integer.valueOf(againstScore));
      System.out.println("\tpredicted\t" + bet + "\tActual\t" + original);

      if ((wcBet.getForscore() == Integer.valueOf(forScore)) && (wcBet.getAgainstscore() == Integer.valueOf(againstScore))) {
       points = 3;
       pushPoints(user, points);
       System.out.println("points is 3 " + wcBet.getAgainstscore() + wcBet.getAgainstscore() + Integer.valueOf(forScore) + Integer.valueOf(againstScore) + points);
       flag = true;
       return String.valueOf(points);

      }

      if (bet == original && flag == false) {
       points = 2;
       pushPoints(user, points);
       System.out.println("\t2 points scenario \t" + bet + original);
       return String.valueOf(points);

      }

      if ((wcBet.getAgainstscore() > wcBet.getForscore()) && (Integer.valueOf(againstScore) > Integer.valueOf(forScore) && flag == false)) {

       points = 1;
       System.out.println("points is 1  " + wcBet.getAgainstscore() + wcBet.getAgainstscore() + Integer.valueOf(forScore) + Integer.valueOf(againstScore) + points);
       System.out.print(wcBet.getAgainstscore() + wcBet.getForscore() + wcBet.getMatch());

       pushPoints(user, points);
       //wcBet.setPointsWon(points);
       return String.valueOf(points);
      }
      System.out.println("ForScore\t" + wcBet.getForscore() + "AgainstScore\t" + wcBet.getAgainstscore() + "ActualForScore\t" + Integer.valueOf(forScore) + "ActualAgainstScore\t" + Integer.valueOf(againstScore));

      if ((wcBet.getForscore() > wcBet.getAgainstscore()) && (Integer.valueOf(forScore) > Integer.valueOf(againstScore) && flag == false)) {
       points = 1;
       System.out.println("points is 1 " + wcBet.getAgainstscore() + wcBet.getAgainstscore() + Integer.valueOf(forScore) + Integer.valueOf(againstScore) + points);
       pushPoints(user, points);
       System.out.println("Scenario - 1B gets 1 point" + wcBet.getAgainstscore() + wcBet.getForscore());
       return String.valueOf(points);
      }

      // pointsMap.put(user,points);
      // response = user + "" + Integer.valueOf(pointsMap.get(user));


     }
    }
   }
  }
  return response;

 }

 private String pushPoints(String user, int points) {
  int value;
  int totalpoints = 0;
  if (pointsMap.isEmpty() || null == pointsMap || pointsMap.get(user) == null) {
   pointsMap.put(user, points);
   return "pushed values";
  } else {
   for (String key: pointsMap.keySet()) {
    if (key.equalsIgnoreCase(user)) {


     value = pointsMap.get(user);
     System.out.print("\t\t" + key + "\t\t" + value);
     totalpoints = points + value;
     System.out.print("\t\t" + points + "\t\t" + totalpoints);

     System.out.println(points + "\t" + value);
     pointsMap.put(user, totalpoints);
    }
   }
  }
  System.out.println("points\t" + points + "points from hash map\t" + pointsMap.get(user) + "" + user);

  return null;
 }

 private String ranking() {
  String resp = "";
  if (pointsMap.isEmpty() || null == pointsMap) {
   System.out.println("PointsMap is empty");
   resp += "Real Scores are yet to come.....";
  }


  for (Map.Entry < String, Integer > entry: pointsMap.entrySet()) {
   System.out.println("User : " + entry.getKey() + " Points : " + entry.getValue());
   resp += entry.getKey() + "\t" + entry.getValue();
  }

  return resp;
 }


}