package com.interactive.data;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.interactive.data.util.IExtraInfoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestExtraInfo {
	@Autowired
	IExtraInfoService extraService;

	@Before
	public void init(){
		//extraService = new ExtraInfoImpl();
	}
	
	@Test
	public void testFibonacci() {
		assertEquals(1, this.extraService.getFibonacciNumber(1));
		assertEquals(1, this.extraService.getFibonacciNumber(2));
		assertEquals(2, this.extraService.getFibonacciNumber(3));
		assertEquals(3, this.extraService.getFibonacciNumber(4));
		assertEquals(5, this.extraService.getFibonacciNumber(5));
		assertEquals(8, this.extraService.getFibonacciNumber(6));
		assertEquals(13, this.extraService.getFibonacciNumber(7));
	}
	
	@Test
	public void testPrime() {
		assertEquals("1 is a prime number", this.extraService.isPrime(1));
		assertEquals("2 is a prime number", this.extraService.isPrime(2));
		assertEquals("3 is a prime number", this.extraService.isPrime(3));
		assertEquals("4 is not a prime number", this.extraService.isPrime(4));
	}
	
	@After
	public void destroy(){
		this.extraService = null;
	}
	
}
