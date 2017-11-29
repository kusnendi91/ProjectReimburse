package entity;

import java.sql.Date;

public class MstKaryawan {
	private String nik;
	private MstCompany mstCompany;
	private String namaKaryawan;
	private String noAbsen;
	private String jenisKelamin;
	private Date tanggalMasuk;
	private String noRekening;
	private boolean status;
	private String stat;
	
	
	public String getStat() {
		return stat;
	}
	public void setStat(Boolean statStr) {
		this.stat = String.valueOf(statStr);
	}
	
	public boolean isStatus() {
		setStat(status);
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
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
	public String getNoAbsen() {
		return noAbsen;
	}
	public void setNoAbsen(String noAbsen) {
		this.noAbsen = noAbsen;
	}
	public String getJenisKelamin() {
		return jenisKelamin;
	}
	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}
	public Date getTanggalMasuk() {
		return tanggalMasuk;
	}
	public void setTanggalMasuk(Date tanggalMasuk) {
		this.tanggalMasuk = tanggalMasuk;
	}
	public String getNoRekening() {
		return noRekening;
	}
	public void setNoRekening(String noRekening) {
		this.noRekening = noRekening;
	}
	public MstCompany getMstCompany() {
		return mstCompany;
	}
	public void setMstCompany(MstCompany mstCompany) {
		this.mstCompany = mstCompany;
	}
	
	
	
	
}
