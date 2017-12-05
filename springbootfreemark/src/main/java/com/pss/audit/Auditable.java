package com.pss.audit;

import java.util.Date;

public interface Auditable {

	/**
	 * Get the value of or reference to createTimestamp.
	 * 
	 * @return the createTimestamp
	 */
	Date getCreateTimestamp();

	/**
	 * Assign the value of aCreateTimestamp to createTimestamp.
	 * 
	 * @param aCreateTimestamp the createTimestamp to set
	 */
	void setCreateTimestamp(Date aCreateTimestamp);

	/**
	 * Get the value of or reference to createUserId.
	 * 
	 * @return the createUserId
	 */
	Long getCreateUserId();

	/**
	 * Assign the value of aCreateUserId to createUserId.
	 * 
	 * @param aCreateUserId the createUserId to set
	 */
	void setCreateUserId(Long aCreateUserId);

	/**
	 * Get the value of or reference to updateTimestamp.
	 * 
	 * @return the updateTimestamp
	 */
	Date getUpdateTimestamp();

	/**
	 * Assign the value of aUpdateTimestamp to updateTimestamp.
	 * 
	 * @param aUpdateTimestamp the updateTimestamp to set
	 */
	void setUpdateTimestamp(Date aUpdateTimestamp);

	/**
	 * Get the value of or reference to updateUserId.
	 * 
	 * @return the updateUserId
	 */
	Long getUpdateUserId();

	/**
	 * Assign the value of aUpdateUserId to updateUserId.
	 * 
	 * @param aUpdateUserId the updateUserId to set
	 */
	void setUpdateUserId(Long aUpdateUserId);

}
