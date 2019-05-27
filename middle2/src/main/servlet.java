package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.microsoft.sqlserver.jdbc.SQLServerException;

/**
 * Servlet implementation class servlet
 */
@WebServlet("/main/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		System.out.println(request.getParameter("seq"));
//		response.getWriter().print(request.getParameter("seq"));

		String Seq = request.getParameter("seq");
//		System.out.println(t1);
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=middleTest";
			conn = DriverManager.getConnection(connUrl, "sa", "sa123456");

			String qryStmt = "select Count(Seq) from school where seq=?";
			PreparedStatement pstmt = conn.prepareStatement(qryStmt);
			pstmt.setString(1, Seq);
			ResultSet rs = pstmt.executeQuery();

			String ename = null;
			while (rs.next()) {
				ename = rs.getString(1);
			}
			response.getWriter().print(ename);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLServerException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		servletService service = new servletService();
		request.setCharacterEncoding("UTF-8");
		int seq = 0;
		java.sql.Date date = null;
		int year = 100;
		double schoolPopulation = 0;
		double transferPopulation = 0;
		double transferRates = 0;

		String Seq = request.getParameter("seq").trim();
		String DateListed = request.getParameter("dateListed").trim();
		String Year = request.getParameter("year").trim();
		String Category = request.getParameter("category").trim();
		String Duration = request.getParameter("duration").trim();
		String SchoolPopulation = request.getParameter("schoolPopulation").trim();
		String TransferPopulation = request.getParameter("transferPopulation").trim();
		String TransferRates = request.getParameter("transferRates").trim();

//----------------------------------------------------------------------------------
		if (request.getParameter("move").equals("查詢")) {
			seq = Integer.valueOf(Seq);

			servletBean bean = service.select_PK(seq);

			if (bean.getSeq() == 0 || bean.getDateListed() == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("method", "查詢成功");
				request.setAttribute("servletBean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/main/result.jsp");
				rd.forward(request, response);
			}

		}
//----------------------------------------------------------------------------------
		if (request.getParameter("move").equals("新增")) {

			seq = Integer.valueOf(Seq);
			date = java.sql.Date.valueOf(DateListed);
			year = Integer.valueOf(Year);
			schoolPopulation = Double.valueOf(SchoolPopulation);
			transferPopulation = Double.valueOf(TransferPopulation);
			transferRates = Double.valueOf(TransferRates);

			servletBean bean = new servletBean(seq, date, year, Category, Duration, schoolPopulation,
					transferPopulation, transferRates);

			service.create(bean);
			if (bean.getSeq() == 0 || bean.getDateListed() == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("method", "新增成功");
				request.setAttribute("servletBean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/main/result.jsp");
				rd.forward(request, response);
			}

		}
//----------------------------------------------------------------------------------
		if (request.getParameter("move").equals("修改")) {

			seq = Integer.valueOf(Seq);

			servletBean bean1 = service.select_PK(seq);
			System.out.println(bean1.toString());

			seq = bean1.getSeq();
			date = bean1.getDateListed();
			year = bean1.getYear();
			String category = bean1.getCategory();
			String duration = bean1.getDuration();
			schoolPopulation = bean1.getSchoolPopulation();
			transferPopulation = bean1.getTransferPopulation();
			transferRates = bean1.getTransferRates();

			if (DateListed.trim().length() != 0) {
				DateListed = request.getParameter("dateListed");
				date = java.sql.Date.valueOf(DateListed);
			}
			System.out.println(Year.trim().length());
			if (Year.trim().length() != 0) {
				Year = request.getParameter("year");
				year = Integer.valueOf(Year);
			}

			if (Category.trim().length() != 0) {
				category = request.getParameter("category");
			}

			if (Duration.trim().length() != 0) {
				duration = request.getParameter("duration");
			}

			if (SchoolPopulation.trim().length() != 0) {
				SchoolPopulation = request.getParameter("schoolPopulation");
				schoolPopulation = Double.valueOf(SchoolPopulation);
			}

			if (TransferPopulation.trim().length() != 0) {
				TransferPopulation = request.getParameter("transferPopulation");
				transferPopulation = Double.valueOf(TransferPopulation);
			}

			if (TransferRates.trim().length() != 0) {
				TransferRates = request.getParameter("transferRates");
				transferRates = Double.valueOf(TransferRates);
			}

			servletBean bean2 = new servletBean(seq, date, year, category, duration, schoolPopulation,
					transferPopulation, transferRates);

			servletBean bean = service.update(bean2);
			if (bean.getSeq() == 0 || bean.getDateListed() == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("method", "修改成功");
				request.setAttribute("servletBean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/main/result.jsp");
				rd.forward(request, response);
			}

		}
//----------------------------------------------------------------------------------
		if (request.getParameter("move").equals("刪除")) {
			seq = Integer.valueOf(Seq);

			servletBean bean = service.select_PK(seq);
			service.remove(seq);

			if (bean.getSeq() == 0 || bean.getDateListed() == null) {
				RequestDispatcher rd = request.getRequestDispatcher("/main/index.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("method", "刪除成功");
				request.setAttribute("servletBean", bean);
				RequestDispatcher rd = request.getRequestDispatcher("/main/result.jsp");
				rd.forward(request, response);
			}
		}
	}
}
