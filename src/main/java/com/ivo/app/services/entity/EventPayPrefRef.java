package com.ivo.app.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "event_pay_pref_ref")
public class EventPayPrefRef {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7892500742965633835L;

	@Id
	@Column(name = "pay_pref_id")
	private Integer payPrefId;

	@Column(name = "pay_pref_name")
	private String payPrefName;

	@Column(name = "pay_pref_display_name")
	private String payPrefDisplayName;

	@Column(name = "order_by")
	private Integer orderBy;

	@Column(name = "is_active")
	private boolean active;

	
	public Integer getPayPrefId() {
		return payPrefId;
	}

	public void setPayPrefId(Integer payPrefId) {
		this.payPrefId = payPrefId;
	}

	public String getPayPrefName() {
		return payPrefName;
	}

	public void setPayPrefName(String payPrefName) {
		this.payPrefName = payPrefName;
	}

	public String getPayPrefDisplayName() {
		return payPrefDisplayName;
	}

	public void setPayPrefDisplayName(String payPrefDisplayName) {
		this.payPrefDisplayName = payPrefDisplayName;
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
