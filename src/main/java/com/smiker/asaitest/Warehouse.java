package com.smiker.asaitest;

import java.util.ArrayList;

/* This should be replaced by a DataBase connection */

public class Warehouse {
	private static ArrayList<Article> 	articles = new ArrayList<Article>();

	public static ArrayList<Article> getArticles() {
		return articles;
	}

	public static void setArticles(ArrayList<Article> articles) {
		Warehouse.articles = articles;
	}

	public static Article findByName(String name) {
		for(Article a : articles) {
			if(a.getName().equals(name)) return a;
		}
		return null;
	}
	
}
