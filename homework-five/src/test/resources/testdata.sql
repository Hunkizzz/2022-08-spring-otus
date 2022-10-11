insert into genre (name)
values ('Роман');
insert into genre (name)
values ('Фэнтези');
insert into genre (name)
values ('Фантастика');

insert into author (name, surname)
values ('Федор', 'Достоевский');
insert into author (name, surname)
values ('Джон', 'Толкин');
insert into author (name, surname)
values ('Айзек', 'Азимов');

insert into book (title, genreId, authorId)
values ('Преступление и наказание', 1, 1);
insert into book (title, genreId, authorId)
values ('Властелин колец', 2, 2);
insert into book (title, genreId, authorId)
values ('Конец вечности', 3, 3);