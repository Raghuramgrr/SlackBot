package testbot;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.worldcupbot.slack.controllers.BetController;

public class threepointstester {

	BetController calcTest;
	
	
	@Before
	public void init3points() {
	calcTest =  new BetController();
		}

	@Test
	public void testCalculateAllThreePoints( ) throws InterruptedException {
	
	assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 1 1:0", "Ronaldo"));
	assertEquals("3",calcTest.placeBet("set-score 1 1:0", "Ronaldo"));
	assertEquals("Ronaldo\t3",calcTest.placeBet("ranking", "Ronaldo"));
	
	TimeUnit.SECONDS.sleep(5);
	
	assertNotEquals(calcTest.placeBet("bet 2 2:0", "Ronaldo"),matchesPattern("^[Good]+"));
	assertEquals("3",calcTest.placeBet("set-score 2 2:0", "Ronaldo"));
	assertEquals("Ronaldo\t6",calcTest.placeBet("ranking", "Ronaldo"));
	
	TimeUnit.SECONDS.sleep(5);
	
	assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 3 3:0", "Ronaldo"));
	assertEquals("3",calcTest.placeBet("set-score 3 3:0", "Ronaldo"));
	assertEquals("Ronaldo\t9",calcTest.placeBet("ranking", "Ronaldo"));
	
	TimeUnit.SECONDS.sleep(5);
	
	assertNotEquals("Welcome This is your first bet Muller" ,calcTest.placeBet("bet 1 1:0", "Muller"));
	assertEquals("3",calcTest.placeBet("set-score 1 1:0", "Muller"));
	assertEquals("Ronaldo\t9Muller\t3",calcTest.placeBet("ranking", "Muller"));
	
	TimeUnit.SECONDS.sleep(5);
	
	assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 2 2:0", "Muller"));
	assertEquals("3",calcTest.placeBet("set-score 2 2:0", "Muller"));
	assertEquals("Ronaldo\t9Muller\t6",calcTest.placeBet("ranking", "Muller"));
	TimeUnit.SECONDS.sleep(5);
	
	assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 3 3:0", "Muller"));
	assertEquals("3",calcTest.placeBet("set-score 3 3:0", "Muller"));
	assertEquals("Ronaldo\t9Muller\t9",calcTest.placeBet("ranking", "Muller"));
	TimeUnit.SECONDS.sleep(5);
	
	
	assertNotEquals("Welcome This is your first bet Muller" ,calcTest.placeBet("bet 1 1:0", "Zidane"));
	assertEquals("3",calcTest.placeBet("set-score 1 1:0", "Zidane"));
	assertEquals("Ronaldo\t9Zidane\t3Muller\t9",calcTest.placeBet("ranking", "Zidane"));
	TimeUnit.SECONDS.sleep(5);
	
	assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 2 2:0", "Zidane"));
	assertEquals("3",calcTest.placeBet("set-score 2 2:0", "Zidane"));
	assertEquals("Ronaldo\t9Zidane\t6Muller\t9",calcTest.placeBet("ranking", "Zidane"));
	
	TimeUnit.SECONDS.sleep(5);
	
	assertNotEquals("Welcome This is your first bet Ronaldo" ,calcTest.placeBet("bet 3 3:0", "Zidane"));
	assertEquals("3",calcTest.placeBet("set-score 3 3:0", "Zidane"));
	assertEquals("Ronaldo\t9Zidane\t9Muller\t9",calcTest.placeBet("ranking", "Zidane"));
	
	TimeUnit.SECONDS.sleep(5);
	
	
	//assertThat(calcTest.placeBet("bet 1 1:0", "Ronaldo"),matchesPattern("^[Bet]+"));

	assertNotEquals("Welcome all this is ",(calcTest.placeBet("bet 1 1:0", "Ronaldo")));
	
	//assertEquals("3",calcTest.placeBet("set-score 1 1:0", "raghu"));
	
	
   
	
}
	
	
}
