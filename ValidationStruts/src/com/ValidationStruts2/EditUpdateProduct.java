package com.ValidationStruts2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;



public class EditUpdateProduct {
	private String productName;
	private String description;
	private int quantity;
	private int price;
	private List<String> categories;
	private String choosenCategory;
	public EditUpdateProduct() {
		categories=new ArrayList<String>();
		categories.add("Fruits");
		categories.add("vegetables");
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public void setChoosenCategory(String choosenCategory) {
		this.choosenCategory=choosenCategory;
	}
	public String execute() {
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from public.\"Fruits\" where id=23");
			if(rs.next()) {
				setChoosenCategory("Fruits");
				setDescription(rs.getString("description"));
				setPrice(rs.getInt(7));
				setQuantity(rs.getInt("quantity"));
				setProductName(rs.getString("name"));
				this.productName=rs.getString("name");
				System.out.println(rs.getString("description")+" "+rs.getString(7));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return "success";
	}
}
