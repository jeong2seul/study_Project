<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
	<!-- 자주사용되는 차량 이미지를 화면에 뿌려주고... -->
		<table width="1000" border="0">
			<tr>
				<td align="center" width="333">
					<a href="CarMain.jsp?center=CarEvent.jsp">
						<img alt="" src="img/lfsonata.jpg" width="280" border="0">
					</a>
				</td>
				<td align="center" width="333">
					<a href="CarMain.jsp?center=CarEvent.jsp">
						<img alt="" src="img/k5.jpg" width="280" border="0">
					</a>
				</td>
				<td align="center" width="333">
					<a href="CarMain.jsp?center=CarEvent.jsp">
						<img alt="" src="img/avante.jpg" width="280" border="0">
					</a>
				</td>
			<tr>		
		</table>

		<!-- 소형,중형,대형을 기준으로 차량을 검색해서 결과를  화면에 뿌려주기 위한 부분... -->
		<h1><img height="50" src="img/ccs.jpg" border="0"> </h1><!-- <차랑 종류 보기> 이미지 -->
		
		<!-- 카데고리별 차검색 -->
		<form action="CarcategoryController.do" method="post"> 
			<table width="400" border="0">
				<tr align="center">
					<td width="100" > 차량종류</td>
					<td width="100" height="50">
						<select name="carcategory">
							<option value="Small">소형</option>
							<option value="Mid">중형</option>
							<option value="Big">대형</option>
						</select>
					</td>
					<td  align="center">
						<!-- 카데고리별 차검색 -->
						<input type="submit" value="검색">
					</td>				
					<td align="center">
						<!-- 전체 검색 -->
						<input type="button" value="전체 검색" 
						onclick="location.href='CarListController.do'">
					</td>
				</tr>				 	
			</table>
		</form>
	</center>
</body>
</html>