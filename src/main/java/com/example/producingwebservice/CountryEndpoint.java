package com.example.producingwebservice;

import com.net128.soap_server.CountryList;
import com.net128.soap_server.CountryNameList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://net128.com/soap-server";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "countryNameList")
	@ResponsePayload
	public CountryList getCountry(@RequestPayload CountryNameList request) {
		CountryList response = new CountryList();
		response.getCountry().addAll(countryRepository.findCountries(request.getName()));
		return response;
	}
}
