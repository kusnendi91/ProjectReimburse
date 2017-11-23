package entity;

import java.sql.Date;

public class MstAbsensiKaryawan {
	private int kodeAbsen;
	private MstKaryawan mstKaryawan;
	private int kantor;
	private int klien;
	private int sakitSuratDokter;
	private int sakitPotongCuti;
	private int cuti;
	private int cutiDitanggung;
	private int mangkir;
	private Date periode;
	
	public Date getPeriode() {
		return periode;
	}
	public void setPeriode(Date periode) {
		this.periode = periode;
	}
	public int getKodeAbsen() {
		return kodeAbsen;
	}
	public void setKodeAbsen(int kodeAbsen) {
		this.kodeAbsen = kodeAbsen;
	}
	public MstKaryawan getMstKaryawan() {
		return mstKaryawan;
	}
	public void setMstKaryawan(MstKaryawan mstKaryawan) {
		this.mstKaryawan = mstKaryawan;
	}
	public int getKantor() {
		return kantor;
	}
	public void setKantor(int kantor) {
		this.kantor = kantor;
	}
	public int getKlien() {
		return klien;
	}
	public void setKlien(int klien) {
		this.klien = klien;
	}
	public int getSakitSuratDokter() {
		return sakitSuratDokter;
	}
	public void setSakitSuratDokter(int sakitSuratDokter) {
		this.sakitSuratDokter = sakitSuratDokter;
	}
	public int getSakitPotongCuti() {
		return sakitPotongCuti;
	}
	public void setSakitPotongCuti(int sakitPotongCuti) {
		this.sakitPotongCuti = sakitPotongCuti;
	}
	public int getCuti() {
		return cuti;
	}
	public void setCuti(int cuti) {
		this.cuti = cuti;
	}
	public int getCutiDitanggung() {
		return cutiDitanggung;
	}
	public void setCutiDitanggung(int cutiDitanggung) {
		this.cutiDitanggung = cutiDitanggung;
	}
	public int getMangkir() {
		return mangkir;
	}
	public void setMangkir(int mangkir) {
		this.mangkir = mangkir;
	}
	
	
}
