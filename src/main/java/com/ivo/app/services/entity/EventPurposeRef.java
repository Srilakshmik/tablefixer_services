package com.ivo.app.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "event_purpose_ref")
public class EventPurposeRef implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5182474701018885214L;
	@Id
	@Column(name = "purpose_id")
	private Integer purposeId;

	@Column(name = "purpose_name")
	private String purposeName;

	@Column(name = "purpose_display_nm")
	private String purposeDisplayNm;

	@Column(name = "order_by")
	private Integer orderBy;

	@Column(name = "is_active")
	private boolean active;

	public Integer getPurposeId() {
		return purposeId;
	}

	public void setPurposeId(Integer purposeId) {
		this.purposeId = purposeId;
	}
	public String getPurposeName() {
		return purposeName;
	}
	public void setPurposeName(String purposeName) {
		this.purposeName = purposeName;
	}
	public String getPurposeDisplayNm() {
		return purposeDisplayNm;
	}
	public void setPurposeDisplayNm(String purposeDisplayNm) {
		this.purposeDisplayNm = purposeDisplayNm;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
