package com.smiker.asaitest;

import java.util.ArrayList;

public abstract class BaseOffer {
	protected OfferType 				type;	
	protected ArrayList<Article> 	targets;			// Affected articles
	protected int 					target_quantity;	// Minimal specimens to be applicable
	protected String 				name;

	protected BaseOffer(ArrayList<Article> targets, int target_quantity, String name) {
		this.targets = targets;
		this.target_quantity = target_quantity;
		this.name = name;
	}
	
	public abstract void apply(Purchase purchase, PurchaseRow row);
	public abstract boolean evaluate(PurchaseRow row);

	public ArrayList<Article> getTargets() {
		return targets;
	}
	public void setTargets(ArrayList<Article> targets) {
		this.targets = targets;
	}
	public int getTarget_quantity() {
		return target_quantity;
	}
	public void setTarget_quantity(int target_quantity) {
		this.target_quantity = target_quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OfferType getType() {
		return type;
	}

	public void setType(OfferType type) {
		this.type = type;
	}
}
