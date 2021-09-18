package com.smiker.asaitest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class FruitShop {
	
	private static ArrayList<Offer> 	offers 	= new ArrayList<Offer>();
	private static Purchase 			purchase;
	
	private static boolean validArguments(String[] args) {
		// Place to check argument constraints
		return args.length == 2;
	}
	
	private static void showHelp() {
		System.out.println("asaitest.jar - Usage info");
		System.out.println("java -jar asaitest.jar <article filename> <purchase filename>");
		System.out.println("\tExample: java -jar asaitest.jar articles.csv purchase.csv");
	}
	
	public static void  main( String[] args ){
		if(!validArguments(args)) {
			showHelp();
			return;
		}
		
		String articles_file = args[0];
		String purchase_file = args[1];
		Warehouse.setArticles(Article.fromCSV( articles_file ));
		FruitShop.purchase = Purchase.fromCSV( purchase_file );
		FruitShop.offers   = Offer.fromCSV( "offers.csv" );
		receipt();
	}
	
	public static String getDate() {
	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now();  
	   return dtf.format(now);  
	}
	
	public static void receipt() {
		final String BAR = "────────────────────────────────────────";
		System.out.println("  Fruit Shop Ltd.");
		System.out.println("  Purchase Date:     "+getDate());
		System.out.println(BAR);
		
		FruitShop.purchase = Offer.processPurchase(purchase);
		
		System.out.println(BAR);
		System.out.println(
				"TOTAL" + 
				String.format("%34.2f", purchase.getTotal())
		);
		System.out.println("Thanks for Coming!");
		
	}
}
