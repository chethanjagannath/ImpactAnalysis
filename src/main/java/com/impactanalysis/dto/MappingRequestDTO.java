package com.impactanalysis.dto;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.entities.MappingEntity;
import com.impactanalysis.pojo.Requestor;
import com.impactanalysis.utilities.CommonUtility;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class MappingRequestDTO implements Serializable{
	
	@Autowired
	private CommonUtility commonUtility;
	
	private static final long serialVersionUID = 1L;

	private Requestor requestor;
	private MappingEntity mappingEntity;
	
	public MappingRequestDTO() {
		super();
	}
	
	public Requestor getRequestor() {
		return requestor;
	}

	public void setRequestor(Requestor requestor) {
		this.requestor = requestor;
	}

	public MappingEntity getMappingEntity() {
		return mappingEntity;
	}

	public void setMappingEntity(MappingEntity mappingEntity) {
		this.mappingEntity = mappingEntity;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
