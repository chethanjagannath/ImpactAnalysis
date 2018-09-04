package com.impactanalysis.dto;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class MappingResponseDTO implements Serializable{
	
	@Autowired
	private CommonUtility commonUtility;
	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
