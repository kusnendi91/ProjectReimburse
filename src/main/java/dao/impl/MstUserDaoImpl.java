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

import dao.MstKaryawanDao;
import dao.MstLevelDao;
import dao.MstUserDao;
import entity.MstKaryawan;
import entity.MstLevel;
import entity.MstUser;

@Repository
public class MstUserDaoImpl implements MstUserDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	MstKaryawanDao mstKaryawanDao;

	@Autowired
	MstLevelDao mstLevelDao;

	@Override
	public void save(MstUser mstUser) {
		String query = "INSERT INTO MST_USER "
				+ "(NIK, KODE_LEVEL, PASSWORD) values (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mstUser.getMstKaryawan().getNik());
			ps.setInt(2, mstUser.getMstLevel().getKodeLevel());
			ps.setString(3, mstUser.getPassword());
			
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
	public void update(MstUser mstUser) {
		String query = "UPDATE MST_USER SET NIK=?, KODE_LEVEL=?, PASSWORD=? "
				+ "where KODE_USER=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mstUser.getMstKaryawan().getNik());
			ps.setInt(2, mstUser.getMstLevel().getKodeLevel());
			ps.setString(3, mstUser.getPassword());
			ps.setInt(4, mstUser.getKodeUser());
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
	public void delete(int kodeUser) {
		String query = "DELETE FROM MST_USER WHERE KODE_USER=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, kodeUser);

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("User Delete With Id=" + kodeUser);
			} else {
				System.out.println("No User Found With Id=" + kodeUser);

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
	public List<MstUser> findAll() {
		String query = "SELECT * FROM MST_USER WHERE NIK NOT IN ('01010001')";
		List<MstUser> listUser = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstUser mstUser = new MstUser();
				MstKaryawan mstKaryawan = new MstKaryawan();
				MstLevel mstLevel = new MstLevel();

				mstUser.setKodeUser(rs.getInt("KODE_USER"));

				String nik = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				mstUser.setMstKaryawan(mstKaryawan);

				int kodLevel = (rs.getInt("KODE_LEVEL"));
				mstLevel = mstLevelDao.findOne(kodLevel);
				mstUser.setMstLevel(mstLevel);

				mstUser.setPassword(rs.getString("PASSWORD"));

				listUser.add(mstUser);
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

		return listUser;
	}

	@Override
	public MstUser findOne(int kodeUser) {
		String query = "SELECT * FROM MST_USER WHERE KODE_USER = '"
				+ kodeUser + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MstUser mstUser = new MstUser();
		MstLevel mstLevel = new MstLevel();
		MstKaryawan mstKaryawan = new MstKaryawan();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				mstUser.setKodeUser(rs.getInt("KODE_USER"));

				String nik = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				mstUser.setMstKaryawan(mstKaryawan);

				int kodLevel = (rs.getInt("KODE_LEVEL"));
				mstLevel = mstLevelDao.findOne(kodLevel);
				mstUser.setMstLevel(mstLevel);

				mstUser.setPassword(rs.getString("PASSWORD"));
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

		return mstUser;
	}

	@Override
	public List<MstUser> searchData(String key) {
		String search = ("%" + key + "%");

		String query = "SELECT KODE_USER, K.NIK, L.KODE_LEVEL, PASSWORD "
				+ "FROM (MST_USER AS U JOIN MST_KARYAWAN AS K ON U.NIK=K.NIK) JOIN MST_LEVEL AS L ON L.KODE_LEVEL=U.KODE_LEVEL "
				+ "WHERE (K.NIK LIKE '" + search + "' OR LEVEL LIKE '" + search
				+ "' OR PASSWORD LIKE '" + search + "' OR NAMA LIKE '"+ search +"') AND U.NIK NOT IN ('01010001')";

		List<MstUser> listUser = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstUser mstUser = new MstUser();
				MstLevel mstLevel = new MstLevel();
				MstKaryawan mstKaryawan = new MstKaryawan();

				mstUser.setKodeUser(rs.getInt("KODE_USER"));

				String nik = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				mstUser.setMstKaryawan(mstKaryawan);

				int kodLevel = (rs.getInt("KODE_LEVEL"));
				mstLevel = mstLevelDao.findOne(kodLevel);
				mstUser.setMstLevel(mstLevel);

				mstUser.setPassword(rs.getString("PASSWORD"));

				listUser.add(mstUser);
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
		return listUser;
	}
	
	
	@Override
	public MstUser findByUsernameAndPassword(String username, String password) {
		String query = "SELECT * FROM MST_USER "
				+ "WHERE NIK = '" +username+"' AND PASSWORD = '"+password+"'";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MstUser mstUser = new MstUser();
		MstKaryawan mstKaryawan = new MstKaryawan();
		MstLevel mstLevel = new MstLevel();
		
		try{
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()){
				mstUser.setKodeUser(rs.getInt("KODE_USER"));
				
				String nik = (rs.getString("NIK"));
				mstKaryawan = mstKaryawanDao.findOne(nik);
				mstUser.setMstKaryawan(mstKaryawan);
				int kodeLevel = (rs.getInt("KODE_LEVEL"));
				mstLevel = mstLevelDao.findOne(kodeLevel);
				mstUser.setMstLevel(mstLevel);
				mstUser.setPassword(rs.getString("PASSWORD"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
				ps.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		return mstUser;
	}

}
