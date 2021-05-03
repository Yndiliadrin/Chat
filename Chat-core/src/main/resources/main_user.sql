create table user
(
    ID       Integer not null
        constraint user_pk
            primary key autoincrement,
    username text    not null,
    password text    not null,
    age      int,
    gender   text    not null,
    interest text    not null,
    role     int     not null
);

create unique index user_ID_uindex
    on user (ID);

create unique index user_username_uindex
    on user (username);

INSERT INTO user (ID, username, password, age, gender, interest, role) VALUES (1, 'berberus', '1q2w3e4r', 20, 'férfi', 'suicide', 1);
INSERT INTO user (ID, username, password, age, gender, interest, role) VALUES (2, 'Taylor', '1q2w3e4r', 34, 'kecske', 'jeay', 0);
INSERT INTO user (ID, username, password, age, gender, interest, role) VALUES (4, 'Bubi', 'cd0ef1097169240f561388dee0ad7ce9', 120, 'other', 'diy', 0);
INSERT INTO user (ID, username, password, age, gender, interest, role) VALUES (5, 'Réka', '037e85817801aa12a11d3e3343db32e3', 120, 'male', 'gameing', 0);
INSERT INTO user (ID, username, password, age, gender, interest, role) VALUES (6, 'Bence', '037e85817801aa12a11d3e3343db32e3', 12, 'male', 'gameing', 0);
INSERT INTO user (ID, username, password, age, gender, interest, role) VALUES (9, 'Mikrobi', '037e85817801aa12a11d3e3343db32e3', 23, 'other', 'diy', 0);
INSERT INTO user (ID, username, password, age, gender, interest, role) VALUES (10, 'Brigike123', 'cd0ef1097169240f561388dee0ad7ce9', 12, 'other', 'gameing', 0);