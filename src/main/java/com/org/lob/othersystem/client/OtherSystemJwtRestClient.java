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
public class OtherSystemJwtRestClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(OtherSystemJwtRestClient.class);

	private RestTemplate otherSystemRestTemplate;

	@Value("${app.other_service.url}")
	private String otherServiceUrl;

	public OtherSystemJwtRestClient(@Qualifier("otherSystemRestTemplate") RestTemplate restTemplate) {
		this.otherSystemRestTemplate = restTemplate;
	}

	public List<OtherSystem> getOtherSystems(String param) {
		LOGGER.info("Getting OtherSystems from {}", otherServiceUrl);

		ResponseEntity<OtherSystem[]> response = otherSystemRestTemplate.getForEntity(otherServiceUrl, OtherSystem[].class);

		return Arrays.asList(response.getBody());
	}

}
