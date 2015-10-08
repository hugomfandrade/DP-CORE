package com.crunchify.tutorials;
 
import com.crunchify.tutorials.CrunchifyCompany;
import com.crunchify.tutorials.CrunchifyEbay;
import com.crunchify.tutorials.CrunchifyGoogle;
 
/**
 * @author Crunchify.com
 */
 
// Now that we have super classes and sub-classes ready, we can write our factory class.
// We can keep Factory class Singleton or we can keep the method that return the subclass a static.
// Notice that based on the input parameter, different subclass is created and returned.
 
public class CrunchifyFactoryPattern {
 
	public static CrunchifyCompany getDetails(String type, String phoneNumber, String zipCode) {
		if ("Ebay".equalsIgnoreCase(type))
			return new CrunchifyEbay(phoneNumber, zipCode);
		else if ("Google".equalsIgnoreCase(type))
			return new CrunchifyGoogle(phoneNumber, zipCode);
 
		return null;
	}
 
}