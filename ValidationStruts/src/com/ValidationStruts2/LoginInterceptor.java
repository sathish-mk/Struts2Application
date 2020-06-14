package com.ValidationStruts2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.StrutsStatics;

import com.ValidationStruts2.LoginAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor implements StrutsStatics {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LoginInterceptor.class);
	private static final String USER_HANDLE = "loggedInUser";

	public void init() {
		log.info("Intializing LoginInterceptor");
	}

	public void destroy() {
	}

	@SuppressWarnings("unused")
	public String intercept(ActionInvocation invocation) throws Exception {

		final ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context.get(HTTP_REQUEST);
		HttpSession session = request.getSession(true);

		// Is there a "user" object stored in the user's HttpSession?
		Object getuser = session.getAttribute(USER_HANDLE);
		System.out.println(getuser);
		if ( getuser== null) {
			// The user has not logged in yet


			/* The user is attempting to log in. */
			if(invocation.getAction().getClass().equals(LoginAction.class)||invocation.getAction().getClass().equals(RegisterAction.class))
			{
				System.out.println("mm");
				return invocation.invoke();
			}
			System.out.println("here");
			return "login";
		} else {
			return invocation.invoke();
		}
	}

}
