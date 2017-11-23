package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				+ "(KODE_HISTORY, KODE_USER, KODE_DETAIL, PERIODE) "
				+ "values(?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, history.getKodeHistory());
			ps.setInt(2, history.getMstUser().getKodeUser());
			ps.setInt(3, history.getDetailReimbursement().getKodeDetail());
			ps.setDate(4, history.getPeriode());

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
	public void update(History history) {
		String query = "UPDATE HISTORY_CLAIM SET KODE_USER=?, KODE_DETAIL=?, PERIODE "
				+ "where KODE_HISTORY=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);

			ps.setInt(1, history.getMstUser().getKodeUser());
			ps.setInt(2, history.getDetailReimbursement().getKodeDetail());
			ps.setDate(3, history.getPeriode());
			ps.setInt(4, history.getKodeHistory());
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
		String query = "SELECT * FROM HISTORY_CLAIM";
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
				MstUser mstUser = new MstUser();
				DetailReimbursement detailReimbursement = new DetailReimbursement();

				history.setKodeHistory(rs.getInt("KODE_HISTORY"));

				int kodUser = (rs.getInt("KODE_USER"));
				mstUser = mstUserDao.findOne(kodUser);
				history.setMstUser(mstUser);

				int kodDetail = (rs.getInt("KODE_DETAIL"));
				detailReimbursement = detailReimbursementDao.findOne(kodDetail);
				history.setDetailReimbursement(detailReimbursement);

				history.setPeriode(rs.getDate("PERIODE"));

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
		MstUser mstUser = new MstUser();
		DetailReimbursement detailReimbursement = new DetailReimbursement();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				history.setKodeHistory(rs.getInt("KODE_HISTORY"));

				int kodUser = (rs.getInt("KODE_USER"));
				mstUser = mstUserDao.findOne(kodUser);
				history.setMstUser(mstUser);

				int kodDetail = (rs.getInt("KODE_DETAIL"));
				detailReimbursement = detailReimbursementDao.findOne(kodDetail);
				history.setDetailReimbursement(detailReimbursement);

				history.setPeriode(rs.getDate("PERIODE"));
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

		String query = "SELECT * "
				+ "FROM (HISTORY_CLAIM AS C JOIN MST_USER AS U ON C.KODE_USER=U.KODE_USER) JOIN DETAIL_REIMBURSEMENT AS R ON C.KODE_DETAIL=R.KODE_DETAIL "
				+ "WHERE (KODE_HISTORY LIKE '" + search
				+ "' OR U.KODE_USER LIKE '" + search
				+ "' OR R.KODE_DETAIL LIKE '" + search + "' OR PERIODE LIKE '"
				+ search + "')";

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
				MstUser mstUser = new MstUser();
				DetailReimbursement detailReimbursement = new DetailReimbursement();

				history.setKodeHistory(rs.getInt("KODE_HISTORY"));

				int kodUser = (rs.getInt("KODE_USER"));
				mstUser = mstUserDao.findOne(kodUser);
				history.setMstUser(mstUser);

				int kodDetail = (rs.getInt("KODE_DETAIL"));
				detailReimbursement = detailReimbursementDao.findOne(kodDetail);
				history.setDetailReimbursement(detailReimbursement);

				history.setPeriode(rs.getDate("PERIODE"));

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

}
