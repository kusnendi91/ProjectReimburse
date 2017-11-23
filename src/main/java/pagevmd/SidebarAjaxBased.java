package pagevmd;

import java.util.List;

import pageservice.SidebarPage;
import pageservice.SidebarPageConfig;
import pageservice.SidebarPageConfigAjaxBasedImpl;

public class SidebarAjaxBased {

	private SidebarPageConfig pageConfig = new SidebarPageConfigAjaxBasedImpl();
	
	public List<SidebarPage> getSidebarPage() {
		return pageConfig.getPage();
	}
}
