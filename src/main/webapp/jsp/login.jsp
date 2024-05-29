<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
</head>
<body>
    <form action="LoginServlet" method="post">
        <p>ID: <input type="text" name="login" value="<%= request.getAttribute("login") != null ? request.getAttribute("login") : "" %>"></p>
        <p>パスワード: <input type="password" name="password"></p>
        <p><input type="submit" value="ログイン"></p>
    </form>
    <p style="color:red;">
        <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
    </p>
</body>
</html>
