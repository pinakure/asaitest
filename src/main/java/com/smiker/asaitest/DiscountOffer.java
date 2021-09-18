package com.smiker.asaitest;

import java.util.ArrayList;

/*
 * This class defines the following offer archetype:
 *  
 * 		"buy 5 oranges and get a 3€ discount" 
 *  
 */


public class DiscountOffer extends BaseOffer {
	
	public DiscountOffer(ArrayList<Article> targets, int target_quantity, String name) {
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
		System.out.println("Discount Offer applied: "+this.getName());
	}

}
