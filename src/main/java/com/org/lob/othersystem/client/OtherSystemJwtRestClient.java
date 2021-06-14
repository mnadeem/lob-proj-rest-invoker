package com.org.lob.othersystem.client;

import java.util.List;

import com.org.lob.othersystem.model.OtherSystem;

public interface OtherSystemJwtRestClient {
	
	List<OtherSystem> getOtherSystems(String param);
	
	OtherSystem create(OtherSystem system);
	
	OtherSystem update(OtherSystem system);
}
