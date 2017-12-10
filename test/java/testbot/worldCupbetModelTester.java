package testbot;

import static org.junit.Assert.*;

import java.awt.PageAttributes.MediaType;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
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
	private WorldCupBet match;
	BetController calcTest;
	
	@Autowired
	private MockMvc mockMvc;
	@Before
	public void init() {
	 match=new WorldCupBet();
			}
	@Test
	public void testMatch() {
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
		
		
	
	
	
	
	

}
