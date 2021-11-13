
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table imooc_mall_cart
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_cart`;

CREATE TABLE `imooc_mall_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `quantity` int(11) NOT NULL DEFAULT '1' COMMENT '商品数量',
  `selected` int(11) NOT NULL DEFAULT '1' COMMENT '是否已勾选：0代表未勾选，1代表已勾选',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='购物车';



# Dump of table imooc_mall_category
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_category`;

CREATE TABLE `imooc_mall_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '分类目录名称',
  `type` int(11) NOT NULL COMMENT '分类目录级别，例如1代表一级，2代表二级，3代表三级',
  `parent_id` int(11) NOT NULL COMMENT '父id，也就是上一级目录的id，如果是一级目录，那么父id为0',
  `order_num` int(11) NOT NULL COMMENT '目录展示时的排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='商品分类 ';

LOCK TABLES `imooc_mall_category` WRITE;
/*!40000 ALTER TABLE `imooc_mall_category` DISABLE KEYS */;

INSERT INTO `imooc_mall_category` (`id`, `name`, `type`, `parent_id`, `order_num`, `create_time`, `update_time`)
VALUES
(3,'新鲜水果',1,0,1,'2020-06-18 01:17:00','2020-06-28 17:11:26'),
(4,'平价水果',2,3,1,'2020-06-18 01:17:00','2020-06-28 16:25:10'),
(5,'海鲜水产',1,0,2,'2020-06-18 01:17:00','2020-06-28 16:25:20'),
(6,'精选肉类',1,0,3,'2020-06-18 01:17:00','2020-06-28 16:25:21'),
(7,'螃蟹',2,5,1,'2020-06-18 01:17:00','2020-06-28 16:25:15'),
(8,'鱼类',2,5,2,'2020-06-18 01:17:00','2020-06-28 16:25:16'),
(9,'冷饮冻食',1,0,4,'2020-06-20 13:45:28','2020-06-28 16:25:22'),
(10,'蔬菜蛋品',1,0,5,'2020-06-20 13:45:28','2020-06-28 16:25:23'),
(11,'草莓',2,3,2,'2020-06-18 01:17:00','2020-06-28 15:44:42'),
(12,'奇异果',2,3,3,'2020-06-18 01:17:00','2020-06-28 16:25:12'),
(13,'虾',2,5,3,'2020-06-18 01:17:00','2020-06-28 16:25:17'),
(14,'车厘子',2,3,4,'2020-06-18 01:17:00','2020-06-28 16:25:12'),
(15,'鲜切菠萝',2,27,5,'2020-06-18 01:17:00','2020-02-11 00:42:33'),
(16,'牛羊肉',2,6,1,'2020-06-18 01:17:00','2020-06-28 16:25:18'),
(17,'冷冻水果',2,9,1,'2020-06-18 01:17:00','2020-06-28 16:25:18'),
(18,'蔬菜综合',2,10,1,'2020-06-18 01:17:00','2020-02-11 00:48:27'),
(19,'苹果',3,4,1,'2020-06-18 01:17:00','2020-02-11 00:37:02'),
(27,'美味果切',1,0,7,'2020-06-20 13:45:28','2020-02-10 23:20:36'),
(28,'其他水果',2,3,4,'2020-06-18 01:17:00','2020-06-28 16:25:12');

/*!40000 ALTER TABLE `imooc_mall_category` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table imooc_mall_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_order`;

CREATE TABLE `imooc_mall_order` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(128) NOT NULL DEFAULT '' COMMENT '订单号（非主键id）',
  `user_id` int(64) NOT NULL COMMENT '用户id',
  `total_price` int(64) NOT NULL COMMENT '订单总价格',
  `receiver_name` varchar(32) NOT NULL COMMENT '收货人姓名快照',
  `receiver_mobile` varchar(32) NOT NULL COMMENT '收货人手机号快照',
  `receiver_address` varchar(128) NOT NULL DEFAULT '' COMMENT '收货地址快照',
  `order_status` int(10) NOT NULL DEFAULT '10' COMMENT '订单状态: 0用户已取消，10未付款（初始状态），20已付款，30已发货，40交易完成',
  `postage` int(10) DEFAULT '0' COMMENT '运费，默认为0',
  `payment_type` int(4) NOT NULL DEFAULT '1' COMMENT '支付类型,1-在线支付',
  `delivery_time` timestamp NULL DEFAULT NULL COMMENT '发货时间',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '交易完成时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='订单表;';



# Dump of table imooc_mall_order_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_order_item`;

CREATE TABLE `imooc_mall_order_item` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_no` varchar(128) NOT NULL DEFAULT '' COMMENT '归属订单id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `product_name` varchar(100) NOT NULL DEFAULT '' COMMENT '商品名称',
  `product_img` varchar(128) NOT NULL DEFAULT '' COMMENT '商品图片',
  `unit_price` int(11) NOT NULL COMMENT '单价（下单时的快照）',
  `quantity` int(10) NOT NULL DEFAULT '1' COMMENT '商品数量',
  `total_price` int(11) NOT NULL DEFAULT '0' COMMENT '商品总价',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='订单的商品表 ';



# Dump of table imooc_mall_product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_product`;

CREATE TABLE `imooc_mall_product` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '商品主键id',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `image` varchar(500) NOT NULL DEFAULT '' COMMENT '产品图片,相对路径地址',
  `detail` varchar(500)  DEFAULT '' COMMENT '商品详情',
  `category_id` int(11) NOT NULL COMMENT '分类id',
  `price` int(11) NOT NULL COMMENT '价格,单位-分',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) NOT NULL DEFAULT '1' COMMENT '商品上架状态：0-下架，1-上架',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='商品表';

LOCK TABLES `imooc_mall_product` WRITE;
/*!40000 ALTER TABLE `imooc_mall_product` DISABLE KEYS */;

INSERT INTO `imooc_mall_product` (`id`, `name`, `image`, `detail`, `category_id`, `price`, `stock`, `status`, `create_time`, `update_time`)
VALUES
(2,'澳洲进口大黑车厘子大樱桃包甜黑樱桃大果多汁 500g 特大果','http://127.0.0.1:8083/images/new-chelizi.png','商品毛重：1.0kg货号：608323093445原产地：智利类别：美早热卖时间：1月，11月，12月国产/进口：进口售卖方式：单品',14,50,10000,1,'2020-06-18 16:08:15','2020-02-11 00:08:25'),
(3,'热带菠萝 500g','http://127.0.0.1:8083/images/new-boluo.png','商品名：改良新品种不挖眼不涩口代餐海南新鲜热带水果2-3个',15,1000,60000,1,'2020-06-18 16:10:50','2020-02-11 00:42:42'),
(14,'Zespri佳沛 新西兰阳光金奇异果 6个装','http://127.0.0.1:8083/images/new-mihoutao.png','商品编号：4635056商品毛重：0.71kg商品产地：新西兰类别：金果包装：简装国产/进口：进口原产地：新西兰',12,39,77000,1,'2020-06-18 16:11:13','2020-02-10 23:36:48'),
(17,'红颜奶油草莓 约重500g/20-24颗 新鲜水果','http://127.0.0.1:8083/images/new-caomei.png','商品毛重：0.58kg商品产地：丹东/南通/武汉类别：红颜草莓包装：简装国产/进口：国产',11,99,84000,1,'2020-06-18 16:11:13','2020-02-10 23:37:48'),
(21,'智利原味三文鱼排（大西洋鲑）240g/袋 4片装','http://127.0.0.1:8083/images/new-sanwenyu.png','商品毛重：260.00g商品产地：中国大陆保存状态：冷冻国产/进口：进口包装：简装类别：三文鱼海水/淡水：海水烹饪建议：煎炸，蒸菜，烧烤原产地：智利',8,499,10000,1,'2020-06-28 15:13:07','2020-02-10 23:38:46'),
(22,'普罗旺斯西红柿','http://127.0.0.1:8083/images/new-xihongshi.png','现摘现发 新鲜水果蔬菜 健康轻食 2.5kg',18,699,30000,1,'2020-06-28 15:16:29','2020-02-11 00:04:29'),
(23,'榴莲进口新鲜冷冻榴莲','http://127.0.0.1:8083/images/new-liulian.png','榴莲进口新鲜冷冻榴莲 商品毛重：2.27kg  4.8-5.4斤家庭贵族 保5房/果肉30~40%',28,15,120000,1,'2020-06-28 16:02:13','2020-02-11 00:40:15'),
(24,'智利面包蟹礼盒装4.4-4.0斤/只 生鲜活鲜熟冻大螃蟹','http://127.0.0.1:8083/images/new-pangxie.png','商品毛重：3.0kg商品产地：智利蟹 售卖方式：公蟹重量：2000-4999g 套餐份量：5人份以上国产/进口：进口海水/淡水：海水烹饪建议：火锅，炒菜，烧烤，刺身，加热即食包装：简装原产地：智利保存状态：冷冻公单蟹重：5.5两及以上分类：面包蟹特产品类：其它售卖方式：单品',7,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:05:05'),
(25,'新疆库尔勒克伦生无籽葡萄 国产新鲜葡萄 提子 5斤装','http://127.0.0.1:8083/images/new-putao.png','商品毛重：2.5kg商品产地：中国大陆货号：XZL202009002重量：2000-3999g套餐份量：2人份国产/进口：国产是否有机：非有机单箱规格：3个装，4个装，5个装类别：葡萄 包装：简装原产地：中国大陆售卖方式：单品',28,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:44:05'),
(26,'越南进口红心火龙果 4个装 红肉中果 单果约330-420g','http://127.0.0.1:8083/images/new-huolongguo.png','商品毛重：1.79kg商品产地：越南重量：1000-1999g类别：红心火龙果包装：简装国产/进口：进口',28,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:44:11'),
(27,'波士顿大龙虾鲜活冰鲜海鲜水产大只装波龙澳洲龙虾 400-500g 一只装','http://127.0.0.1:8083/images/new-longxia.png','类别：澳洲龙虾特产品类：澳洲龙虾国产/进口：国产保存状态：冰鲜原产地：中国大陆',13,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:11:30'),
(28,'牛腿肉2kg','http://127.0.0.1:8083/images/new-niurou.png','新鲜黄牛肉生鲜冷冻国产牛肉高品质牛后腿肉 原切90%瘦',16,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:10:40'),
(29,'蜜脆红富士苹果','http://127.0.0.1:8083/images/new-pingguo.png','品种：富士果实直径：75-80mm特产品类：烟台苹果包装形式：简装原产地：山东国产/进口：国产',28,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:34:01'),
(36,'新鲜蔬菜 精品 沙拉菜 生菜 罗马生菜当季','http://127.0.0.1:8083/images/new-shengcai.png','商品毛重：370.00g商品产地：四川',18,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:38:14'),
(37,'进口牛油果 中果6粒装 单果约130-160g ','http://127.0.0.1:8083/images/new-niuyouguo.png','商品名称：京觅进口牛油果 6个装商品编号：3628240商品毛重：1.2kg商品产地：秘鲁、智利、墨西哥重量：1000g以下国产/进口：进口',28,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:47:42'),
(38,'新鲜混合莓蓝莓红树莓','http://127.0.0.1:8083/images/new-shuangmei.png','原产地：云南 包装形式：简装 数量：1盒 国产/进口：国产',17,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:50:54'),
(39,'即食大虾','http://127.0.0.1:8083/images/new-xia.png',' 干虾烤虾大对虾干500g淡干烤海虾干货海鲜干货年货',13,40,22200,1,'2020-06-28 16:06:34','2020-02-11 00:51:59'),
(40,'胡萝卜','http://127.0.0.1:8083/images/new-huluobo.png','商品名称：绿鲜知胡萝卜商品编号：4116192商品毛重：1.07kg商品产地：北京包装：简装分类：萝卜烹饪建议：火锅，炒菜，炖菜',18,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:53:25'),
(41,'香蕉','http://127.0.0.1:8083/images/new-xiangjiao.png','云南高山甜香蕉10斤 新鲜当季水果大芭蕉banana 5000g',28,222,22200,1,'2020-06-28 16:06:34','2020-02-11 00:48:03'),
(42,'甜玉米 切好 香甜','http://127.0.0.1:8083/images/new-yumi.png','品牌： 绿鲜知（greenseer）\n商品名称：绿鲜知甜玉米商品编号：4983604商品毛重：1.1kg商品产地：云南玉溪类别：玉米',18,240,22200,1,'2020-06-28 16:06:34','2020-02-11 00:52:19');
/*!40000 ALTER TABLE `imooc_mall_product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table imooc_mall_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `imooc_mall_user`;

CREATE TABLE `imooc_mall_user` (
  `id` int(64) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `personalized_signature` varchar(50) NOT NULL DEFAULT '' COMMENT '个性签名',
  `email_address` varchar(100) NOT NULL DEFAULT '' COMMENT '邮件地址',
  `role` int(4) NOT NULL DEFAULT '1' COMMENT '角色，1-普通用户，2-管理员',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='用户表 ';

LOCK TABLES `imooc_mall_user` WRITE;
/*!40000 ALTER TABLE `imooc_mall_user` DISABLE KEYS */;

INSERT INTO `imooc_mall_user` (`id`, `username`, `password`, `personalized_signature`, `role`, `create_time`, `update_time`)
VALUES
	(1,'1','1','666',1,'2020-06-16 02:37:33','2020-02-29 14:33:23'),
	(2,'xiaomu','AWRuqaxc6iryhHuA4OnFag==','更新了我的签名',2,'2020-06-17 15:11:32','2020-02-10 09:52:12'),
	(9,'xiaomu2','AWRuqaxc6iryhHuA4OnFag==','祝你今天好心情',2,'2020-02-09 20:39:47','2020-02-11 00:56:02'),
	(10,'mumu','12345678','',1,'2020-02-29 16:53:22','2020-02-29 16:53:22'),
	(11,'mumu3','124567474','',1,'2020-02-29 16:56:07','2020-02-29 16:56:07'),
	(12,'mumu4','SMRMN9k6YmXAjbsJCMdxrQ==','天气晴朗',1,'2020-02-29 17:25:55','2020-02-29 21:59:02'),
	(13,'mumu5','SMRMN9k6YmXAjbsJCMdxrQ==','奋勇向前',2,'2020-02-29 22:09:56','2020-02-29 22:12:11'),
	(14,'imooc','SMRMN9k6YmXAjbsJCMdxrQ==','',1,'2020-03-02 22:45:48','2020-03-02 22:45:48'),
	(15,'super2','SMRMN9k6YmXAjbsJCMdxrQ==','',1,'2020-03-07 18:09:47','2020-03-07 18:09:47');

/*!40000 ALTER TABLE `imooc_mall_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
