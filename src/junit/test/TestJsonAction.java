package junit.test;

import java.util.Iterator;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class TestJsonAction extends ActionSupport implements Action {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
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
		System.out.println("-----Dao Username:"+username);
		return SUCCESS;
	}
}
