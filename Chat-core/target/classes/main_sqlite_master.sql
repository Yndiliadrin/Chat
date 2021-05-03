create table sqlite_master
(
    type     text,
    name     text,
    tbl_name text,
    rootpage int,
    sql      text
);

INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'sqlite_sequence', 'sqlite_sequence', 4, 'CREATE TABLE sqlite_sequence(name,seq)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'room', 'room', 8, 'CREATE TABLE "room"
(
	ID INTEGER not null
		constraint table_name_pk
			primary key autoincrement,
	name text not null,
	rules text not null,
	kategori text not null
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('index', 'table_name_name_uindex', 'room', 3, 'CREATE UNIQUE INDEX table_name_name_uindex
	on room (name)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'user', 'user', 9, 'CREATE TABLE "user"
(
	ID Integer not null
		constraint user_pk
			primary key autoincrement,
	username text not null,
	password text not null,
	age int,
	gender text not null,
	interest text not null,
	role int not null
)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('index', 'user_ID_uindex', 'user', 2, 'CREATE UNIQUE INDEX user_ID_uindex
	on user (ID)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('index', 'user_username_uindex', 'user', 6, 'CREATE UNIQUE INDEX user_username_uindex
	on user (username)');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'messeng', 'messeng', 5, 'CREATE TABLE "messeng"
(
	ID INTEGER not null
		constraint messeng_pk
			primary key autoincrement,
	sender text not null,
	receiver int not null,
	type text not null,
	textMessage text,
	img blob,
	to_what int not null
)');