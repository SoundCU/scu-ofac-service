package com.soundcu.ofac.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Response
{
	private List<Entry> entries;
	private String search_name;
	private Requestor search_performed_by;
	private List<SourceMetaData> sources_used;
	
	public Response(List<Entry> entries, String search_name)
	{
		this.entries = entries;
		this.search_name = search_name;
	}
	
	public boolean getConfirmed_match()
	{
		return !entries.isEmpty();
	}
	
	public List<Entry> getResult()
	{
		return entries;
	}
	
	public String getSearch_name()
	{
		return search_name;
	}
	
	public String getSearch_performed_at()
	{
		TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        dateFormatter.setTimeZone(zone);
        return dateFormatter.format(new Date()) + OffsetDateTime.now().getOffset().toString();
	}
	
	public Requestor getSearch_performed_by()
	{
		return search_performed_by;
	}
	
	public List<SourceMetaData> getSources_used()
	{
		return sources_used;
	}
	
	public void setSeach_performed_by(Requestor requestor)
	{
		this.search_performed_by = requestor;
	}

	public void setSources_used(List<SourceMetaData> sources_used)
	{
		Set<String> sources = new HashSet<String>();
		this.sources_used = new ArrayList<SourceMetaData>();
		entries.forEach( (entry) -> { if (entry.getScore() != null) sources.add(entry.getSource()); });
		sources_used.forEach( (data) -> 
		{ 
			if (data.getSource() != null && sources.contains(data.getSource()))
				this.sources_used.add(data);
		});
	}
}
