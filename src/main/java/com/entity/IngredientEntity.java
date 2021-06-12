package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class IngredientEntity {


	@Id
	@GeneratedValue
	Long ingredientId;
	
	String name;
	String description;
	String effects;
	String source;
	boolean active;
	String notConsumeBy;
	public Long getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEffects() {
		return effects;
	}
	public void setEffects(String effects) {
		this.effects = effects;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getNotConsumeBy() {
		return notConsumeBy;
	}
	public void setNotConsumeBy(String notConsumeBy) {
		this.notConsumeBy = notConsumeBy;
	}
	
	
}
