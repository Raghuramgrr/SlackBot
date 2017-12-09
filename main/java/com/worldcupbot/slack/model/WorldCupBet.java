package com.worldcupbot.slack.model;

import java.util.HashMap;
import java.util.Map;

public class WorldCupBet {
	
	private int match;
    private int forscore;
    private int againstscore;
    private int pointsWon;
    
    
    
    
	public WorldCupBet(int match, int forscore, int againstscore, int pointsWon) {
		super();
		this.match = match;
		this.forscore = forscore;
		this.againstscore = againstscore;
		this.pointsWon = pointsWon;
	}
	
	
	
	public WorldCupBet() {
		
	}



	public int getPointsWon() {
		return pointsWon;
	}
	public void setPointsWon(int pointsWon) {
		this.pointsWon = pointsWon;
	}
	public int getMatch() {
		return match;
	}
	public void setMatch(int match) {
		this.match = match;
	}
	public int getForscore() {
		return forscore;
	}
	public void setForscore(int forscore) {
		this.forscore = forscore;
	}
	public int getAgainstscore() {
		return againstscore;
	}
	public void setAgainstscore(int againstscore) {
		this.againstscore = againstscore;
	}
	
}
