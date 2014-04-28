package com.interactive.data.control;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.interactive.data.model.GameStatusVO;
import com.interactive.data.model.IGameLogicService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("actual") //Store the random number generated in session
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private IGameLogicService gameLogicHandler;
	
	public IGameLogicService getGameLogicHandler() {
		return gameLogicHandler;
	}

	public void setGameLogicHandler(IGameLogicService gameLogicHandler) {
		this.gameLogicHandler = gameLogicHandler;
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) throws Exception {
		//Using GameLogicHandler get the initial values for the start of the game
		GameStatusVO gameObj = gameLogicHandler.initGame();
		model.addAttribute("actual", String.valueOf(gameObj.getActual()));
		model.addAttribute("gameObj", gameObj);
		return "home";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String guess(Locale locale, Model model, @ModelAttribute("actual") String actual, @ModelAttribute("gameObj") GameStatusVO gameObj) throws Exception {
		
		//Get the information from the session for the actual number
		logger.info("Guess is " + gameObj.getGuess() + " actual " + actual);
		logger.info("Attempt is " + gameObj.getAttempt());
		//Set the actual value and send it for processing to game logic handler
		gameObj.setActual(Integer.valueOf(actual));
		GameStatusVO guessResponse = gameLogicHandler.handleLogic(gameObj);
		model.addAttribute("gameObj", guessResponse);
		return "home";
	}

}
