package com.pss.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.pss.audit.AuditInfo;
import com.pss.audit.JpaManagedAuditable;

@Entity
public class Gril implements JpaManagedAuditable, Serializable {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	private String cupSize;
	
	@Embedded
	private AuditInfo auditInfo = new AuditInfo();
	
	public Gril() {
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

	public String getCupSize() {
		return cupSize;
	}

	public void setCupSize(String cupSize) {
		this.cupSize = cupSize;
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

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
