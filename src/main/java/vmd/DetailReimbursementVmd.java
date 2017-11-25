package vmd;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.Subtotal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import entity.DetailReimbursement;
import entity.MstKaryawan;
import entity.MstProject;
import service.DetailReimbursementSvc;
import service.MstKaryawanSvc;
import service.MstProjectSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class DetailReimbursementVmd {
	
	@WireVariable
	private DetailReimbursementSvc detailReimbursmentSvc;
	@WireVariable
	private MstKaryawanSvc mstKaryawanSvc;
	@WireVariable
	private MstProjectSvc mstProjectSvc;
	
	ApplicationContext ctx = new ClassPathXmlApplicationContext("/META-INF/spring/app-config.xml");
	DetailReimbursementSvc reimbursementSvc = ctx.getBean(DetailReimbursementSvc.class);
	
	List<DetailReimbursement> listDetailReimburse = new ArrayList<>();
	List<MstKaryawan> listKaryawan = new ArrayList<>();
	List<MstProject> listProject = new ArrayList<>();
	DetailReimbursement detailReimbursement = new DetailReimbursement();
	MstKaryawan mstKaryawan = new MstKaryawan();
	MstProject mstProject = new MstProject();
	DateFormat dateFormatM = new SimpleDateFormat("MMMM");
	DateFormat dateFormatY = new SimpleDateFormat("yyyy");
	
	String bulan;
	String bulan2;
	int bulanI;
	int tahun;
	int tahun2;
	private Date date = new Date();
	private Date date2 = new Date();
	
	private String nik;
	private String rek;
	private int proj;
	
	@Init
	@NotifyChange({"tahun2","bulan2"})
	public void load() {
		listKaryawan = mstKaryawanSvc.findAllData();
		setTanggalSekarang();
	}
		
	@Command("findNik")
	@NotifyChange({"nik","rek","listProject"})
	public void findNik() {
		System.out.println("Nik Dari Nama Karyawan "  + mstKaryawan.getNik());
		nik = mstKaryawan.getNik();
		rek = mstKaryawan.getNoRekening();
		listProject = mstProjectSvc.findAll();
		mstProject = null;
	}
	
	@Command("save")
	@NotifyChange({"proj","detailReimbursement","nik"})
	public void saveClaim() {
		tahun = tahun2;
		int day = 1;
		int Bulan = convert() - 1;
		
		Calendar c = Calendar.getInstance();
		c.set(tahun, Bulan, day, 0, 0);
		Date date = c.getTime();
		java.sql.Date date1 = new java.sql.Date(date.getTime());
		
		int kode = mstProject.getKodeProject();
		if(nik != null &&  kode!= 0) {
			String niks = nik;
			MstKaryawan mk = new MstKaryawan();
			MstProject mp = new MstProject();
			mk.setNik(niks);
			mp.setKodeProject(kode);
			System.out.println(" Nik : " + nik );
			System.out.println("Kode Project " + proj );
			detailReimbursement.setMstKaryawan(mk);
			detailReimbursement.setMstProject(mp);
			detailReimbursement.setPeriode(date1);
			detailReimbursement.setSubtotal(hitungSubTotal());
			reimbursementSvc.save(detailReimbursement);
			
			Clients.showNotification("Data berhasil disimpan", Clients.NOTIFICATION_TYPE_INFO, null, null, 3500);	
			
//			hitungSubTotal();
		} else {
			Messagebox.show("Pastikan Anda Telah Memilih Project dan Nama Karyawan");
		}
	}
	
	@Command("HitungSubTotal")
	@NotifyChange("detailReimbursement")
	public double hitungSubTotal() {
		double hitungSubtotal = detailReimbursement.getBpjs() + detailReimbursement.getEntertainEksternal() +
				detailReimbursement.getEntertainInternal()+ detailReimbursement.getKesehatan() +
				detailReimbursement.getLembur() + detailReimbursement.getNilaiOther() +
				detailReimbursement.getParkir() + detailReimbursement.getRewardMonthly() +
				detailReimbursement.getRewardTriwulan() + detailReimbursement.getTaxi()+
				detailReimbursement.getTransport();
		return hitungSubtotal;
	}
	
	@Command("NextProject")
	@NotifyChange({"detailReimbursement","listProject", "mstProject"})
	public void nextProject() {
		//saveClaim();
		clear();
		findNik();
		listProject.clear();
		//mstProject.setNamaProject("Azwar");
		//mstProject.setNamaProject("");
		//listProject = mstProjectSvc.findAll();
	}
	
	
	
	public void clear() {
		detailReimbursement.setBpjs(0);
		detailReimbursement.setDeskripsiOther(null);
		detailReimbursement.setEntertainEksternal(0);
		detailReimbursement.setEntertainInternal(0);
		detailReimbursement.setKesehatan(0);
		detailReimbursement.setLembur(0);
		detailReimbursement.setNilaiOther(0);
		detailReimbursement.setNotes(null);
		detailReimbursement.setParkir(0);
		detailReimbursement.setRewardMonthly(0);
		detailReimbursement.setRewardTriwulan(0);
		detailReimbursement.setTaxi(0);
		detailReimbursement.setTransport(0);
		detailReimbursement.setSubtotal(0);
		
	}
	
	public void setTanggalSekarang(){
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.MONTH, +2);
		date = cal.getTime();
		bulan = dateFormatM.format(date);
		
		if(bulan.equalsIgnoreCase("January")){
			 Calendar cals = Calendar.getInstance();
				cals.add(Calendar.MONTH, -1);
				date2 = cals.getTime();
				bulan2 = dateFormatM.format(date2);
				tahun2 =  cals.get(Calendar.YEAR) ;		
				System.out.println("bulan default" + bulanI);
		 }else{
			 Calendar cals = Calendar.getInstance();
				cals.add(Calendar.MONTH, -1);
				date2 = cals.getTime();
				bulan2 = dateFormatM.format(date2);
				tahun2 =  cals.get(Calendar.YEAR);			
		 }
		 bulan=bulan2;
	}
	
	public int convert(){
		int hasil = 0 ;
		if(bulan.equalsIgnoreCase("January")){
			hasil = 1;
		} else if(bulan.equalsIgnoreCase("February")){
			hasil = 2;
		} else if(bulan.equalsIgnoreCase("March")){
			hasil = 3;
		} else if(bulan.equalsIgnoreCase("April")){
			hasil = 4;
		} else if(bulan.equalsIgnoreCase("May")){
			hasil = 5;
		} else if(bulan.equalsIgnoreCase("June")){
			hasil = 6;
		} else if(bulan.equalsIgnoreCase("July")){
			hasil = 7;
		} else if(bulan.equalsIgnoreCase("August")){
			hasil = 8;
		} else if(bulan.equalsIgnoreCase("September")){
			hasil = 9;
		} else if(bulan.equalsIgnoreCase("October")){
			hasil = 10;
		} else if(bulan.equalsIgnoreCase("November")){
			hasil = 11;
		} else if(bulan.equalsIgnoreCase("December")){
			hasil = 12;
		}
		return hasil;
	}

	public DetailReimbursementSvc getDetailReimbursmentSvc() {
		return detailReimbursmentSvc;
	}

	public void setDetailReimbursmentSvc(DetailReimbursementSvc detailReimbursmentSvc) {
		this.detailReimbursmentSvc = detailReimbursmentSvc;
	}

	public MstKaryawanSvc getMstKaryawanSvc() {
		return mstKaryawanSvc;
	}

	public void setMstKaryawanSvc(MstKaryawanSvc mstKaryawanSvc) {
		this.mstKaryawanSvc = mstKaryawanSvc;
	}

	public MstProjectSvc getMstProjectSvc() {
		return mstProjectSvc;
	}

	public void setMstProjectSvc(MstProjectSvc mstProjectSvc) {
		this.mstProjectSvc = mstProjectSvc;
	}

	public List<DetailReimbursement> getListDetailReimburse() {
		return listDetailReimburse;
	}

	public void setListDetailReimburse(List<DetailReimbursement> listDetailReimburse) {
		this.listDetailReimburse = listDetailReimburse;
	}

	public List<MstKaryawan> getListKaryawan() {
		return listKaryawan;
	}

	public void setListKaryawan(List<MstKaryawan> listKaryawan) {
		this.listKaryawan = listKaryawan;
	}

	public List<MstProject> getListProject() {
		return listProject;
	}

	public void setListProject(List<MstProject> listProject) {
		this.listProject = listProject;
	}

	public DetailReimbursement getDetailReimbursement() {
		return detailReimbursement;
	}

	public void setDetailReimbursement(DetailReimbursement detailReimbursement) {
		this.detailReimbursement = detailReimbursement;
	}

	public MstKaryawan getMstKaryawan() {
		return mstKaryawan;
	}

	public void setMstKaryawan(MstKaryawan mstKaryawan) {
		this.mstKaryawan = mstKaryawan;
	}

	public MstProject getMstProject() {
		return mstProject;
	}

	public void setMstProject(MstProject mstProject) {
		this.mstProject = mstProject;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getRek() {
		return rek;
	}

	public void setRek(String rek) {
		this.rek = rek;
	}

	public int getProj() {
		return proj;
	}

	public void setProj(int proj) {
		this.proj = proj;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public String getBulan() {
		return bulan;
	}

	public void setBulan(String bulan) {
		this.bulan = bulan;
	}

	public int getTahun() {
		return tahun;
	}

	public void setTahun(int tahun) {
		this.tahun = tahun;
	}

	public String getBulan2() {
		return bulan2;
	}

	public void setBulan2(String bulan2) {
		this.bulan2 = bulan2;
	}

	public int getTahun2() {
		return tahun2;
	}

	public void setTahun2(int tahun2) {
		this.tahun2 = tahun2;
	}


	public int getBulanI() {
		return bulanI;
	}


	public void setBulanI(int bulanI) {
		this.bulanI = bulanI;
	}
	
	


	
	
	
	
	
	
	
	
	
		
	
	
	
}
