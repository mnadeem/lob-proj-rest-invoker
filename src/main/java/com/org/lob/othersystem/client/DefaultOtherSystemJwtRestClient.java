package com.org.lob.othersystem.client;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.org.lob.othersystem.model.OtherSystem;

@Component
public class DefaultOtherSystemJwtRestClient implements OtherSystemJwtRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultOtherSystemJwtRestClient.class);

	private RestTemplate otherSystemRestTemplate;

	@Value("${app.other_service.url}")
	private String otherServiceUrl;

	public DefaultOtherSystemJwtRestClient(@Qualifier("otherSystemRestTemplate") RestTemplate restTemplate) {
		this.otherSystemRestTemplate = restTemplate;
	}
	
	@Override
	public List<OtherSystem> getOtherSystems(String param) {
		LOGGER.info("Getting OtherSystems from {}", otherServiceUrl);

		ResponseEntity<OtherSystem[]> response = otherSystemRestTemplate.getForEntity(otherServiceUrl, OtherSystem[].class);

		return Arrays.asList(response.getBody());
	}

	@Override
	public OtherSystem create(OtherSystem system) {
		// TODO Use otherSystemRestTemplate to execute the call 
		return null;
	}

	@Override
	public OtherSystem update(OtherSystem system) {
		// TODO Auto-generated method stub
		return null;
	}

}
