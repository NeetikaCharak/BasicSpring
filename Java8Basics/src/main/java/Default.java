package main.java;

import java.math.BigDecimal;

interface Default {

	BigDecimal mul(BigDecimal one, BigDecimal two);
	default int add(int one, int two){
		return Math.addExact(one, two);
	}
	static int sub(int one, int two){
		return Math.subtractExact(one, two);
	}
	
	
}
