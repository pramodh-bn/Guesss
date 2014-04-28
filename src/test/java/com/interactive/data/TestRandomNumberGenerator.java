package com.interactive.data;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.interactive.data.util.NumberGenerator;

public class TestRandomNumberGenerator {
	long seed;
	int limit;
	int number;
	private NumberGenerator numberGenerator;
	@Before
	public void initialize(){
		this.seed = 10;
		this.limit = 20;
		this.numberGenerator = new NumberGenerator(this.seed, this.limit);
		this.number = this.numberGenerator.getRandomNumber();
	}
	@Test
	public void testGetRandomNumberEquals() {
		assertEquals(14, this.number);
	}
	@Test
	public void testGetRandomNumberBetween() {
		assertTrue("Between 1 and " + this.limit + " " + this.number, this.number >= 1 && this.number <= this.limit);
	}
	
	@After 
	public void remove() {
		this.numberGenerator = null;
	}
}
