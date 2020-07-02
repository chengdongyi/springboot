CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(256) NOT NULL COMMENT '密码',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '用户状态：0-有效；1-无效；',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE `user` ADD UNIQUE (`username`);
INSERT INTO `user` (`id`, `username`, `password`, `status`) VALUES ('1', 'root', '123456', '0');
INSERT INTO `user` (`id`, `username`, `password`, `status`) VALUES ('2', 'zhangsan', '123456', '0');
INSERT INTO `user` (`id`, `username`, `password`, `status`) VALUES ('3', 'lisi', '123456', '0');
INSERT INTO `user` (`id`, `username`, `password`, `status`) VALUES ('4', 'wangwu', '123456', '0');
COMMIT;

CREATE TABLE `role` (
  `id` int(11) NOT NULL COMMENT '用户ID',
  `rolename` varchar(16) NOT NULL COMMENT '角色名称',
  `roledesc` varchar(256) NOT NULL COMMENT '角色描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
ALTER TABLE `role` ADD INDEX index_name (`id`);
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('1', 'user', '普通用户');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('1', 'vip', '普通会员');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('1', 'svip', '黄金会员');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('2', 'user', '普通用户');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('2', 'vip', '普通会员');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('2', 'svip', '黄金会员');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('3', 'user', '普通用户');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('3', 'vip', '普通会员');
INSERT INTO `role` (`id`, `rolename`, `roledesc`) VALUES ('4', 'user', '普通用户');
COMMIT;

CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(16) NOT NULL COMMENT '姓名',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `age` int(2) NOT NULL COMMENT '年龄',
  `email` varchar(32) COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO `user_info` (`id`, `name`, `mobile`, `age`, `email`) VALUES ('2', '张三', '13996993542', '18', 'zhangsan@163.com');
INSERT INTO `user_info` (`id`, `name`, `mobile`, `age`, `email`) VALUES ('3', '李四', '13996994329', '18', 'lisi@163.com');
INSERT INTO `user_info` (`id`, `name`, `mobile`, `age`, `email`) VALUES ('4', '王五', '13996994524', '18', 'wangwu@163.com');
COMMIT;

CREATE TABLE `t_order_info` (
  `order_id` varchar(32) NOT NULL COMMENT '订单号',
  `mobile` varchar(11) NOT NULL COMMENT '手机号',
  `product_id` varchar(32)  NOT NULL COMMENT '产品Id',
  `product_name` varchar(32) COMMENT '产品名称',
  `price` varchar(12)  NOT NULL COMMENT '价格',
  `status` varchar(2)  NOT NULL COMMENT '订单状态',
  `order_time` datetime  NOT NULL COMMENT '订单状态',
  `update_time` datetime  NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
INSERT INTO `t_order_info` (`order_id`, `mobile`, `product_id`, `product_name`, `price`, `status`, `order_time`, `update_time`)
VALUES ('2020070212300500100001', '13996993542', '10000000520', '一次性医用口罩(10只装)', '19.80', '1', '2020-07-02 12:30:05', '2020-07-02 12:30:10');
INSERT INTO `t_order_info` (`order_id`, `mobile`, `product_id`, `product_name`, `price`, `status`, `order_time`, `update_time`)
VALUES ('2020070216380300100002', '13996994329', '10000000521', '一次性医用口罩(30只装)', '55.00', '1', '2020-07-02 16:38:03', '2020-07-02 16:38:09');
INSERT INTO `t_order_info` (`order_id`, `mobile`, `product_id`, `product_name`, `price`, `status`, `order_time`, `update_time`)
VALUES ('2020070210000500100003', '13996994524', '10000000522', 'N95口罩(20只装)', '126.00', '1', '2020-07-02 10:00:05', '2020-07-02 10:00:10');
COMMIT;
