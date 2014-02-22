# --- !Ups

insert into monitor (id, manufacturer, name, product_id, vendor_id) values (1, 'Status Instruments', 'SEM710', 47794, 1027);

insert into user (id, email, name, password, container_list_number) values (10000, 'cpeck1@ualberta.ca', 'Connor Peck', 'R1chd0ghunt$', 1);

insert into user (id, email, name, password, container_list_number) values (10001, 'apeck@ualberta.ca', 'Aaron Peck', 'secret', 2);

# --- !Downs

delete from monitor where id = 1;

delete from user where id = 10000;
delete from user where id = 10001;
