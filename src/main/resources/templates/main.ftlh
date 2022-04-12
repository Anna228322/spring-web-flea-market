<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
        <input type="submit" value="Выйти"/>
    </form>

    <a href="/product/add">Добавить товар</a><br/>
    <a href="/product">Все товары</a><br/>
    <a href="/product/own">Мои товары</a><br/>

    <a href="/category">Просмотр по категориям</a><br/>

    <form action="/product/search" method="get">
        <input type="text" name="query" placeholder="Поиск по ключевому слову"/>

        <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
        <input type="submit" value="Найти"/>
    </form><br/>
</@c.page>