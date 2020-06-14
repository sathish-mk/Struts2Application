package com.ValidationStruts2;


import java.sql.Connection;
import java.sql.DriverManager;
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


public class LoginAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	private static final String USER_HANDLE = "loggedInUser";
	
		private String loginEmail;
		private String loginPassword;


		public String execute() {
			HttpSession session=ServletActionContext.getRequest().getSession();
			
			if(true) {
			try{
				Connection con=ConnectionFactory.getConnection();
				Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from public.user where email='"+getLoginEmail()+"'");
			if(rs.next()){
				String pass=rs.getString("password");
				if(pass.equals(getLoginPassword())) {
					
					ServletActionContext.getRequest().getSession().setAttribute("loggedInUser",loginEmail);
					if(rs.getString("status").equals("seller")) {
						return "seller";
					}
					else if(rs.getString("status").equals("admin")) {
						return "admin";
					}
					return "user";
				}
				else {
					addActionMessage("please enter correct password");
					return "login";
				}
			
			}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			}
			addActionMessage("please register first");
			return "login";
		}

		public String getLoginEmail() {
			return loginEmail;
		}

		public void setLoginEmail(String loginEmail) {
			this.loginEmail = loginEmail;
		}

		public String getLoginPassword() {
			return loginPassword;
		}

		public void setLoginPassword(String loginPassword) {
			this.loginPassword = loginPassword;
		}

}
