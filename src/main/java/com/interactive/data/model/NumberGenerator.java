package com.interactive.data.model;

import java.util.Random;

/**
 * 
 * @author Pramodh
 * This is the Number Generator class which is a singleton
 * It will send a random number back when invoked.
 */
public class NumberGenerator {
	private Random randNumberGenerator;
	private int highLimit = 10;
	private long seed = -1;
	public NumberGenerator() {
		randNumberGenerator = new Random();
	}
	
	public NumberGenerator(int highLimit){
		this.highLimit = highLimit;
		randNumberGenerator = new Random();
	}
	
	public NumberGenerator(long seed){
		this.seed = seed;
		randNumberGenerator = new Random(seed);
	}
	
	public NumberGenerator(long seed, int highLimit){
		this.seed = seed;
		this.highLimit = highLimit;
		randNumberGenerator = new Random(this.seed);
	}
	
	public int getRandomNumber(){
		return randNumberGenerator.nextInt(this.highLimit) + 1;
	}

	public int getHighLimit() {
		return highLimit;
	}
}
