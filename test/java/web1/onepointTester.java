package web1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.worldcupbot.slack.controllers.BetController;

public class onepointTester {

	BetController calcTest;
	
	@Before
	public void init1points() {
	calcTest =  new BetController();
	}
	@Test 
	public void testCalculateAllOnePoints() throws InterruptedException {
		
		
		assertNotEquals("Welcome This is your first bet Zlatan" , calcTest.placeBet("bet 1 1:0", "Zlatan"));
		assertEquals("1",calcTest.placeBet("set-score 1 3:0", "Zlatan"));
		TimeUnit.SECONDS.sleep(5);
		
		assertNotEquals("Welcome This is your first bet Zlatan" , calcTest.placeBet("bet 2 2:0", "Zlatan"));
		assertEquals("1",calcTest.placeBet("set-score 2 6:5", "Zlatan"));
		TimeUnit.SECONDS.sleep(5);
		
		assertNotEquals("Welcome This is your first bet Zlatan" , calcTest.placeBet("bet 3 3:0", "Zlatan"));
		assertEquals("1",calcTest.placeBet("set-score 3 5:0", "Zlatan"));
		TimeUnit.SECONDS.sleep(5);
		
		
		assertNotEquals("Welcome This is your first bet Modric" , calcTest.placeBet("bet 1 0:1", "Modric"));
		assertEquals("1",calcTest.placeBet("set-score 1 0:9", "Modric"));
		TimeUnit.SECONDS.sleep(5);
		
		assertNotEquals("Welcome This is your first bet Modric" , calcTest.placeBet("bet 2 2:0", "Modric"));
		assertEquals("1",calcTest.placeBet("set-score 2 5:2", "Modric"));
		TimeUnit.SECONDS.sleep(5);
		
		assertNotEquals("Welcome This is your first bet Modric" , calcTest.placeBet("bet 3 5:0", "Modric"));
		assertEquals("1",calcTest.placeBet("set-score 3 3:1", "Modric"));
		TimeUnit.SECONDS.sleep(5);
		
		
	}

	
	
}
