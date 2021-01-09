DROP TABLE t_order;
CREATE TABLE t_order(
	`id` VARCHAR(50) PRIMARY KEY,
	`create_time` VARCHAR(50),
	`count` INT NOT NULL,
	`totalPrice` DECIMAL(20,8),
	`user_id` INT ,
	FOREIGN KEY(`user_id`) REFERENCES t_user(`id`)
);
ALTER TABLE t_order MODIFY COLUMN `create_time` VARCHAR(50);

INSERT INTO t_order(id,create_time,`count`,`totalPrice`,`user_id`)
VALUES('k1',NOW(),12,123.15,3);
SELECT * FROM t_order;

##    创建 orderItem表

DROP TABLE t_orderItem;
CREATE TABLE t_orderItem(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(50),
	`count` INT NOT NULL,
	`price` DECIMAL(20,2),
	`totalPrice` DECIMAL(20,2),
	`order_id` VARCHAR(50) ,
	FOREIGN KEY(`order_id`) REFERENCES t_order(`id`)
);

ALTER TABLE t_orderItem MODIFY COLUMN `totalPrice` DECIMAL(20,2);
INSERT INTO t_orderItem
(`name`,`count`,`price`,`totalPrice`,`order_id`)
VALUES('数据结构与算法',10,55,55*10,'k1');
SELECT * FROM t_orderItem;


SELECT * FROM t_order;