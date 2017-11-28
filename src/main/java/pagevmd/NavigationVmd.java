package pagevmd;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;

import pageservice.SidebarPage;

public class NavigationVmd {
	private String includeSrc = "/home.zul";
	
	@GlobalCommand("onNavigate")
	@NotifyChange("includeSrc")
	public void onNavigate(@BindingParam("page") SidebarPage page){
		String locationUri = page.getUri();
		includeSrc = locationUri;
	}
	
	public String getIncludeSrc(){
		return includeSrc;
	}
}
