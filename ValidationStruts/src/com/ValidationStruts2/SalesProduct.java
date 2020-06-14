package com.ValidationStruts2;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class SalesProduct {
	private String choosenCategory;
	private String productName;
	private File fileUpload;
	private String description;
	private int quantity;
	private int price;
	private String fileUploadContentType;
	private String fileUploadFileName;
	private List<String> categories;
	private String shopLocation;
	public SalesProduct(){
		categories=new ArrayList<String>();
		categories.add("Fruits");
		categories.add("vegetables");
	}
	public List<String> getCategories() {
		return this.categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public int getPrice() {
		System.out.println(price);
		return price;
	}
	public void setPrice(int pric) {
		this.price=pric;
	}
	public String getChoosenCategory() {
		return this.choosenCategory;
	}
	public void setChoosenCategory(String choosenCategory) {
		this.choosenCategory = choosenCategory;
	}
	public String getProductName() {
		return this.productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public File getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String execute() {
		try {
			String path=ServletActionContext.getServletContext().getRealPath("\\").concat("images\\"+this.choosenCategory+"\\"+this.fileUploadFileName);
		File copy=new File(path);
		
		System.out.println(path);
		System.out.println(copy.getAbsolutePath());
		System.out.println(this.fileUpload.getAbsolutePath());
		System.out.println(this.choosenCategory);
		FileUtils.copyFile(this.getFileUpload(),copy);
		path="images/"+this.choosenCategory+"/"+this.fileUploadFileName;
		Connection con=ConnectionFactory.getConnection();
		PreparedStatement pst=con.prepareStatement("insert into public.products (name,quantity,image_path,seller_name,description,\"pricePerUnit\",\"shop_Location\",category) values (?,?,?,?,?,?,?,?)");
		pst.setString(1,getProductName());
		pst.setInt(2, getPrice());
		pst.setString(3,"images/"+getChoosenCategory()+"/"+getFileUploadFileName());
		pst.setString(4,"seller1");
		pst.setString(5, getDescription());
		pst.setInt(6,getPrice());
		pst.setString(7, getShopLocation());
		pst.setString(8,getChoosenCategory());
		
		System.out.println("success");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}
	public String display() {
		return "none";
	}
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	
	public void setShopLocation(String shopLocation) {
		this.shopLocation = shopLocation;
	}
	public String getShopLocation() {
		return shopLocation;
	}
	public String edit() {
		setPrice(12);
		System.out.println(getPrice());
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from public.\"Fruits\" where id=23");
			if(rs.next()) {
				setChoosenCategory("Fruits");
				setDescription(rs.getString("description"));
				setPrice(rs.getInt(7));
				this.quantity=rs.getInt("quantity");
				this.price=rs.getInt(7);
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
