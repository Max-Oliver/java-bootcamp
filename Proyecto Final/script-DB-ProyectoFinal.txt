CREATE DATABASE API_SHOP_CART;
use API_SHOP_CART;
use sys;
DROP TABLE API_SHOP_CART;
CREATE TABLE `cart` (
	`idcart` int(11) NOT NULL AUTO_INCREMENT comment 'number of cart',
   -- `capCart` int(11) NOT NULL comment 'max 50', 
	`stateOfBuy` boolean default null comment 'State of cart can be used or not',
    `lastDateBuy` date default null comment 'last date updated of buy',			
	`idClient` int(11)not null comment 'foraign key of client id',						
	 Primary key(`idcart`),
     Foreign key (`idClient`) references `client`(`idClient`)
);

CREATE TABLE IF NOT EXISTS `client`(
	`idClient` int (11) not null auto_increment,
    `firstName` varchar (25) not null comment 'First Name of client',
	`lastName` varchar(25) not null comment 'Last Name of client',
    primary Key (`idClient`)
);

CREATE TABLE IF NOT EXISTS `product`(

	`idProduct` int(11) not null auto_increment comment 'Id of every Product',
    `category` 	varchar(30) not null comment 'Fruits, Meet, Electronic Product, etc',
    `nameProd` 	varchar (20) not null comment 'Name of product for more deatails',
	`price` 	double not null comment 'price of product',
   -- `tamProd` 	int not null comment 'tam of product must to be max 10 for every product and min 1 , this is a rest on the capacity of the cart, has maxrange 50',
    primary key (`idProduct`)
);

CREATE TABLE IF NOT EXISTS `client_cart`(
	`idClient` int(11) not null comment 'foreign key references "client" table',
     `idcart` int(11) not null comment 'foreign key references "cart" table',
	`idProduct` int(11) not null comment 'foreing key references "product" table',
   --  `capCart` int(11) not null comment 'cap of cart max value 50 for every product decrement by one depend of product',
	 `totImport` int(11) not null comment ' the sum of all product to count how many the client must spend',
     `nameProd` varchar(20) not null comment 'count of all product into the cart',
     
     primary key (`idcart`,`idClient`, `idProduct`)	
);


alter table `client_cart` drop column `desc`;
alter table `client_cart` add column `nameProd` varchar(20) not null;
alter table `client_cart` add foreign key (`idcart`) references `cart`(`idcart`);
alter table `client_cart` add foreign key (`idclient`) references `client`(`idClient`);
alter table `client_cart` add foreign key (`idcart`,`idClient`);
ALTER TABLE `client_cart` add FOREIGN KEY (`idProduct`) REFERENCES `product`(`idProduct`);


-- insert in table cart
INSERT INTO `cart`(`stateOFBuy`,`lastDateBuy`,`idClient`) Values(1,"2018-09-10",2); 
INSERT INTO `cart`(`stateOFBuy`,`lastDateBuy`,`idClient`) Values(1,"2018-08-10",1); 
INSERT INTO `cart`(`stateOFBuy`,`lastDateBuy`,`idClient`) Values(1,"2018-10-10",3); 
INSERT INTO `cart`(`stateOFBuy`,`lastDateBuy`,`idClient`) Values(1,"2018-09-10",5); 
INSERT INTO `cart`(`stateOFBuy`,`lastDateBuy`,`idClient`) Values(1,"2018-09-10",4); 

-- insetrt int table client
INSERT INTO `client`(`firstName`,`lastName`) Values("Marcos","Roldan");
INSERT INTO `client`(`firstName`,`lastName`) Values("Jorge","Kombawa");
INSERT INTO `client`(`firstName`,`lastName`) Values("Damian","Midoriya");
INSERT INTO `client`(`firstName`,`lastName`) Values("Karla","Sandie");
INSERT INTO `client`(`firstName`,`lastName`) Values("Rodrigo","Dragon");

-- insert in table of Product
INSERT INTO `product`(`category`,`nameProd`,`price`) Values("Electronic","Iphone",15000);
INSERT INTO `product`(`category`,`nameProd`,`price`) Values("Fruit","Kiwi",57);
INSERT INTO `product`(`category`,`nameProd`,`price`) Values("HealtLife","MascFace",650);
INSERT INTO `product`(`category`,`nameProd`,`price`) Values("Deport","Footaball glove",3000);
INSERT INTO `product`(`category`,`nameProd`,`price`) Values("Offimatic","Calculator",350);

-- insert into the table buy 
INSERT INTO `client_cart`(`idClient`,`idcart`,`idProduct`,`totImport`,`nameProd`) values(1,1,1,15000,"Iphone x");
INSERT INTO `client_cart`(`idClient`,`idcart`,`idProduct`,`totImport`,`nameProd`) values(2,2,2,57,"kiwi 1kg");
INSERT INTO `client_cart`(`idClient`,`idcart`,`idProduct`,`totImport`,`nameProd`) values(3,3,3,650,"MascFace");
INSERT INTO `client_cart`(`idClient`,`idcart`,`idProduct`,`totImport`,`nameProd`) values(1,1,2,80,"Funda Iphone X");



-- stataments of tables
Select * from `client`;
Select * from `cart`;
select * from `product`;
Select * from `client_cart` inner join `client` on (`client`.`idClient`=`client_cart`.`idClient`) order by `client`.firstName; 


-- Sentences in the Api
-- ListOfCartByClient
Select * from `client_cart` inner join `product` on (`client_cart`.idProduct = `product`.idProduct) where `client_cart`.idcart = 1;

-- findCartById
Select * from `cart` where `cart`.idClient = 1 ;

-- getTotImport
select sum(totImport) from `client_cart` where `client_cart`.idCart = 1 ;



-- proc alta cliente
create procedure upClient 
@`firstName` varchar(25),
@`lastName` varchar(25)
as
begin

	if not exists(Select * from `client` where `client`.`firstName` = @`firstName` and `client`.`firstName` = @`lastName`)
		begin
				if @@ERROR <> 0
                begin
					print 'The client already exists in the BD'
					return (-1)
                end
		end
	else
		begin
			begin commit
			INSERT INTO `client` (`firstName`,`lastName`) Values(@"firstName",@"lastName")
              if @@ERROR <> 0
				begin
					rollback 
					print 'Error on the insert'
					return (-2)
				end
                
                if @@ERROR = 0 
				begin
					print 'Insert succes!'
					return (1)
                end
        end
end
;
