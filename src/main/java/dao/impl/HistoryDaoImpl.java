package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.DetailReimbursementDao;
import dao.HistoryDao;
import dao.MstUserDao;
import entity.DetailReimbursement;
import entity.History;
import entity.MstUser;

@Repository
public class HistoryDaoImpl implements HistoryDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	MstUserDao mstUserDao;

	@Autowired
	DetailReimbursementDao detailReimbursementDao;

	@Override
	public void save(History history) {
		String query = "INSERT INTO HISTORY_CLAIM "
				+ "(NIK, NAMA_KARYAWAN, NAMA_PROJECT, PERIODE, TRANSPORT, PARKIR, KESEHATAN, "
				+ "BPJS, REWARD_MONTHLY, REWARD_TRIWULAN, TAXI, LEMBUR, ENTERTAIN_INTERNAL, "
				+ "ENTERTAIN_EKSTERNAL, DESKRIPSI_OTHER, NILAI_OTHER, SUBTOTAL, NAMA_USER, "
				+ "HISTORY_DATE, STATUS, NOTES) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);

			ps.setString(1, history.getNik());
			ps.setString(2, history.getNamaKaryawan());
			ps.setString(3, history.getNamaProject());
			ps.setDate(4, history.getPeriode());
			ps.setDouble(5, history.getTransport());
			ps.setDouble(6, history.getParkir());
			ps.setDouble(7, history.getKesehatan());
			ps.setDouble(8, history.getBpjs());
			ps.setDouble(9, history.getRewardMonthly());
			ps.setDouble(10, history.getRewardTriwulan());
			ps.setDouble(11, history.getTaxi());
			ps.setDouble(12, history.getLembur());
			ps.setDouble(13, history.getEntertainInternal());
			ps.setDouble(14, history.getEntertainEksternal());
			ps.setString(15, history.getDeskripsiOther());
			ps.setDouble(16, history.getNilaiOther());
			ps.setDouble(17, history.getSubtotal());
			ps.setString(18, history.getNamaUser());
			ps.setTimestamp(19, history.getHistoryDate());
			ps.setString(20, history.getStatus());
			ps.setString(21, history.getNotes());

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
	public void delete(int kodeHistory) {
		String query = "DELETE FROM HISTORY_CLAIM WHERE KODE_HISTORY=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, kodeHistory);

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("History Delete With Id=" + kodeHistory);
			} else {
				System.out.println("No History Found With Id=" + kodeHistory);

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
	public List<History> findAll() {
		String query = "SELECT * FROM HISTORY_CLAIM ORDER BY HISTORY_DATE DESC";
		List<History> listHistory = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				History history = new History();

				history.setKodeHistory(rs.getInt("KODE_HISTORY"));
				history.setNik(rs.getString("NIK"));
				history.setNamaKaryawan(rs.getString("NAMA_KARYAWAN"));
				history.setNamaProject(rs.getString("NAMA_PROJECT"));
				history.setPeriode(rs.getDate("PERIODE"));
				history.setTransport(rs.getDouble("TRANSPORT"));
				history.setParkir(rs.getDouble("PARKIR"));
				history.setKesehatan(rs.getDouble("KESEHATAN"));
				history.setBpjs(rs.getDouble("BPJS"));
				history.setRewardMonthly(rs.getDouble("REWARD_MONTHLY"));
				history.setRewardTriwulan(rs.getDouble("REWARD_TRIWULAN"));
				history.setTaxi(rs.getDouble("TAXI"));
				history.setLembur(rs.getDouble("LEMBUR"));
				history.setEntertainInternal(rs.getDouble("ENTERTAIN_INTERNAL"));
				history.setEntertainEksternal(rs.getDouble("ENTERTAIN_EKSTERNAL"));
				history.setDeskripsiOther(rs.getString("DESKRIPSI_OTHER"));
				history.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				history.setSubtotal(rs.getDouble("SUBTOTAL"));
				history.setNamaUser(rs.getString("NAMA_USER"));
				SimpleDateFormat ft = new SimpleDateFormat ("E dd-MM-yyyy hh:mm:s");
				String date = ft.format(rs.getTimestamp("HISTORY_DATE"));
				history.sethDate(date);
				history.setHistoryDate(rs.getTimestamp("HISTORY_DATE"));
				history.setStatus(rs.getString("STATUS"));
				history.setNotes(rs.getString("NOTES"));

				listHistory.add(history);
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

		return listHistory;
	}

	@Override
	public History findOne(int kodeHistory) {
		String query = "SELECT * FROM HISTORY_CLAIM WHERE KODE_HISTORY = '"
				+ kodeHistory + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		History history = new History();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				history.setKodeHistory(rs.getInt("KODE_HISTORY"));
				history.setNik(rs.getString("NIK"));
				history.setNamaKaryawan(rs.getString("NAMA_KARYAWAN"));
				history.setNamaProject(rs.getString("NAMA_PROJECT"));
				history.setPeriode(rs.getDate("PERIODE"));
				history.setTransport(rs.getDouble("TRANSPORT"));
				history.setParkir(rs.getDouble("PARKIR"));
				history.setKesehatan(rs.getDouble("KESEHATAN"));
				history.setBpjs(rs.getDouble("BPJS"));
				history.setRewardMonthly(rs.getDouble("REWARD_MONTHLY"));
				history.setRewardTriwulan(rs.getDouble("REWARD_TRIWULAN"));
				history.setTaxi(rs.getDouble("TAXI"));
				history.setLembur(rs.getDouble("LEMBUR"));
				history.setEntertainInternal(rs.getDouble("ENTERTAIN_INTERNAL"));
				history.setEntertainEksternal(rs.getDouble("ENTERTAIN_EKSTERNAL"));
				history.setDeskripsiOther(rs.getString("DESKRIPSI_OTHER"));
				history.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				history.setSubtotal(rs.getDouble("SUBTOTAL"));
				history.setNamaUser(rs.getString("NAMA_USER"));
				history.setHistoryDate(rs.getTimestamp("HISTORY_DATE"));
				history.setStatus(rs.getString("STATUS"));
				history.setNotes(rs.getString("NOTES"));
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

		return history;
	}

	@Override
	public List<History> searchData(String key) {
		String search = ("%" + key + "%");

		String query = "SELECT * " + "FROM HISTORY_CLAIM "
				+ "WHERE (KODE_HISTORY LIKE '" + search + "' OR NIK LIKE '"
				+ search + "' OR NAMA_KARYAWAN LIKE '" + search
				+ "' OR PERIODE LIKE '" + search + "' OR NAMA_PROJECT LIKE '"
				+ search + "' OR NAMA_USER LIKE '" + search + "' OR STATUS LIKE '" + search + "') ORDER BY HISTORY_DATE DESC";

		List<History> listHistory = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				History history = new History();

				history.setKodeHistory(rs.getInt("KODE_HISTORY"));
				history.setNik(rs.getString("NIK"));
				history.setNamaKaryawan(rs.getString("NAMA_KARYAWAN"));
				history.setNamaProject(rs.getString("NAMA_PROJECT"));
				history.setPeriode(rs.getDate("PERIODE"));
				history.setTransport(rs.getDouble("TRANSPORT"));
				history.setParkir(rs.getDouble("PARKIR"));
				history.setKesehatan(rs.getDouble("KESEHATAN"));
				history.setBpjs(rs.getDouble("BPJS"));
				history.setRewardMonthly(rs.getDouble("REWARD_MONTHLY"));
				history.setRewardTriwulan(rs.getDouble("REWARD_TRIWULAN"));
				history.setTaxi(rs.getDouble("TAXI"));
				history.setLembur(rs.getDouble("LEMBUR"));
				history.setEntertainInternal(rs.getDouble("ENTERTAIN_INTERNAL"));
				history.setEntertainEksternal(rs.getDouble("ENTERTAIN_EKSTERNAL"));
				history.setDeskripsiOther(rs.getString("DESKRIPSI_OTHER"));
				history.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				history.setSubtotal(rs.getDouble("SUBTOTAL"));
				history.setNamaUser(rs.getString("NAMA_USER"));
				SimpleDateFormat ft = new SimpleDateFormat ("E dd-MM-yyyy hh:mm:s");
				String date = ft.format(rs.getTimestamp("HISTORY_DATE"));
				history.sethDate(date);
				history.setHistoryDate(rs.getTimestamp("HISTORY_DATE"));
				history.setStatus(rs.getString("STATUS"));
				history.setNotes(rs.getString("NOTES"));

				listHistory.add(history);
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
		return listHistory;
	}

	@Override
	public List<History> findUpdate(String nik, String namaProject, Date periode, Timestamp historyDate) {
		String query = "SELECT TOP 2 * FROM HISTORY_CLAIM WHERE NIK = '"+ nik +"' AND "
				+ "NAMA_PROJECT = '"+ namaProject +"' AND PERIODE = '"+ periode +"' AND HISTORY_DATE <= '"+ historyDate +"' "
				+ "ORDER BY HISTORY_DATE DESC";
		List<History> listHistory = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				History history = new History();

				history.setKodeHistory(rs.getInt("KODE_HISTORY"));
				history.setNik(rs.getString("NIK"));
				history.setNamaKaryawan(rs.getString("NAMA_KARYAWAN"));
				history.setNamaProject(rs.getString("NAMA_PROJECT"));
				history.setPeriode(rs.getDate("PERIODE"));
				history.setTransport(rs.getDouble("TRANSPORT"));
				history.setParkir(rs.getDouble("PARKIR"));
				history.setKesehatan(rs.getDouble("KESEHATAN"));
				history.setBpjs(rs.getDouble("BPJS"));
				history.setRewardMonthly(rs.getDouble("REWARD_MONTHLY"));
				history.setRewardTriwulan(rs.getDouble("REWARD_TRIWULAN"));
				history.setTaxi(rs.getDouble("TAXI"));
				history.setLembur(rs.getDouble("LEMBUR"));
				history.setEntertainInternal(rs.getDouble("ENTERTAIN_INTERNAL"));
				history.setEntertainEksternal(rs.getDouble("ENTERTAIN_EKSTERNAL"));
				history.setDeskripsiOther(rs.getString("DESKRIPSI_OTHER"));
				history.setNilaiOther(rs.getDouble("NILAI_OTHER"));
				history.setSubtotal(rs.getDouble("SUBTOTAL"));
				history.setNamaUser(rs.getString("NAMA_USER"));
				SimpleDateFormat ft = new SimpleDateFormat ("E dd-MM-yyyy hh:mm:s");
				String date = ft.format(rs.getTimestamp("HISTORY_DATE"));
				history.sethDate(date);
				history.setHistoryDate(rs.getTimestamp("HISTORY_DATE"));
				history.setStatus(rs.getString("STATUS"));
				history.setNotes(rs.getString("NOTES"));

				listHistory.add(history);
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

		return listHistory;
	}

}
