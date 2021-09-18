package com.smiker.asaitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Article {
	private int id;
	
	private String name;
	private float importPrice 	= 0.0F;
	private float exportPrice 	= 0.0F;
	private float tax			= 0.21F;

	public Article(int id, String name, float importPrice, float exportPrice, float tax) {
		super();
		this.id = id;
		this.name = name;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.tax = tax;
	}
	
	private static Article fromMetadata(String[] metadata) {
		try {
			int 	id 	 		= Integer.parseInt(metadata[0]);
			String 	name 		= metadata[1]; 
			float 	importPrice = Float.parseFloat(metadata[2]); 
			float   exportPrice = Float.parseFloat(metadata[3]); 
			float 	tax			= Float.parseFloat(metadata[4]);
			return new Article(id, name, importPrice, exportPrice, tax);
		} catch(Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public static ArrayList<Article> fromCSV(String fileName) {
		ArrayList<Article> articles= new ArrayList<Article>(); 
		Path pathToFile = Paths.get(fileName); 
		StringBuilder sb = new StringBuilder();
		try { 
			BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII);
			String line = br.readLine(); 
			boolean skip_header = true;
			while (line != null) { 
				if(!skip_header) {
					String[] attributes = line.split(","); 
					Article article = Article.fromMetadata(attributes);
					if(article!=null) articles.add(article);
					else System.out.println("Skipping malformed row: "+line);
				}
				skip_header = false;
				line = br.readLine(); 
			}
			//sb.setLength(0);
			//sb.append(articles.size()).append(" articles loaded from ").append(fileName);
			//System.out.println(sb.toString());
		} catch (IOException ioe) { 
			sb.setLength(0);
			sb.append("File ").append(fileName).append(" was not found");
			System.out.println(sb.toString());
		} return articles; 
	}	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getImportPrice() {
		return importPrice;
	}
	public void setImportPrice(float importPrice) {
		this.importPrice = importPrice;
	}
	public float getExportPrice() {
		return exportPrice;
	}
	public void setExportPrice(float exportPrice) {
		this.exportPrice = exportPrice;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", importPrice=" + importPrice + ", exportPrice=" + exportPrice
				+ ", tax=" + tax + "]";
	}	
	
}	
