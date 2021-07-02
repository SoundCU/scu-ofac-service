package com.soundcu.ofac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SourceMetaData
{
	private String import_rate;
	private String last_imported;
	private String source;
    private String source_last_updated;

	public String getImport_rate()
	{
		return import_rate;
	}

	public String getLast_imported()
	{
		return last_imported;
	}

	public String getSource()
	{
		return source;
	}

	public String getSource_last_updated()
	{
		return source_last_updated;
	}

	public void setImport_rate(String import_rate)
	{
		this.import_rate = import_rate;
	}

	public void setLast_imported(String last_imported)
	{
		this.last_imported = last_imported;
	}

	public void setSource(String source)
	{
		this.source = source;
	}

	public void setSource_last_updated(String source_last_updated)
	{
		this.source_last_updated = source_last_updated;
	}
}
