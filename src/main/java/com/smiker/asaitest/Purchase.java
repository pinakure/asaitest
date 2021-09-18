package com.smiker.asaitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Purchase {
	
	private String 	name;
	private Float 	quantity;
	
	
	private ArrayList<PurchaseRow> rows;
	
	public Purchase() {
		super();
		this.rows = new ArrayList<PurchaseRow>();
	}
	
	public static Purchase fromCSV(String fileName) {
		Purchase purchase = new Purchase();
		Path pathToFile = Paths.get(fileName); 
		StringBuilder sb = new StringBuilder();
		try { 
			BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
			String line = br.readLine(); 
			boolean skip_header = true;
			while (line != null) { 
				if(!skip_header) {
					String[] attributes = line.split(","); 
					PurchaseRow row = PurchaseRow.fromMetadata(attributes);
					if(row!=null) purchase.getRows().add(row);
					else System.out.println("Skipping malformed row: "+line);
				}
				skip_header = false;
				line = br.readLine(); 
			}
			sb.setLength(0);
			sb.append(purchase.getRows().size()).append(" purchase rows loaded from ").append(fileName);
			System.out.println(sb.toString());
		} catch (IOException ioe) { 
			sb.setLength(0);
			sb.append("File ").append(fileName).append(" was not found");
			System.out.println(sb.toString());
		} return purchase; 
	}
	
	public Float getTotal() {
		Float total = 0.0f;
		for(PurchaseRow pr : rows) {
			total += pr.getQuantity() * pr.getPrice();
		}
		return total;
	}
	

	public ArrayList<PurchaseRow> getRows() {
		return rows;
	}


	public void setRows(ArrayList<PurchaseRow> rows) {
		this.rows = rows;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Purchase [name=" + name + ", quantity=" + quantity + "]";
	}
}	
