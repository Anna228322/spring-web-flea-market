set foreign_key_checks = 0;
delete from product;
delete from product_categories;
delete from category;

insert into product (id, name, description, phone, price, user_id)
values (1, 'Тестовый продукт', 'Описание', 'Телефон', 100, 1);
set foreign_key_checks = 1;
