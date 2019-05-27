<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5" import="java.sql.*"%>
<%

Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String connUrl = "jdbc:sqlserver://localhost:1433;databaseName=middleTest";
Connection conn = DriverManager.getConnection(connUrl, "sa", "sa123456");

String qryStmt = "select Seq from school";
PreparedStatement stmt = conn.prepareStatement(qryStmt);
ResultSet rs = stmt.executeQuery();

String ename = null;
while (rs.next()) {
	ename = rs.getString("Seq");
	System.out.println(ename);
}
response.getWriter().write(ename);   
conn.close();
%>