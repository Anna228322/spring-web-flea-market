<#import "parts/common.ftl" as c>
<@c.page>
    <form action="/category/add" method="post">
        <div><label>Название: <input type="text" name="categoryName" required/></label></div>
        <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
        <div><input type="submit" value="Сохранить"/></div>
    </form>
</@c.page>