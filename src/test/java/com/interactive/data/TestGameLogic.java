package com.interactive.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.interactive.data.model.GameStatusVO;
import com.interactive.data.model.IGameLogicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestGameLogic {
	GameStatusVO lastAttemptFail;
	GameStatusVO correctGuess;
	GameStatusVO coldGuess;
	GameStatusVO warmGuess;
	GameStatusVO hotGuess;
	GameStatusVO gameAfter;
	@Autowired
	IGameLogicService gameHandler;
	
	@Before
	public void setUp() throws Exception {
		lastAttemptFail = new GameStatusVO();
		correctGuess = new GameStatusVO();
		coldGuess = new GameStatusVO();
		warmGuess = new GameStatusVO();
		hotGuess = new GameStatusVO();
		lastAttemptFail.setAttempt(4);
		correctGuess.setAttempt(1);
		correctGuess.setActual(4);
		correctGuess.setGuess(4);
		coldGuess.setAttempt(1);
		coldGuess.setActual(10);
		coldGuess.setGuess(4);
		warmGuess.setAttempt(1);
		warmGuess.setActual(10);
		warmGuess.setGuess(8);
		hotGuess.setAttempt(1);
		hotGuess.setActual(8);
		hotGuess.setGuess(9);
	}

	@After
	public void tearDown() throws Exception {
		lastAttemptFail = null;
		correctGuess = null;
		coldGuess = null;
		warmGuess = null;
		hotGuess = null;
		gameAfter = null;
		gameHandler = null;
	}

	@Test
	public void testLastAttempt() throws Exception {
		gameAfter = gameHandler.handleLogic(lastAttemptFail);
		assertNotNull(gameAfter);
		assertEquals("done", gameAfter.getGameStatus());
		assertEquals("lost", gameAfter.getResult());
	}

	@Test
	public void testCorrectGuess() throws Exception {
		gameAfter = gameHandler.handleLogic(correctGuess);
		assertNotNull(gameAfter);
		assertEquals("done", gameAfter.getGameStatus());
		assertEquals("won", gameAfter.getResult());
	}

	@Test
	public void testColdGuess() throws Exception{
		gameAfter = gameHandler.handleLogic(coldGuess);
		assertNotNull(gameAfter);
		assertEquals("playing", gameAfter.getGameStatus());
		assertEquals(coldGuess.getGuess(), gameAfter.getGuess());
		assertEquals(coldGuess.getAttempt()+1, gameAfter.getAttempt());
		assertEquals("cold", gameAfter.getType());
	}

	@Test
	public void testWarmGuess() throws Exception{
		gameAfter = gameHandler.handleLogic(warmGuess);
		assertNotNull(gameAfter);
		assertEquals("playing", gameAfter.getGameStatus());
		assertEquals(warmGuess.getGuess(), gameAfter.getGuess());
		assertEquals(warmGuess.getAttempt()+1, gameAfter.getAttempt());
		assertEquals("warm", gameAfter.getType());
	}

	@Test
	public void testHotGuess() throws Exception{
		gameAfter = gameHandler.handleLogic(hotGuess);
		assertNotNull(gameAfter);
		assertEquals("playing", gameAfter.getGameStatus());
		assertEquals(hotGuess.getGuess(), gameAfter.getGuess());
		assertEquals(hotGuess.getAttempt()+1, gameAfter.getAttempt());
		assertEquals("hot", gameAfter.getType());
	}
}
