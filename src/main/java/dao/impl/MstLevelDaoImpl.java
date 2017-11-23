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

import dao.MstLevelDao;
import entity.MstLevel;

@Repository
public class MstLevelDaoImpl implements MstLevelDao{

	@Autowired
	DataSource dataSource;
	
	@Override
	public void save(MstLevel mstLevel) {
		String query = "INSERT INTO MST_LEVEL "
				+ "(KODE_LEVEL, LEVEL) " + "values(?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, mstLevel.getKodeLevel());
			ps.setString(2, mstLevel.getLevel());
			
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
	public void update(MstLevel mstLevel) {
		String query = "UPDATE MST_LEVEL SET LEVEL=? "
				+ "where KODE_LEVEL=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mstLevel.getLevel());
			ps.setInt(2, mstLevel.getKodeLevel());
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
	public void delete(int kodeLevel) {
		String query = "DELETE FROM MST_LEVEL WHERE KODE_LEVEL=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, kodeLevel);

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Level Delete With Id=" + kodeLevel);
			} else {
				System.out.println("No Level Found With Id=" + kodeLevel);

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
	public List<MstLevel> findAll() {
		String query = "SELECT * FROM MST_LEVEL";
		List<MstLevel> listLevel = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstLevel mstLevel = new MstLevel();

				mstLevel.setKodeLevel(rs.getInt("KODE_LEVEL"));
				mstLevel.setLevel(rs.getString("LEVEL"));
				listLevel.add(mstLevel);
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

		return listLevel;
	}

	@Override
	public MstLevel findOne(int kodeLevel) {
		String query = "SELECT * FROM MST_LEVEL WHERE KODE_LEVEL = '"
				+ kodeLevel + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MstLevel mstLevel = new MstLevel();
		
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				mstLevel.setKodeLevel(rs.getInt("KODE_LEVEL"));
				mstLevel.setLevel(rs.getString("LEVEL"));
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

		return mstLevel;
	}

}
