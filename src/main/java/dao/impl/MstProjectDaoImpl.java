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

import dao.MstProjectDao;
import entity.MstKaryawan;
import entity.MstProject;

@Repository
public class MstProjectDaoImpl implements MstProjectDao{

	@Autowired
	DataSource dataSource;
	
	@Override
	public void save(MstProject mstProject) {
		String query = "INSERT INTO MST_PROJECT "
				+ "(NAMA_PROJECT) values (?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mstProject.getNamaProject());
			
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
	public void update(MstProject mstProject) {
		String query = "UPDATE MST_PROJECT SET NAMA_PROJECT=? "
				+ "where KODE_PROJECT=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mstProject.getNamaProject());
			ps.setInt(2, mstProject.getKodeProject());
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
	public void delete(int kodeProject) {
		String query = "DELETE FROM MST_PROJECT WHERE KODE_PROJECT=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, kodeProject);

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Projet Delete With Id=" + kodeProject);
			} else {
				System.out.println("No Project Found With Id=" + kodeProject);

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
	public List<MstProject> findAll() {
		String query = "SELECT * FROM MST_PROJECT";
		List<MstProject> listProject = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstProject mstProject = new MstProject();

				mstProject.setKodeProject(rs.getInt("KODE_PROJECT"));
				mstProject.setNamaProject(rs.getString("NAMA_PROJECT"));
				listProject.add(mstProject);
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

		return listProject;
	}

	@Override
	public MstProject findOne(int kodeProject) {
		String query = "SELECT * FROM MST_PROJECT WHERE KODE_PROJECT = '"
				+ kodeProject + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MstProject mstProject = new MstProject();
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				mstProject.setKodeProject(rs.getInt("KODE_PROJECT"));
				mstProject.setNamaProject(rs.getString("NAMA_PROJECT"));
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

		return mstProject;
	}

	@Override
	public List<MstProject> searchData(String key) {
		String search = ("%" + key + "%");

		String query = "SELECT * "
				+ "FROM MST_PROJECT "
				+ "WHERE (KODE_PROJECT LIKE '"+search+"' OR NAMA_PROJECT LIKE '"+search+"')";

		List<MstProject> listProject = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstProject mstProject = new MstProject();
				mstProject.setKodeProject(rs.getInt("KODE_PROJECT"));
				mstProject.setNamaProject(rs.getString("NAMA_PROJECT"));
				listProject.add(mstProject);
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
		return listProject;
	}

}
