drop table if exists comment;
create table comment
(
    id   serial not null primary key,
    text varchar(255),
    book_id bigint
);
drop table if exists book;
create table book
(
    id      serial not null primary key,
    title   varchar(255),
    genre_id bigint,
    author_id bigint
);

drop table if exists genre;
create table genre
(
    id   serial not null primary key,
    name  varchar(255) unique
);

drop table if exists author;
create table author
(
    id   serial not null primary key,
    name  varchar(255) unique
);

CREATE UNIQUE INDEX uq_author
    ON author (name);

alter table book
    add constraint fk_bookGenre
        foreign key (genre_id) references genre (Id);

alter table book
    add constraint fk_bookAuthor
        foreign key (author_id) references author (Id);

alter table comment
    add constraint fk_commentBook
        foreign key (book_id) references book (Id) ON DELETE CASCADE;