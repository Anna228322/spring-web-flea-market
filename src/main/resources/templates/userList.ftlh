<#import "parts/common.ftl" as c>
<@c.page>
    Список пользователей
    <table>
        <thead>
        <tr>
            <th>Имя</th>
            <th>Роли</th>
        </tr>
        </thead>
        <tbody>
            <#list users as user>
                <tr id="row">
                    <td>
                        ${user.username}
                    </td>
                    <td>
                        <#list user.roles as role>${role.name()}<#sep>, </#sep></#list>
                    </td>
                    <td>
                        <a href="/user/${user.id}">Редактировать</a>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
</@c.page>