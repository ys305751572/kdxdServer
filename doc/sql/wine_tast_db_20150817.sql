/*
Navicat MySQL Data Transfer

Source Server         : 10.58.168.91
Source Server Version : 50703
Source Host           : 10.58.168.91:3306
Source Database       : wine_tast_db

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2015-08-17 18:47:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '商家编码',
  `name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `linkman` varchar(255) DEFAULT NULL COMMENT '联系人',
  `contact` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `order_tel` varchar(255) DEFAULT NULL COMMENT '订餐电话',
  `latitude` varchar(255) DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(255) DEFAULT NULL COMMENT '经度',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `wxuser_id` int(11) DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL COMMENT '酒店图片',
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE,
  KEY `wxuser_id` (`wxuser_id`),
  KEY `image_id` (`image_id`),
  CONSTRAINT `business_ibfk_2` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`),
  CONSTRAINT `business_ibfk_1` FOREIGN KEY (`wxuser_id`) REFERENCES `wx_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商家';

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('7', null, '和平饭店', null, 'ddd', null, '30.480188', '114.39724', 'ff', null, null, null, '2015-08-11 12:00:28', '2015-08-11 12:00:28');
INSERT INTO `business` VALUES ('10', 'buss15000010', '和平饭店', '王彬', '1888044114', '027-22541141', '30.480164', '114.39726', '订单啊', 'sshh', '4', '1434', '2015-08-11 14:31:23', '2015-08-14 17:29:15');

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片编号',
  `path` varchar(500) NOT NULL DEFAULT '' COMMENT '图片路径',
  `thumbs` varchar(500) DEFAULT NULL COMMENT '图片尺寸数据',
  `attribute` varchar(50) DEFAULT NULL,
  `create_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1436 DEFAULT CHARSET=utf8 COMMENT='图片表';

-- ----------------------------
-- Records of image
-- ----------------------------
INSERT INTO `image` VALUES ('1429', 'images/2015/8/1439539842204.jpg', '{\"480x800\":\"images/2015/8/1439539842204_480x800.jpg\"}', '{\"width\":720,\"height\":1280}', '2015-08-14 16:10:43');
INSERT INTO `image` VALUES ('1430', 'images/2015/8/1439540354318.jpg', '{\"480x800\":\"images/2015/8/1439540354318_480x800.jpg\"}', '{\"width\":720,\"height\":1280}', '2015-08-14 16:19:15');
INSERT INTO `image` VALUES ('1431', 'images/2015/8/1439540892175.jpg', '{\"480x800\":\"images/2015/8/1439540892175_480x800.jpg\"}', '{\"width\":720,\"height\":1280}', '2015-08-14 16:28:13');
INSERT INTO `image` VALUES ('1432', 'images/2015/8/1439541595477.jpg', '{\"480x800\":\"images/2015/8/1439541595477_480x800.jpg\"}', '{\"width\":720,\"height\":1280}', '2015-08-14 16:39:56');
INSERT INTO `image` VALUES ('1433', 'images/2015/8/1439541917875.jpg', '{\"480x800\":\"images/2015/8/1439541917875_480x800.jpg\"}', '{\"width\":720,\"height\":1280}', '2015-08-14 16:45:18');
INSERT INTO `image` VALUES ('1434', 'images/2015/8/1439542460075.jpg', '{\"480x800\":\"images/2015/8/1439542460075_480x800.jpg\"}', '{\"width\":720,\"height\":1280}', '2015-08-14 16:54:20');
INSERT INTO `image` VALUES ('1435', 'images/2015/8/1439543512619.jpg', '{\"480x800\":\"images/2015/8/1439543512619_480x800.jpg\"}', '{\"width\":720,\"height\":1280}', '2015-08-14 17:11:53');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `business_id` int(11) DEFAULT NULL COMMENT '商家ID',
  `type` varchar(255) NOT NULL COMMENT '类型(globle总后台，business商家后台)',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `business_id` (`business_id`),
  CONSTRAINT `member_ibfk_1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', null, 'GLOBLE', '2015-08-11 10:11:43', '2015-08-11 10:11:45');
INSERT INTO `member` VALUES ('4', 'wangbin', 'E10ADC3949BA59ABBE56E057F20F883E', '10', 'BUSINESS', '2015-08-12 10:14:13', '2015-08-12 10:14:13');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `market_price` decimal(10,0) DEFAULT NULL COMMENT '商品价格',
  `price` decimal(10,0) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `brand` varchar(255) DEFAULT NULL COMMENT '品牌',
  `image_id` int(11) DEFAULT NULL COMMENT '默认图片',
  `product_area_id` int(11) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_area_id` (`product_area_id`),
  KEY `product_type_id` (`product_type_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`product_area_id`) REFERENCES `product_area` (`id`),
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for product_area
-- ----------------------------
DROP TABLE IF EXISTS `product_area`;
CREATE TABLE `product_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品产地';

-- ----------------------------
-- Records of product_area
-- ----------------------------

-- ----------------------------
-- Table structure for product_type
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '商品类别',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品类别';

-- ----------------------------
-- Records of product_type
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wx_user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `wx_user_id` (`wx_user_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`wx_user_id`) REFERENCES `wx_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(255) DEFAULT NULL COMMENT '用户唯一标识',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `sex` varchar(11) DEFAULT NULL COMMENT '用户性别',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '城市',
  `country` varchar(255) DEFAULT NULL COMMENT '国家',
  `headimgurl` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `unionid` int(11) DEFAULT NULL,
  `subscribe_time` timestamp NULL DEFAULT NULL COMMENT '订阅时间',
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `openid` (`openid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wx_user
-- ----------------------------
INSERT INTO `wx_user` VALUES ('4', 'oQmRps5sNt0QHgYpqGggc2xqRQB0', 'bin', '男', '湖北', '武汉', '中国', 'http://wx.qlogo.cn/mmopen/C7JxJJuoHS6J9FcM9e4usl7PGoMyzwaPI49iavg0V8sPIQyTJ8yiaBnBo8jttolvZ77TzNvGJvoV7n4BgDic9mfkiaXjnKWQNJ7D/0', null, null, '2015-08-04 16:57:14');
