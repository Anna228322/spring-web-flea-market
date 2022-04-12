<#import "parts/common.ftl" as c>
<@c.page>
    <#if product??>
        <form action="/product/add/${product.id}" method="post">
            <label>
                Название:
                <input type="text"
                       value="${product.getName()}"
                       name="productName" required/>
            </label><br/>

            <label>
                Цена:
                <input type="number"
                       value="${product.getPrice()}"
                       name="productPrice"
                       step=".01"
                       min="0" required />
            </label><br/>

            <label>
                Описание:
                <input type="text"
                       value="${product.getDescription()}"
                       name="productDescription" required />
            </label><br/>

            <label>
                Ваш телефон:
                <input type="tel"
                       value="${product.getPhone()}"
                       name="productPhone" required />
            </label><br/>

            <div id="categories">
                Категории:<br/>
                <#list categories as category>
                    ${category.getName()}
                    <label>
                        <input type="checkbox"
                               name="checkbox-${category.getName()}"
                        ${(product.categories?seq_contains(category)?string("checked", ""))}/>
                    </label><br/>
                </#list>
            </div><br/>

            <input type="hidden" name="_csrf" value="${ _csrf.token }"/>
            <div><input type="submit" value="Сохранить"/></div>
        </form>
    </#if>
</@c.page>