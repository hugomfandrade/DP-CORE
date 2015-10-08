package com.crunchify.tutorials;
 
/**
 * @author Crunchify.com
 */
 
// Super class in factory pattern can be an interface, abstract class or a
// normal java class. For our example, we have super class as abstract class
// with overridden toString() method for testing purpose.
 
public abstract class CrunchifyCompany {
 
	public abstract String getPhoneNumber();
 
	public abstract String getZipCode();
 
	@Override
	public String toString() {
		return "Phone #= " + this.getPhoneNumber() + ", Zip Code= " + this.getZipCode();
	}
}