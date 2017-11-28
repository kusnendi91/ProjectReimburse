package dao.impl;

import java.io.Closeable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




import dao.DetailReimbursementDao;
import dao.MstAbsensiKaryawanDao;
import dao.MstKaryawanDao;
import dao.MstProjectDao;
import entity.DetailReimbursement;
import entity.MstAbsensiKaryawan;
import entity.MstKaryawan;
import entity.MstProject;


@Repository
public class DetailReimbursementDaoImpl implements DetailReimbursementDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	MstKaryawanDao mstKaryawanDao;

	@Autowired
	MstProjectDao mstProjectDao;
	
	
	@Override
	public void insert(DetailReimbursement detailReimbursement){
		Connection con = null;
		CallableStatement cstmt = null;
		
		try {
			con = dataSource.getConnection();
			cstmt = con.prepareCall("{call inputDetail (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			cstmt.setString(1, detailReimbursement.getMstKaryawan().getNik());
			cstmt.setInt(2, detailReimbursement.getMstProject().getKodeProject());
			cstmt.setDate(3, detailReimbursement.getPeriode());
			cstmt.setDouble(4, detailReimbursement.getTransport());
			cstmt.setDouble(5, detailReimbursement.getParkir());
			cstmt.setDouble(6, detailReimbursement.getKesehatan());
			cstmt.setDouble(7, detailReimbursement.getBpjs());
			cstmt.setDouble(8, detailReimbursement.getRewardMonthly());
			cstmt.setDouble(9, detailReimbursement.getRewardTriwulan());
			cstmt.setDouble(10, detailReimbursement.getTaxi());
			cstmt.setDouble(11, detailReimbursement.getLembur());
			cstmt.setDouble(12, detailReimbursement.getEntertainInternal());
			cstmt.setDouble(13, detailReimbursement.getEntertainEksternal());
			cstmt.setString(14, detailReimbursement.getDeskripsiOther());
			cstmt.setDouble(15, detailReimbursement.getNilaiOther());
			cstmt.setDouble(16, detailReimbursement.getSubtotal());
			cstmt.setString(17, detailReimbursement.getNotes());
//			
//			cstmt.setString("NIK", detailReimbursement.getMstKaryawan().getNik());
//			cstmt.setInt("KODE_PROJECT", detailReimbursement.getMstProject().getKodeProject());
//			cstmt.setDate("PERIODE", detailReimbursement.getPeriode());
//			cstmt.setDouble("TRANSPORT", detailReimbursement.getTransport());
//			cstmt.setDouble("PARKIR", detailReimbursement.getParkir());
//			cstmt.setDouble("KESEHATAN", detailReimbursement.getKesehatan());
//			cstmt.setDouble("BPJS", detailReimbursement.getBpjs());
//			cstmt.setDouble("REWARD_MONTHLY", detailReimbursement.getRewardMonthly());
//			cstmt.setDouble("REWARD_TRIWULAN", detailReimbursement.getRewardTriwulan());
//			cstmt.setDouble("TAXI", detailReimbursement.getTaxi());
//			cstmt.setDouble("LEMBUR", detailReimbursement.getLembur());
//			cstmt.setDouble("ENTERTAIN_INTERNAL", detailReimbursement.getEntertainInternal());
//			cstmt.setDouble("ENTERTAIN_EKSTERNAL", detailReimbursement.getEntertainEksternal());
//			cstmt.setString("DESKRIPSI_OTHER", detailReimbursement.getDeskripsiOther());
//			cstmt.setDouble("NILAI_OTHER", detailReimbursement.getNilaiOther());
//			cstmt.setDouble("SUBTOTAL", detailReimbursement.getSubtotal());
//			cstmt.setString("NOTES", detailReimbursement.getNotes());
			cstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				cstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	@Override
	public void save(DetailReimbursement detailReimbursement) {
		String query = "INSERT INTO DETAIL_REIMBURSMENT "
				+ "(NIK, KODE_PROJECT, PERIODE, "
				+ "TRANSPORT, PARKIR, KESEHATAN, BPJS, "
				+ "REWARD_MONTHLY, REWARD_TRIWULAN, "
				+ "TAXI, LEMBUR, ENTERTAIN_INTERNAL, ENTERTAIN_EKSTERNAL, "
				+ "DESKRIPSI_OTHER, NILAI_OTHER, SUBTOTAL, NOTES) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, detailReimbursement.getMstKaryawan().getNik());
			ps.setInt(2, detailReimbursement.getMstProject().getKodeProject());
			ps.setDate(3, detailReimbursement.getPeriode());
			ps.setDouble(4, detailReimbursement.getTransport());
			ps.setDouble(5, detailReimbursement.getParkir());
			ps.setDouble(6, detailReimbursement.getKesehatan());
			ps.setDouble(7, detailReimbursement.getBpjs());
			ps.setDouble(8, detailReimbursement.getRewardMonthly());
			ps.setDouble(9, detailReimbursement.getRewardTriwulan());
			ps.setDouble(10, detailReimbursement.getTaxi());
			ps.setDouble(11, detailReimbursement.getLembur());
			ps.setDouble(12, detailReimbursement.getEntertainInternal());
			ps.setDouble(13, detailReimbursement.getEntertainEksternal());
			ps.setString(14, detailReimbursement.getDeskripsiOther());
			ps.setDouble(15, detailReimbursement.getNilaiOther());
			ps.setDouble(16, detailReimbursement.getSubtotal());
			ps.setString(17, detailReimbursement.getNotes());

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Sukses");
			} else {
				System.out.println("Failed");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(DetailReimbursement detailReimbursement) {
		String query = "UPDATE DETAIL_REIMBURSMENT SET NIK=?, KODE_PROJECT=?, "
				+ "PERIODE=?, TRANSPORT=?, PARKIR=?, KESEHATAN=?, BPJS=?, REWARD_MONTHLY=?, "
				+ "REWARD_TRIWULAN=?, TAXI=?, LEMBUR=?, ENTERTAIN_INTERNAL=?, ENTERTAIN_EKSTERNAL=?, "
				+ "DESKRIPSI_OTHER=?, NILAI_OTHER=?, SUBTOTAL=?, NOTES=? "
				+ "where KODE_DETAIL=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, detailReimbursement.getMstKaryawan().getNik());
			ps.setInt(2, detailReimbursement.getMstProject().getKodeProject());
			ps.setDate(3, detailReimbursement.getPeriode());
			ps.setDouble(4, detailReimbursement.getTransport());
			ps.setDouble(5, detailReimbursement.getParkir());
			ps.setDouble(6, detailReimbursement.getKesehatan());
			ps.setDouble(7, detailReimbursement.getBpjs());
			ps.setDouble(8, detailReimbursement.getRewardMonthly());
			ps.setDouble(9, detailReimbursement.getRewardTriwulan());
			ps.setDouble(10, detailReimbursement.getTaxi());
			ps.setDouble(11, detailReimbursement.getLembur());
			ps.setDouble(12, detailReimbursement.getEntertainInternal());
			ps.setDouble(13, detailReimbursement.getEntertainEksternal());
			ps.setString(14, detailReimbursement.getDeskripsiOther());
			ps.setDouble(15, detailReimbursement.getNilaiOther());
			ps.setDouble(16, detailReimbursement.getSubtotal());
			ps.setString(17, detailReimbursement.getNotes());
			ps.setInt(18, detailReimbursement.getKodeDetail());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Update Sukses");
			} else {
				System.out.println("Update Gagal");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(int kodeDetail) {
		String query = "DELETE FROM DETAIL_REIMBURSMENT WHERE KODE_DETAIL=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, kodeDetail);

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Detail Delete With Id=" + kodeDetail);
			} else {
				System.out.println("No Detail Found With Id=" + kodeDetail);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<DetailReimbursement> findAll() {
		String query = "SELECT * FROM DETAIL_REIMBURSMENT";
		List<DetailReimbursement> listDetail = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				DetailReimbursement detailReimbursement = new DetailReimbursement();
				MstKaryawan mstKaryawan = new MstKaryawan();
				MstProject mstProject = new MstProject();

				detailReimbursement.setKodeDetail(rs.getInt("KODE_DETAIL"));

				String nik = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				detailReimbursement.setMstKaryawan(mstKaryawan);

				int kodProject = (rs.getInt("KODE_PROJECT"));
				mstProject = mstProjectDao.findOne(kodProject);
				detailReimbursement.setMstProject(mstProject);

				detailReimbursement.setPeriode(rs.getDate("PERIODE"));
				detailReimbursement.setTransport(rs.getDouble("TRANSPORT"));
				detailReimbursement.setParkir(rs.getDouble("PARKIR"));
				detailReimbursement.setKesehatan(rs.getDouble("KESEHATAN"));
				detailReimbursement.setBpjs(rs.getDouble("BPJS"));
				detailReimbursement.setRewardMonthly(rs
						.getDouble("REWARD_MONTHLY"));
				detailReimbursement.setRewardTriwulan(rs
						.getDouble("REWARD_TRIWULAN"));
				detailReimbursement.setTaxi(rs.getDouble("TAXI"));
				detailReimbursement.setLembur(rs.getDouble("LEMBUR"));
				detailReimbursement.setEntertainInternal(rs
						.getDouble("ENTERTAIN_INTERNAL"));
				detailReimbursement.setEntertainEksternal(rs
						.getDouble("ENTERTAIN_EKSTERNAL"));
				detailReimbursement.setDeskripsiOther(rs
						.getString("DESKRIPSI_OTHER"));
				detailReimbursement.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				detailReimbursement.setSubtotal(rs.getDouble("SUBTOTAL"));
				detailReimbursement.setNotes(rs.getString("NOTES"));

				listDetail.add(detailReimbursement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listDetail;
	}

	@Override
	public DetailReimbursement findOne(int kodeDetail) {
		String query = "SELECT * FROM DETAIL_REIMBURSMENT WHERE KODE_DETAIL = '"
				+ kodeDetail + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		DetailReimbursement detailReimbursement = new DetailReimbursement();
		MstKaryawan mstKaryawan = new MstKaryawan();
		MstProject mstProject = new MstProject();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				detailReimbursement.setKodeDetail(rs.getInt("KODE_DETAIL"));

				String nik = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				detailReimbursement.setMstKaryawan(mstKaryawan);

				int kodProject = (rs.getInt("KODE_PROJECT"));
				mstProject = mstProjectDao.findOne(kodProject);
				detailReimbursement.setMstProject(mstProject);

				detailReimbursement.setPeriode(rs.getDate("PERIODE"));
				detailReimbursement.setTransport(rs.getDouble("TRANSPORT"));
				detailReimbursement.setParkir(rs.getDouble("PARKIR"));
				detailReimbursement.setKesehatan(rs.getDouble("KESEHATAN"));
				detailReimbursement.setBpjs(rs.getDouble("BPJS"));
				detailReimbursement.setRewardMonthly(rs
						.getDouble("REWARD_MONTHLY"));
				detailReimbursement.setRewardTriwulan(rs
						.getDouble("REWARD_TRIWULAN"));
				detailReimbursement.setTaxi(rs.getDouble("TAXI"));
				detailReimbursement.setLembur(rs.getDouble("LEMBUR"));
				detailReimbursement.setEntertainInternal(rs
						.getDouble("ENTERTAIN_INTERNAL"));
				detailReimbursement.setEntertainEksternal(rs
						.getDouble("ENTERTAIN_EKSTERNAL"));
				detailReimbursement.setDeskripsiOther(rs
						.getString("DESKRIPSI_OTHER"));
				detailReimbursement.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				detailReimbursement.setSubtotal(rs.getDouble("SUBTOTAL"));
				detailReimbursement.setNotes(rs.getString("NOTES"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return detailReimbursement;
	}

	@Override
	public List<DetailReimbursement> searchData(String key) {
		String search = ("%" + key + "%");

		String query = "SELECT * "
				+ "FROM (DETAIL_REIMBURSEMENT AS R "
				+ "JOIN MST_KARYAWAN AS K ON R.NIK=K.NIK) "
				+ "JOIN MST_PROJECT AS P ON R.KODE_PROJECT=P.KODE_PROJECT "
				+ "WHERE (NIK LIKE '"+search+"' OR NAMA LIKE '"+search+"' "
				+ "OR COMPANY LIKE '"+search+"' OR NO_ABSEN LIKE '"+search+"' "
				+ "OR JENIS_KELAMIN LIKE '"+search+"' OR TANGGAL_MASUK LIKE '"+search+"' "
				+ "OR NO_REKENING LIKE '"+search+"')";

		List<DetailReimbursement> listDetail = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				DetailReimbursement detailReimbursement = new DetailReimbursement();
				MstKaryawan mstKaryawan = new MstKaryawan();
				MstProject mstProject = new MstProject();

				detailReimbursement.setKodeDetail(rs.getInt("KODE_DETAIL"));

				String nik = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				detailReimbursement.setMstKaryawan(mstKaryawan);

				int kodProject = (rs.getInt("KODE_PROJECT"));
				mstProject = mstProjectDao.findOne(kodProject);
				detailReimbursement.setMstProject(mstProject);

				detailReimbursement.setPeriode(rs.getDate("PERIODE"));
				detailReimbursement.setTransport(rs.getDouble("TRANSPORT"));
				detailReimbursement.setParkir(rs.getDouble("PARKIR"));
				detailReimbursement.setKesehatan(rs.getDouble("KESEHATAN"));
				detailReimbursement.setBpjs(rs.getDouble("BPJS"));
				detailReimbursement.setRewardMonthly(rs
						.getDouble("REWARD_MONTHLY"));
				detailReimbursement.setRewardTriwulan(rs
						.getDouble("REWARD_TRIWULAN"));
				detailReimbursement.setTaxi(rs.getDouble("TAXI"));
				detailReimbursement.setLembur(rs.getDouble("LEMBUR"));
				detailReimbursement.setEntertainInternal(rs
						.getDouble("ENTERTAIN_INTERNAL"));
				detailReimbursement.setEntertainEksternal(rs
						.getDouble("ENTERTAIN_EKSTERNAL"));
				detailReimbursement.setDeskripsiOther(rs
						.getString("DESKRIPSI_OTHER"));
				detailReimbursement.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				detailReimbursement.setSubtotal(rs.getDouble("SUBTOTAL"));
				detailReimbursement.setNotes(rs.getString("NOTES"));

				listDetail.add(detailReimbursement);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return listDetail;
	}

	@Override
	public DetailReimbursement findByDateNameAndKlaim(String nik, int kode, int m , int y) {
		//String query = "SELECT * FROM DETAIL_REIMBURSMENT WHERE NIK = '"+nik+"' AND KODE_PROJECT = '"+kode+"' AND PERIODE = '"+date+"'  ";
		String query = "Select KODE_DETAIL,NIK,KODE_PROJECT,PERIODE,TRANSPORT,PARKIR,KESEHATAN,BPJS,REWARD_MONTHLY"
					   +",REWARD_TRIWULAN,TAXI,LEMBUR,ENTERTAIN_INTERNAL,ENTERTAIN_EKSTERNAL,DESKRIPSI_OTHER,NILAI_OTHER"
				       +",SUBTOTAL,NOTES,DATEADD(MONTH, DATEDIFF(MONTH, 0, PERIODE), 0) AS year_month_date_field FROM DETAIL_REIMBURSMENT "
					   +" where NIK = '"+nik+"' AND KODE_PROJECT = '"+kode+"' AND Month(PERIODE)='"+m+"' AND Year(PERIODE)='"+y+"' ";		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		DetailReimbursement detailReimbursement = new DetailReimbursement();
		MstKaryawan mstKaryawan = new MstKaryawan();
		MstProject mstProject = new MstProject();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				detailReimbursement.setKodeDetail(rs.getInt("KODE_DETAIL"));

				String niks = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(niks);
				detailReimbursement.setMstKaryawan(mstKaryawan);

				int kodProject = (rs.getInt("KODE_PROJECT"));
				mstProject = mstProjectDao.findOne(kodProject);
				detailReimbursement.setMstProject(mstProject);

				detailReimbursement.setPeriode(rs.getDate("PERIODE"));
				detailReimbursement.setTransport(rs.getDouble("TRANSPORT"));
				detailReimbursement.setParkir(rs.getDouble("PARKIR"));
				detailReimbursement.setKesehatan(rs.getDouble("KESEHATAN"));
				detailReimbursement.setBpjs(rs.getDouble("BPJS"));
				detailReimbursement.setRewardMonthly(rs
						.getDouble("REWARD_MONTHLY"));
				detailReimbursement.setRewardTriwulan(rs
						.getDouble("REWARD_TRIWULAN"));
				detailReimbursement.setTaxi(rs.getDouble("TAXI"));
				detailReimbursement.setLembur(rs.getDouble("LEMBUR"));
				detailReimbursement.setEntertainInternal(rs
						.getDouble("ENTERTAIN_INTERNAL"));
				detailReimbursement.setEntertainEksternal(rs
						.getDouble("ENTERTAIN_EKSTERNAL"));
				detailReimbursement.setDeskripsiOther(rs
						.getString("DESKRIPSI_OTHER"));
				detailReimbursement.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				detailReimbursement.setSubtotal(rs.getDouble("SUBTOTAL"));
				detailReimbursement.setNotes(rs.getString("NOTES"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return detailReimbursement;
	}

}
