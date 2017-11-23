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
import entity.MstKaryawan;

@Repository
public class MstKaryawanDaoImpl implements MstKaryawanDao{

	@Autowired
	DataSource dataSource;
	
	@Override
	public void save(MstKaryawan mstKaryawan) {
		String query = "INSERT INTO MST_KARYAWAN "
				+ "(NIK, NAMA, COMPANY, NO_ABSEN, JENIS_KELAMIN, TANGGAL_MASUK, NO_REKENING) "
				+ "values(?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mstKaryawan.getNik());
			ps.setString(2, mstKaryawan.getNamaKaryawan());
			ps.setString(3, mstKaryawan.getCompany());
			ps.setString(4, mstKaryawan.getNoAbsen());
			ps.setString(5, mstKaryawan.getJenisKelamin());
			ps.setDate(6, mstKaryawan.getTanggalMasuk());
			ps.setString(7, mstKaryawan.getNoRekening());

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
	public void update(MstKaryawan mstKaryawan) {
		String query = "UPDATE MST_KARYAWAN SET NAMA=?, COMPANY=?, NO_ABSEN=?, JENIS_KELAMIN=?, TANGGAL_MASUK=?, NO_REKENING=? "
				+ "where NIK=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mstKaryawan.getNamaKaryawan());
			ps.setString(2, mstKaryawan.getCompany());
			ps.setString(3, mstKaryawan.getNoAbsen());
			ps.setString(4, mstKaryawan.getJenisKelamin());
			ps.setDate(5, mstKaryawan.getTanggalMasuk());
			ps.setString(6, mstKaryawan.getNoRekening());
			ps.setString(7, mstKaryawan.getNik());
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
	public void delete(String nikKaryawan) {
		String query = "DELETE FROM MST_KARYAWAN where NIK=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, nikKaryawan);

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Karyawan Delete With Id=" + nikKaryawan);
			} else {
				System.out.println("No Karyawan Found With Id=" + nikKaryawan);

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
	public List<MstKaryawan> findAll() {
		String query = "SELECT * FROM MST_KARYAWAN ORDER BY NAMA";
		List<MstKaryawan> listKaryawan = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstKaryawan mstKaryawan = new MstKaryawan();
				mstKaryawan.setNik(rs.getString("NIK"));
				mstKaryawan.setNamaKaryawan(rs.getString("NAMA"));
				mstKaryawan.setCompany(rs.getString("COMPANY"));
				mstKaryawan.setNoAbsen(rs.getString("NO_ABSEN"));
				mstKaryawan.setJenisKelamin(rs.getString("JENIS_KELAMIN"));
				mstKaryawan.setTanggalMasuk(rs.getDate("TANGGAL_MASUK"));
				mstKaryawan.setNoRekening(rs.getString("NO_REKENING"));
				listKaryawan.add(mstKaryawan);
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

		return listKaryawan;
	}

	@Override
	public MstKaryawan findOne(String nikKaryawan) {
		String query = "SELECT * FROM MST_KARYAWAN WHERE NIK = '" + nikKaryawan + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MstKaryawan mstKaryawan = new MstKaryawan();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				mstKaryawan.setNik(rs.getString("NIK"));
				mstKaryawan.setNamaKaryawan(rs.getString("NAMA"));
				mstKaryawan.setCompany(rs.getString("COMPANY"));
				mstKaryawan.setNoAbsen(rs.getString("NO_ABSEN"));
				mstKaryawan.setJenisKelamin(rs.getString("JENIS_KELAMIN"));
				mstKaryawan.setTanggalMasuk(rs.getDate("TANGGAL_MASUK"));
				mstKaryawan.setNoRekening(rs.getString("NO_REKENING"));
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

		return mstKaryawan;
	}

	@Override
	public List<MstKaryawan> searchData(String key) {
		String search = ("%" + key + "%");

		String query = "SELECT * "
				+ "FROM MST_KARYAWAN "
				+ "WHERE (NIK LIKE '"+search+"' OR NAMA LIKE '"+search+"' "
				+ "OR COMPANY LIKE '"+search+"' OR NO_ABSEN LIKE '"+search+"' "
				+ "OR JENIS_KELAMIN LIKE '"+search+"' OR TANGGAL_MASUK LIKE '"+search+"' "
				+ "OR NO_REKENING LIKE '"+search+"')";

		List<MstKaryawan> listKaryawan = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstKaryawan mstKaryawan = new MstKaryawan();
				mstKaryawan.setNik(rs.getString("NIK"));
				mstKaryawan.setNamaKaryawan(rs.getString("NAMA"));
				mstKaryawan.setCompany(rs.getString("COMPANY"));
				mstKaryawan.setNoAbsen(rs.getString("NO_ABSEN"));
				mstKaryawan.setJenisKelamin(rs.getString("JENIS_KELAMIN"));
				mstKaryawan.setTanggalMasuk(rs.getDate("TANGGAL_MASUK"));
				mstKaryawan.setNoRekening(rs.getString("NO_REKENING"));
				listKaryawan.add(mstKaryawan);
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
		return listKaryawan;
	}

	@Override
	public MstKaryawan findOneAbsen(String noAbsen) {
		String query = "SELECT * FROM MST_KARYAWAN WHERE NO_ABSEN = '" + noAbsen + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MstKaryawan mstKaryawan = new MstKaryawan();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				mstKaryawan.setNik(rs.getString("NIK"));
				mstKaryawan.setNamaKaryawan(rs.getString("NAMA"));
				mstKaryawan.setCompany(rs.getString("COMPANY"));
				mstKaryawan.setNoAbsen(rs.getString("NO_ABSEN"));
				mstKaryawan.setJenisKelamin(rs.getString("JENIS_KELAMIN"));
				mstKaryawan.setTanggalMasuk(rs.getDate("TANGGAL_MASUK"));
				mstKaryawan.setNoRekening(rs.getString("NO_REKENING"));
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

		return mstKaryawan;
	}

}
