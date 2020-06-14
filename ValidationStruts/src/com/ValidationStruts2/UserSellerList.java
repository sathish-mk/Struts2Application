package com.ValidationStruts2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;

public class UserSellerList {
	private List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
	private Map<String,Object> m;
	private String status;
	public String execute() {
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from public.user where status='"+getStatus()+"'");
			while(rs.next()) {
				m=new HashMap<String,Object>();
				m.put("Name",rs.getString(2));
				m.put("email",rs.getString("email"));
				m.put("id",rs.getString("userId"));
				list.add(m);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
