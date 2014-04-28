package com.interactive.data.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


@Service
@Scope("prototype")
public class ExtraInfoImpl implements IExtraInfoService {
	
	@Autowired
	private NumberGenerator numGenerator;
	
	public NumberGenerator getNumGenerator() {
		return numGenerator;
	}

	public void setNumGenerator(NumberGenerator numGenerator) {
		this.numGenerator = numGenerator;
	}

	// Generate Fibonacci number using tail recursion
	private static int generateFibinocci(int first, int second, int count){
        if(count <= 0)
            return first;
        else
            return generateFibinocci(second, first+second, count-1);
    }
	
	// Check whether the number is prime or not
	private static boolean isItPrime(int number){
		for(int i=2; i<=Math.sqrt(number); i++){
			if(number % i == 0){
	                return false;
	            }
	    }
        return true;
	}

	// Entry to generate the Fibonacci number
	@Override
	public int getFibonacciNumber(int number) {
		return generateFibinocci(0, 1, number);
	}

	// Entry point for checking whether a number is prime or not
	// and sending a response as a string
	@Override
	public String isPrime(int number) {
		StringBuilder sb = new StringBuilder();
		sb.append(number);
		if(!isItPrime(number)){
			sb.append(" is not a prime number");
		} else {
			sb.append(" is a prime number");
		}
		return sb.toString();
	}

	// Entry point to get a random number
	@Override
	public int getRandomeNumber() {
		return numGenerator.getRandomNumber();
	}
}
