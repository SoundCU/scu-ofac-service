package com.soundcu.ofac.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Entry
{
	private List<Address> addresses;
	private List<String> alt_names;
	private String call_sign;
	private List<String> citizenships;
	private List<String> dates_of_birth;
	private String entity_number;
	private String end_date;
	private String federal_register_notice;
	private String gross_registered_tonnage;
	private String gross_tonnage;
	private List<Id> ids;
	private String license_policy;
	private String license_requirement;
	private String name;
	private List<String> nationalities;
	private List<String> places_of_birth;
	private List<String> programs;
	private String remarks;
	private Integer score;
	private String source;
	private String source_information_url;
	private String source_list_url;
	private String standard_order;
	private String start_date;
	private String title;
	private String type;
	private String vessel_owner;
	private String vessel_flag;
	private String vessel_type;

	public List<Address> getAddresses()
	{
		return addresses;
	}

	public List<String> getAlt_names()
	{
		return alt_names;
	}

	public String getCall_sign()
	{
		return call_sign;
	}

	public List<String> getCitizenships()
	{
		return citizenships;
	}

	public List<String> getDates_of_birth()
	{
		return dates_of_birth;
	}

	public String getEntity_number()
	{
		return entity_number;
	}

	public String getEnd_date()
	{
		return end_date;
	}

	public String getFederal_register_notice()
	{
		return federal_register_notice;
	}

	public String getGross_registered_tonnage()
	{
		return gross_registered_tonnage;
	}

	public String getGross_tonnage()
	{
		return gross_tonnage;
	}

	public List<Id> getIds()
	{
		return ids;
	}

	public String getLicense_policy()
	{
		return license_policy;
	}

	public String getLicense_requirement()
	{
		return license_requirement;
	}

	public String getName()
	{
		return name;
	}

	public List<String> getNationalities()
	{
		return nationalities;
	}

	public List<String> getPlaces_of_birth()
	{
		return places_of_birth;
	}

	public List<String> getPrograms()
	{
		return programs;
	}

	public String getRemarks()
	{
		return remarks;
	}
	
	public Integer getScore()
	{
		return score;
	}

	public String getSource()
	{
		return source;
	}

	public String getSource_information_url()
	{
		return source_information_url;
	}

	public String getSource_list_url()
	{
		return source_list_url;
	}

	public String getStandard_order()
	{
		return standard_order;
	}

	public String getStart_date()
	{
		return start_date;
	}

	public String getTitle()
	{
		return title;
	}

	public String getType()
	{
		return type;
	}

	public String getVessel_owner()
	{
		return vessel_owner;
	}

	public String getVessel_flag()
	{
		return vessel_flag;
	}

	public String getVessel_type()
	{
		return vessel_type;
	}

	public void setAddresses(List<Address> addresses)
	{
		this.addresses = addresses;
	}

	public void setAlt_names(List<String> alt_names)
	{
		this.alt_names = alt_names;
	}

	public void setCall_sign(String call_sign)
	{
		this.call_sign = call_sign;
	}

	public void setCitizenships(List<String> citizenships)
	{
		this.citizenships = citizenships;
	}

	public void setDates_of_birth(List<String> dates_of_birth)
	{
		this.dates_of_birth = dates_of_birth;
	}

	public void setEntity_number(String entity_number)
	{
		this.entity_number = entity_number;
	}

	public void setEnd_date(String end_date)
	{
		this.end_date = end_date;
	}

	public void setFederal_register_notice(String federal_register_notice)
	{
		this.federal_register_notice = federal_register_notice;
	}

	public void setGross_registered_tonnage(String gross_registered_tonnage)
	{
		this.gross_registered_tonnage = gross_registered_tonnage;
	}

	public void setGross_tonnage(String gross_tonnage)
	{
		this.gross_tonnage = gross_tonnage;
	}

	public void setIds(List<Id> ids)
	{
		this.ids = ids;
	}

	public void setLicense_policy(String license_policy)
	{
		this.license_policy = license_policy;
	}

	public void setLicense_requirement(String license_requirement)
	{
		this.license_requirement = license_requirement;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setNationalities(List<String> nationalities)
	{
		this.nationalities = nationalities;
	}

	public void setPlaces_of_birth(List<String> places_of_birth)
	{
		this.places_of_birth = places_of_birth;
	}

	public void setPrograms(List<String> programs)
	{
		this.programs = programs;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}
	
	public void setScore(Integer score)
	{
		this.score = score;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public void setSource_information_url(String source_information_url)
	{
		this.source_information_url = source_information_url;
	}

	public void setSource_list_url(String source_list_url)
	{
		this.source_list_url = source_list_url;
	}

	public void setStandard_order(String standard_order)
	{
		this.standard_order = standard_order;
	}

	public void setStart_date(String start_date)
	{
		this.start_date = start_date;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public void setVessel_owner(String vessel_owner)
	{
		this.vessel_owner = vessel_owner;
	}

	public void setVessel_flag(String vessel_flag)
	{
		this.vessel_flag = vessel_flag;
	}

	public void setVessel_type(String vessel_type)
	{
		this.vessel_type = vessel_type;
	}
}
