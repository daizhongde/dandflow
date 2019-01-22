package junit.test;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import person.daizhongde.nontaxteam.spring.sys.dao.SecuritySessionDao;
//import person.daizhongde.nontaxteam.spring.sys.dao.impl.SecuritySessionDaoImpl;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport implements Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
//	private SecuritySessionDao securitySessionDao;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public void setSecuritySessionDao(SecuritySessionDao securitySessionDao) {
//		this.securitySessionDao = securitySessionDao;
//	}

	public String execute() throws Exception {
		System.out.println("Dao getUsername()");
//		Object principal =  SecurityContextHolder.getContext()
//			    .getAuthentication()
//			    .getPrincipal();
//		username = principal.toString();
//		if(principal instanceof UserDetails){
//			System.out.println("----is instanceof userdetails---");
//			username =((UserDetails)principal).getUsername();
//			Iterator it = ((UserDetails)principal).getAuthorities().iterator();
//			String authority = "";
//			while(it.hasNext()){
//				((GrantedAuthority)it.next()).getAuthority();
//				System.out.println("Authority:"+authority);
//			}
//		}
//		System.out.println("-----Dao Username:"+username);
//		System.out.println( securitySessionDao.getUsername());;
//		
//		
//		//Map session = ActionContext.getContext().getSession();
//		HttpServletRequest request = ServletActionContext.getRequest();
////		String CAcip = request.getRemoteAddr();
//		//ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
//		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
//		username = SecuritySessionDaoImpl.getFromApplicationContext(ctx).getUsername();;
		System.out.println("ha ha username:"+username);
		return SUCCESS;
	}
}
