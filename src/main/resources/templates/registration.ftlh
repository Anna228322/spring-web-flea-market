<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>
    Регистрация<br/>
    <#if message??>
        ${message}
    </#if>

    <form action="/registration" method="post">
        <div><label>Имя: <input type="text" name="username" required/></label></div>
        <div><label>Пароль: <input type="password" name="password" required/></label></div>
        <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
        <div><input type="submit" value="Войти"/></div>
    </form>

    <a href="/login">Уже есть аккаунт</a>
</body>
</html>

