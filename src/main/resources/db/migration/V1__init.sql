create table hibernate_sequence (next_val bigint) engine=InnoDB;
insert into hibernate_sequence values ( 1 );
create table user (id bigint not null, active bit not null, password varchar(255), username varchar(255), primary key (id)) engine=InnoDB;
create table user_role (user_id bigint not null, roles varchar(255)) engine=InnoDB;
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id);

insert into user values (1, true, '123', 'root');
insert into user_role values (1, 'ADMIN');

create table category (id bigint not null, name varchar(255), primary key (id)) engine=InnoDB;
create table product (id bigint not null, name varchar(255), description varchar(255), phone varchar(255), price double, user_id bigint, primary key (id)) engine=InnoDB;
alter table product add constraint product_constraint_user foreign key (user_id) references user (id);
create table product_categories (product_id bigint not null, categories_id bigint not null, primary key (product_id, categories_id)) engine=InnoDB;
alter table product_categories add constraint product_category_constraint_category foreign key (categories_id) references category (id);
alter table product_categories add constraint product_category_constraint_product foreign key (product_id) references product (id);
