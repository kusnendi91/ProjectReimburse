package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.MstProject;
import service.MstProjectSvc;

public class MainProject {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		
		MstProjectSvc mstProjectSvc = ctx.getBean(MstProjectSvc.class);
		
		MstProject mstProject = new MstProject();
		
		

	}

}
