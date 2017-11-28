package entity;

import java.sql.Date;
import java.sql.Timestamp;

public class History {
	private int kodeHistory;
	private String nik;
	private String namaKaryawan;
	private String namaProject;
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
	private String namaUser;
	private Timestamp historyDate;
	private String hDate;
	private String status;
	private String notes;
	
	
	public String gethDate() {
		return hDate;
	}
	public void sethDate(String hDate) {
		this.hDate = hDate;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getKodeHistory() {
		return kodeHistory;
	}
	public void setKodeHistory(int kodeHistory) {
		this.kodeHistory = kodeHistory;
	}
	public String getNik() {
		return nik;
	}
	public void setNik(String nik) {
		this.nik = nik;
	}
	public String getNamaKaryawan() {
		return namaKaryawan;
	}
	public void setNamaKaryawan(String namaKaryawan) {
		this.namaKaryawan = namaKaryawan;
	}
	public String getNamaProject() {
		return namaProject;
	}
	public void setNamaProject(String namaProject) {
		this.namaProject = namaProject;
	}
	public Date getPeriode() {
		return periode;
	}
	public void setPeriode(Date periode) {
		this.periode = periode;
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
	public String getNamaUser() {
		return namaUser;
	}
	public void setNamaUser(String namaUser) {
		this.namaUser = namaUser;
	}
	public Timestamp getHistoryDate() {
		return historyDate;
	}
	public void setHistoryDate(Timestamp historyDate) {
		this.historyDate = historyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
