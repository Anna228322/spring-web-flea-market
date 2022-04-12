<#import "parts/common.ftl" as c>
<@c.page>
    <a href="/product"><h2>Все категории</h2></a>
    <#list categories as category>
        <a href="/product/filter/${category.getId()}">
            ${category_index + 1}. ${category.getName()}
        </a><br/>
    <#else>
        Список категорий пуст
    </#list>
    <div>
        <a href="/category/add">Добавить категорию</a>
    </div>
</@c.page>