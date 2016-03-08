
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin`(
	`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
	`username` varchar(50) not null default '' COMMENT '用户名',
	`password` varchar(50) NOT NULL default '' COMMENT '密码',
	`status` int(2) default 0 COMMENT '状态 0:正常 1:禁用',
	`create_date` BIGINT COMMENT '创建时间',
	PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`(
	`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
	`mobile` VARCHAR(15) NOT NULL DEFAULT '' COMMENT '手机',
	`password` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '密码',
 	`nickname` VARCHAR(50) NOT NULL DEFAULT '' COMMENT '昵称',
	`plat` INT(2) DEFAULT 0 COMMENT '平台 0:app平台 1:微信平台',
	`status` INT(2) DEFAULT 0 COMMENT '状态 0:正常 1:禁用',
	`money` DOUBLE DEFAULT 0 COMMENT '余额',
	`create_date` BIGINT COMMENT '创建时间',
	PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_user_address`;
CREATE TABLE `tb_user_address`(
	`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(32) NOT NULL COMMENT '用户ID',
	`name` varchar(50) default '' COMMENT  '姓名',
	`mobile`VARCHAR(50) DEFAULT '' COMMENT '联系电话',
	`address` VARCHAR(500) DEFAULT '' COMMENT '地址',
	`is_default` int(2) default 0 COMMENT  '是否默认 0:是 1:否',
	`create_date` BIGINT COMMENT '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_pay_record`;
CREATE TABLE `tb_pay_record`(
	`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
	`user_id` BIGINT(32) NOT NULL COMMENT '用户ID',
	`money` DOUBLE NOT NULL DEFAULT 0 COMMENT '缴费金额',
	`create_date` BIGINT COMMENT '创建时间',

	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_web_pay_record`;

CREATE TABLE `tb_web_pay_record`(
	`id` BIGINT(32) NOT NULL AUTO_INCREMENT,
	`record_code` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '编号',
	`money` DOUBLE DEFAULT 0 COMMENT '收支金额 正数为收入,负数为支出',
	`plat` DOUBLE DEFAULT 0 COMMENT '支付方式 0:微信 1:其他',
	`create_date` BIGINT COMMENT '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_information`;

CREATE TABLE `tb_information` (
	`id` BIGINT(32) AUTO_INCREMENT,
	`title` VARCHAR(50) DEFAULT '' COMMENT '资讯标题',
	`content` VARCHAR(500) DEFAULT '' COMMENT '资讯内容',
	`is_list` INT(2) DEFAULT 0 COMMENT '是否上架 0:未发布  1:上架 2:下架',
	`create_date` BIGINT COMMENT '创建时间',
	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_message`;

CREATE TABLE `tb_message` (
	`id` BIGINT(32) AUTO_INCREMENT,
	`title` VARCHAR(50) DEFAULT '' COMMENT '标题',
	`content` VARCHAR(500) DEFAULT '' COMMENT '消息内容',
	`is_list` INT(2) DEFAULT 0 COMMENT '是否发送 0:未发送 1:已发送',
	`create_date` BIGINT COMMENT '创建时间',
	`send_date` BIGINT COMMENT '发送时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_product`;

CREATE TABLE `tb_product`(
	`id` BIGINT(32) AUTO_INCREMENT,
	`title` VARCHAR(50) DEFAULT '' COMMENT '商品标题',
	`start_date` BIGINT DEFAULT 0 COMMENT '开始时间',
	`counts` int(32) default 0 comment '商品数量',
	`coupons_counts` int(32) default 0 comment '优惠前数量',
	`content` VARCHAR(500) DEFAULT '' COMMENT '商品详情',
	PRIMARY KEY (`id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_product_image` ;

CREATE TABLE `tb_product_image`(
	`id` BIGINT(32) AUTO_INCREMENT,
	`product_id` BIGINT(32) COMMENT '产品id',
	`image_url` VARCHAR(200) COMMENT '图片url',
	`create_date` BIGINT COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_product_money`;

CREATE TABLE `tb_product_money`(
	`id` BIGINT(32) AUTO_INCREMENT,
	`product_id` BIGINT(32) COMMENT '商品ID',
	`days` INT(11) DEFAULT 0 COMMENT '天数',
	`money` DOUBLE DEFAULT 0 COMMENT '金额',
	`create_date` BIGINT COMMENT '创建时间',
	PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
	`id` BIGINT(32) AUTO_INCREMENT,
	`sn` VARCHAR(100) DEFAULT '' COMMENT '订单号',
	`product_id` BIGINT(32) COMMENT '商品ID',
	`user_id` BIGINT(32) COMMENT '用户ID',
	`create_date` BIGINT COMMENT '创建时间',
	`status` INT(2) DEFAULT 0 COMMENT '状态 0:带发货 1:已发货 2:已签到',
	PRIMARY KEY (`id`)
)  ENGINE=INNODB DEFAULT CHARSET=utf8;



