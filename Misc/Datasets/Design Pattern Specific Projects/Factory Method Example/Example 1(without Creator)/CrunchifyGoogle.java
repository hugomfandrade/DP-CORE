package com.crunchify.tutorials;
 
/**
 * @author Crunchify.com
 */
 
// Notice that the class is extending CrunchifyCompany class.
 
public class CrunchifyGoogle extends CrunchifyCompany {
 
	private String phoneNumber;
	private String zipCode;
 
	public CrunchifyGoogle(String phoneNumber, String zipCode) {
		this.phoneNumber = phoneNumber;
		this.zipCode = zipCode;
	}
 
	@Override
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
 
	@Override
	public String getZipCode() {
		return this.zipCode;
	}
}