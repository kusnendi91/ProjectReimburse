package pagevmd;

import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.BindingParam;

import pageservice.SidebarPage;

public class NavigationVmd {
  private String includeSrc = "/page/home.zul";
  
  public String getIncludeSrc() {
	return includeSrc;
}


@GlobalCommand("onNavigate")
  @NotifyChange("includeSrc")
  public void onNavigate(@BindingParam("page") SidebarPage page) {
	  String locationUri = page.getUri();
	  includeSrc = locationUri;
	  
  }

  
}
