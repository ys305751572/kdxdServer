/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.11 : Database - kdxg
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kdxg` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `kdxg`;

/*Table structure for table `tb_activity` */

DROP TABLE IF EXISTS `tb_activity`;

CREATE TABLE `tb_activity` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT '',
  `create_date` bigint(20) DEFAULT NULL,
  `modify_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_activity` */

insert  into `tb_activity`(`id`,`content`,`create_date`,`modify_date`) values (1,'',1,1);

/*Table structure for table `tb_admin` */

DROP TABLE IF EXISTS `tb_admin`;

CREATE TABLE `tb_admin` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `status` int(2) DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` bigint(20) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_admin` */

insert  into `tb_admin`(`id`,`username`,`password`,`status`,`create_date`,`modify_date`) values (1,'admin','E10ADC3949BA59ABBE56E057F20F883E',0,1,1);

/*Table structure for table `tb_image` */

DROP TABLE IF EXISTS `tb_image`;

CREATE TABLE `tb_image` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `path` varchar(200) DEFAULT '',
  `thumbs` varchar(200) DEFAULT '',
  `attribute` varchar(200) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_image` */

insert  into `tb_image`(`id`,`path`,`thumbs`,`attribute`,`create_date`) values (1,'images/2016/3/1457577268886.jpg','{\"480x800\":\"images/2016/3/1457577268886_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 10:34:29'),(2,'images/2016/3/1457577863445.jpg','{\"480x800\":\"images/2016/3/1457577863445_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 10:44:24'),(3,'images/2016/3/1457578187304.jpg','{\"480x800\":\"images/2016/3/1457578187304_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 10:49:48'),(4,'images/2016/3/1457578506533.jpg','{\"480x800\":\"images/2016/3/1457578506533_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 10:55:07'),(5,'images/2016/3/1457578686624.jpg','{\"480x800\":\"images/2016/3/1457578686624_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 10:58:07'),(6,'images/2016/3/1457578880618.jpg','{\"480x800\":\"images/2016/3/1457578880618_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 11:01:21'),(7,'images/2016/3/1457579142668.jpg','{\"480x800\":\"images/2016/3/1457579142668_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 11:05:43'),(8,'images/2016/3/1457579147334.jpg','{\"480x800\":\"images/2016/3/1457579147334_480x800.jpg\"}','{\"height\":612,\"width\":816}','2016-03-10 11:05:47'),(9,'images/2016/3/1457579406095.jpg','{\"480x800\":\"images/2016/3/1457579406095_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 11:10:06'),(10,'images/2016/3/1457594877917.jpg','{\"480x800\":\"images/2016/3/1457594877917_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 15:27:58'),(11,'images/2016/3/1457595204394.jpg','{\"480x800\":\"images/2016/3/1457595204394_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 15:33:24'),(12,'images/2016/3/1457599588450.jpg','{\"480x800\":\"images/2016/3/1457599588450_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 16:46:29'),(13,'images/2016/3/1457599695518.jpg','{\"480x800\":\"images/2016/3/1457599695518_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 16:48:16'),(14,'images/2016/3/1457599739066.jpg','{\"480x800\":\"images/2016/3/1457599739066_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 16:48:59'),(15,'images/2016/3/1457599764884.jpg','{\"480x800\":\"images/2016/3/1457599764884_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 16:49:25'),(16,'images/2016/3/1457599794795.jpg','{\"480x800\":\"images/2016/3/1457599794795_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 16:49:55'),(17,'images/2016/3/1457599929543.jpg','{\"480x800\":\"images/2016/3/1457599929543_480x800.jpg\"}','{\"height\":136,\"width\":218}','2016-03-10 16:52:10');

/*Table structure for table `tb_information` */

DROP TABLE IF EXISTS `tb_information`;

CREATE TABLE `tb_information` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '' COMMENT '资讯标题',
  `content` varchar(500) DEFAULT '' COMMENT '资讯内容',
  `image_id` int(32) DEFAULT NULL COMMENT '图片ID',
  `is_list` int(2) DEFAULT '0' COMMENT '是否上架 0:未发布  1:上架 2:下架',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tb_information` */

insert  into `tb_information`(`id`,`title`,`content`,`image_id`,`is_list`,`create_date`,`modify_date`) values (1,'11','&ltp&gt11&lt/p&gt',3,1,1457578195227,1457590589844),(2,'11','&ltp&gt11&lt/p&gt',4,1,1457578509518,1457590589811),(3,'222','&ltp&gt222&lt/p&gt',5,0,1457578689094,NULL),(7,'11','&ltp&gt1&lt/p&gt',11,0,1457595208019,NULL),(8,'xiaoxo ','&ltp&gt111&lt/p&gt',12,0,1457599590472,NULL),(9,'11','&ltp&gt1212&lt/p&gt',13,0,1457599697808,NULL);

/*Table structure for table `tb_message` */

DROP TABLE IF EXISTS `tb_message`;

CREATE TABLE `tb_message` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '' COMMENT '标题',
  `content` varchar(500) DEFAULT '' COMMENT '消息内容',
  `is_list` int(2) DEFAULT '0' COMMENT '是否发送 0:未发送 1:已发送',
  `image_id` int(32) DEFAULT NULL COMMENT '封面ID',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `send_date` bigint(20) DEFAULT NULL COMMENT '发送时间',
  `modify_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_message` */

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `sn` varchar(32) DEFAULT NULL COMMENT '订单号',
  `product_id` bigint(32) DEFAULT NULL COMMENT '商品ID',
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户ID',
  `status` int(2) DEFAULT '0' COMMENT '状态 0:待发货 1:已发货 2:已签收',
  `name` varchar(50) DEFAULT NULL COMMENT '收货人姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `address` varchar(500) DEFAULT NULL COMMENT '收货地址',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

insert  into `tb_order`(`id`,`sn`,`product_id`,`user_id`,`status`,`name`,`mobile`,`address`,`create_date`,`modify_date`) values (1,'13245689',1,1,2,'孙悟空','110','天堂',1457681080826,1457682792108);

/*Table structure for table `tb_pay_record` */

DROP TABLE IF EXISTS `tb_pay_record`;

CREATE TABLE `tb_pay_record` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL COMMENT '用户ID',
  `money` double NOT NULL DEFAULT '0' COMMENT '缴费金额',
  `start_date` bigint(20) DEFAULT NULL COMMENT '服务开始时间',
  `end_date` bigint(20) DEFAULT NULL COMMENT '服务结束时间',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_pay_record` */

insert  into `tb_pay_record`(`id`,`user_id`,`money`,`start_date`,`end_date`,`create_date`,`modify_date`) values (1,1,23,1457424927148,1457424927148,1457424927148,1457424927148);

/*Table structure for table `tb_product` */

DROP TABLE IF EXISTS `tb_product`;

CREATE TABLE `tb_product` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT '' COMMENT '商品标题',
  `cover_image_id` int(32) DEFAULT NULL COMMENT '封面图片ID',
  `start_date` bigint(20) DEFAULT '0' COMMENT '开始时间',
  `service_start_date` bigint(20) DEFAULT NULL COMMENT '服务开始时间',
  `counts` int(32) DEFAULT '0' COMMENT '商品数量',
  `coupons_counts` int(32) DEFAULT '0' COMMENT '优惠劵数量',
  `content` varchar(500) DEFAULT '' COMMENT '商品详情',
  `status` int(2) DEFAULT '0' COMMENT '状态0:待开始 1:抢购中 2:已结束',
  `create_date` bigint(20) DEFAULT NULL,
  `modify_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_product` */

insert  into `tb_product`(`id`,`title`,`cover_image_id`,`start_date`,`service_start_date`,`counts`,`coupons_counts`,`content`,`status`,`create_date`,`modify_date`) values (1,'测试',1,1457681080826,1457681080826,100,5,'测试',0,1457681080826,1457681080826);

/*Table structure for table `tb_product_buy_record` */

DROP TABLE IF EXISTS `tb_product_buy_record`;

CREATE TABLE `tb_product_buy_record` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) DEFAULT NULL COMMENT '用户ID',
  `product_id` bigint(32) DEFAULT NULL COMMENT '商品ID',
  `is_use_coupons` int(2) DEFAULT '0' COMMENT '是否使用优惠券0:未使用 1:已使用',
  `result_status` int(2) DEFAULT '0' COMMENT '抢购结果 0:成功 1:失败',
  `pay_money` double DEFAULT '0' COMMENT '缴费金额',
  `pay_days` int(2) DEFAULT '0' COMMENT '缴费天数',
  `result` varchar(200) DEFAULT '' COMMENT '抢购结果',
  `create_date` bigint(20) DEFAULT NULL,
  `modify_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='抢购人员表\r\n';

/*Data for the table `tb_product_buy_record` */

/*Table structure for table `tb_product_image` */

DROP TABLE IF EXISTS `tb_product_image`;

CREATE TABLE `tb_product_image` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(32) DEFAULT NULL COMMENT '产品id',
  `image_id` varchar(200) DEFAULT NULL COMMENT '图片id',
  `create_date` bigint(20) DEFAULT NULL,
  `modify_date` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_product_image` */

/*Table structure for table `tb_product_money` */

DROP TABLE IF EXISTS `tb_product_money`;

CREATE TABLE `tb_product_money` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(32) DEFAULT NULL COMMENT '商品ID',
  `days` int(11) DEFAULT '0' COMMENT '天数',
  `money` double DEFAULT '0' COMMENT '金额',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_product_money` */

/*Table structure for table `tb_product_service` */

DROP TABLE IF EXISTS `tb_product_service`;

CREATE TABLE `tb_product_service` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(32) DEFAULT NULL COMMENT '商品ID',
  `days` int(10) DEFAULT NULL COMMENT '天数',
  `money` double DEFAULT NULL COMMENT '金额',
  `create_date` bigint(32) DEFAULT NULL,
  `modify_date` bigint(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `tb_product_service` */

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `mobile` varchar(15) NOT NULL DEFAULT '' COMMENT '手机',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `nickname` varchar(50) NOT NULL DEFAULT '' COMMENT '昵称',
  `plat` int(2) DEFAULT '0' COMMENT '平台 0:app平台 1:微信平台',
  `status` int(2) DEFAULT '0' COMMENT '状态 0:正常 1:禁用',
  `money` double DEFAULT '0' COMMENT '余额',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`mobile`,`password`,`nickname`,`plat`,`status`,`money`,`create_date`,`modify_date`) values (1,'13476107753','12345','孙悟空',0,1,40,1457424927148,1457677268160),(2,'13476107752','12345','猪八戒',1,1,50,1457424927148,1457424927148),(3,'13476107751','12345','沙悟净',0,1,23.5,1457424927148,1457677278095);

/*Table structure for table `tb_user_address` */

DROP TABLE IF EXISTS `tb_user_address`;

CREATE TABLE `tb_user_address` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(32) NOT NULL COMMENT '用户ID',
  `name` varchar(50) DEFAULT '' COMMENT '姓名',
  `mobile` varchar(50) DEFAULT '' COMMENT '联系电话',
  `address` varchar(500) DEFAULT '' COMMENT '地址',
  `is_default` int(2) DEFAULT '0' COMMENT '是否默认 0:是 1:否',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_address` */

insert  into `tb_user_address`(`id`,`user_id`,`name`,`mobile`,`address`,`is_default`,`create_date`,`modify_date`) values (1,1,'白骨精','123456789','白虎岭',0,1457424927148,1457424927148),(2,1,'太上老君','123456789','天庭',1,1457424927148,1457424927148),(3,1,'玉皇大帝','123456789','凌霄宝殿',1,1457424927148,1457424927148);

/*Table structure for table `tb_web_pay_record` */

DROP TABLE IF EXISTS `tb_web_pay_record`;

CREATE TABLE `tb_web_pay_record` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `record_code` varchar(32) NOT NULL DEFAULT '' COMMENT '编号',
  `money` double DEFAULT '0' COMMENT '收支金额 正数为收入,负数为支出',
  `plat` double DEFAULT '0' COMMENT '支付方式 0:微信 1:其他',
  `create_date` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` bigint(20) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `tb_web_pay_record` */

insert  into `tb_web_pay_record`(`id`,`record_code`,`money`,`plat`,`create_date`,`modify_date`) values (1,'KDXG10004',45.5,0,1457424927148,1457424927148),(2,'KDXG10005',35.5,1,1457424927148,1457424927148),(3,'KDXG100056',-56.2,1,1457424927148,1457424927148);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
