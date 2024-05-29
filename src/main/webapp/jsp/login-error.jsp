<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログインエラー</title>
</head>
<body>
    <h1>IDもしくはパスワードが違います</h1>
    <form action="LoginServlet" method="post">
        <p>ログイン名: <input type="text" name="login" value="<%= request.getAttribute("login") != null ? request.getAttribute("login") : "" %>"></p>
        <p>パスワード: <input type="password" name="password"></p>
        <p><input type="submit" value="ログイン"></p>
    </form>
</body>
</html>
