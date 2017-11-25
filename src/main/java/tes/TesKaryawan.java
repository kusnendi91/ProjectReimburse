package tes;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.MstKaryawan;
import entity.MstProject;
import service.MstKaryawanSvc;
import service.MstProjectSvc;

public class TesKaryawan {

	public static void main(String[] args) {
		ApplicationContext ctx = new  ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		MstKaryawanSvc mstKaryawanSvc = ctx.getBean(MstKaryawanSvc.class);
		MstProjectSvc mstProjectSvc = ctx.getBean(MstProjectSvc.class);
		
		//		MstKaryawan list =  mstKaryawanSvc.findOne("01010001");
//		System.out.println("NIK : " + list.getNik());
//		System.out.println("REK : " + list.getNoRekening());
	
		List<MstKaryawan> karyawans = mstKaryawanSvc.findAllData();
		MstKaryawan mstKaryawan = mstKaryawanSvc.findOne("01010001");
//		for (MstKaryawan mstKaryawan : karyawans) {
			System.out.println("Nik " + mstKaryawan.getNik());
			System.out.println("Nama Karyawan " + mstKaryawan.getNamaKaryawan());
//		}
			
			MstProject mstProject = mstProjectSvc.findOne(2);
			System.out.println("Kode Project " + mstProject.getNamaProject());
			
	}

}
