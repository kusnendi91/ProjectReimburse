package entity;

import java.sql.Date;

public class DetailReimbursement implements Comparable<DetailReimbursement>{
	private int kodeDetail;
	private MstKaryawan mstKaryawan;
	private MstUser mstUser;
	private MstProject mstProject;
	private MstAbsensiKaryawan mstAbsensiKaryawan;
	private Date periode;
	private double transport;
	private double parkir;
	private double kesehatan;
	private double bpjs;
	private double rewardMonthly;
	private double rewardTriwulan;
	private double taxi;
	private double lembur;
	private double entertainInternal;
	private double entertainEksternal;
	private String deskripsiOther;
	private double nilaiOther;
	private double subtotal;
	private String notesAdmin;
	private String notesFinance;
	private boolean status;
	
	
	public MstUser getMstUser() {
		return mstUser;
	}
	public void setMstUser(MstUser mstUser) {
		this.mstUser = mstUser;
	}
	public int getKodeDetail() {
		return kodeDetail;
	}
	public void setKodeDetail(int kodeDetail) {
		this.kodeDetail = kodeDetail;
	}
	public MstKaryawan getMstKaryawan() {
		return mstKaryawan;
	}
	public void setMstKaryawan(MstKaryawan mstKaryawan) {
		this.mstKaryawan = mstKaryawan;
	}
	public Date getPeriode() {
		return periode;
	}
	public void setPeriode(Date periode) {
		this.periode = periode;
	}
	public MstProject getMstProject() {
		return mstProject;
	}
	public void setMstProject(MstProject mstProject) {
		this.mstProject = mstProject;
	}
	public double getTransport() {
		return transport;
	}
	public void setTransport(double transport) {
		this.transport = transport;
	}
	public double getParkir() {
		return parkir;
	}
	public void setParkir(double parkir) {
		this.parkir = parkir;
	}
	public double getKesehatan() {
		return kesehatan;
	}
	public void setKesehatan(double kesehatan) {
		this.kesehatan = kesehatan;
	}
	public double getBpjs() {
		return bpjs;
	}
	public void setBpjs(double bpjs) {
		this.bpjs = bpjs;
	}
	public double getRewardMonthly() {
		return rewardMonthly;
	}
	public void setRewardMonthly(double rewardMonthly) {
		this.rewardMonthly = rewardMonthly;
	}
	public double getRewardTriwulan() {
		return rewardTriwulan;
	}
	public void setRewardTriwulan(double rewardTriwulan) {
		this.rewardTriwulan = rewardTriwulan;
	}
	public double getTaxi() {
		return taxi;
	}
	public void setTaxi(double taxi) {
		this.taxi = taxi;
	}
	public double getLembur() {
		return lembur;
	}
	public void setLembur(double lembur) {
		this.lembur = lembur;
	}
	public double getEntertainInternal() {
		return entertainInternal;
	}
	public void setEntertainInternal(double entertainInternal) {
		this.entertainInternal = entertainInternal;
	}
	public double getEntertainEksternal() {
		return entertainEksternal;
	}
	public void setEntertainEksternal(double entertainEksternal) {
		this.entertainEksternal = entertainEksternal;
	}
	public String getDeskripsiOther() {
		return deskripsiOther;
	}
	public void setDeskripsiOther(String deskripsiOther) {
		this.deskripsiOther = deskripsiOther;
	}
	public double getNilaiOther() {
		return nilaiOther;
	}
	public void setNilaiOther(double nilaiOther) {
		this.nilaiOther = nilaiOther;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getNotesAdmin() {
		return notesAdmin;
	}
	public void setNotesAdmin(String notesAdmin) {
		this.notesAdmin = notesAdmin;
	}
	public String getNotesFinance() {
		return notesFinance;
	}
	public void setNotesFinance(String notesFinance) {
		this.notesFinance = notesFinance;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public int compareTo(DetailReimbursement o) {
		return this.mstKaryawan.getNamaKaryawan().compareTo(o.mstKaryawan.getNamaKaryawan());
	}
	public MstAbsensiKaryawan getMstAbsensiKaryawan() {
		return mstAbsensiKaryawan;
	}
	public void setMstAbsensiKaryawan(MstAbsensiKaryawan mstAbsensiKaryawan) {
		this.mstAbsensiKaryawan = mstAbsensiKaryawan;
	}
	
	
	
	
}
