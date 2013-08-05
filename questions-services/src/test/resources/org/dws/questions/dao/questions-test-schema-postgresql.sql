drop table if exists teacher;

create table teacher (
	id serial primary key,
	last_name varchar(40) not null,
	first_name varchar(40) not null,
	email varchar(80)
);

insert into teacher (first_name, last_name, email) values ('George', 'Washington', 'george.washington@gmail.com');
insert into teacher (first_name, last_name, email) values ('Abraham', 'Lincoln', 'abraham.lincoln@gmail.com');
insert into teacher (first_name, last_name, email) values ('John', 'Kennedy', 'john.kennedy@gmail.com');