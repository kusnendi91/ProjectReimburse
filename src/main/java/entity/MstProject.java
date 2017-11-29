package entity;

public class MstProject {
	private int kodeProject;
	private String namaProject;
	private boolean status;
	private String stat;
	
	
	public boolean isStatus() {
		setStat(status);
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getStat() {
		return stat;
	}
	public void setStat(Boolean stat) {
		this.stat = String.valueOf(stat);
	}
	public int getKodeProject() {
		return kodeProject;
	}
	public void setKodeProject(int kodeProject) {
		this.kodeProject = kodeProject;
	}
	public String getNamaProject() {
		return namaProject;
	}
	public void setNamaProject(String namaProject) {
		this.namaProject = namaProject;
	}
	
	
}
