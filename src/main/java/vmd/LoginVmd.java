package vmd;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import service.MstUserSvc;
import utils.PasswordDigest;
import entity.MstUser;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginVmd {
	
	@WireVariable
	private MstUserSvc mstUserSvc;
	
	private MstUser user;
	private String nik;
	private String password;
	public MstUser getUser() {
		return user;
	}
	public void setUser(MstUser user) {
		this.user = user;
	}
	
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Command("login")
	@NotifyChange({"nik","password"})
	public void login(){      
		if (nik !=null && password != null){
			
			String encryptedPassword = PasswordDigest.createEncryptedPassword(password);
			user = mstUserSvc.login(nik, password);
			
			if(user.getMstKaryawan().getNik() != null && user.getPassword().equals(encryptedPassword)){
				if(user.getMstLevel().getKodeLevel()==1){
					Sessions.getCurrent().setAttribute("user", user);
					Executions.sendRedirect("");
				}else if(user.getMstLevel().getKodeLevel() == 2){
					Sessions.getCurrent().setAttribute("user", user);
					Executions.sendRedirect("");
				}else {
					Sessions.getCurrent().setAttribute("user", user);
					Executions.sendRedirect("");
				}
			}
			else{
				Messagebox.show("Invalid Login");
				setNik(null);
				setPassword(null);
			}
		}else{
			Messagebox.show("Harap mengisi username & password terlebih dahulu");
		}
	}

}
