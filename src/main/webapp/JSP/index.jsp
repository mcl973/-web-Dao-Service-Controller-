<%@ page import="java.util.*" %>
<%@ page import="SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>index</head>
<body>
<% Vector objects =(Vector)request.getAttribute("mds");
    for(int i=0;i<objects.size();i++){
        md m = (md)objects.get(i);
 %>
    id:<input type="text" name="id"value=<%=m.id%>>
    name:<input type="text" name="name"value=<%=m.name%>>
    age:<input type="text" name="age"value=<%=m.age%>>
<%}%>
</body>
</html>
