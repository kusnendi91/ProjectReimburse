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
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Messagebox.ClickEvent;

import entity.MstCompany;
import entity.MstKaryawan;
import service.MstCompanySvc;
import service.MstKaryawanSvc;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class karyawanVmd {

	@WireVariable
	MstKaryawanSvc mstKaryawanSvc;

	@WireVariable
	MstCompanySvc mstCompanySvc;

	private MstKaryawan mstKaryawan;
	private MstCompany mstCompany;
	private List<MstKaryawan> listKaryawan = new ArrayList<>();
	private List<MstCompany> listStatus = new ArrayList<>();
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

	public MstCompany getMstCompany() {
		return mstCompany;
	}

	public void setMstCompany(MstCompany mstCompany) {
		this.mstCompany = mstCompany;
	}

	public List<MstCompany> getListStatus() {
		return listStatus;
	}

	public void setListStatus(List<MstCompany> listStatus) {
		this.listStatus = listStatus;
	}

	@Init
	public void load() {
		listKaryawan = mstKaryawanSvc.findAllData();
		listStatus = mstCompanySvc.findAll();
		setStatusPopUp(false);
	}

	@Command("search")
	@NotifyChange("listKaryawan")
	public void search() {
		listKaryawan.clear();
		listKaryawan = mstKaryawanSvc.searchData(search);
	}

	@Command("addEmployee")
	@NotifyChange({ "statusPopUp", "mstKaryawan", "disable", "flagtambah" })
	public void add() {
		setStatusPopUp(true);
		setDisable(false);
		setFlagtambah(1);
		mstKaryawan = new MstKaryawan();
	}

	@Command("editEmployee")
	@NotifyChange({ "statusPopUp", "mstKaryawan", "disable", "flagtambah" })
	public void edit() {
		if (mstKaryawan == null) {
			Messagebox.show("Pilih data yang akan diedit!");
		} else {
			setFlagtambah(0);
			setStatusPopUp(true);
			setDisable(true);
		}
	}

	//
	// @Command("deleteEmployee")
	// @NotifyChange("mstKaryawan")
	// public void delete() {
	// try {
	// Messagebox.show("Apakah yakin karyawan atas nama " +
	// mstKaryawan.getNamaKaryawan() + " dihapus?", "perhatian",
	// new Button[] { Button.YES, Button.NO },
	// Messagebox.QUESTION, Button.NO,
	// new EventListener<Messagebox.ClickEvent>() {
	//
	// @Override
	// public void onEvent(ClickEvent event) throws Exception {
	//
	// if (Messagebox.ON_YES.equals(event.getName())) {
	// mstKaryawanSvc.delete(mstKaryawan.getNik());
	// listKaryawan.remove(mstKaryawan);
	// BindUtils.postNotifyChange(null, null, karyawanVmd.this,
	// "listKaryawan");
	// Clients.showNotification("Data berhasil di delete",
	// Clients.NOTIFICATION_TYPE_INFO, null, null, 500);
	// }
	// }
	// });
	// } catch (NullPointerException e) {
	// Messagebox.show("Pilih data yang akan dihapus!");
	// }
	// }

	@Command("back")
	@NotifyChange({ "mstKaryawan", "statusPopUp", "listKaryawan" })
	public void backDetail() {
		load();
		mstKaryawan = null;
		setStatusPopUp(false);
	}

	@Command("save")
	@NotifyChange({ "statusPopUp", "mstKaryawan", "listKaryawan" })
	public void save() {
		try {
			MstKaryawan findKaryawan = mstKaryawanSvc.findOne(mstKaryawan
					.getNik());

			if (findKaryawan.getNik() == null) {
				if (mstKaryawan.getNamaKaryawan() != null
						&& mstKaryawan.getNoAbsen() != null
						&& mstKaryawan.getMstCompany() != null) {
					mstKaryawanSvc.save(mstKaryawan);
					BindUtils.postNotifyChange(null, null, karyawanVmd.this,
							"listKaryawan");
					Clients.showNotification("Data berhasil disimpan",
							Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
					setStatusPopUp(false);
					setStatusPopUp(true);
					add();
				} else {
					Messagebox
							.show("ERROR! Silahkan cek kembali inputan Anda!");
				}

			} else if (findKaryawan.getNik() != null) {
				mstKaryawanSvc.update(mstKaryawan);
				BindUtils.postNotifyChange(null, null, karyawanVmd.this,
						"listKaryawan");
				Clients.showNotification("Data berhasil diupdate",
						Clients.NOTIFICATION_TYPE_INFO, null, null, 1500);
				setStatusPopUp(false);
			}
		} catch (Exception e) {
			Messagebox.show("ERROR! Silahkan cek kembali inputan Anda!");
		}
	}

	@Command("cekIsi")
	@NotifyChange("mstKaryawan")
	public void cekIsi() {
		if (mstKaryawan.getJenisKelamin().equals("L")
				|| mstKaryawan.getJenisKelamin().equals("P")
				|| mstKaryawan.getJenisKelamin().equals("")) {

		} else {
			Messagebox
					.show("ERROR! Cek kembali inputan! Inputan Jenis Kelamin L/P");
			mstKaryawan.setJenisKelamin(null);
		}

	}

	@Command("cekRek")
	@NotifyChange("mstKaryawan")
	public void cekRek() {

	}

	@Command("isiSearch")
	@NotifyChange("search")
	public void isiSearch() {
		setSearch("");
	}

	@Command("check")
	@NotifyChange("mstKaryawan")
	public void aktivation() {
		MstKaryawan findKar = mstKaryawanSvc.findOne(mstKaryawan.getNik());

	}
}
