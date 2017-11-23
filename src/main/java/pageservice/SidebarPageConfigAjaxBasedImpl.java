package pageservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SidebarPageConfigAjaxBasedImpl implements SidebarPageConfig {
	
	HashMap<String,SidebarPage> pageMp = new LinkedHashMap<String,SidebarPage>();
	
	public SidebarPageConfigAjaxBasedImpl() {
		pageMp.put("fn1", new SidebarPage("Claim Input","/imgs/fn.png","/page/inputclaim.zul"));
		pageMp.put("fn2", new SidebarPage("Claim History","/imgs/fn.png","/page/historyclaim.zul"));
		pageMp.put("fn3", new SidebarPage("User Management","/imgs/fn.png","/page/usermanagement.zul"));
		pageMp.put("fn4", new SidebarPage("Employee Management","/imgs/fn.png","/page/karyawan.zul"));
		pageMp.put("fn5", new SidebarPage("Project Management","/imgs/fn.png","/page/project.zul"));
	}

	@Override
	public List<SidebarPage> getPage() {
		return new ArrayList<SidebarPage>(pageMp.values());
	}
	

}
