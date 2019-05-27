<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


<script type="text/javascript" src="myJSS.js"></script>

<style type="text/css">
.enter {
	width: "100px";
}

.msg {
	display: inline;
	color: red;
	font-size: 10px;
}

.txts {}

.dd {
	background-color: black;
}
</style>
</head>
<body>
	<form action="servlet" method="POST">
		<table border="1" width="600px" style="background-color: black">
			<thead style="background-color: #FDF5E6">
				<td align="center" colspan="2">
				<h1>原住民族國中小學校學生轉學情形統計</h1>
				</td>
			</thead>
			<tbody style="background-color: #FDF5E6">
				<tr>
					<td width=120px>編號:</td>
					<td>
					<input class="txts" width="100px" type="text" name="seq"
						id="seq" value="${param.seq}">
						<div id="seq_res" class="msg">${ErrorMsg.Fseq}</div></td>
				</tr>
				<tr>
					<td>資料日期:</td>
					<td><input class="txts" type="text" name="dateListed"
						id="dateListed" value="${param.dateListed}">
						<div id="dateListed_res" class="msg">${ErrorMsg.Fdate}</div></td>
				</tr>
				<tr>
					<td>年度:</td>
					<td><input class="txts" name="year" id="year"
						value="${param.year}">
						<div id="year_res" class="msg">${ErrorMsg.Fyear}</div></td>
				</tr>
				<tr>
					<td>類別:</td>
					<td><input class="txts" type="text" name="category"
						id="category" value="${param.category}">
						<div id="category_res" class="msg">${ErrorMsg.Fcategory}</div></td>
				</tr>
				<tr>
					<td>學制:</td>
					<td><input class="txts" type="text" name="duration"
						id="duration" value="${param.duration}">
						<div id="duration_res" class="msg">${ErrorMsg.Fduration}</div></td>
				</tr>
				<tr>
					<td>就學人數:</td>
					<td><input class="txts" type="text" name="schoolPopulation"
						id="schoolPopulation" value="${param.schoolPopulation}">
						<div id="schoolPopulation_res" class="msg">${ErrorMsg.FschoolPopulation}</div>
					</td>
				</tr>
				<tr>
					<td>轉學轉出人數:</td>
					<td><input class=	"txts" type="text" name="transferPopulation"
						id="transferPopulation" value="${param.transferPopulation}">
						<div id="transferPopulation_res" class="msg">${ErrorMsg.FtransferPopulation}</div>
					</td>
				</tr>
				<tr>
					<td>轉學比例:</td>
					<td><input class="txts" type="text" name="transferRates"
						id="transferRates" value="${param.transferRates}">
						<div id="transferRates_res" class="msg">${ErrorMsg.FtransferRates}</div>
					</td>
				</tr>
			</tbody>
			<tfoot style="background-color: #FDF5E6">
				<tr>
					<td align="center" colspan="2"><span>====></span> <select
						name="move" id="move">
							<option value="查詢">查詢</option>
							<option value="新增">新增</option>
							<option value="修改">修改</option>
							<option value="刪除">刪除</option>
					</select> <input type="submit" value="送出" name="button" id="button">
						<span><====</span>
						</td>
				</tr>
			</tfoot>
		</table>
	</form>
<!-- 		<form action="" id="form123" method="GET" name="form123"><input type="text" id="321" name="321" style="display:none"></form> -->
</body>
</html>
