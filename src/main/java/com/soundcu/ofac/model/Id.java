package com.soundcu.ofac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Id
{
	private String country;
	private String expiration_date;
	private String issue_date;
	private String number;
	private String type;

	public String getCountry()
	{
		return country;
	}

	public String getExpiration_date()
	{
		return expiration_date;
	}

	public String getIssue_date()
	{
		return issue_date;
	}

	public String getNumber()
	{
		return number;
	}

	public String getType()
	{
		return type;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public void setExpiration_date(String expiration_date)
	{
		this.expiration_date = expiration_date;
	}

	public void setIssue_date(String issue_date)
	{
		this.issue_date = issue_date;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public void setType(String type)
	{
		this.type = type;
	}
}
