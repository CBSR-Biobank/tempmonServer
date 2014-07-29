# --- !Ups

insert into monitor (id, manufacturer, name, product_id, vendor_id) values (1, 'Status Instruments', 'SEM710', 47794, 1027);

insert into user (id, email, name_first, name_last, password, gender, country, container_list_number) values (10000, 'admin@cbsrtempmon.ca', 'Admin', ' ', 'secret', 'M', 'Canada', 1);

# --- !Downs

delete from monitor where id = 1;
delete from user where id = 10000;
