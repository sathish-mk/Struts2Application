package com.ValidationStruts2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AcceptDenyRequest {
	private String process;
	private String id;
	public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String execute() {
		try{
			Connection con=ConnectionFactory.getConnection();
			PreparedStatement pst;
			if(!(getProcess().equals("accept"))) {
			pst=con.prepareStatement("update public.user set \"sellerRequest\"='new' where id="+getId());
			}
			else {
				pst=con.prepareStatement("update public.user set \"sellerRequest\"='accept',status='seller' where id="+getId());
			}
			pst.executeUpdate();
			}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "success";
	}
}
