DROP TABLE IF EXISTS `project`;
create table `project` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`project_name` varchar(32) default '' comment '项目名称',
	`project_detail` varchar(1000) default '' comment '项目介绍',
	`project_image_index` varchar(500) default '' comment '首页图片',
	`product_image_bg` varchar(500) default '' comment '背景图片',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='项目';

DROP TABLE IF EXISTS `project_images`;

create table `project_images`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`product_id` varchar(11) not null comment '项目ID',
	`image_url` varchar(500) default '' comment '图片地址',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='项目图片';