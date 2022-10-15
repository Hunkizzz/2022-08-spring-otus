drop table if exists book;
create table book
(
    id       bigint primary key auto_increment,
    title    varchar2(255),
    genreId  bigint,
    authorId bigint
);

drop table if exists genre;
create table genre
(
    id   bigint primary key auto_increment,
    name varchar2(255) unique
);

drop table if exists author;
create table author
(
    id      bigint primary key auto_increment,
    name    varchar2(255),
    surname varchar2(255)
);

CREATE UNIQUE INDEX uq_author
    ON author (name, surname);

alter table book
    add constraint fk_bookGenre
        foreign key (genreId) references genre (Id);

alter table book
    add constraint fk_bookAuthor
        foreign key (authorId) references author (Id);