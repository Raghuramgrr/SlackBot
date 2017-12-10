### ***SlackBot for Betting in Football worldcup***

A simple slackbot to allow slack channel users to bet  and view the scores

### **Getting Started **
Ask Uncle Google  :: 
Slack API documentation 
Spring maven understanding
REST- POST,GET understanding
Tomcat servers in ubuntu 
AWS EC2,Documentation

### ***Prerequisites:***
Spring Tool suite - Any Eclipse will also suffice
POSTMAN - Chrome Extension
Git CLI 


### ***Usage examples:***
/worldcup bet 1 1:0 - places bet for match 1 with score 1:0
/worldcup set-score 1 1:0 - sets score for match 1 with score 1:0
/worldcup ranking  - ranks all the slack users 


### ***Scores ***
  *-> 3 points in case the result is right*
  *-> 2 points in case the difference is right (e.g. score is 2:1 and bet is 3:2 or 1:0)*
  *-> 1 point in case the tendency is right (correct winner or tie)*


### Deployment
WAR deployment in AWS EC2

*Make sure you delete the existing version and restart the Tomcat*

Create a LINUX instance in EC2 and install the latest JAVA-SDK and TOMCAT

Paste the WAR file in :+1: /var/lib/tomacatX/webapps

Use the PUBLIC IP address of EC2 and paste in URL path of slash command URL. 





### Testing 

All unitTests in slackBot Folder 

*Without unitTests its very hard to test a controller which runs in server*

Tested using postMan for multiple User scenario  

Tried with Collections of requests - sending a set of 20...30 POST requests.


*Now Trying to automate the collection of requests  via Jenkins - Useful of Continuous Integration.*


