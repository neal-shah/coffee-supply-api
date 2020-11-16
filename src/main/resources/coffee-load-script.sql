insert into coffee ("id", sku, productname, origincountry, price, description)
values (1, 10001, 'Kiriga AB', 'Kenya', 12, 'Blood orange, pear, biscoff');

insert into coffee ("id", sku, productname, origincountry, price, description)
values (2, 10002, 'Sumava Ruva', 'Costa Rica', 18, 'Black cherry, dark chocolate, lime');

insert into coffee ("id", sku, productname, origincountry, price, description)
values (3, 10003, 'Mama Mina', 'Nicaragua', 12.75, 'Toffee apple, orange rid, sweet tobacco');

insert into coffee ("id", sku, productname, origincountry, price, description)
values (4, 10004, 'El Limon', 'Guatemala', 13.50, 'Lemon sweets, grapefruit, demerara');

insert into coffee ("id", sku, productname, origincountry, price, description)
values (5, 10005, 'El Yalcon', 'Colombia', 9.00, 'Dark chocolate, lemon sherbert, lime');

select setval('hibernate_sequence', 5, true);