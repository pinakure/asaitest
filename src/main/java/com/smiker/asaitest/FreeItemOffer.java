package com.smiker.asaitest;

import java.util.ArrayList;

/*
 * This class defines the following offer archetype:
 *  	
 *  	"buy 3 oranges and pay only 2"
 * */

public class FreeItemOffer extends BaseOffer {

	public FreeItemOffer(ArrayList<Article> targets, int target_quantity, String name) {
		super(targets, target_quantity, name);
	}
	

	@Override
	public boolean evaluate(PurchaseRow row) {
		/* Should evaluate offer candidates */
		return false;
	}

	
	@Override
	public void apply(Purchase purchase, PurchaseRow row) {
		// Call offer logic
		System.out.println("Free Item Offer applied: "+this.getName());
	}
	
}
