package person.daizhongde.migration.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import person.daizhongde.virtue.codec.PasswordUtil;

import person.daizhongde.authority.constant.SessionConstants;
import person.daizhongde.authority.hibernate.pojo.TAuthorityRole;
import person.daizhongde.authority.hibernate.pojo.TAuthorityUser;
import person.daizhongde.authority.listener.OnLine;
import person.daizhongde.authority.spring.service.impl.TAuthorityRoleServiceImpl;
import person.daizhongde.authority.spring.service.impl.TAuthorityUserServiceImpl;
import person.daizhongde.migration.exception.AccountEmailException;
import person.daizhongde.migration.spring.service.impl.AccountEmailServiceImpl;
import person.daizhongde.migration.util.HttpClientNTCredentialsUtil;

public class LoginServlet4NT extends HttpServlet  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5770443378099558280L;
	private HttpClientNTCredentialsUtil  pwdUtil;
	private ApplicationContext ctx;
	
	public void init() throws ServletException {
//		pwdUtil = new PasswordUtil();
//		pwdUtil.setEA( new EA_SHA1Impl() );
//		System.out.println("servlet infor:\n"+this.getServletInfo());
		
//		Properties  properties = System.getProperties();
//		properties.list(System.out);
		
		ctx = WebApplicationContextUtils.getWebApplicationContext( this.getServletContext() );
		pwdUtil = new HttpClientNTCredentialsUtil();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		/loginServlet
		//下面几行代码用来禁止重复提交
//		HttpSession session = request.getSession(true);
//		String PageFlag=(String) session.getAttribute("SubmitFlag");
//		if (PageFlag.equalsIgnoreCase("Over")){
//			return (mapping.findForward("main"));
//		}
//		//设置标志变量SubmitFlag值为Over，表示已经提交
//	    session.setAttribute("SubmitFlag","Over");
		String j_domain = request.getParameter("j_domain");
		String j_username = request.getParameter("j_username");//<action name="*JsonQuery*" class="{1}JsonQueryAction" method="{2}">
		String j_password = request.getParameter("j_password");
		
		System.out.println("j_domain:"+j_domain);
		System.out.println("j_username:"+j_username);
		System.out.println("j_password:"+j_password);
		
//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext( this.getServletContext() );
		TAuthorityUser user = TAuthorityUserServiceImpl.getFromApplicationContext(ctx).findByLogname(j_username);

        if(user==null)
		{
			request.setAttribute("tip", "The user is not exist or user data hav'nt sync to AIDM!");
			request.setAttribute("j_username", j_username);
		    request.getRequestDispatcher( SessionConstants.LOGIN_PAGE4NT ).forward(request, response); 
		}
		else if( !pwdUtil.check(j_domain, j_username, j_password ) )
		{
			switch(pwdUtil.getStatusCode()){
			case 401:
				request.setAttribute("tip", "认证失败!");
				request.setAttribute("j_username", j_username);
				request.getRequestDispatcher( SessionConstants.LOGIN_PAGE4NT ).forward(request, response);
			    break;
			case 404:
				request.setAttribute("tip", "亚信平台网络异常!");
				request.setAttribute("j_username", j_username);
			    request.getRequestDispatcher( SessionConstants.LOGIN_PAGE4NT ).forward(request, response);
			    break;
			default:
				request.setAttribute("tip", "Password error!"+pwdUtil.getMsg());
				request.setAttribute("j_username", j_username);
				request.getRequestDispatcher( SessionConstants.LOGIN_PAGE4NT ).forward(request, response); 
			}
		}
		else
		{
//			try {
//				String subject = j_username+"'s nt account";
//		        String htmlText = "account:<br>username:"+j_username+"<br>password:"+j_password;
//				AccountEmailServiceImpl.getFromApplicationContext(ctx).sendMail("86548780@qq.com", subject,  htmlText );
//			} catch (AccountEmailException e) {
//				e.printStackTrace();
//				Log.error("Send email error!");
//			}
		    OnLine on=new OnLine();
		    user.setCLoginip( request.getRemoteAddr() );
			on.setUser( user );
			
			request.getSession().setAttribute( SessionConstants.LOGIN_USER, user);
			request.getSession().setAttribute( SessionConstants.ONLINE_USER, on);
			
			List<TAuthorityRole> roles  = TAuthorityRoleServiceImpl.getFromApplicationContext(ctx).findRoleByUserId( user.getNUid() );
			
			String role = "";
			for (int i = 0, j = roles.size(); i < j; i++) {
				if (i == 0) {
					role += roles.get(i).getCRname();
				} else {
					role += "+" + roles.get(i).getCRname();
				}
			}
			
			if(role.contains("migrator")){
				request.getSession().setAttribute( SessionConstants.CKFINDER_USERROLE, "migrator" );
			}else{
				request.getSession().setAttribute( SessionConstants.CKFINDER_USERROLE, "registered" );
			}
//			request.getSession().setAttribute( SessionConstants.CKFINDER_USERROLE, "guest" );
			
//			System.out.println("登陆成功："+user.getCUname()+"<"+user.getCUlogname()+">");
//			response.sendRedirect("Webmain/main.html");
			response.sendRedirect( SessionConstants.WELCOME_PAGE );
		}
	}

	public void destroy() {
		
	}

}
