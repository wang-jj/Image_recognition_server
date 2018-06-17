<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<body>
<h2>Hello World!</h2>
<form action="/home/upload_picture" method="post" enctype="multipart/form-data">
    <label>头 像</label><input type="file" name="file"/><br/>
    <input type="submit" value="提  交"/>
</form>
</body>
</html>
