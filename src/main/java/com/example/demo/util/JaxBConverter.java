package com.example.demo.util;

import com.example.demo.bo.EmployeeBo;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JaxBConverter {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	public static String convertPojoToJson(EmployeeBo eBo) throws Exception{
		
		return objectMapper.writeValueAsString(eBo);
	}
	
	public static EmployeeBo convertJsonToPojo(String json, Class<EmployeeBo> eBo) throws Exception{
		
		return objectMapper.readValue(json, eBo);
	}

}
