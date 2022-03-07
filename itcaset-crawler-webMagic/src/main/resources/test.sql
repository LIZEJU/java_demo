CREATE TABLE `job_info` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
                            `url` varchar(150) DEFAULT NULL COMMENT '招聘信息详情页',
                            `time` varchar(10) DEFAULT NULL COMMENT '职位最近发布时间',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='招聘信息';