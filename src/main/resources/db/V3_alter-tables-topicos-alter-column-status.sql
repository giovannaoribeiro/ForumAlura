alter table topicos CHANGE status status varchar(100) not null;
update topicos set status = "Aberto";