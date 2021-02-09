/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 8.0.18 : Database - second_hand_trading
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`second_hand_trading` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `second_hand_trading`;

/*Table structure for table `sh_address` */

DROP TABLE IF EXISTS `sh_address`;

CREATE TABLE `sh_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `consignee_name` varchar(32) NOT NULL COMMENT '收货人姓名',
  `consignee_phone` varchar(16) NOT NULL COMMENT '收货人手机号',
  `province_name` varchar(32) NOT NULL COMMENT '省',
  `city_name` varchar(32) NOT NULL COMMENT '市',
  `region_name` varchar(32) NOT NULL COMMENT '区',
  `detail_address` varchar(64) NOT NULL COMMENT '详细地址',
  `default_flag` tinyint(4) NOT NULL COMMENT '是否默认地址',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_address` */

insert  into `sh_address`(`id`,`consignee_name`,`consignee_phone`,`province_name`,`city_name`,`region_name`,`detail_address`,`default_flag`,`user_id`) values (20,'shiny','17322611234','广东省','佛山市','禅城区','江湾一路1号',0,1),(21,'hhhh','17322610001','广东省','佛山市','禅城区','江湾一路2号',0,1),(22,'myl','17322611234','湖北省','宜昌市','点军区','啦啦啦啦啦啦啦啦啦啦啦啦啦',0,1),(23,'lll','17322612356','山东省','淄博市','博山区','fdsafsdfsdfsd',1,1);

/*Table structure for table `sh_admin` */

DROP TABLE IF EXISTS `sh_admin`;

CREATE TABLE `sh_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员账号',
  `admin_password` varchar(16) NOT NULL COMMENT '密码',
  `admin_name` varchar(8) NOT NULL COMMENT '管理员名字',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_admin` */

insert  into `sh_admin`(`id`,`account_number`,`admin_password`,`admin_name`) values (1,'root','123456','超级管理员'),(3,'123456','123456','管理员1'),(5,'123457','123456','管理员2'),(8,'123458','123456','fdfa'),(9,'123459','123456','dgfrg');

/*Table structure for table `sh_favorite` */

DROP TABLE IF EXISTS `sh_favorite`;

CREATE TABLE `sh_favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键id',
  `create_time` datetime NOT NULL COMMENT '加入收藏的时间',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `idle_id` bigint(20) NOT NULL COMMENT '闲置物主键id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`idle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_favorite` */

insert  into `sh_favorite`(`id`,`create_time`,`user_id`,`idle_id`) values (32,'2020-12-27 00:28:00',1,25),(33,'2020-12-27 00:29:11',1,22),(34,'2020-12-27 01:28:51',1,17),(38,'2020-12-28 12:34:03',1,35);

/*Table structure for table `sh_idle_item` */

DROP TABLE IF EXISTS `sh_idle_item`;

CREATE TABLE `sh_idle_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `idle_name` varchar(64) NOT NULL COMMENT '闲置物名称',
  `idle_details` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详情',
  `picture_list` varchar(1024) NOT NULL COMMENT '图集',
  `idle_price` decimal(10,2) NOT NULL COMMENT '价格',
  `idle_place` varchar(32) NOT NULL COMMENT '发货地区',
  `idle_label` int(11) NOT NULL COMMENT '分类标签',
  `release_time` datetime NOT NULL COMMENT '发布时间',
  `idle_status` tinyint(4) NOT NULL COMMENT '状态（发布1、下架2、删除0）',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_idle_item` */

insert  into `sh_idle_item`(`id`,`idle_name`,`idle_details`,`picture_list`,`idle_price`,`idle_place`,`idle_label`,`release_time`,`idle_status`,`user_id`) values (16,'苹果XSMAX ','国行256G，面容正常使用，95成新','[\"http://localhost:8080/image?imageName=file16089948344681006n_v293c139e616154d1c9ff050f6a4832a5e.jpg\",\"http://localhost:8080/image?imageName=file16089948385671007n_v215ddc255e7db45ec8e3e2d5124eb928b.jpg\"]','3500.00','广州市',1,'2020-12-26 23:01:01',1,26),(17,'荣耀play3手机转让','荣耀PⅠay3，6.39英寸屏幕，全网通，内存6GB十64GB，4000毫安大电池，麒麟710F处理器，2020年8月5日购入，没维修过，没进过水。','[\"http://localhost:8080/image?imageName=file16089950163791008n_v23c010f8000c14d62b24ec532a8ada6ed.jpg\",\"http://localhost:8080/image?imageName=file16089950199481009n_v26b5148bb44264b9b8fdc6455ed5f94a2.jpg\"]','700.00','广州市',1,'2020-12-26 23:03:48',1,26),(18,'8成新oppor17蓝色全网通8g+128g','8成新oppor17蓝色全网通8g+128g，屏幕有明显划痕，功能完好。','[\"http://localhost:8080/image?imageName=file16089951910621010n_v2fe547385e2634ae9a7519e5b24457391.jpg\",\"http://localhost:8080/image?imageName=file16089951910681011n_v201c9f19dad3140c7a38778d714715ae2.jpg\",\"http://localhost:8080/image?imageName=file16089951910731012n_v2353f8e73eb604b938a20e94cf64043d9.jpg\"]','1300.00','广州市',1,'2020-12-26 23:06:56',1,26),(19,'先锋42寸高清智能电视','先锋42寸高清智能电视，优酷网络播放盒带4K高清，功能完好，画面超清完美，和康佳，创维，TCL，小米，三星可以相比较，低价转让','[\"http://localhost:8080/image?imageName=file16089954483341013n_v2c33a135e4e5b4172ac28dbb94f83b5ee.jpg\",\"http://localhost:8080/image?imageName=file16089954519631014n_v291ff76627cb5406ab4a0e0a289e5a37e.jpg\"]','650.00','唐山市',2,'2020-12-26 23:10:57',1,24),(20,'飞利浦液晶电视，原装遥控器，无维修','50寸飞利浦液晶电视\n原装遥控器，无维修！\n欢迎打包拿走！','[\"http://localhost:8080/image?imageName=file16089955851111015n_v27a9cd22d0a7f4af88dedf68cdeb37e5b.jpg\"]','200.00','唐山市',2,'2020-12-26 23:13:08',1,24),(21,'注册会计师正版轻松过关1-5全套','注册会计师cpa，2020年轻松过关1-5全套。\n科目：审计+战略。今年已过审计和战略，所以出这两门。\n自己买书之后用二维码在官网上做的题，所以纸质书95%新。几乎是新书了。','[\"http://localhost:8080/image?imageName=file16089956978301016n_v2767bb5890e9045fe96ff488d6a831593.jpg\"]','60.00','深圳市',4,'2020-12-26 23:15:05',1,23),(22,'245元出iphone6plus 16G单机一台','iphone6 plus 16G 苹果6plus一台，国行，成色看图，无锁无ID，可以随意刷机，还原，清除，功能正常，指纹好用正常，三网，插卡来信号！爱思验机基本全录95分！\n注意：特价机器，只保是苹果正品手机，其他不保，机器提供测试功能视频做参考，收到手机出现任何视频没测出来的问题都不售后不退换，售出不管任何理由问题都不退不换！收到机器必须5小时内测试好确认收货！想好再拍发出不退！\n备注：机器来路是手机店里别人以旧换新的旧机处理！\n测试视频不知道怎么上传，加V信索要吧，这台机器发号2016，微信说要2016号机器测试视频就行！\n以下是测试到的机器问题：\n问题1：后置摄像头有淡斑，扫码不影响！','[\"http://localhost:8080/image?imageName=file16089976814771018247287429_1608967303070_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089976814881019247287629_1608967303044_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089976814881020247287654_1608967303275_1024x1024.jpg\"]','245.00','开封市',1,'2020-12-26 23:48:22',1,24),(23,'国行在保SE2白色256G','国行SE/白色/256G/在保4个月左右，整机无拆无修，自用一切正常。\n换iPhone12mini后出，成色小花，屏幕有点刮痕，具体成色以实物图为准，系统14.2，电池94%，有包装盒、充电器、数据线，南沙区可面交，最好省内，顺丰到付。\n卖￥2488 ','[\"http://localhost:8080/image?imageName=file16089978361271021247267876_1608955400192_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089978399751022247266548_1608955405482_1024x1024.jpg\"]','2488.00','广州市',1,'2020-12-26 23:50:49',1,1),(24,'苹果AiR笔记本电脑 3代i5处理器','配置i5三代+4G+128G固态硬盘\nHD4000集成高性能显卡，成色整体有9成新，具体看图定义','[\"http://localhost:8080/image?imageName=file16089981005181023246491890_1608532396883_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089981076361024246491839_1608532395497_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089981076371025246491867_1608532394164_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089981076791026246491915_1608532392983_1024x1024.jpg\"]','1600.00','大同市',1,'2020-12-26 23:55:39',1,26),(25,'4年多小米平衡车','小米平衡车购于京东，下面有购物记录，几乎没有用，8成新  电池不行了满电骑8公里不到  新的时候可以25公里  发票找不到了 16年买的','[\"http://localhost:8080/image?imageName=file16089983607681027242349238_1606282819973_1024x1024.png\",\"http://localhost:8080/image?imageName=file16089983658011028242349211_1606282835583_1024x1024.png\"]','400.00','青岛市',3,'2020-12-26 23:59:34',1,26),(26,'电动单车','今年四月过来 广州换的电池  电池百分百没问题，充满电可以跑30公里    上班到住的地方10公里左右的样子，可以跑三次。  充电口换电池里修车老板把充电口接出来了说电池大了点装不下。有能力的自己可以回去。还有就是喇叭坏了。','[\"http://localhost:8080/image?imageName=file16089985305781029240601903_1605367165490_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089985305791030240601879_1605367164042_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089985306221031240601867_1605367162632_1024x1024.jpg\"]','500.00','遵义市',3,'2020-12-27 00:02:18',1,24),(27,'百搭翡丽 万年历自动机械表','规格：43MM\n瑞士机芯\n带日历、星期、生活防水、防震、防磁;\n316L精钢表壳、真皮表带，蓝宝石高密度镜面','[\"http://localhost:8080/image?imageName=file16089986478091032242878991_1606547744160_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089986530121033242878963_1606547742968_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089986530411034242878897_1606547708802_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089986530651035242878920_1606547741367_1024x1024.jpg\"]','480.00','石家庄市',5,'2020-12-27 00:04:39',1,23),(28,'闲置羽绒轻薄风衣一件','闲置羽绒轻薄风衣一件，很轻买回来没穿几次，规则如图XL加大','[\"http://localhost:8080/image?imageName=file16089988154031036194028382_1582442289410_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089988225761037194028067_1582442300485_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16089988226041038194028284_1582442292072_1024x1024.jpg\"]','50.50','广州市',5,'2020-12-27 00:07:28',2,24),(29,'空中英语教室带MP3光盘','空中英语教室 2019年12月 带MP3光盘 适用于中级英语学习者 附练习题 附练习题\n全新\n未拆封，未使用，重0.16公斤','[\"http://localhost:8080/image?imageName=file160912638638710100271921a66888191a0_b.jpg\",\"http://localhost:8080/image?imageName=file160912638638710110271921adeb4187212_b.jpg\"]','30.00','珠海市',4,'2020-12-28 11:33:11',1,26),(30,'游戏世界','游戏世界 2001年8月 2001年10月 2001年12月 2002年5月 2002年7月 2003年1月 2003年2月 2003年3月 8本合售','[\"http://localhost:8080/image?imageName=file16091264305151012025f38eb92860b20dd_b.jpg\"]','40.00','洛阳市',4,'2020-12-28 11:34:05',1,27),(31,'contax g2全套','康泰时Contax G2D全套配件关联contax t3 美能达Tc1标价非卖价 12000全套135系统中唯一一个能跟leica M对抗的contax G系统，全套蔡司镜头代表着最顶尖的光学素质，加上鼎盛时期的京瓷科技加成，组成了自动对焦，自动过片，每秒4张连拍，自动调整取景器大小，电子测距测光等强大功能矩阵。G2D机身+G45+G90+一堆配件+送雅西卡28-80自用机，退坑出，真正意义上的大全套，所有稀有配件齐全。所有配件不单出，镜头可以全要也可以2选1，机身未出前不单出镜头。顺丰保价到付，签收不退。\n以下列出价格方便算价。\nCONTAX G2 机身，有几处小磨损已拍出，右肩屏轻微漏液不影响读数。4000元CONTAX G45 95新 (最后期95编号，镀膜颜色不同）2500元CONTAX G90 97新1100元CONTAX 原厂皮套3支，滤镜2枚，遮光罩1个，肩带1条 镜头说明书2份 打包500元CONTAX GA-1 YC口转G口原厂转接环800元CONTAX GP-1 原厂外接电池组，需配合电池包300元CONTAX P-8 原厂电池包100元CONTAX GD-2 G2专用数据后背，可打印光圈快门信息，支持任意年限(不仅限19年)1500元CONTAX TLA200 闪光灯1100元CONTAX Cable switch S 快门线20元CONTAX Cable switch L 快门线100元雅西卡28-80 yc口150元','[\"http://localhost:8080/image?imageName=file1609126661364101369690_06624104341655527_1280.jpg\",\"http://localhost:8080/image?imageName=file1609126664593101469690_06624104505862917_1280.jpg\"]','1200.00','唐山市',1,'2020-12-28 11:38:16',1,29),(32,'萨拿DV 8/3+3 SB液压云台 + 炭纤维三脚架','实用成色。以前电视台用的。除了手柄不是原装的。其他全部原装正品。\n\n没有快装板。云台液压功能正常（水平灯不会亮了）。三脚架功能正常（脚架底拖有一条破损了、现在用铁丝连接着可以使用、有能力的朋友可以想办法修复修复）。','[\"http://localhost:8080/image?imageName=file1609127083830101520201117151410_28926.jpg\",\"http://localhost:8080/image?imageName=file1609127086684101620201117151414_80554.jpg\"]','3500.00','青岛市',3,'2020-12-28 11:45:22',1,30),(33,'尼康官方双肩摄影包','尼康官网奖品双肩摄影包，之前尼康官网中奖得到的，自己一直没用过，一直放在柜子里\n\n背部内衬和肩带衬垫都很厚实，储物格非常多，两个内胆都可以自由组合中间的格子，内胆还是可以完全取出来的，220全国包邮，成色定义为微灰吧','[\"http://localhost:8080/image?imageName=file1609127210920101769402_06034982356930348_1280.jpg\",\"http://localhost:8080/image?imageName=file1609127211169101869402_06034982343038606_1280.jpg\",\"http://localhost:8080/image?imageName=file1609127211180101969402_06034982376447926_1280.jpg\"]','220.00','邢台市',5,'2020-12-28 11:46:54',1,31),(34,'便宜出超薄电子竞技27英寸无边框显示器','大水牛超薄电子竞技27英寸无边框显示器，铝合金中框，全A屏，九九成新，1920*1080分辨率 VGA HDMI接口 \n液晶介绍链接：http://liaoning.qd256.com/sj/25605704/prod/113676.256','[\"http://localhost:8080/image?imageName=file16091274353481020247274062_1608959287000_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16091274395741021247274063_1608959308244_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16091274395781022247274082_1608959297734_1024x1024.jpg\"]','1300.00','深圳市',2,'2020-12-28 11:50:45',2,23),(35,'联想ThinkPad L440，i5四代i5-4300M，集','联想L440\ni5四代标压 i5-4300M @3000MHz，\nCPU是可以换的\n14英寸，1366x768\nVGA，Mini-DP高清，双显卡\nIntel GMA HD 4600 集显\nNVIDIA GeForce GT 720M 独立显卡\n左上边一笔头大小淡光斑，\n开机黑时看到，使用看不到\n用DDR3L内存，无内存，有两条内存槽\n有NGFF口可另加固态，用SATA硬盘，无硬盘\n3×USB2.0+1×USB3.0\n散热口几条小栏已断\n英文键盘，少油\n有外置电池已坏了，有电源\n这机性能很不错\n\n空机（主机，电池，电源）830元','[\"http://localhost:8080/image?imageName=file16091275555121023247488590_1609121452957_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16091275606191024247489052_1609121451670_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16091275606301025247489043_1609121452467_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16091275606401026247488591_1609121457638_1024x1024.jpg\"]','830.00','香港特别行政区',1,'2020-12-28 11:52:46',2,24),(36,'64G金色全网通6SP，电池饱满耐用，指纹灵敏','金色6SP，全网3网4G，容量64G的，4月份左右换的大容量电池，目前容量100%，机器成色不做评价，后盖有掉漆，但是带套绝对对得起观众，机器在WiFi信号显示的那里有光斑，但是绝对不影响使用，2年了都没有扩大，其他没有任何问题，通话声音大，电池耐用，WiFi信号强，指纹灵敏，爱思除了电池之外全绿，备用绝对一流','[\"http://localhost:8080/image?imageName=file16091277000561027247489042_1609121448746_1024x1024.jpg\",\"http://localhost:8080/image?imageName=file16091277058791028247488589_1609121451914_1024x1024.jpg\"]','800.00','台北市',1,'2020-12-28 11:55:09',0,1);

/*Table structure for table `sh_message` */

DROP TABLE IF EXISTS `sh_message`;

CREATE TABLE `sh_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `idle_id` bigint(20) NOT NULL COMMENT '闲置主键id',
  `content` varchar(256) NOT NULL COMMENT '留言内容',
  `create_time` datetime NOT NULL COMMENT '留言时间',
  `to_user` bigint(20) NOT NULL COMMENT '所回复的用户',
  `to_message` bigint(20) DEFAULT NULL COMMENT '所回复的留言',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`),
  KEY `idle_id_index` (`idle_id`),
  KEY `to_user_index` (`to_user`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_message` */

insert  into `sh_message`(`id`,`user_id`,`idle_id`,`content`,`create_time`,`to_user`,`to_message`) values (1,26,26,'啦啦啦啦啦啦','2020-12-27 12:02:12',24,NULL),(2,26,26,'来了来了','2020-12-27 12:07:18',24,NULL),(3,1,26,'哈哈哈哈哈哈哈哈哈哈哈','2020-12-27 12:34:16',26,2),(4,1,26,'啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦\n啦啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊','2020-12-27 13:50:42',24,NULL),(6,1,26,'啊啊啊啊啊啊\n阿凡达发','2020-12-27 13:53:53',26,1),(7,1,26,'gggggggg','2020-12-27 14:00:39',24,NULL),(9,1,26,'aaaaaaa','2020-12-27 14:01:01',24,NULL),(11,1,26,'11111111111111111111\n22222222\n3\n25\n56','2020-12-27 14:02:54',24,NULL),(12,1,26,'fagraeg','2020-12-27 14:03:22',24,NULL),(13,1,26,'garyhery','2020-12-27 14:05:51',24,NULL),(14,1,26,'thtsjysutyere','2020-12-27 14:06:29',24,NULL),(15,1,26,'fsdagfrtgr','2020-12-27 14:09:00',24,NULL),(16,1,26,'tgrgarg','2020-12-27 14:09:07',24,NULL),(17,1,26,'的发射点发射点','2020-12-27 14:10:29',24,NULL),(18,1,26,'gagrtgataeryhratw','2020-12-27 14:10:33',24,NULL),(19,1,26,'发给大哥虽然他<br>更是让她以后他更是<br>gs','2020-12-27 14:10:41',24,NULL),(20,1,26,'fgretretretgr<br>ragr<br><br>rgarg','2020-12-27 14:11:01',26,2),(21,1,25,'买买买','2020-12-27 14:13:50',26,NULL),(22,26,26,'吼吼吼吼吼吼吼吼吼吼吼吼吼吼吼','2020-12-27 14:30:16',1,20),(23,1,22,'11515215415151<br>uiiku]<br>hikuhik','2020-12-27 16:00:15',24,NULL),(25,1,19,'11111','2020-12-27 16:13:19',24,NULL),(26,1,33,'买买买','2020-12-28 23:11:16',31,NULL),(27,26,36,'真不错','2020-12-28 23:15:29',1,NULL),(28,30,36,'发什么快递','2020-12-28 23:16:27',1,NULL),(29,30,34,'真不错，屏幕够大','2020-12-29 13:03:20',23,NULL),(30,1,34,'我也得这款不错<br>买它','2020-12-29 13:04:34',30,29),(31,26,32,'最高多高<br>感觉不错','2020-12-29 14:07:09',30,NULL),(32,1,34,'efe<br>dsvd<br><br>vd','2020-12-29 14:37:23',23,NULL),(33,1,34,'jljfsldjf','2020-12-29 14:37:32',30,29);

/*Table structure for table `sh_order` */

DROP TABLE IF EXISTS `sh_order`;

CREATE TABLE `sh_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `order_number` varchar(32) NOT NULL COMMENT '订单编号',
  `user_id` bigint(20) NOT NULL COMMENT '用户主键id',
  `idle_id` bigint(20) NOT NULL COMMENT '闲置物品主键id',
  `order_price` decimal(10,2) NOT NULL COMMENT '订单总价',
  `payment_status` tinyint(4) NOT NULL COMMENT '支付状态',
  `payment_way` varchar(16) DEFAULT NULL COMMENT '支付方式',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `order_status` tinyint(4) NOT NULL COMMENT '订单状态',
  `is_deleted` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_order` */

insert  into `sh_order`(`id`,`order_number`,`user_id`,`idle_id`,`order_price`,`payment_status`,`payment_way`,`create_time`,`payment_time`,`order_status`,`is_deleted`) values (17,'160899911486210002',1,27,'480.00',1,'支付宝','2020-12-27 00:11:55','2020-12-27 00:13:29',3,NULL),(18,'160899933354610003',1,20,'200.00',1,'支付宝','2020-12-27 00:15:34','2020-12-27 00:26:03',1,NULL),(19,'160899987794110004',1,22,'245.00',1,'支付宝','2020-12-27 00:24:38','2020-12-27 00:25:12',1,NULL),(20,'160899992907110005',23,23,'2488.00',1,'支付宝','2020-12-27 00:25:29','2020-12-29 12:54:16',1,NULL),(21,'160900015733710006',1,22,'245.00',0,NULL,'2020-12-27 00:29:17',NULL,4,NULL),(22,'160900353089610002',1,17,'700.00',1,'支付宝','2020-12-27 01:25:31','2020-12-27 01:28:07',2,NULL),(29,'160900425303010003',26,23,'2488.00',0,NULL,'2020-12-27 01:37:33',NULL,4,NULL),(30,'160905682411510002',1,19,'650.00',0,NULL,'2020-12-27 16:13:44',NULL,4,NULL),(31,'160905688371110003',1,17,'700.00',1,'支付宝','2020-12-27 16:14:44','2020-12-27 16:14:50',3,NULL),(32,'160915228608010002',1,34,'1300.00',1,'支付宝','2020-12-28 18:44:46','2020-12-28 18:44:53',1,NULL),(33,'160916879872410002',1,35,'830.00',1,'支付宝','2020-12-28 23:19:59','2020-12-28 23:27:59',3,NULL),(34,'160922203401910002',26,32,'3500.00',0,NULL,'2020-12-29 14:07:14',NULL,1,NULL),(35,'160922254193410003',30,36,'800.00',0,NULL,'2020-12-29 14:15:42',NULL,1,NULL),(36,'160922392165710004',1,34,'1300.00',1,'支付宝','2020-12-29 14:38:42','2020-12-29 14:39:16',1,NULL),(37,'160922400241710005',1,33,'220.00',0,NULL,'2020-12-29 14:40:02',NULL,4,NULL),(38,'160922401497810006',26,30,'40.00',0,NULL,'2020-12-29 14:40:15',NULL,1,NULL),(39,'161176742128710002',1,21,'60.00',0,NULL,'2021-01-28 01:10:21',NULL,4,NULL),(40,'161176806763710002',1,25,'400.00',0,NULL,'2021-01-28 01:21:08',NULL,4,NULL),(41,'161176813492210002',1,25,'400.00',0,NULL,'2021-01-28 01:22:15',NULL,4,NULL),(42,'161176813970810003',1,27,'480.00',0,NULL,'2021-01-28 01:22:20',NULL,4,NULL),(43,'161176894549212786',1,27,'480.00',0,NULL,'2021-01-28 01:35:45',NULL,4,NULL),(44,'161176899634813427',1,28,'50.50',0,NULL,'2021-01-28 01:36:36',NULL,4,NULL),(45,'161176926581710002',1,31,'1200.00',0,NULL,'2021-01-28 01:41:06',NULL,1,NULL),(46,'161176927724210003',1,22,'245.00',0,NULL,'2021-01-28 01:41:17',NULL,1,NULL),(47,'161179895658410002',1,20,'200.00',0,NULL,'2021-01-28 09:55:57',NULL,1,NULL),(48,'161286181342710002',1,27,'480.00',0,NULL,'2021-02-09 17:10:13',NULL,4,NULL),(54,'161287569646810003',1,28,'50.50',1,'支付宝','2021-02-09 21:01:36','2021-02-09 21:01:39',1,NULL);

/*Table structure for table `sh_order_address` */

DROP TABLE IF EXISTS `sh_order_address`;

CREATE TABLE `sh_order_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `order_id` bigint(20) NOT NULL COMMENT '订单id',
  `consignee_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人',
  `consignee_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `detail_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderId` (`order_id`),
  KEY `order_id_index` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_order_address` */

insert  into `sh_order_address`(`id`,`order_id`,`consignee_name`,`consignee_phone`,`detail_address`) values (12,17,'shiny','17322611234','广东省佛山市禅城区江湾一路1号'),(13,18,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(14,19,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(15,20,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(16,21,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(17,22,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(18,28,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(19,29,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(20,30,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(21,31,'hhhh','17322610001','广东省佛山市禅城区江湾一路2号'),(22,32,'shiny','17322611234','广东省佛山市禅城区江湾一路1号'),(23,33,'shiny','17322611234','广东省佛山市禅城区江湾一路1号'),(24,36,'shiny','17322611234','广东省佛山市禅城区江湾一路1号'),(25,37,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(26,39,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(27,40,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(28,41,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(29,42,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(30,43,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(31,44,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(32,45,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(33,46,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(34,47,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(35,48,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(36,49,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(37,50,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(38,51,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(39,52,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(40,53,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd'),(41,54,'lll','17322612356','山东省淄博市博山区fdsafsdfsdfsd');

/*Table structure for table `sh_user` */

DROP TABLE IF EXISTS `sh_user`;

CREATE TABLE `sh_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `account_number` varchar(16) NOT NULL COMMENT '账号（手机号）',
  `user_password` varchar(16) NOT NULL COMMENT '登录密码',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `avatar` varchar(256) NOT NULL COMMENT '头像',
  `sign_in_time` datetime NOT NULL COMMENT '注册时间',
  `user_status` tinyint(4) DEFAULT NULL COMMENT '状态（1代表封禁）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `sh_user` */

insert  into `sh_user`(`id`,`account_number`,`user_password`,`nickname`,`avatar`,`sign_in_time`,`user_status`) values (1,'17322611234','123456','MYL','http://localhost:8080/image?imageName=file16089942542411002profile.png','2020-12-17 10:49:01',0),(23,'17322611235','123456','u1235','http://localhost:8080/image?imageName=file16091261422401005touxiang5.jpg','2020-12-26 22:47:19',NULL),(24,'17322611236','123456','u1236','http://localhost:8080/image?imageName=file16091261613141006touxiang6.png','2020-12-26 22:47:30',NULL),(26,'17322611237','123456','u1237','http://localhost:8080/image?imageName=file16091261795071007touxiang7.jpg','2020-12-26 22:53:15',NULL),(27,'17322611238','123456','u1238','http://localhost:8080/image?imageName=file16091261946991008touxiang8.jpg','2020-12-28 11:21:50',NULL),(28,'17322611239','123456','u1239','http://localhost:8080/image?imageName=file16091262081901009touxiang9.jpg','2020-12-28 11:22:33',NULL),(29,'17322611230','123456','u1230','http://localhost:8080/image?imageName=file16091259892131002touxiang10.jpg','2020-12-28 11:23:15',NULL),(30,'17322611231','123456','u1231','http://localhost:8080/image?imageName=file16091260392671003touxiang1.jpg','2020-12-28 11:23:36',1),(31,'17322611232','123456','u1232','http://localhost:8080/image?imageName=file16091261011671004touxiang2.jpg','2020-12-28 11:24:04',1),(33,'17322611240','123456','u1240','http://localhost:8080/image?imageName=file16089942542411002profile.png','2020-12-17 10:49:01',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
