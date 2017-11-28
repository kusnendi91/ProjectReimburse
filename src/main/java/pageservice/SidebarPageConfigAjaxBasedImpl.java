package pageservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SidebarPageConfigAjaxBasedImpl implements SidebarPageConfig{

	HashMap<String,SidebarPage> pageMap = new LinkedHashMap<String,SidebarPage>();
	
	public SidebarPageConfigAjaxBasedImpl() { 
		pageMap.put("fn1", new SidebarPage("Input Employee Claim", "/imgs/fn.png","/page/addClaim.zul"));
		pageMap.put("fn2", new SidebarPage("History Claim", "/imgs/fn.png","/page/historyclaim.zul"));
		pageMap.put("fn3", new SidebarPage("User Management", "/imgs/fn.png","/page/usermanagement.zul"));
		pageMap.put("fn4", new SidebarPage("Employee Management", "/imgs/fn.png","/page/karyawan.zul"));
		pageMap.put("fn5", new SidebarPage("Project Management", "/imgs/fn.png","/page/project.zul"));
	}

	public List<SidebarPage> getPages() {
		// TODO Auto-generated method stub
		return new ArrayList<SidebarPage>(pageMap.values());
	}
	
}
