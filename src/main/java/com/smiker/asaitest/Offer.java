package com.smiker.asaitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Offer {
	private enum OfferTypes {
		FREE_UNIT, // each X units, you get a free unit of another or the same fruit
		
	}
	

	private static String name; 
	
	public static Offer fromMetadata(String[] metadata) {
		try {
			String 	type		= metadata[0]; 
			String 	condition   = metadata[1]; 
			return null; //new Purchase(type, condition);
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}
	

	public static ArrayList<Offer> fromCSV(String fileName) {
		ArrayList<Offer> offers = new ArrayList<Offer>(); 
		Path pathToFile = Paths.get(fileName); 
		StringBuilder sb = new StringBuilder();
		try { 
			BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
			String line = br.readLine(); 
			while (line != null) { 
				String[] attributes = line.split(","); 
				Offer offer = Offer.fromMetadata(attributes); 
				offers.add(offer); 
				line = br.readLine(); 
			}
			sb.setLength(0);
			sb.append(offers.size()).append(" offers loaded from ").append(fileName);
			System.out.println(sb.toString());
		} catch (IOException ioe) { 
			sb.setLength(0);
			sb.append("File ").append(fileName).append(" was not found");
			System.out.println(sb.toString());
		} return offers; 
	}
	
	public static ArrayList<Offer> findOffer(PurchaseRow pr) {
		// returns Offer[] if one ore more an offer applies to the purchaseRow
		return null;
	}
	
	public static Purchase processPurchase(Purchase purchase) {
		for(PurchaseRow pr : purchase.getRows()) {
			ArrayList<Offer> offers = findOffer(pr);
			if(offers==null) {
				Article article = pr.getArticle();
				String 	caption = article.getName();
				System.out.println(
					String.format(
						"%16s x %3d %14.2f", 
						article.getName(), 
						pr.getQuantity(), 
						pr.getPrice()
					)
					+ "\n" +
					String.format("%39.2f", pr.getQuantity()*pr.getPrice())
				);				
			} else {
				//applyOffer(purchase, pr);
				for(Offer o : offers) {
					System.out.println("Offer applied: "+o.getName());
				}
			}
		}
		return purchase;
	}


	public static String getName() {
		return name;
	}


	public static void setName(String name) {
		Offer.name = name;
	}
	
	
}
