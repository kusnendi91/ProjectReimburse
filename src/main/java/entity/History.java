package entity;

import java.sql.Date;

public class History {
	private int kodeHistory;
	private MstUser mstUser;
	private DetailReimbursement detailReimbursement;
	private Date periode;
	
	
	public int getKodeHistory() {
		return kodeHistory;
	}
	public void setKodeHistory(int kodeHistory) {
		this.kodeHistory = kodeHistory;
	}
	public MstUser getMstUser() {
		return mstUser;
	}
	public void setMstUser(MstUser mstUser) {
		this.mstUser = mstUser;
	}
	public DetailReimbursement getDetailReimbursement() {
		return detailReimbursement;
	}
	public void setDetailReimbursement(DetailReimbursement detailReimbursement) {
		this.detailReimbursement = detailReimbursement;
	}
	public Date getPeriode() {
		return periode;
	}
	public void setPeriode(Date periode) {
		this.periode = periode;
	}
	
	
}
