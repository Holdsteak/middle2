package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class servletService implements servletDAO {
	DataSource ds = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public servletService() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/MemberDB2");

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String SELECT_BY_PK = "select Seq,DateListed,Year,Category,Duration,SchoolPopulation,TransferPopulation,TransferRates from school where Seq=?";

	@Override
	public servletBean select_PK(int Seq) {
		servletBean bean = null;
		bean = new servletBean();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(SELECT_BY_PK);
			pstmt.setInt(1, Seq);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bean.setSeq(rs.getInt(1));
				bean.setDateListed(rs.getDate(2));
				bean.setYear(rs.getInt(3));
				bean.setCategory(rs.getString(4));
				bean.setDuration(rs.getString(5));
				bean.setSchoolPopulation(rs.getDouble(6));
				bean.setTransferPopulation(rs.getDouble(7));
				bean.setTransferRates(rs.getDouble(8));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return bean;
	}

	private static final String INSERT = "insert into school(Seq,DateListed,Year,Category,Duration,SchoolPopulation,TransferPopulation,TransferRates) values(?,?,?,?,?,?,?,?)";

	@Override
	public servletBean create(servletBean bean) {
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setInt(1, bean.getSeq());
			pstmt.setDate(2, bean.getDateListed());
			pstmt.setInt(3, bean.getYear());
			pstmt.setString(4, bean.getCategory());
			pstmt.setString(5, bean.getDuration());
			pstmt.setDouble(6, bean.getSchoolPopulation());
			pstmt.setDouble(7, bean.getTransferPopulation());
			pstmt.setDouble(8, bean.getTransferRates());
			int rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return bean;
	}

	private static final String UPDATE = "update school set DateListed=?,Year=?,Category=?,Duration=?,SchoolPopulation=?,TransferPopulation=?,TransferRates=? where seq=? ";

	@Override
	public servletBean update(servletBean bean) {
		new servletService();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setDate(1, bean.getDateListed());
			pstmt.setInt(2, bean.getYear());
			pstmt.setString(3, bean.getCategory());
			pstmt.setString(4, bean.getDuration());
			pstmt.setDouble(5, bean.getSchoolPopulation());
			pstmt.setDouble(6, bean.getTransferPopulation());
			pstmt.setDouble(7, bean.getTransferRates());
			pstmt.setInt(8, bean.getSeq());
			int rs = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return bean;
	}

	private static final String DELETE = "delete school where seq=?";

	@Override
	public boolean remove(int Seq) {
		boolean result = false;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, Seq);
			int rs = pstmt.executeUpdate();
			if (rs == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return result;
	}

	private void closeConn() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}