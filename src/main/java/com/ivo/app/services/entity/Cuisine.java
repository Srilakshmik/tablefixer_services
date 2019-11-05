package com.ivo.app.services.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "location_tags_ref")
public class Cuisine {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3092376161522119670L;

	@Id
	@Column(name = "tag_id")
	private Integer tagId;

	@Column(name = "tag_name")
	private String tagName;

	@Column(name = "tag_display_name")
	private String tagDisplayName;


	@Column(name = "loc_type_id")
	private Integer locTypeId;

	@Column(name = "is_active")
	private boolean active;

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagDisplayName() {
		return tagDisplayName;
	}
	public void setTagDisplayName(String tagDisplayName) {
		this.tagDisplayName = tagDisplayName;
	}

	public Integer getLocTypeId() {
		return locTypeId;
	}

	public void setLocTypeId(Integer locTypeId) {
		this.locTypeId = locTypeId;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
