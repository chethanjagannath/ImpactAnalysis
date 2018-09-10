package com.impactanalysis.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@Entity
@Table(name = "TBL_AUDIT")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class AuditEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Transient
	private CommonUtility commonUtility;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq")
	@SequenceGenerator(name = "audit_seq", sequenceName = "audit_seq", allocationSize = 1)
	@Column(name = "auditId")
	private Long auditId;
	
	@Column(name = "auditDate")
	private LocalDateTime auditDate = LocalDateTime.now();
	
	@Column(name = "apiName")
	private String apiName;
	
	@Lob
	@Column(name = "request")
	private String request;
	
	@Lob
	@Column(name = "response")
	private String response;

	public AuditEntity() {
		super();
	}

	public AuditEntity(String apiName, String request, String response) {
		super();
		this.apiName = apiName;
		this.request = request;
		this.response = response;
	}

	public Long getAuditId() {
		return auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public LocalDateTime getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(LocalDateTime auditDate) {
		this.auditDate = auditDate;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	                                 
	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}
