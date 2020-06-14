package com.ValidationStruts2;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String execute() {
		ServletActionContext.getRequest().getSession().invalidate();
		addActionMessage("logged Out Successfully");
		System.out.println("logged out");
		return "logout-success";
		
	}
}
