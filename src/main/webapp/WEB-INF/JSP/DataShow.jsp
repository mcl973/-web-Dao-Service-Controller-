<%@ page import="java.util.*" %>
<%@ page import="SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>personal info</head>
<body>
<br>
<br>
<% Vector objects =(Vector)request.getAttribute("object"); %>
  <%
  for(int i=0;i<objects.size();i++){
  mycontext mc = (mycontext)objects.get(i);
%>
<table name = "table" >
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tr>username:<%=mc.username%><br></tr>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tr>context:<br><textarea rows="5" cols="20" name="context"><%=mc.context%></textarea><br></tr>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <tr>data:<%=mc.data%><br></tr>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<tr>count:<%=mc.count%><br></tr>
</table>
----------split----------
<br>

<%}%>
</body>
</html>