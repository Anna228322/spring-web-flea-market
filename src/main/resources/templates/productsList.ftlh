<#import "parts/common.ftl" as c>
<@c.page>
    <a href="/product/filter">Поиск по категориям</a><br/>
    <form action="/product/search" method="get">
        <input type="text" name="query" placeholder="Поиск по ключевому слову"/>

        <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
        <input type="submit" value="Найти"/>
    </form><br/>
    <a href="/product">Сбросить фильтры</a><br/>
    <#if category??>
        Показаны продукты из категории: ${category.getName()}<br/>
    </#if>
    <#if query??>
        Показаны результаты поиска по запросу: ${query}<br/>
    </#if>
    <#if own?? && own>
        Показаны только мои товары<br/>
    </#if>
    Список продуктов:
    <ul>
        <#list products as product>
            <li>
                <h2>${product_index + 1}. ${product.getName()}</h2>
                Цена: ${product.getPrice()}<br/>
                Описание: ${product.getDescription()}<br/>
                Контактный телефон: ${product.getPhone()}<br/>
                Продавец: ${product.getAuthor().getUsername()}<br/>
                Категории:
                <#list product.categories as category>
                    ${category.getName()},&nbsp
                </#list><br/>
                <#if own?? && own>
                    <a href="/product/edit/${product.getId()}">Редактировать</a>&nbsp
                    <a href="/product/delete/${product.getId()}">Удалить</a>
                </#if>
            </li>
        <#else>
            <p>Список пуст</p>
        </#list>
    </ul>
</@c.page>