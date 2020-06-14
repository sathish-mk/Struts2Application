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

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class RequestAction extends ActionSupport{
	private Map<String,String> m;
	private List<Map<String,String>> results=new ArrayList<Map<String,String>>();
	public String execute(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		String mail=(String) session.getAttribute("loggedInUser");
		m=new HashMap<String,String>();
		try {
			Connection con=ConnectionFactory.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select \"sellerRequest\" from public.user where email='"+mail+"'");
			System.out.println("what me");
			if(rs.next()) {
				if(rs.getString(1).equals("sent")) {
					m.put("result","sent" );
					results.add(m);
					return Action.SUCCESS;
				}
				else if(rs.getString(1).equals("accept")) {
					
					m.put("result","exist");
					results.add(m);
					return Action.SUCCESS;
				}
				
				else {
			st.close();
			
			PreparedStatement pst=con.prepareStatement("update public.user set \"sellerRequest\"='sent' where email='"+mail+"'");
			pst.executeUpdate();
			m.put("result","sent");
			results.add(m);
			return Action.SUCCESS;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage()+"no not here");
			e.printStackTrace();
		}
		m.put("result","nothing");
		return Action.SUCCESS;
	}
	public List<Map<String,String>> getResults() {
		return results;
	}
	public void setResults(List<Map<String,String>> results) {
		this.results = results;
	}
}
