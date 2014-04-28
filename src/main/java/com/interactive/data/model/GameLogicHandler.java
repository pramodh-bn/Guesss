package com.interactive.data.model;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.interactive.data.util.IExtraInfoService;

@Service("gameLogicHandler")
@Scope("prototype")
@Configurable
public class GameLogicHandler implements IGameLogicService {
	// Construct hashmap for translating attempts to string values
	private final static HashMap<Integer, String> attemptHash = new HashMap<Integer, String>();
	static {
		attemptHash.put(1, "first");
		attemptHash.put(2, "second");
		attemptHash.put(3, "last");
	}
	
	private GameStatusVO previousAttempt;
	@Autowired
	private IExtraInfoService extraService;
	
	public IExtraInfoService getExtraService() {
		return extraService;
	}

	public void setExtraService(IExtraInfoService extraService) {
		this.extraService = extraService;
	}

	public GameStatusVO getPreviousAttempt() {
		return previousAttempt;
	}

	public void setPreviousAttempt(GameStatusVO previousAttempt) {
		this.previousAttempt = previousAttempt;
	}

	//Get the initial setup values for the game 
	@Override
	public GameStatusVO initGame() throws Exception {
		GameStatusVO setupInfo = new GameStatusVO();
		setupInfo.setAttempt(0);
		setupInfo.setGameStatus("start");
		setupInfo.setActual(getExtraService().getRandomeNumber());
		return setupInfo;
	}
	
	//Main game logic is handled by this
	public GameStatusVO handleLogic(GameStatusVO previousAttempt) throws Exception{
		this.setPreviousAttempt(previousAttempt);
		GameStatusVO presentStatus = new GameStatusVO();
		switch(getGameSituation(previousAttempt)){
			case IGameTurnTypeConstants.NOT_LAST_ATTEMPT_RIGHT_GUESS:
				presentStatus = rightGuess();
				break;
			case IGameTurnTypeConstants.NOT_LAST_ATTEMPT_WRONG_GUESS:
				presentStatus = wrongGuess();
				break;
			case IGameTurnTypeConstants.LAST_ATTEMPT_RIGHT_GUESS:
				presentStatus = rightGuess();
				break;
			case IGameTurnTypeConstants.LAST_ATTEMPT_WRONG_GUESS:
				presentStatus = wrongGuessLastAttempt();
				break;
			case IGameTurnTypeConstants.PAST_LAST_ATTEMPT:
				presentStatus = wrongGuessLastAttempt();
				break;
		}
		return presentStatus;
	}
	
	//If the user guesses right this method is invoked
	private GameStatusVO rightGuess(){
		GameStatusVO presentStatus = new GameStatusVO();
		presentStatus.setGuess(previousAttempt.getGuess());
		presentStatus.setGameStatus("done");
		presentStatus.setResult("won");
		presentStatus.setAttempt(previousAttempt.getAttempt()+1);
		presentStatus.setAttemptString(attemptHash.get(presentStatus.getAttempt()));
		presentStatus.setIsItPrimeMessage(getExtraService().isPrime(previousAttempt.getGuess()));
		presentStatus.setFibNumber(getExtraService().getFibonacciNumber(previousAttempt.getGuess()));
		return presentStatus;
	}
	
	// If the user guesses wrong in the first 2 attempts this method would be invoked
	public GameStatusVO wrongGuess(){
		GameStatusVO presentStatus = new GameStatusVO();
		presentStatus.setGameStatus("playing");
		presentStatus.setGuess(previousAttempt.getGuess());
		presentStatus.setAttempt(previousAttempt.getAttempt()+1);
		presentStatus.setAttemptString(attemptHash.get(presentStatus.getAttempt()));
		switch(getGameGuessType(previousAttempt.getActual(), previousAttempt.getGuess())){
			case IGameGuessTypeConstants.COLD_GUESS:
				presentStatus.setType(IGameGuessTypeConstants.COLD_GUESS_STRING);
				break;
			case IGameGuessTypeConstants.WARM_GUESS:
				presentStatus.setType(IGameGuessTypeConstants.WARM_GUESS_STRING);
				break;
			case IGameGuessTypeConstants.HOT_GUESS:
				presentStatus.setType(IGameGuessTypeConstants.HOT_GUESS_STRING);
		}
		return presentStatus;
	}
	
	//If the user guesses wrong value at the last attempt this method would be invoked
	public GameStatusVO wrongGuessLastAttempt(){
		GameStatusVO presentStatus = new GameStatusVO();
		presentStatus.setGameStatus("done");
		presentStatus.setResult("lost");
		presentStatus.setGuess(previousAttempt.getGuess());
		presentStatus.setAttempt(previousAttempt.getAttempt()+1);
		presentStatus.setAttemptString(attemptHash.get(presentStatus.getAttempt()));
		presentStatus.setActual(previousAttempt.getActual());
		return presentStatus;
	}
	
	//Gets the type of game situation(last attempt or the first 2 attempts with guesses(right or wrong))
	//Accordingly the situation would be handled
	private static int getGameSituation(GameStatusVO attempt){
		int situationCase = 0;
		if(attempt.getAttempt() < 2){
			if(attempt.getGuess() == attempt.getActual()){
				situationCase = IGameTurnTypeConstants.NOT_LAST_ATTEMPT_RIGHT_GUESS;
			} else {
				situationCase = IGameTurnTypeConstants.NOT_LAST_ATTEMPT_WRONG_GUESS;
			}
		} else if(attempt.getAttempt() == 2){
			if(attempt.getGuess() == attempt.getActual()){
				situationCase = IGameTurnTypeConstants.LAST_ATTEMPT_RIGHT_GUESS;
			} else {
				situationCase = IGameTurnTypeConstants.LAST_ATTEMPT_WRONG_GUESS;
			}
		} else {
			situationCase = IGameTurnTypeConstants.PAST_LAST_ATTEMPT;
		}
		return situationCase;
	}
	
	//If the guess is wrong, this method identifies whether the guess is cold, warm or hot
	private static int getGameGuessType(int actual, int guess){
		int guessType = -1;
		if(Math.abs(guess - actual) >= 3)
			guessType = IGameGuessTypeConstants.COLD_GUESS;
		else if(Math.abs(guess - actual) == 2)
			guessType = IGameGuessTypeConstants.WARM_GUESS;
		else 
			guessType = IGameGuessTypeConstants.HOT_GUESS;
		return guessType;
	}
}
