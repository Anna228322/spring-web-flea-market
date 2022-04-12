<#import "parts/common.ftl" as c>
<@c.page>
    Изменение данных пользователя
    <div>
        <#if message??>
            ${message}
        </#if>
    </div>
    <div>
        <form action="/user" method="post">
            <input type="text" value="${user.username}" name="username">
            <#list roles as role>
                <div>
                    <label><input type="checkbox" name="${role.name()}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
                </div>
            </#list>
            <input type="hidden" value="${user.id}" name="userId">
            <input type="hidden" value="${_csrf.token}" name="_csrf">
            <button type="submit">Сохранить</button>
        </form>
    </div>
</@c.page>