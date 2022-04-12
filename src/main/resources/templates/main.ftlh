<#import "parts/common.ftl" as c>
<#import "parts/loginForm.ftl" as lf>
<@c.page>
    <@lf.logout/>

    <a href="/product/add">Добавить товар</a><br/>
    <a href="/product">Все товары</a><br/>
    <a href="/product/own">Мои товары</a><br/>

    <a href="/category">Просмотр по категориям</a><br/>

    <form action="/product/search" method="get">
        <input type="text" name="query" placeholder="Поиск по ключевому слову"/>

        <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
        <input type="submit" value="Найти"/>
    </form><br/>
<#--    <div>-->
<#--        <@lf.logout/>-->
<#--        <span><a href="/user">Список пользователей</a></span>-->
<#--    </div>-->

<#--    <div>Список форм:</div>-->
<#--    <div id="form-list">-->
<#--        <#list forms as form>-->
<#--            <div>-->
<#--                <b>${form.id}</b>-->
<#--                <a href="/answer/${form.id}">${form.name}</a>-->
<#--                <span>от ${form.author.username}</span>-->
<#--            </div>-->
<#--            <#else>-->
<#--            <div id="none">-->
<#--                Никто еще не добавил форм :(-->
<#--            </div>-->
<#--        </#list>-->
<#--        <br/><span><a href="/form">Полный список форм</a></span>-->
<#--    </div>-->
</@c.page>