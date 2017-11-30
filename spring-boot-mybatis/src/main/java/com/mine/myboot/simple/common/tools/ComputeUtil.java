package com.mine.myboot.simple.common.tools;

import java.math.BigDecimal;

public class ComputeUtil {

	public static Double add(Double num1, Double num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.add(bgm2).doubleValue();
	}

	public static Double reduce(Double num1, Double num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.subtract(bgm2).doubleValue();
	}

	public static Double multiply(Double num1, Double num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.multiply(bgm2).doubleValue();
	}

	public static Double divide(Double num1, Double num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.divide(bgm2).doubleValue();
	}

	
	
	public static Float add(Float num1, Float num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.add(bgm2).floatValue();
	}

	public static Float reduce(Float num1, Float num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.subtract(bgm2).floatValue();
	}

	public static Float multiply(Float num1, Float num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.multiply(bgm2).floatValue();
	}

	public static Float divide(Float num1, Float num2) {
		BigDecimal bgm1 = BigDecimal.valueOf(num1);
		BigDecimal bgm2 = BigDecimal.valueOf(num2);
		return bgm1.divide(bgm2).floatValue();
	}
}
