package vmd;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

import entity.MstKaryawan;
import service.MstKaryawanSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class karyawanVmd {

	@WireVariable
	MstKaryawanSvc mstKaryawanSvc;

	private MstKaryawan mstKaryawan;
	private List<MstKaryawan> listKaryawan = new ArrayList<>();
	private List<String> listStatus = new ArrayList<>();
	private String search;

	private boolean visible = true;
	private boolean statusPopUp = false;
	private boolean readonly = false;
	private boolean disable = false;
	
	private int flagtambah = 0;
	
	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public int getFlagtambah() {
		return flagtambah;
	}

	public void setFlagtambah(int flagtambah) {
		this.flagtambah = flagtambah;
	}

	public MstKaryawan getMstKaryawan() {
		return mstKaryawan;
	}

	public void setMstKaryawan(MstKaryawan mstKaryawan) {
		this.mstKaryawan = mstKaryawan;
	}

	public List<MstKaryawan> getListKaryawan() {
		return listKaryawan;
	}

	public void setListKaryawan(List<MstKaryawan> listKaryawan) {
		this.listKaryawan = listKaryawan;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isStatusPopUp() {
		return statusPopUp;
	}

	public void setStatusPopUp(boolean statusPopUp) {
		this.statusPopUp = statusPopUp;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public List<String> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<String> listStatus) {
		this.listStatus = listStatus;
	}

	@Init
	public void load() {
		listKaryawan = mstKaryawanSvc.findAllData();
		setStatusPopUp(false);
	}

	@Command("search")
	@NotifyChange("listKaryawan")
	public void search() {
		listKaryawan.clear();
		listKaryawan = mstKaryawanSvc.searchData(search);
	}

	@Command("addEmployee")
	@NotifyChange({ "statusPopUp", "mstKaryawan", "listStatus", "disable", "flagtambah"})
	public void add() {
		listStatus.add("IGT");
		listStatus.add("IMK");
		listStatus.add("ICN");
		listStatus.add("IDS");
		setStatusPopUp(true);
		setDisable(false);
		setFlagtambah(1);
		mstKaryawan = new MstKaryawan();
	}

	@Command("editEmployee")
	@NotifyChange({ "statusPopUp", "mstKaryawan", "listStatus", "disable", "flagtambah"})
	public void edit() {
		if (mstKaryawan == null) {
			Messagebox.show("Pilih data yang akan diedit!");
		} else {
			listStatus.add("IGT");
			listStatus.add("IMK");
			listStatus.add("ICN");
			listStatus.add("IDS");
			setFlagtambah(0);
			setStatusPopUp(true);
			setDisable(true);
		}
	}

	@Command("deleteEmployee")
	@NotifyChange("mstKaryawan")
	public void delete() {
		try {
			mstKaryawanSvc.delete(mstKaryawan.getNik());
			listKaryawan.remove(mstKaryawan);
			BindUtils.postNotifyChange(null, null, karyawanVmd.this,
					"listKaryawan");
			Clients.showNotification("Data berhasil di delete",
					Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
		} catch (NullPointerException e) {
			Messagebox.show("Pilih data yang akan dihapus!");
		}
	}
	
	@Command("back")
	@NotifyChange({"mstKaryawan","statusPopUp","listStatus"})
	public void backDetail() {
		listStatus.clear();
		mstKaryawan = null;
		setStatusPopUp(false);
	}
	
	
	@Command("save")
	@NotifyChange({"statusPopUp", "mstKaryawan"})
	public void save(){
		try {
			MstKaryawan findKaryawan = mstKaryawanSvc.findOne(mstKaryawan.getNik());

			if (findKaryawan.getNik() == null) {
				if (mstKaryawan.getCompany().equals("IGT") || 
						(mstKaryawan.getCompany().equals("IMK")) || 
						(mstKaryawan.getCompany().equals("ICN")) || 
						(mstKaryawan.getCompany().equals("IDS"))) {
							mstKaryawanSvc.save(mstKaryawan);
							Clients.showNotification("Data berhasil disimpan",
									Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
							setStatusPopUp(false);
							setStatusPopUp(true);
							add();
				} else {
					Messagebox.show("ERROR! Inputan status tidak sesuai! Cek kembali inputan.");
				}
			} else if (findKaryawan.getNik() != null) {
				if (mstKaryawan.getCompany().equals("IGT") || 
						(mstKaryawan.getCompany().equals("IMK")) || 
						(mstKaryawan.getCompany().equals("ICN")) || 
						(mstKaryawan.getCompany().equals("IDS"))) {
							mstKaryawanSvc.update(mstKaryawan);
							Clients.showNotification("Data berhasil diupdate",
									Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
							setStatusPopUp(false);
				} else {
					Messagebox.show("ERROR! Inputan status tidak sesuai! Cek kembali inputan.");
				}
			}
		} catch (Exception e) {
			Messagebox.show("ERROR! Silahkan cek kembali inputan Anda!");
		}
	}
}
