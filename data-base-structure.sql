create table users{
    UUID ID Primary key,
    varchar(30) username not null,
    varchar(80) email not null,
    varchar(100) password not null
}