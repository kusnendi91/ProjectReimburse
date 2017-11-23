package entity;

public class MstUser {
	private int kodeUser;
	private MstKaryawan mstKaryawan;
	private MstLevel mstLevel;
	private String password;
	
	
	public int getKodeUser() {
		return kodeUser;
	}
	public void setKodeUser(int kodeUser) {
		this.kodeUser = kodeUser;
	}
	public MstKaryawan getMstKaryawan() {
		return mstKaryawan;
	}
	public void setMstKaryawan(MstKaryawan mstKaryawan) {
		this.mstKaryawan = mstKaryawan;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public MstLevel getMstLevel() {
		return mstLevel;
	}
	public void setMstLevel(MstLevel mstLevel) {
		this.mstLevel = mstLevel;
	}
	
	
}
