package com.smiker.asaitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class OfferManager {
	
	public static ArrayList<BaseOffer> offers = new ArrayList<BaseOffer>();
	

	private static String name; 

	//public Offer(OfferType type, String condition)
	
	public static OfferManager fromMetadata(String[] metadata) {
		try {
			String 	type		= metadata[0]; 
			String 	condition   = metadata[1]; 
			return null;//new Offer(type, condition);
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	

	public static ArrayList<OfferManager> fromCSV(String fileName) {
		ArrayList<OfferManager> offers = new ArrayList<OfferManager>(); 
		Path pathToFile = Paths.get(fileName); 
		StringBuilder sb = new StringBuilder();
		try { 
			BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
			String line = br.readLine(); 
			while (line != null) { 
				String[] attributes = line.split(","); 
				OfferManager offer = OfferManager.fromMetadata(attributes); 
				offers.add(offer); 
				line = br.readLine(); 
			}
		} catch (IOException ioe) { 
			sb.setLength(0);
			sb.append("File ").append(fileName).append(" was not found");
			System.out.println(sb.toString());
		} return offers; 
	}
	
	public static ArrayList<BaseOffer> findOffer(PurchaseRow pr) {
		// returns Offer[] if one ore more an offer applies to the purchaseRow
		ArrayList<BaseOffer> payload = new ArrayList<BaseOffer>();
		for(BaseOffer o : OfferManager.offers) {
			if(o.evaluate(pr)) payload.add(o);
		}
		return payload;
	}
	
	public static Purchase processPurchase(Purchase purchase) {
		for(PurchaseRow pr : purchase.getRows()) {
			ArrayList<BaseOffer> offers = findOffer(pr);
			if(offers.size()==0) System.out.println(pr);				
			else {
				for(BaseOffer o : offers) {
					switch(o.getType()) {
						case DISCOUNT:
							((DiscountOffer)o).apply(purchase, pr);
							break;
						case FREEITEM:
							((FreeItemOffer)o).apply(purchase, pr);
							break;
					}
					
				}
			}
		}
		return purchase;
	}


	public static String getName() {
		return name;
	}


	public static void setName(String name) {
		OfferManager.name = name;
	}

	
	public static void initializeOffers() {
		
		
		
	}
	
}
