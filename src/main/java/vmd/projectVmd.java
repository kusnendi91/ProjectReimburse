package vmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;

import entity.MstKaryawan;
import entity.MstProject;
import service.MstProjectSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class projectVmd {

	@WireVariable
	MstProjectSvc mstProjectSvc;

	private MstProject mstProject;
	private List<MstProject> listProject = new ArrayList<>();
	private String search;

	private boolean visible = true;
	private boolean statusPopUp = false;
	private boolean readonly = false;
	private boolean disable = false;

	private int flagtambah = 0;
	
	public int getFlagtambah() {
		return flagtambah;
	}

	public void setFlagtambah(int flagtambah) {
		this.flagtambah = flagtambah;
	}

	public MstProject getMstProject() {
		return mstProject;
	}

	public void setMstProject(MstProject mstProject) {
		this.mstProject = mstProject;
	}

	public List<MstProject> getListProject() {
		return listProject;
	}

	public void setListProject(List<MstProject> listProject) {
		this.listProject = listProject;
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

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	@Init
	public void load() {
		listProject = mstProjectSvc.findAll();
		setStatusPopUp(false);
	}
	
	
	@Command("search")
	@NotifyChange("listProject")
	public void search() {
		listProject.clear();
		listProject = mstProjectSvc.searchData(search);
	}
	
	@Command("addProject")
	@NotifyChange({ "statusPopUp", "mstProject", "disable", "flagtambah"})
	public void add() {
		setStatusPopUp(true);
		setDisable(false);
		setFlagtambah(1);
		mstProject = new MstProject();
	}

	@Command("editProject")
	@NotifyChange({ "statusPopUp", "mstProject", "disable", "flagtambah"})
	public void edit() {
		if (mstProject == null) {
			Messagebox.show("Pilih data yang akan diedit!");
		} else {
			setFlagtambah(0);
			setStatusPopUp(true);
			setDisable(true);
		}
	}

	@Command("deleteProject")
	@NotifyChange("mstProject")
	public void delete() {
		try {
			mstProjectSvc.delete(mstProject.getKodeProject());
			listProject.remove(mstProject);
			BindUtils.postNotifyChange(null, null, projectVmd.this,
					"listKaryawan");
			Clients.showNotification("Data berhasil di delete",
					Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
		} catch (NullPointerException e) {
			Messagebox.show("Pilih data yang akan dihapus!");
		}
	}
	
	@Command("back")
	@NotifyChange({"mstProject","statusPopUp"})
	public void backDetail() {
		mstProject = null;
		setStatusPopUp(false);
	}
	
	
//	@Command("save")
//	@NotifyChange({"statusPopUp", "mstProject"})
//	public void save(){
//		try {
//			MstProject findProject = mstProjectSvc.findOne(mstProject.getKodeProject());
//
//			if (findProject.getKodeProject() == ) {
//							mstKaryawanSvc.save(mstKaryawan);
//							Clients.showNotification("Data berhasil disimpan",
//									Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
//							setStatusPopUp(false);
//							mstKaryawan = null;
//							setStatusPopUp(true);
//			} else if (findKaryawan.getNik() != null) {
//				if (mstKaryawan.getCompany().equals("IGT") || 
//						(mstKaryawan.getCompany().equals("IMK")) || 
//						(mstKaryawan.getCompany().equals("ICN")) || 
//						(mstKaryawan.getCompany().equals("SBY"))) {
//							mstKaryawanSvc.update(mstKaryawan);
//							Clients.showNotification("Data berhasil diupdate",
//									Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
//							setStatusPopUp(false);
//				} else {
//					Messagebox.show("ERROR! Inputan status tidak sesuai! Cek kembali inputan.");
//				}
//			}
//		} catch (Exception e) {
//			Messagebox.show("ERROR! Silahkan cek kembali inputan Anda!");
//		}
//	}
	
}
