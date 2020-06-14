package com.ValidationStruts2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class CartAction {
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id=id;
	}
	private String comment;
	private int productId;
	private List<Map<String,Object>> li=new ArrayList<Map<String,Object>>();
	private Map<String,Object> m;
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment=comment;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId=productId;
	}

	public List<Map<String,Object>> getLi() {
		return li;
	}
	public void setLi(List<Map<String,Object>> li) {
		this.li = li;
	}
	public Map<String,Object> getM() {
		return m;
	}
	public void setM(Map<String,Object> m) {
		this.m = m;
	}
	
	public String execute() {
		System.out.println(getId());
		return "success";
	}
	public String getProductDetails() {
		try {
			System.out.println(getId());
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from products where \"productId\"="+Integer.parseInt(getId()));
			while(rs.next()) {
				m=new HashMap<String,Object>();
				m.put("name", rs.getString("name"));
				m.put("description", rs.getString("description"));
				m.put("quantity",rs.getInt("quantity"));
				m.put("price", rs.getInt("price"));
				m.put("seller", rs.getString("seller_name"));
				li.add(m);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	public String addComment() {
		System.out.println("addComment");
		String commentor=(String) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		try {
			Connection con=ConnectionFactory.getConnection();
			PreparedStatement pst=con.prepareStatement("insert into public.comments (\"userId\",\"productId\",comment) select \"user\".\"userId\",\"productId\",'"+getComment()+"' from \"user\",products where \"user\".email='"+commentor+"' and \"productId\"=2");
			pst.executeUpdate();
			System.out.println("hlo");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String getComments() {
		System.out.println("getComment");
		try {
			
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			
			ResultSet rs=st.executeQuery("select comment,\"userName\" from comments inner join \"user\" on \"user\".\"userId\"=comments.\"userId\" where comments.\"productId\"=2");
			System.out.println("hi");
			while(rs.next()) {
				m=new HashMap<String,Object>();
				m.put("user",rs.getString(2));
				m.put("comment",rs.getString("comment"));
				li.add(m);
			}
			System.out.println("li");
			st.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
}
