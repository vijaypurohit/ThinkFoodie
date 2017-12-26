package com.example.item;

public class ItemCategory {
	
	private String CategoryId;
	private String CategoryName;
	private String CategoryImageUrl; 
	
	public String getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(String categoryid) {
		this.CategoryId = categoryid;
	}
	
	
	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryname) {
		this.CategoryName = categoryname;
	}
	
	public String getCategoryImageurl()
	{
		return CategoryImageUrl;
		
	}
	
	public void setCategoryImageurl(String catimageurl)
	{
		this.CategoryImageUrl=catimageurl;
	}

}
