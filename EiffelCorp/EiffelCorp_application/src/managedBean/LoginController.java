package managedBean;

import java.rmi.RemoteException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import service.AdminService;
import shared.IEmploy;
import shared.IUser;

@ManagedBean(name="LoginController")
@RequestScoped
public class LoginController {
	 	
		private String username;
	    private String password;
	    
	    AdminService adminService = new AdminService();
		
	    
	    public String login() {
	    	try {
	    		if(username.equals("admin")&& password.equals("admin")) {
	    			 FacesContext context = FacesContext.getCurrentInstance();
	    			 context.getExternalContext().getSessionMap().put("user", "admin");
	    			return "adminIndex?faces-redirect=true";
	    			
	    		}else {
	    			
	    		
		    	IUser user = this.adminService.searchUserByLoginMdp(username, password);
		        FacesContext context = FacesContext.getCurrentInstance();
	
		        if (user == null) {
		            context.addMessage(null, new FacesMessage("Unknown login, try again"));
		            username = null;
		            password = null;
		            return null;
		        } else {
		        	
						IEmploy emp = this.adminService.searchEmployeeById(user.getEmployee_id());
						
						context.getExternalContext().getSessionMap().put("user", emp);
				        
						return "employeeIndex?faces-redirect=true";
					}
	    		}
	    	}catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return null;
	        }
	    

	    public String logout() {
	        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        return "login?faces-redirect=true";
	    }


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


		public AdminService getAdminService() {
			return adminService;
		}


		public void setAdminService(AdminService adminService) {
			this.adminService = adminService;
		}
	    
	    
}
