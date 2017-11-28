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

import dao.MstAbsensiKaryawanDao;
import dao.MstKaryawanDao;
import entity.MstAbsensiKaryawan;
import entity.MstKaryawan;

@Repository
public class MstAbsensiKaryawanDaoImpl implements MstAbsensiKaryawanDao {

	@Autowired
	DataSource dataSource;

	@Autowired
	MstKaryawanDao mstKaryawanDao;

	@Override
	public void save(MstAbsensiKaryawan mstAbsensiKaryawan) {
		String query = "INSERT INTO MST_ABSENSI_KARYAWAN "
				+ "(NO_ABSEN, KANTOR, KLIEN, SAKIT_SURAT, SAKIT_CUTI, CUTI, CUTI_DITANGGUNG, MANGKIR, PERIODE) "
				+ "values(?,?,?,?,?,?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			//ps.setInt(1, mstAbsensiKaryawan.getKodeAbsen());
			ps.setString(1, mstAbsensiKaryawan.getMstKaryawan().getNoAbsen());
			ps.setInt(2, mstAbsensiKaryawan.getKantor());
			ps.setInt(3, mstAbsensiKaryawan.getKlien());
			ps.setInt(4, mstAbsensiKaryawan.getSakitSuratDokter());
			ps.setInt(5, mstAbsensiKaryawan.getSakitPotongCuti());
			ps.setInt(6, mstAbsensiKaryawan.getCuti());
			ps.setInt(7, mstAbsensiKaryawan.getCutiDitanggung());
			ps.setInt(8, mstAbsensiKaryawan.getMangkir());
			ps.setDate(9, mstAbsensiKaryawan.getPeriode());

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
	public void update(MstAbsensiKaryawan mstAbsensiKaryawan) {
		String query = "UPDATE MST_ABSENSI_KARYAWAN SET KANTOR=?, KLIEN=?, "
				+ "SAKIT_SURAT=?, SAKIT_CUTI=?, CUTI=?, CUTI_DITANGGUNG=?, MANGKIR=?, PERIODE=? "
				+ "where NO_ABSEN = ?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, mstAbsensiKaryawan.getKantor());
			ps.setInt(2, mstAbsensiKaryawan.getKlien());
			ps.setInt(3, mstAbsensiKaryawan.getSakitSuratDokter());
			ps.setInt(4, mstAbsensiKaryawan.getSakitPotongCuti());
			ps.setInt(5, mstAbsensiKaryawan.getCuti());
			ps.setInt(6, mstAbsensiKaryawan.getCutiDitanggung());
			ps.setInt(7, mstAbsensiKaryawan.getMangkir());
			ps.setDate(8, mstAbsensiKaryawan.getPeriode());
			ps.setString(9, mstAbsensiKaryawan.getMstKaryawan().getNoAbsen());
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
	public void delete(int kodeAbsen) {
		String query = "DELETE FROM MST_ABSENSI_KARYAWAN WHERE KODE_ABSEN=?";
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, kodeAbsen);

			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("Absensi Delete With Id=" + kodeAbsen);
			} else {
				System.out.println("No Absensi Found With Id=" + kodeAbsen);

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
	public List<MstAbsensiKaryawan> findAll() {
		String query = "SELECT * FROM MST_ABSENSI_KARYAWAN";
		List<MstAbsensiKaryawan> listAbsensi = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstAbsensiKaryawan mstAbsensiKaryawan = new MstAbsensiKaryawan();
				MstKaryawan mstKaryawan = new MstKaryawan();

				mstAbsensiKaryawan.setKodeAbsen(rs.getInt("KODE_ABSEN"));
				String noAbsen = (rs.getString("NO_ABSEN"));
				mstKaryawan = mstKaryawanDao.findOneAbsen(noAbsen);
				mstAbsensiKaryawan.setMstKaryawan(mstKaryawan);

				mstAbsensiKaryawan.setKantor(rs.getInt("KANTOR"));
				mstAbsensiKaryawan.setKlien(rs.getInt("KLIEN"));
				mstAbsensiKaryawan
						.setSakitSuratDokter(rs.getInt("SAKIT_SURAT"));
				mstAbsensiKaryawan.setSakitPotongCuti(rs.getInt("SAKIT_CUTI"));
				mstAbsensiKaryawan.setCuti(rs.getInt("CUTI"));
				mstAbsensiKaryawan.setCutiDitanggung(rs
						.getInt("CUTI_DITANGGUNG"));
				mstAbsensiKaryawan.setMangkir(rs.getInt("MANGKIR"));
				mstAbsensiKaryawan.setPeriode(rs.getDate("PERIODE"));
				listAbsensi.add(mstAbsensiKaryawan);
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

		return listAbsensi;
	}

	@Override
	public MstAbsensiKaryawan findOne(int kodeAbsen) {
		String query = "SELECT * FROM MST_ABSENSI_KARYAWAN WHERE KODE_ABSEN = '"
				+ kodeAbsen + "'";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		MstAbsensiKaryawan mstAbsensiKaryawan = new MstAbsensiKaryawan();
		MstKaryawan mstKaryawan = new MstKaryawan();

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				mstAbsensiKaryawan.setKodeAbsen(rs.getInt("KODE_ABSEN"));
				String noAbsen = (rs.getString("NO_ABSEN"));
				mstKaryawan = mstKaryawanDao.findOneAbsen(noAbsen);
				mstAbsensiKaryawan.setMstKaryawan(mstKaryawan);

				mstAbsensiKaryawan.setKantor(rs.getInt("KANTOR"));
				mstAbsensiKaryawan.setKlien(rs.getInt("KLIEN"));
				mstAbsensiKaryawan
						.setSakitSuratDokter(rs.getInt("SAKIT_SURAT"));
				mstAbsensiKaryawan.setSakitPotongCuti(rs.getInt("SAKIT_CUTI"));
				mstAbsensiKaryawan.setCuti(rs.getInt("CUTI"));
				mstAbsensiKaryawan.setCutiDitanggung(rs
						.getInt("CUTI_DITANGGUNG"));
				mstAbsensiKaryawan.setMangkir(rs.getInt("MANGKIR"));
				mstAbsensiKaryawan.setPeriode(rs.getDate("PERIODE"));
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

		return mstAbsensiKaryawan;
	}

	@Override
	public List<MstAbsensiKaryawan> searchData(String key) {
		String search = ("%" + key + "%");

		String query = "SELECT * " + "FROM MST_ABSENSI_KARYAWAN "
				+ "WHERE (KODE_ABSEN LIKE '" + search + "' OR NO_ABSEN LIKE '"
				+ search + "' " + "OR KANTOR LIKE '" + search
				+ "' OR KLIEN LIKE '" + search + "' " + "OR SAKIT_SURAT LIKE '"
				+ search + "' OR SAKIT_CUTI LIKE '" + search + "' "
				+ "OR CUTI LIKE '" + search + "' OR CUTI_DITANGGUNG LIKE '"
				+ search + "' OR MANGKIR LIKE '"+search+"' OR PERIODE LIKE '"+search+"')";

		List<MstAbsensiKaryawan> listAbsensi = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();

			while (rs.next()) {
				MstAbsensiKaryawan mstAbsensiKaryawan = new MstAbsensiKaryawan();
				MstKaryawan mstKaryawan = new MstKaryawan();

				mstAbsensiKaryawan.setKodeAbsen(rs.getInt("KODE_ABSEN"));
				String noAbsen = (rs.getString("NO_ABSEN"));
				mstKaryawan = mstKaryawanDao.findOneAbsen(noAbsen);
				mstAbsensiKaryawan.setMstKaryawan(mstKaryawan);

				mstAbsensiKaryawan.setKantor(rs.getInt("KANTOR"));
				mstAbsensiKaryawan.setKlien(rs.getInt("KLIEN"));
				mstAbsensiKaryawan
						.setSakitSuratDokter(rs.getInt("SAKIT_SURAT"));
				mstAbsensiKaryawan.setSakitPotongCuti(rs.getInt("SAKIT_CUTI"));
				mstAbsensiKaryawan.setCuti(rs.getInt("CUTI"));
				mstAbsensiKaryawan.setCutiDitanggung(rs
						.getInt("CUTI_DITANGGUNG"));
				mstAbsensiKaryawan.setMangkir(rs.getInt("MANGKIR"));
				mstAbsensiKaryawan.setPeriode(rs.getDate("PERIODE"));
				listAbsensi.add(mstAbsensiKaryawan);
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
		return listAbsensi;
	}

	@Override
	public MstAbsensiKaryawan findByPeriode(String noAbsen, int m, int y) {
			String query = "SELECT * FROM MST_ABSENSI_KARYAWAN "
					     + "WHERE NO_ABSEN ='"+noAbsen+"' AND Month(PERIODE)='"+m+"' AND Year(PERIODE)='"+y+"' ";
					
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			MstAbsensiKaryawan mstAbsensiKaryawan = new MstAbsensiKaryawan();
			MstKaryawan mstKaryawan = new MstKaryawan();

			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement(query);
				rs = ps.executeQuery();

				while (rs.next()) {
					mstAbsensiKaryawan.setKodeAbsen(rs.getInt("KODE_ABSEN"));
					String noAbsens = (rs.getString("NO_ABSEN"));
					mstKaryawan = mstKaryawanDao.findOneAbsen(noAbsens);
					mstAbsensiKaryawan.setMstKaryawan(mstKaryawan);

					mstAbsensiKaryawan.setKantor(rs.getInt("KANTOR"));
					mstAbsensiKaryawan.setKlien(rs.getInt("KLIEN"));
					mstAbsensiKaryawan
							.setSakitSuratDokter(rs.getInt("SAKIT_SURAT"));
					mstAbsensiKaryawan.setSakitPotongCuti(rs.getInt("SAKIT_CUTI"));
					mstAbsensiKaryawan.setCuti(rs.getInt("CUTI"));
					mstAbsensiKaryawan.setCutiDitanggung(rs
							.getInt("CUTI_DITANGGUNG"));
					mstAbsensiKaryawan.setMangkir(rs.getInt("MANGKIR"));
					mstAbsensiKaryawan.setPeriode(rs.getDate("PERIODE"));
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

			return mstAbsensiKaryawan;
	}

}
