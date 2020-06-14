package com.ValidationStruts2;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


public class RegisterAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private static final String USER_HANDLE = "loggedInUser";
	
		private String user;
		private String email;
		private String password;


		public String execute() {
			try{
				Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
				
			ResultSet rs=st.executeQuery("select * from public.user where email='"+getEmail()+"'");
			if(rs.next()) {
				if(rs.getString("\"userName\"").equals(getUser())) {
					addActionMessage("user name alreay exists");
				}
				System.out.println("s already exists");
				addActionMessage("already exists");
				st.close();
				return "input";
			}
			else {
			
				PreparedStatement s=con.prepareStatement("insert into public.user (\"userName\",email,password,status,\"sellerRequest\") values ('"+getUser()+"','"+getEmail()+"','"+getPassword()+"','user','new')");
				s.executeUpdate();
				s.close();
				addActionMessage("successfully registered");
			}
			st.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			return "input";
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}
	}