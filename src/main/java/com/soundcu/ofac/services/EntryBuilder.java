package com.soundcu.ofac.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soundcu.ofac.model.Entry;
import com.soundcu.ofac.model.SourceMetaData;

@Service
public class EntryBuilder
{
	private static final ObjectMapper mapper = new ObjectMapper();
	private static final String RESULTS = "results";
	private static final String SOURCES = "sources_used";
	
	@Value("${entries.host}")
	private String entriesHost;
	
	private List<Entry> entries;
	private List<SourceMetaData> sources;
	
	/**
	 * Fetches the list of entries from https://api.trade.gov/ every hour and builds
	 * our model.
	 */
	@Scheduled(fixedRate = 60000 * 60)
	public void fetchEntries()
	{
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(URI.create(entriesHost))
				.header("Accept", "application/json")
				.build();
		try
		{
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			if (response.statusCode() != 200)
				return;
			
			JSONObject entriesObj = new JSONObject(response.body());
			JSONArray array = entriesObj.getJSONArray(RESULTS);
			entries = Arrays.asList(mapper.readValue(array.toString(), Entry[].class));
			array = entriesObj.getJSONArray(SOURCES);
			sources = Arrays.asList(mapper.readValue(array.toString(), SourceMetaData[].class));
		}
		catch (InterruptedException | IOException | JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Entry> getEntries()
	{
		if (entries == null)
			fetchEntries();
		
		return entries;
	}
	
	public List<SourceMetaData> getSourceData()
	{
		if (sources == null)
			fetchEntries();
		
		return sources;
	}
}
