package com.logica.hummingbird.telemetry;

import java.util.List;

public interface HummingbirdPacket {
	public void setName(String name);

	public String getName();

	public void addParameters(HummingbirdParameter parameter);
	
	public List<HummingbirdParameter> getParameters();
	
	public HummingbirdParameter getParameter(String name);	
}
