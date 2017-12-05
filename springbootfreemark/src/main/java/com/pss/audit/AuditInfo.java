package com.pss.audit;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class AuditInfo implements Serializable {

	private static final long serialVersionUID = -5805794441647977643L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TS", length = 23)
	private Date createTimestamp;

	@Column(name = "CREATE_USERID")
	private Long createUserId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TS", length = 23)
	private Date updateTimestamp;

	@Column(name = "UPDATE_USERID")
	private Long updateUserId;

	/**
	 * Get the value of or reference to createTimestamp.
	 * 
	 * @return the createTimestamp
	 */
	public Date getCreateTimestamp() {
		if (this.createTimestamp == null) {
			return null;
		} else {
			return new Date(this.createTimestamp.getTime());
		}
	}

	/**
	 * Assign the value of aCreateTimestamp to createTimestamp.
	 * 
	 * @param aCreateTimestamp the createTimestamp to set
	 */
	public void setCreateTimestamp(Date aCreateTimestamp) {
		if (aCreateTimestamp == null) {
			this.createTimestamp = null;
		} else {
			this.createTimestamp = new Date(aCreateTimestamp.getTime());
		}
	}

	/**
	 * Get the value of or reference to createUserId.
	 * 
	 * @return the createUserId
	 */
	public Long getCreateUserId() {
		return this.createUserId;
	}

	/**
	 * Assign the value of aCreateUserId to createUserId.
	 * 
	 * @param aCreateUserId the createUserId to set
	 */
	public void setCreateUserId(Long aCreateUserId) {
		this.createUserId = aCreateUserId;
	}

	/**
	 * Get the value of or reference to updateTimestamp.
	 * 
	 * @return the updateTimestamp
	 */
	public Date getUpdateTimestamp() {
		if (this.updateTimestamp == null) {
			return null;
		} else {
			return new Date(this.updateTimestamp.getTime());
		}
	}

	/**
	 * Assign the value of aUpdateTimestamp to updateTimestamp.
	 * 
	 * @param aUpdateTimestamp the updateTimestamp to set
	 */
	public void setUpdateTimestamp(Date aUpdateTimestamp) {
		if (aUpdateTimestamp == null) {
			this.updateTimestamp = null;
		} else {
			this.updateTimestamp = new Date(aUpdateTimestamp.getTime());
		}
	}

	/**
	 * Get the value of or reference to updateUserId.
	 * 
	 * @return the updateUserId
	 */
	public Long getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * Assign the value of aUpdateUserId to updateUserId.
	 * 
	 * @param aUpdateUserId the updateUserId to set
	 */
	public void setUpdateUserId(Long aUpdateUserId) {
		this.updateUserId = aUpdateUserId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuditInfo [createTimestamp=").append(this.createTimestamp).append(", createUserId=")
				.append(this.createUserId).append(", updateTimestamp=").append(this.updateTimestamp).append(", updateUserId=")
				.append(this.updateUserId).append("]");
		return builder.toString();
	}

}
