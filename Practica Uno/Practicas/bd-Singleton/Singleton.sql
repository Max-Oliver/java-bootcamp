create database Singleton
go

use Singleton
go

create table cosas	
(
	IdProduct int primary key not null,
	Nombre varchar(20) not null,
	Precio int not null,
	Stock int
)
go

insert into cosas (IdProduct,Nombre,Precio,Stock) values(4,'Mesa',1399,4),
		(2,'Tenedor',49,50),
		(3,'Cuchara',49,45)	

select * from cosas