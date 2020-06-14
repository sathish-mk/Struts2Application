package com.ValidationStruts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;

public class SellerRequestList {
	private Map<String,Object> m;
	private List<Map<String,Object>> requestList=new ArrayList<Map<String,Object>>();
	
	public String execute() {
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from public.user where \"sellerRequest\"='sent'");
			while(rs.next()) {
				System.out.println(rs.getString("email"));
				m=new HashMap<String, Object>();
				m.put("id",rs.getInt("id"));
				m.put("email",rs.getString("email"));
				m.put("Name",rs.getString("userName"));
				
				requestList.add(m);
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Action.SUCCESS;
	}
	public List<Map<String, Object>> getRequestList() {
		return this.requestList;
	}
	public void setRequestList(List<Map<String,Object>> requestList) {
		this.requestList=requestList;
	}
}
