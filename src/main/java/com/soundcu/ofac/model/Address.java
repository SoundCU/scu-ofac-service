package com.soundcu.ofac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Address
{
	private String address;
	private String city;
	private String country;
	private String postal_code;
	private String state;

	public String getAddress()
	{
		return address;
	}

	public String getCity()
	{
		return city;
	}

	public String getCountry()
	{
		return country;
	}

	public String getPostal_code()
	{
		return postal_code;
	}

	public String getState()
	{
		return state;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public void setPostal_code(String postal_code)
	{
		this.postal_code = postal_code;
	}

	public void setState(String state)
	{
		this.state = state;
	}
}
