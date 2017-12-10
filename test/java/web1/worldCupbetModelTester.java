package web1;

import static org.junit.Assert.*;

import java.awt.PageAttributes.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.worldcupbot.slack.controllers.BetController;
import com.worldcupbot.slack.model.WorldCupBet;
import static org.junit.Assert.*;
public class worldCupbetModelTester {
	@Mock
	private BetController betTest;
	
	@Autowired
	private MockMvc mockMvc;
	@Test
	public void testMatch() {
		WorldCupBet match=new WorldCupBet();
		
		match.setMatch(5);
		assertTrue(match.getMatch() == 5);
		
		match.setMatch(5);
		assertTrue(match.getMatch() != 2220);
		
		match.setForscore(1);
		assertTrue(match.getForscore() == 1);
		
		assertTrue(match.getAgainstscore() != 9900);
		
		match.setAgainstscore(9);
		assertTrue(match.getAgainstscore()==9);
		
		
		match.setPointsWon(6);
		assertTrue(match.getPointsWon() == 6);
		
		match.setPointsWon(90);
		assertTrue(match.getPointsWon()!= 119877);
		
		
	}
	
	
	@Test
	public void testCalculatePoints( ) {
		BetController calcTest = new BetController();
		
		assertEquals("Welcome This is your first bet raghu" ,calcTest.placeBet("bet 1 1:0", "raghu"));
		assertEquals("3",calcTest.placeBet("set-score 1 1:0", "raghu"));
		assertNotEquals(calcTest.placeBet("bet 2 2:0", "raghu"),matchesPattern("^[Good]+"));
		assertEquals("3",calcTest.placeBet("set-score 2 2:0", "raghu"));
		assertNotEquals("Welcome This is your first bet raghu" ,calcTest.placeBet("bet 3 3:0", "raghu"));
		assertEquals("3",calcTest.placeBet("set-score 3 3:0", "raghu"));
		
		assertEquals("Welcome This is your first bet ram" ,calcTest.placeBet("bet 1 1:0", "ram"));
		assertEquals("3",calcTest.placeBet("set-score 1 1:0", "ram"));
		assertNotEquals("Welcome This is your first bet raghu" ,calcTest.placeBet("bet 2 2:0", "ram"));
		assertEquals("3",calcTest.placeBet("set-score 2 2:0", "ram"));
		assertNotEquals("Welcome This is your first bet raghu" ,calcTest.placeBet("bet 3 3:0", "ram"));
		assertEquals("3",calcTest.placeBet("set-score 3 3:0", "ram"));
		
		//assertThat(calcTest.placeBet("bet 1 1:0", "raghu"),matchesPattern("^[Bet]+"));

		assertNotEquals("Welcome all this is ",(calcTest.placeBet("bet 1 1:0", "raghu")));
		
		//assertEquals("3",calcTest.placeBet("set-score 1 1:0", "raghu"));
		
		
	   
		
	}
	
	
	
	
	
	
	

}
