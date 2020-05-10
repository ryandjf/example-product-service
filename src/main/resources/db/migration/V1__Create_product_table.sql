CREATE TABLE IF NOT EXISTS `product_database`.`product`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` nvarchar(100) NULL,
  `description` nvarchar(500) NULL,
  `picture` nvarchar(200) NULL,
  PRIMARY KEY (`id`)
);