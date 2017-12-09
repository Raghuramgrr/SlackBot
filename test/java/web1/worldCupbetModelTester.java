package web1;
import static org.junit.Assert.*;

import java.awt.PageAttributes.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.worldcupbot.slack.controllers.BetController;
import com.worldcupbot.slack.model.WorldCupBet;

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
		assertTrue(match.getMatch() != Integer.parseInt("String"));
		
		match.setForscore(1);
		assertTrue(match.getForscore() == 1);
		
		assertTrue(match.getAgainstscore() != Integer.parseInt("slack"));
		
		match.setAgainstscore(9);
		assertTrue(match.getAgainstscore()==9);
		
		
		match.setPointsWon(6);
		assertTrue(match.getPointsWon() == 6);
		
		match.setPointsWon(90);
		assertTrue(match.getPointsWon()!= Integer.parseInt("Bot"));
		
		
	}
	
	
	
	

}
