DELETE FROM menu;
DELETE FROM admin;
DELETE FROM stock;

insert into admin(admin_id,id,pw,name,auth_id) values(0,'root','1234','root',1);

SELECT * FROM admin;
SELECT * FROM menu;
SELECT * FROM stock;
DELETE FROM device;

DELETE FROM ORDER_DETAIL;
DELETE FROM ORDER_SUMMARY;

SELECT * FROM CATEGORY
