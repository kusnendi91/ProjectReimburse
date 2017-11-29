package vmd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.sound.AAudio;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

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
	@NotifyChange({ "statusPopUp", "mstProject", "disable", "flagtambah" })
	public void add() {
		setStatusPopUp(true);
		setDisable(false);
		setFlagtambah(1);
		mstProject = null;
		mstProject = new MstProject();
	}

	@Command("editProject")
	@NotifyChange({ "statusPopUp", "mstProject", "disable", "flagtambah" })
	public void edit() {
		if (mstProject == null) {
			Messagebox.show("Pilih data yang akan diedit!");
		} else {
			setFlagtambah(0);
			setStatusPopUp(true);
			setDisable(true);
		}
	}

//	@Command("deleteProject")
//	@NotifyChange("mstProject")
//	public void delete() {
//		try {
//			Messagebox.show("Apakah yakin project " + mstProject.getNamaProject() + " dihapus?", "Perhatian",
//					new Button[] { Button.YES, Button.NO },
//					Messagebox.QUESTION, Button.NO,
//					new EventListener<Messagebox.ClickEvent>() {
//
//						@Override
//						public void onEvent(ClickEvent event) throws Exception {
//
//							if (Messagebox.ON_YES.equals(event.getName())) {
//								mstProjectSvc.delete(mstProject.getKodeProject());
//								listProject.remove(mstProject);
//								BindUtils.postNotifyChange(null, null, projectVmd.this,
//										"listProject");
//								Clients.showNotification("Data berhasil di delete",
//										Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
//							}
//						}
//					});
//		} catch (NullPointerException e) {
//			Messagebox.show("Pilih data yang akan dihapus!");
//		}
//	}

	@Command("back")
	@NotifyChange({ "mstProject", "statusPopUp" })
	public void backDetail() {
		mstProject = null;
		setStatusPopUp(false);
	}

	@Command("save")
	@NotifyChange({ "statusPopUp","mstProject", "flagtambah" })
	public void save() {
		try {
			MstProject findPro = mstProjectSvc.findNama(mstProject
					.getNamaProject());

			if (mstProject.getNamaProject() == null) {
				Messagebox.show("ERROR! Silahkan cek kembali inputan Anda!");
			} else {
				if (mstProject.getKodeProject() != 0) {
					mstProjectSvc.update(mstProject);
					Clients.showNotification("Data berhasil diupdate",
							Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
					setStatusPopUp(false);
				} else {
					if (findPro.getNamaProject() != null) {
						Messagebox.show("Nama project " + mstProject.getNamaProject() + " sudah ada di database. Lanjut?", "Perhatian!",
								new Button[] { Button.YES, Button.NO },
								Messagebox.QUESTION, Button.NO,
								new EventListener<Messagebox.ClickEvent>() {

									@Override
									public void onEvent(ClickEvent event) throws Exception {

										if (Messagebox.ON_YES.equals(event.getName())) {
											mstProjectSvc.save(mstProject);
											Clients.showNotification("Data berhasil disimpan",
													Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
										}
									}
								});
						setStatusPopUp(false);
					} else if (findPro.getNamaProject() == null){
						mstProjectSvc.save(mstProject);
						Clients.showNotification("Data berhasil disimpan",
								Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
						add();
					}
				}
			}
		} catch (Exception e) {
			Messagebox.show("ERROR! Silahkan cek kembali inputan Anda!");
		}
	}

	@Command("isiSearch")
	@NotifyChange("search")
	public void isiSearch() {
		setSearch("");
	}
}
