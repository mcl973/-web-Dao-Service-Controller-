<%@ page import="java.util.*" %>
<%@ page import="SqlInit.AchieveAllTableAndInstance.CreateFile.Dao.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <object type="application/x-shockwave-flash" style="outline:none;" data="http://cdn.abowman.com/widgets/hamster/hamster.swf?up_snoutColor=F7F4E9&up_wheelSpokeColor=DEDEDE&up_wheelColor=FFFFFF&up_wheelOuterColor=0FA67E&up_eyeColor=000000&up_bgColor=FFFFFF&up_feetColor=D4C898&up_foodColor=cba920&up_waterColor=E0EFFF&up_wheelCenterColor=E4EB2F&up_tailColor=E6DEBE&up_bodyColor=A18825&up_earColor=D4C898&" width="300" height="225"><param name="movie" value="http://cdn.abowman.com/widgets/hamster/hamster.swf?up_snoutColor=F7F4E9&up_wheelSpokeColor=DEDEDE&up_wheelColor=FFFFFF&up_wheelOuterColor=0FA67E&up_eyeColor=000000&up_bgColor=FFFFFF&up_feetColor=D4C898&up_foodColor=cba920&up_waterColor=E0EFFF&up_wheelCenterColor=E4EB2F&up_tailColor=E6DEBE&up_bodyColor=A18825&up_earColor=D4C898&"></param><param name="AllowScriptAccess" value="always"></param><param name="wmode" value="opaque"></param></object><br>

<html>
<head>personal info</head>
<body>
<br>
<br>
<% Vector objects =(Vector)request.getAttribute("object"); %>
  <%
  for(int i=0;i<objects.size();i++){
  mycontext mc = (mycontext)objects.get(i);
  //session.getAttribute("nihao");
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