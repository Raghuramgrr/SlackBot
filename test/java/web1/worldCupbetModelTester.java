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
	/*

	@Test
	public void testCalculateAllThreePoints( ) {
		BetController calcTest = new BetController();
		
		assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 1 1:0", "Ronaldo"));
		assertEquals("3",calcTest.placeBet("set-score 1 1:0", "Ronaldo"));
		assertNotEquals(calcTest.placeBet("bet 2 2:0", "Ronaldo"),matchesPattern("^[Good]+"));
		assertEquals("3",calcTest.placeBet("set-score 2 2:0", "Ronaldo"));
		assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 3 3:0", "Ronaldo"));
		assertEquals("3",calcTest.placeBet("set-score 3 3:0", "Ronaldo"));
		
		assertEquals("Welcome This is your first bet Muller" ,calcTest.placeBet("bet 1 1:0", "Muller"));
		assertEquals("3",calcTest.placeBet("set-score 1 1:0", "Muller"));
		assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 2 2:0", "Muller"));
		assertEquals("3",calcTest.placeBet("set-score 2 2:0", "Muller"));
		assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 3 3:0", "Muller"));
		assertEquals("3",calcTest.placeBet("set-score 3 3:0", "Muller"));
		
		assertEquals("Welcome This is your first bet Muller" ,calcTest.placeBet("bet 1 1:0", "Muller"));
		assertEquals("3",calcTest.placeBet("set-score 1 1:0", "Muller"));
		assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 2 2:0", "Muller"));
		assertEquals("3",calcTest.placeBet("set-score 2 2:0", "Muller"));
		assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 3 3:0", "Muller"));
		assertEquals("3",calcTest.placeBet("set-score 3 3:0", "Muller"));
		
		//assertThat(calcTest.placeBet("bet 1 1:0", "Ronaldo"),matchesPattern("^[Bet]+"));

		assertNotEquals("Welcome all this is ",(calcTest.placeBet("bet 1 1:0", "Ronaldo")));
		
		//assertEquals("3",calcTest.placeBet("set-score 1 1:0", "raghu"));
		
		
	   
		
	}
	
	@Test
	public void testCalculateAlltwoPoints( ) {
		BetController calcTest = new BetController();
		
		assertNotEquals("Welcome This is your first bet Rooney" , calcTest.placeBet("bet 1 1:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 1 2:1", "Rooney"));
		assertNotEquals("Welcome This is your first bet Rooney" ,calcTest.placeBet("bet 2 2:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 2 4:2", "Rooney"));
		assertNotEquals("Welcome This is your first bet Rooney" ,calcTest.placeBet("bet 3 4:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 3 5:1", "Rooney"));
		assertNotEquals("Welcome This is your first bet Rooney" ,calcTest.placeBet("bet 1 1:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 1 2:1", "Rooney"));
		
		
		assertNotEquals("Welcome This is your first bet Neur" , calcTest.placeBet("bet 1 1:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 1 3:2", "Neur"));
		assertNotEquals("Welcome This is your first bet Neur" ,calcTest.placeBet("bet 2 2:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 2 6:4", "Neur"));
		assertNotEquals("Welcome This is your first bet Neur" ,calcTest.placeBet("bet 3 4:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 3 4:8", "Neur"));
		assertNotEquals("Welcome This is your first bet Neur" ,calcTest.placeBet("bet 1 1:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 1 5:6", "Neur"));
		
	}
	*/
	@Test 
	public void testCalculateAllOnePoints() {
		
		BetController calcTest = new BetController();
	
		assertNotEquals("Welcome This is your first bet Zlatan" , calcTest.placeBet("bet 1 1:0", "Zlatan"));
		assertEquals("1",calcTest.placeBet("set-score 1 3:0", "Zlatan"));
		assertNotEquals("Welcome This is your first bet Zlatan" , calcTest.placeBet("bet 2 2:0", "Zlatan"));
		assertEquals("1",calcTest.placeBet("set-score 2 6:5", "Zlatan"));
		assertNotEquals("Welcome This is your first bet Zlatan" , calcTest.placeBet("bet 3 3:0", "Zlatan"));
		assertEquals("1",calcTest.placeBet("set-score 3 5:0", "Zlatan"));
		
		assertNotEquals("Welcome This is your first bet Modric" , calcTest.placeBet("bet 1 0:1", "Modric"));
		assertEquals("1",calcTest.placeBet("set-score 1 0:9", "Modric"));
		assertNotEquals("Welcome This is your first bet Modric" , calcTest.placeBet("bet 2 0:2", "Modric"));
		assertEquals("1",calcTest.placeBet("set-score 2 0:5", "Modric"));
		assertNotEquals("Welcome This is your first bet Modric" , calcTest.placeBet("bet 3 0:5", "Modric"));
		assertEquals("1",calcTest.placeBet("set-score 3 0:2", "Modric"));
		
	}
	
	
	
	
	
	

}
