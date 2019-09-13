package com.boot;

public class TestClass {
	
	public static void main(String[] args) {
		String testString = "1AB";
		String testString2 = "13255";
		String testString3 = "ADSFG";
		System.out.println(testString.matches(".*\\d+.*"));
		System.out.println(testString2.matches(".*\\d+.*"));
		System.out.println(testString3.matches(".*\\d+.*"));
	}
}
