package com.smiker.asaitest;

public class PurchaseRow {
	
	private Article article;
	private Integer quantity; 
	private Float 	price; 
	
	public PurchaseRow(Article article, int quantity) {
		super();
		this.article 	= article;
		this.price		= article.getExportPrice();
		this.quantity 	= quantity;		
	}
	
	public static PurchaseRow fromMetadata(String[] metadata) {
		try {
			String 	name 		= metadata[0]; 
			Integer quantity 	= Integer.parseInt(metadata[1]);
			Article article 	= Warehouse.findByName(name);
			if(article!=null) return new PurchaseRow(article, quantity);
		} catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format(
				"%16s x %3d %14.2f", 
				this.getArticle().getName(), 
				this.getQuantity(), 
				this.getPrice()
			)
			+ "\n" +
			String.format("%39.2f", this.getQuantity() * this.getPrice()
		);
	}
	
	
	
}
