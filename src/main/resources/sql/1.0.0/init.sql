CREATE TABLE `order_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_id` varchar(255) NOT NULL COMMENT '订单ID',
  `buyer_id` varchar(255) NOT NULL COMMENT '买家ID',
  `seller_id` varchar(255) NOT NULL COMMENT '卖家ID',
  `status` varchar(20) NOT NULL COMMENT '订单状态',
  `total_price` decimal(19,2) NOT NULL COMMENT '订单总价',
  `details` mediumtext COLLATE utf8_bin NOT NULL COMMENT '订单详情',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updater` bigint(20) DEFAULT NULL COMMENT '更新者',
  PRIMARY KEY (`id`),
   UNIQUE KEY `UNQ_ORDER_ID` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `product_table` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` decimal(19,2) NOT NULL,
  `product_id` varchar(255) NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `stock` int(11) NOT NULL,
  `seller_id` varchar(255) NOT NULL,
  `version` int(11) NOT NULL DEFAULT '0' COMMENT '版本',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNQ_PRODUCT_ID` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
