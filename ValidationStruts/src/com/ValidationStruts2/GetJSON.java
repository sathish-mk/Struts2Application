package com.ValidationStruts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;


public class GetJSON {
	private String id;
	private List<Map<String,String>> li=new ArrayList<Map<String,String>>();
	private Map<String,String> m;
	public String execute() {
		try{
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			
		ResultSet rs=st.executeQuery("select \"productId\",image_path,name from public.products where category='"+getId()+"'");
		while(rs.next()) {
			String json=rs.getString("image_path");
			m=new HashMap<String,String>();
			System.out.println(json);
			m.put("id",rs.getString(1));
			m.put("path",json);
			m.put("name", rs.getString("name"));
			li.add(m);
			System.out.println(m);
		}
		
		st.close();
		con.close();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}
	public List<Map<String,String>> getLi(){
		return li;
	}
	public void setLi(List<Map<String,String>> li) {
		this.li=li;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id=id;
	}
}
