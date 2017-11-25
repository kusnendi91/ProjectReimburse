package tes;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.ls.LSInput;

import entity.DetailReimbursement;
import entity.MstKaryawan;
import entity.MstProject;
import service.DetailReimbursementSvc;

public class TesKlaim {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
		DetailReimbursementSvc reimbursementSvc = ctx.getBean(DetailReimbursementSvc.class);
		MstKaryawan mstKaryawan = new MstKaryawan();
		mstKaryawan.setNik("10");
		MstProject mstProject = new MstProject();
		mstProject.setKodeProject(002);
		DetailReimbursement reimbursement  = new DetailReimbursement();
		reimbursement.setMstKaryawan(mstKaryawan);
		reimbursement.setMstProject(mstProject);
		reimbursement.setBpjs(20000);
		reimbursement.setKesehatan(25000);
		reimbursement.setLembur(50000);
		reimbursementSvc.save(reimbursement);
		

//		List<DetailReimbursement> list = reimbursementSvc.findAll();
//		for (DetailReimbursement detailReimbursement : list) {
//			System.out.println("Daftar Nama Karyawan : " + detailReimbursement.getMstKaryawan().getNamaKaryawan());
//			System.out.println("Daftar Nama Project : " + detailReimbursement.getMstProject().getNamaProject());
//			System.out.println("Daftar Jumlah Klaim Kesehatan : " + detailReimbursement.getKesehatan());
//		}
		
//		reimbursementSvc.delete(1);

	}

}
