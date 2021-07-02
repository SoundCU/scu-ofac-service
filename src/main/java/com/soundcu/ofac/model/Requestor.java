package com.soundcu.ofac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Requestor
{
	private String user_id;
	private String user_id_type;

	public String getUser_id()
	{
		return user_id;
	}

	public String getUser_id_type()
	{
		return user_id_type;
	}

	public void setUser_id(String user_id)
	{
		this.user_id = user_id;
	}

	public void setUser_id_type(String user_id_type)
	{
		this.user_id_type = user_id_type;
	}
}
