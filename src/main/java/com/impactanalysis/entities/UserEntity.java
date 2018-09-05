package com.impactanalysis.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.impactanalysis.utilities.CommonUtility;

@Entity
@Table(name = "TBL_USERS")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	@Transient
	private CommonUtility commonUtility;

	@Id
	@Column(name = "userEmailId", length=100)
	private String userEmailId;

	@Column(name = "userName")
	private String userName;

	public UserEntity() {
		super();
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return commonUtility.toJson(this);
	}
}