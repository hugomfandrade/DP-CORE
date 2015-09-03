package com.crunchify.tutorials;
 
import com.crunchify.tutorials.CrunchifyCompany;
import com.crunchify.tutorials.CrunchifyFactoryPattern;
 
/**
 * @author Crunchify.com
 */
 
// Simple Test client program that uses above factory pattern implementation.
 
public class CrunchifyFactoryPatternTest {
 
	public static void main(String[] args) {
		CrunchifyCompany eBay = CrunchifyFactoryPattern.getDetails("Ebay", "408.123.4567", "98765");
		CrunchifyCompany google = CrunchifyFactoryPattern.getDetails("Google", "519.123.4567", "56789");
		System.out.println("Factory eBay Config::" + eBay);
		System.out.println("Factory Google Config::" + google);
	}
}