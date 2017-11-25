package tes;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import entity.DetailReimbursement;
import service.DetailReimbursementSvc;
import service.MstKaryawanSvc;

public class TesDetailReimburse {

	public static void main(String[] args) {
		ApplicationContext ctx = new  ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
//		MstKaryawanSvc mstKaryawanSvc = ctx.getBean(MstKaryawanSvc.class);
		DetailReimbursementSvc detailReimbursementSvc =  ctx.getBean(DetailReimbursementSvc.class);
		DetailReimbursement detailReimbursement = new DetailReimbursement();
		detailReimbursementSvc.findByDateNameAndKlaim("17070762", 2, 10, 2017);
		System.out.println("Tes Main " + detailReimbursement.getBpjs() + "Get NIK "+ detailReimbursement.getMstKaryawan().getNamaKaryawan());
//		List<DetailReimbursement> list = detailReimbursementSvc.searchData("3");
		
		
		
	}

}
