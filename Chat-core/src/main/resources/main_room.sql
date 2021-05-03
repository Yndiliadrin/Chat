create table room
(
    ID       INTEGER not null
        constraint table_name_pk
            primary key autoincrement,
    name     text    not null,
    rules    text    not null,
    kategori text    not null
);

create unique index table_name_name_uindex
    on room (name);

INSERT INTO room (ID, name, rules, kategori) VALUES (1, 'Csevegő', 'Lesszives nem káromkodni', 'other');
INSERT INTO room (ID, name, rules, kategori) VALUES (3, 'Offtopic', 'Amit csak szeretnétek', 'other');
INSERT INTO room (ID, name, rules, kategori) VALUES (4, 'Valorant', 'Lehetőség szerint, csak a játékkal kapcsolatos dolgok jöjjenek ide.', 'gameing');
INSERT INTO room (ID, name, rules, kategori) VALUES (5, 'Alkfejl', 'Csak, hogy legyen még pár szoba az adatbázisban.', 'diy');
INSERT INTO room (ID, name, rules, kategori) VALUES (6, 'LookAtMyArt', 'Ide jöhetnek az általatok készített képek, fényképek, rajzok, egyéb alkotásaitok...', 'diy');