package com.pss.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.pss.audit.AuditInfo;
import com.pss.audit.JpaManagedAuditable;

@Entity
@Table(name="USER")
public class User implements JpaManagedAuditable, Serializable {
	
	private static final long serialVersionUID = 2723205562140493849L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="NAME")
	private String name;
	
	@Min(value=18, message="The user age is under 18!!")
	@Column(name="AGE")
	private int age;
	
	@Column(name="ADDRESS")
	private String adress;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="STATUS")
	private boolean status;
	
	@Embedded
	private AuditInfo auditInfo = new AuditInfo();
	
	public User() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	@Override
	public Date getCreateTimestamp() {
		return this.auditInfo.getCreateTimestamp();
	}

	@Override
	public void setCreateTimestamp(Date aCreateTimestamp) {
		this.auditInfo.setCreateTimestamp(aCreateTimestamp);
	}

	@Override
	public Long getCreateUserId() {
		return this.auditInfo.getCreateUserId();
	}

	@Override
	public void setCreateUserId(Long aCreateUserId) {
		this.auditInfo.setCreateUserId(aCreateUserId);
	}

	@Override
	public Date getUpdateTimestamp() {
		return this.auditInfo.getUpdateTimestamp();
	}

	@Override
	public void setUpdateTimestamp(Date aUpdateTimestamp) {
		this.auditInfo.setUpdateTimestamp(aUpdateTimestamp);
	}

	@Override
	public Long getUpdateUserId() {
		return this.auditInfo.getUpdateUserId();
	}

	@Override
	public void setUpdateUserId(Long aUpdateUserId) {
			this.auditInfo.setUpdateUserId(aUpdateUserId);
	}

}
