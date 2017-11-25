package service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DetailReimbursementDao;
import dao.MstKaryawanDao;
import dao.MstProjectDao;
import entity.DetailReimbursement;
import entity.MstKaryawan;
import entity.MstProject;
import service.DetailReimbursementSvc;

@Service("detailReimbursementSvc")
public class DetailReimbursementSvcImpl implements DetailReimbursementSvc
{

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	private MstKaryawanDao mstKaryawanDao;
	
	@Autowired
	private MstProjectDao mstProjectDao;
	
	
	@Autowired
	DetailReimbursementDao detailReimbursementDao;
	
	@Override
	public void save(DetailReimbursement detailReimbursement) {
		detailReimbursementDao.save(detailReimbursement);
	}

	@Override
	public void update(DetailReimbursement detailReimbursement) {
		detailReimbursementDao.update(detailReimbursement);
	}

	@Override
	public void delete(int kodeDetail) {
		detailReimbursementDao.delete(kodeDetail);
	}

	@Override
	public List<DetailReimbursement> findAll() {
		return detailReimbursementDao.findAll();
	}

	@Override
	public DetailReimbursement findOne(int kodeDetail) {
		return detailReimbursementDao.findOne(kodeDetail);
	}

	@Override
	public List<DetailReimbursement> searchData(String key) {
		return detailReimbursementDao.searchData(key);
	}

	@Override
	public DetailReimbursement findByDateNameAndKlaim(String nik, int kodeProject, int m, int y) {
		String query = "SELECT * from DETAIL_REIMBURSMENT "
					+ " WHERE MONTH(PERIODE) = ? AND YEAR(PERIODE) = ? ";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		DetailReimbursement detailReimbursement = new DetailReimbursement();
		MstKaryawan mstKaryawan = new MstKaryawan();
		MstProject mstProject = new MstProject();
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, m);
			ps.setInt(2, y);
			rs = ps.executeQuery();
			while (rs.next()) {
				detailReimbursement.setKodeDetail(rs.getInt("KODE_DETAIL"));
				String nik2 = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				detailReimbursement.setMstKaryawan(mstKaryawan);

				int kodProject = (rs.getInt("KODE_PROJECT"));
				mstProject = mstProjectDao.findOne(kodeProject);
				detailReimbursement.setMstProject(mstProject);

				detailReimbursement.setTransport(rs.getDouble("TRANSPORT"));
				detailReimbursement.setParkir(rs.getDouble("PARKIR"));
				detailReimbursement.setKesehatan(rs.getDouble("KESEHATAN"));
				detailReimbursement.setBpjs(rs.getDouble("BPJS"));
				detailReimbursement.setRewardMonthly(rs.getDouble("REWARD_MONTHLY"));
				detailReimbursement.setRewardTriwulan(rs.getDouble("REWARD_TRIWULAN"));
				detailReimbursement.setTaxi(rs.getDouble("TAXI"));
				detailReimbursement.setLembur(rs.getDouble("LEMBUR"));
				detailReimbursement.setEntertainInternal(rs.getDouble("ENTERTAIN_INTERNAL"));
				detailReimbursement.setEntertainEksternal(rs.getDouble("ENTERTAIN_EKSTERNAL"));
				detailReimbursement.setDeskripsiOther(rs.getString("DESKRIPSI_OTHER"));
				detailReimbursement.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				detailReimbursement.setSubtotal(rs.getDouble("SUBTOTAL"));
				detailReimbursement.setNotes(rs.getString("NOTES"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return detailReimbursement;
	}

	}

