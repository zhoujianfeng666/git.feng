<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/webjars/jquery/1.12.4/jquery.min.js"></script>
</head>
<body>
	<form action="" method="post">
		出售商品：<select id="shop">
					<option value ="0">请选择</option>
					<c:forEach items="${list}" var="i">
							<option value ="${i.productName}">${i.productName}</option>
					</c:forEach>
				</select ></br>
		数量：<input id="number" type="text" onblur="cha()"><span id="aaa" style="display:none;color:red"></span></br>
		谁：<input type="text" id="people"></br>
		<input type="button" value="提交" onclick="tijiao()"><input type="reset" value="重置">
	</form>
</body>
</html>
<script>
	function cha(){
		var a=$("select").val();
		var b=$("#number").val();
		$.ajax(
				{
					type:"post",
					url:"pan",
					dataType:"json",
					data:"productName="+a+"&quantity="+b,
					success:function(data){
						
						if(!data["pan"]){
							document.getElementById("aaa").style.display="inline";
							document.getElementById("aaa").innerHTML="不够了库存只有"+data["aInteger"];
							
						}else{
							document.getElementById("aaa").style.display="none";
						}
					}
				})
	}
	
	function tijiao(){
		var a=$("select").val();
		var b=$("#number").val();
		var c=$("#people").val();
		if(a!=0){
			if(b!==""){
				if(b>=0){
					if(c!==""){
						$.ajax({
							type:"post",
							url:"pan",
							dataType:"json",
							data:"productName="+a+"&quantity="+b,
							success:function(data){
								
							}
						})
					}else{
						alert("请输入人");
					}
				}else{
					alert("请输入正数");
				}
			}else{
				alert("请输入数量");
			}
		}else{
			alert("请选择商品");
		}
			
	}
	
</script>