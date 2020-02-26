
<html>
<body>
<h2>Hello World!
    </h2>
    <% String[] str = new String[10];
            for(int i=0;i<10;i++)
                str[i] = "sdnvfvs"+i;
        for(int i=0;i<10;i++){%>
        id:<input type="text" name="id"value=<%=str[i]%>>
    <%}%>
</body>
</html>
