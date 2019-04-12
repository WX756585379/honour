drop database if exists honour;
drop user if exists 'honour'@'localhost';
create database honour default character set utf8 collate utf8_general_ci;
use honour;
create user 'honour'@'localhost' identified by 'Honour123!';
grant all privileges on honour.* to 'honour'@'localhost' WITH GRANT OPTION;
flush privileges;

