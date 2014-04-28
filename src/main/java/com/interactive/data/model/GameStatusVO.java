package com.interactive.data.model;

import java.io.Serializable;

public class GameStatusVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4763478585440512699L;
	private int attempt;
	private String attemptString;
	private String type;
	private int guess;
	private String gameStatus; 
	private String result;
	private int actual;
	private int fibNumber;
	private String isItPrimeMessage;
	
	public int getAttempt() {
		return attempt;
	}
	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	public String getAttemptString() {
		return attemptString;
	}
	public void setAttemptString(String attemptString) {
		this.attemptString = attemptString;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getGuess() {
		return guess;
	}
	public void setGuess(int guess) {
		this.guess = guess;
	}
	public String getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(String gameStatus) {
		this.gameStatus = gameStatus;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getActual() {
		return actual;
	}
	public void setActual(int actual) {
		this.actual = actual;
	}
	public int getFibNumber() {
		return fibNumber;
	}
	public void setFibNumber(int fibNumber) {
		this.fibNumber = fibNumber;
	}
	public String getIsItPrimeMessage() {
		return isItPrimeMessage;
	}
	public void setIsItPrimeMessage(String isItPrimeMessage) {
		this.isItPrimeMessage = isItPrimeMessage;
	}
	
}
