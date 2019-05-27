<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>

	<table border="1">
		<thead>
			<tr>
				<td align="center" colspan="2">
					<h1>${method}</h1>
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>編號:</td>
				<td>${servletBean.seq}</td>
			</tr>
			<tr>
				<td>資料日期:</td>
				<td>${servletBean.dateListed}</td>
			</tr>
			<tr>
				<td>年度:</td>
				<td>${servletBean.year}</td>
			</tr>
			<tr>
				<td>類別:</td>
				<td>${servletBean.category}</td>
			</tr>
			<tr>
				<td>學制:</td>
				<td>${servletBean.duration}</td>
			</tr>
			<tr>
				<td>就學人數:</td>
				<td>${servletBean.schoolPopulation}</td>
			</tr>
			<tr>
				<td>轉學轉出人數:</td>
				<td>${servletBean.transferPopulation}</td>
			</tr>
			<tr>
				<td>轉學比例:</td>
				<td>${servletBean.transferRates}</td>
			</tr>
		</tbody>
	</table>
</body>
</html>