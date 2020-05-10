CREATE TABLE IF NOT EXISTS `product_database`.`product`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` nvarchar(100) NULL,
  `description` nvarchar(500) NULL,
  `picture` nvarchar(200) NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `product_database`.`product` VALUES (10011234,'EDGE: Value-Driven Digital Transformation','The Agile Operating Model That Will Help You Successfully Execute Your Digital Transformation.','https://images-na.ssl-images-amazon.com/images/I/51uOmGGFvyL._SX399_BO1,204,203,200_.jpg');
INSERT INTO `product_database`.`product` VALUES (10012345,'Clean Agile: Back to Basics','Agile Values and Principles for a New Generation.','https://images-na.ssl-images-amazon.com/images/I/41tc6iwhQUL._SX379_BO1,204,203,200_.jpg');
