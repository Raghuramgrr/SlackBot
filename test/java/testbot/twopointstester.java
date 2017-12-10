package testbot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.worldcupbot.slack.controllers.BetController;

public class twopointstester {

	BetController calcTest;
	
	
	
	
	@Before
	public void init2points() {
	calcTest =  new BetController();
		
	}

	@Test
	public void testCalculateAlltwoPoints( ) throws InterruptedException {
		
		assertNotEquals("Welcome This is your first bet Rooney" , calcTest.placeBet("bet 1 1:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 1 2:1", "Rooney"));
		assertEquals("Rooney\t2",calcTest.placeBet("ranking", "Rooney"));
		
		//TimeUnit.SECONDS.sleep(5);
		assertNotEquals("Welcome This is your first bet Rooney" ,calcTest.placeBet("bet 2 2:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 2 4:2", "Rooney"));
		assertEquals("Rooney\t4",calcTest.placeBet("ranking", "Rooney"));
		
		TimeUnit.SECONDS.sleep(5);
		assertNotEquals("Welcome This is your first bet Rooney" ,calcTest.placeBet("bet 3 4:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 3 5:1", "Rooney"));
		assertEquals("Rooney\t6",calcTest.placeBet("ranking", "Rooney"));
		
		TimeUnit.SECONDS.sleep(5);
		assertNotEquals("Welcome This is your first bet Rooney" ,calcTest.placeBet("bet 1 1:0", "Rooney"));
		assertEquals("2",calcTest.placeBet("set-score 1 2:1", "Rooney"));
		assertEquals("Rooney\t8",calcTest.placeBet("ranking", "Rooney"));
		TimeUnit.SECONDS.sleep(5);
		
		assertNotEquals("Welcome This is your first bet Neur" , calcTest.placeBet("bet 1 1:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 1 3:2", "Neur"));
		assertEquals("Neur\t2Rooney\t8",calcTest.placeBet("ranking", "Neur"));
		
		TimeUnit.SECONDS.sleep(5);
		assertNotEquals("Welcome This is your first bet Neur" ,calcTest.placeBet("bet 2 2:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 2 6:4", "Neur"));
		assertEquals("Neur\t4Rooney\t8",calcTest.placeBet("ranking", "Neur"));
		
		TimeUnit.SECONDS.sleep(5);
		assertNotEquals("Welcome This is your first bet Neur" ,calcTest.placeBet("bet 3 4:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 3 4:8", "Neur"));
		assertEquals("Neur\t6Rooney\t8",calcTest.placeBet("ranking", "Neur"));
		
		TimeUnit.SECONDS.sleep(5);
		assertNotEquals("Welcome This is your first bet Neur" ,calcTest.placeBet("bet 1 1:0", "Neur"));
		assertEquals("2",calcTest.placeBet("set-score 1 5:6", "Neur"));
		assertEquals("Neur\t8Rooney\t8",calcTest.placeBet("ranking", "Neur"));
		
		TimeUnit.SECONDS.sleep(5);
		
	}

	
}
