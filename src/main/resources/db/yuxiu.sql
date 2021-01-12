/*
 Navicat MySQL Data Transfer

 Source Server         : yuxiu
 Source Server Type    : MySQL
 Source Server Version : 50635
 Source Host           : 114.55.96.162:3306
 Source Schema         : yuxiu

 Target Server Type    : MySQL
 Target Server Version : 50635
 File Encoding         : 65001

 Date: 15/09/2020 16:14:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_dealer_user_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_dealer_user_log`;
CREATE TABLE `tb_dealer_user_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `buy_user_id` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '折扣',
  `total_price` decimal(11, 2) NULL DEFAULT NULL COMMENT '总金额',
  `coupon_money` decimal(11, 2) NULL DEFAULT NULL COMMENT '用户优惠金额',
  `rebate_money` decimal(11, 2) NULL DEFAULT NULL COMMENT '推广员返利金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_account_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_account_log`;
CREATE TABLE `ty_account_log`  (
  `log_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` mediumint(8) UNSIGNED NOT NULL,
  `user_money` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '用户金额',
  `frozen_money` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '冻结金额',
  `pay_points` mediumint(9) NOT NULL DEFAULT 0 COMMENT '支付积分',
  `change_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '变动时间',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '描述',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_id` int(10) NULL DEFAULT NULL COMMENT '订单id',
  `integral_order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '返佣对象类型 1-用户 2-门店',
  `category` tinyint(1) NULL DEFAULT 1 COMMENT '积分来源 1.注册  2.签到  3.购物  4.分销  5.兑换 6.会员升级分销',
  `sign_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '签到id',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 313 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_account_log_store
-- ----------------------------
DROP TABLE IF EXISTS `ty_account_log_store`;
CREATE TABLE `ty_account_log_store`  (
  `log_id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_id` int(8) UNSIGNED NOT NULL,
  `store_money` decimal(10, 2) NOT NULL COMMENT '店铺金额',
  `pending_money` decimal(10, 2) NOT NULL COMMENT '店铺未结算金额',
  `change_time` int(10) UNSIGNED NOT NULL COMMENT '变动时间',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_id` int(10) NULL DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `user_id`(`store_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_account_log_suppliers
-- ----------------------------
DROP TABLE IF EXISTS `ty_account_log_suppliers`;
CREATE TABLE `ty_account_log_suppliers`  (
  `log_id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `suppliers_id` int(8) UNSIGNED NOT NULL,
  `supper_money` decimal(10, 2) NOT NULL COMMENT '店铺金额',
  `supper_frozen_money` decimal(10, 2) NOT NULL COMMENT '店铺未结算金额',
  `change_time` int(10) UNSIGNED NOT NULL COMMENT '变动时间',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_id` int(10) NULL DEFAULT NULL COMMENT '订单id',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `user_id`(`suppliers_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_activity
-- ----------------------------
DROP TABLE IF EXISTS `ty_activity`;
CREATE TABLE `ty_activity`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '促销活动名称',
  `type` int(2) NOT NULL DEFAULT 0 COMMENT '活动类型activity_type 表中type_id',
  `expression` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '优惠体现',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动描述',
  `start_time` int(11) NOT NULL COMMENT '活动开始时间',
  `end_time` int(11) NOT NULL COMMENT '活动结束时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '0待审核，1正常2拒绝3关闭 4-已结束 5-暂停',
  `group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用范围',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺id',
  `orderby` int(10) NULL DEFAULT 0 COMMENT '排序',
  `prom_img` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动宣传图片',
  `recommend` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告图',
  `is_del` tinyint(4) NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  `goods_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商品数量',
  `buy_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商品已购买数',
  `order_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '已下单人数',
  `goods_id` int(11) NULL DEFAULT 0 COMMENT '商品id',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '活动价格',
  `is_deal` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0-未定时任务处理 1-已定时任务处理',
  `type_id1` int(11) NULL DEFAULT 0 COMMENT '一级分类id',
  `type_id2` int(11) NULL DEFAULT 0 COMMENT '二级分类id',
  `type_id3` int(11) NULL DEFAULT 0 COMMENT '三级分类id',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '审核信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动管理表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_activity_type
-- ----------------------------
DROP TABLE IF EXISTS `ty_activity_type`;
CREATE TABLE `ty_activity_type`  (
  `type_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名字',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 0-正常 1-禁止',
  `sort` smallint(4) UNSIGNED NOT NULL DEFAULT 10 COMMENT '排序',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级id',
  `show_in_nav` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否导航栏显示 1-是 0-否',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型图片',
  `describe` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `introduce` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `parent_id_path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家族图谱',
  `level` tinyint(1) NULL DEFAULT 1 COMMENT '等级',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_ad
-- ----------------------------
DROP TABLE IF EXISTS `ty_ad`;
CREATE TABLE `ty_ad`  (
  `ad_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '广告id',
  `pid` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '广告位置ID',
  `media_type` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '广告类型',
  `ad_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告名称',
  `ad_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '链接地址',
  `ad_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址',
  `start_time` int(11) NOT NULL DEFAULT 0 COMMENT '投放时间',
  `end_time` int(11) NOT NULL DEFAULT 0 COMMENT '结束时间',
  `link_man` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '添加人',
  `link_email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '添加人邮箱',
  `link_phone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '添加人联系电话',
  `click_count` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点击量',
  `enabled` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示',
  `orderby` smallint(6) NULL DEFAULT 50 COMMENT '排序',
  `target` tinyint(1) NULL DEFAULT 0 COMMENT '是否开启浏览器新窗口',
  `bgcolor` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '背景颜色',
  PRIMARY KEY (`ad_id`) USING BTREE,
  INDEX `enabled`(`enabled`) USING BTREE,
  INDEX `position_id`(`pid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_ad_position
-- ----------------------------
DROP TABLE IF EXISTS `ty_ad_position`;
CREATE TABLE `ty_ad_position`  (
  `position_id` int(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `position_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告位置名称',
  `ad_width` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '广告位宽度',
  `ad_height` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '广告位高度',
  `position_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告描述',
  `position_style` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板',
  `is_open` tinyint(1) NULL DEFAULT 0 COMMENT '0关闭1开启',
  PRIMARY KEY (`position_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_admin
-- ----------------------------
DROP TABLE IF EXISTS `ty_admin`;
CREATE TABLE `ty_admin`  (
  `admin_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `ec_salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '秘钥',
  `add_time` int(11) NOT NULL DEFAULT 0,
  `last_login` int(11) NOT NULL DEFAULT 0,
  `last_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `nav_list` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `lang_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `agency_id` smallint(5) UNSIGNED NULL DEFAULT NULL,
  `suppliers_id` smallint(5) UNSIGNED NULL DEFAULT 0,
  `todolist` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `role_id` smallint(5) NULL DEFAULT NULL COMMENT '角色id',
  `is_lock` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否被锁定冻结 0：否 1：是',
  PRIMARY KEY (`admin_id`) USING BTREE,
  INDEX `user_name`(`user_name`) USING BTREE,
  INDEX `agency_id`(`agency_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_admin_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_admin_log`;
CREATE TABLE `ty_admin_log`  (
  `log_id` bigint(16) UNSIGNED NOT NULL AUTO_INCREMENT,
  `admin_id` int(10) NULL DEFAULT NULL,
  `log_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_time` int(10) NULL DEFAULT NULL,
  `log_type` tinyint(2) NULL DEFAULT 0 COMMENT '0默认1操作店铺2审核活动3处理投诉4其他',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 636 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `ty_admin_role`;
CREATE TABLE `ty_admin_role`  (
  `role_id` smallint(6) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `act_list` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限列表',
  `role_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `goods_is_check` tinyint(1) NULL DEFAULT 0 COMMENT '商品是否需要审核 0-不审核 1-审核',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_apply
-- ----------------------------
DROP TABLE IF EXISTS `ty_apply`;
CREATE TABLE `ty_apply`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT 0 COMMENT '申请人会员ID',
  `contacts_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `contacts_mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人手机',
  `contacts_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人邮箱',
  `company_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `company_type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '公司性质',
  `company_website` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司网址',
  `company_province` mediumint(8) NULL DEFAULT NULL COMMENT '公司所在省份',
  `company_city` mediumint(8) NULL DEFAULT NULL COMMENT '公司所在城市',
  `company_district` mediumint(8) NULL DEFAULT 0 COMMENT '公司所在地区',
  `company_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司详细地址',
  `company_telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定电话',
  `company_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `company_fax` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '传真',
  `company_zipcode` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `business_licence_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照注册号/统一社会信用代码',
  `business_licence_cert` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照电子版',
  `threeinone` tinyint(1) NULL DEFAULT 1 COMMENT '是否为一证一码商家',
  `reg_capital` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册资金',
  `legal_person` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人代表',
  `legal_identity_cert` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证照片',
  `legal_identity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证号',
  `business_date_start` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业执照有效期',
  `business_date_end` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `orgnization_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `orgnization_cert` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码证',
  `attached_tax_number` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `tax_rate` tinyint(2) NULL DEFAULT 0 COMMENT '纳税类型税码',
  `taxpayer` tinyint(1) NULL DEFAULT 1 COMMENT '纳税人类型',
  `taxpayer_cert` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '一般纳税人资格证书',
  `business_scope` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '营业执照经营范围',
  `store_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `seller_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家账号',
  `store_type` tinyint(1) NULL DEFAULT 0 COMMENT '店铺性质',
  `stroe_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_person_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺负责人姓名',
  `store_person_mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺负责人手机',
  `store_person_qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺联系人QQ',
  `store_person_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺负责人邮箱',
  `store_person_cert` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺负责人身份证照片',
  `store_person_identity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺负责人身份证号',
  `bank_account_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结算银行名称',
  `bank_account_number` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '结算银行账号',
  `bank_branch_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行支行名称',
  `bank_province` smallint(6) UNSIGNED NULL DEFAULT NULL COMMENT '开户银行支行所在地',
  `bank_city` smallint(6) NULL DEFAULT NULL COMMENT '开户银行支行所在城市',
  `main_channel` tinyint(1) NULL DEFAULT 0 COMMENT '近一年主营渠道',
  `sales_volume` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '近一年销售额',
  `sku_num` mediumint(8) NULL DEFAULT 1 COMMENT '可网售商品数量',
  `ec_experience` tinyint(1) NULL DEFAULT 0 COMMENT '同类电子商务网站经验',
  `avg_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '预计平均客单价',
  `ware_house` tinyint(1) NULL DEFAULT 0 COMMENT '仓库情况',
  `entity_shop` tinyint(1) NULL DEFAULT 0 COMMENT '有无实体店',
  `sc_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺分类名称',
  `sc_id` int(10) NULL DEFAULT NULL COMMENT '店铺分类编号',
  `sc_bail` mediumint(8) NULL DEFAULT 0 COMMENT '店铺分类保证金',
  `sg_id` tinyint(2) NULL DEFAULT NULL COMMENT '店铺等级编号',
  `sg_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺等级名称',
  `store_class_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '申请分类佣金信息',
  `paying_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '付款金额',
  `paying_amount_cert` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款凭证',
  `apply_state` tinyint(2) NULL DEFAULT 0 COMMENT '店铺申请状态',
  `review_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员审核信息',
  `add_time` int(11) NULL DEFAULT 0 COMMENT '提交申请时间',
  `apply_type` tinyint(1) NULL DEFAULT 0 COMMENT '申请类型默认0企业1个人',
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '店铺id',
  `card1_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片1名字',
  `card1_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `card2_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片2名字',
  `legal_identity_cert1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法人身份证反面',
  `card2_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `class_id` int(11) NULL DEFAULT NULL COMMENT '主营类目id  与ty_class关联',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '真实姓名',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '身份证号码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_apply_review
-- ----------------------------
DROP TABLE IF EXISTS `ty_apply_review`;
CREATE TABLE `ty_apply_review`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `apply_id` int(11) UNSIGNED NOT NULL COMMENT '审核id',
  `admin_id` int(11) NOT NULL COMMENT '审核者id',
  `status` tinyint(1) NOT NULL COMMENT '审核状态 0-审核中 1-审核成功 2-审核失败',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核备注',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '审核记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_area_region
-- ----------------------------
DROP TABLE IF EXISTS `ty_area_region`;
CREATE TABLE `ty_area_region`  (
  `shipping_area_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '物流配置id',
  `region_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '地区id对应region表id',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺id',
  PRIMARY KEY (`shipping_area_id`, `region_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_article
-- ----------------------------
DROP TABLE IF EXISTS `ty_article`;
CREATE TABLE `ty_article`  (
  `article_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cat_id` smallint(5) NOT NULL DEFAULT 0 COMMENT '类别ID',
  `cat_id1` int(11) NULL DEFAULT NULL,
  `cat_id_arr` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `title` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文章标题',
  `en_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章内容',
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '文章作者',
  `author_email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '作者邮箱',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '关键字',
  `article_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 2,
  `is_open` tinyint(1) UNSIGNED NOT NULL DEFAULT 1,
  `add_time` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `file_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '附件地址',
  `open_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '链接地址',
  `description` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '文章摘要',
  `click` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `publish_time` int(11) NULL DEFAULT NULL COMMENT '文章预告发布时间',
  `thumb` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '文章缩略图',
  `sub_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tag_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `images` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `sort` smallint(4) NULL DEFAULT 10 COMMENT '排序',
  `is_recommend` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否推荐 0-否 1-是',
  `recommend_sort` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '推荐排序',
  `read_num` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '阅读量',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `cat_id`(`cat_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_article_cat
-- ----------------------------
DROP TABLE IF EXISTS `ty_article_cat`;
CREATE TABLE `ty_article_cat`  (
  `cat_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cat_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  `cat_type` smallint(6) NULL DEFAULT 0 COMMENT '默认分组',
  `parent_id` smallint(6) NULL DEFAULT NULL COMMENT '夫级ID',
  `show_in_nav` tinyint(1) NULL DEFAULT 0 COMMENT '是否导航显示',
  `sort_order` smallint(6) NULL DEFAULT 50 COMMENT '排序',
  `cat_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类描述',
  `keywords` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '搜索关键词',
  `cat_alias` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  PRIMARY KEY (`cat_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_article_comment
-- ----------------------------
DROP TABLE IF EXISTS `ty_article_comment`;
CREATE TABLE `ty_article_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `article_id` int(11) NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `add_time` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_article_content
-- ----------------------------
DROP TABLE IF EXISTS `ty_article_content`;
CREATE TABLE `ty_article_content`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL,
  `class_id` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_bank
-- ----------------------------
DROP TABLE IF EXISTS `ty_bank`;
CREATE TABLE `ty_bank`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `bank_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡号',
  `bank_id` int(10) NOT NULL COMMENT '银行id 关联 bank_list表',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行名',
  `opening_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户行名',
  `account_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '开户名',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '预留手机号',
  `edit_time` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '1 删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_bank_list
-- ----------------------------
DROP TABLE IF EXISTS `ty_bank_list`;
CREATE TABLE `ty_bank_list`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行名称',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '1 已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_banner
-- ----------------------------
DROP TABLE IF EXISTS `ty_banner`;
CREATE TABLE `ty_banner`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片',
  `goods_ids` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '商品id,多个商品用,隔开',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述',
  `create_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT ' 0-正常 1-禁用',
  `sort` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '排序',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '多级广告页面表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_banner_goods
-- ----------------------------
DROP TABLE IF EXISTS `ty_banner_goods`;
CREATE TABLE `ty_banner_goods`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `banner_id` int(11) UNSIGNED NOT NULL COMMENT 'banner表中id',
  `goods_id` int(11) UNSIGNED NOT NULL COMMENT '商品id',
  `sort` int(11) NULL DEFAULT 10 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '广告商品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_brand
-- ----------------------------
DROP TABLE IF EXISTS `ty_brand`;
CREATE TABLE `ty_brand`  (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '品牌表',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '品牌名称',
  `logo` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '品牌logo',
  `desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌描述',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '品牌地址',
  `sort` tinyint(3) UNSIGNED NOT NULL DEFAULT 50 COMMENT '排序',
  `cat_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '品牌分类',
  `cat_id1` int(11) NULL DEFAULT 0 COMMENT '一级分类id',
  `cat_id2` int(10) NULL DEFAULT 0 COMMENT '二级分类id',
  `cat_id3` int(11) NULL DEFAULT 0 COMMENT '三级分类id',
  `is_hot` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家ID',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0正常 1审核中 2审核失败 审核状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_brand_type
-- ----------------------------
DROP TABLE IF EXISTS `ty_brand_type`;
CREATE TABLE `ty_brand_type`  (
  `type_id` int(10) UNSIGNED NOT NULL COMMENT '类型id',
  `brand_id` int(10) UNSIGNED NOT NULL COMMENT '品牌id'
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类型与品牌对应表' ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_browse
-- ----------------------------
DROP TABLE IF EXISTS `ty_browse`;
CREATE TABLE `ty_browse`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `add_time` int(10) NULL DEFAULT NULL COMMENT '添加时间',
  `browse_times` int(11) NULL DEFAULT NULL COMMENT '浏览次数',
  `edit_time` int(11) NULL DEFAULT 0 COMMENT '修改时间',
  `today_browse` int(11) NULL DEFAULT 0 COMMENT '今日人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '浏览表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_business_return_records
-- ----------------------------
DROP TABLE IF EXISTS `ty_business_return_records`;
CREATE TABLE `ty_business_return_records`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '状态0:未处理1:处理中2:审核通过3拒绝审核4已发快递5已收到快递6平台介入8退款完成9撤销审核',
  `order_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '订单号',
  `operation_time` int(11) NULL DEFAULT NULL COMMENT '操作时间',
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '1退款2退款退货3换货',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '处理备注',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '退货操作记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_cart
-- ----------------------------
DROP TABLE IF EXISTS `ty_cart`;
CREATE TABLE `ty_cart`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购物车表',
  `user_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `session_id` char(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'session',
  `goods_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `goods_sn` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品货号',
  `goods_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `market_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '市场价',
  `goods_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '本店价',
  `member_goods_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '会员折扣价',
  `goods_num` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '购买数量',
  `spec_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品规格key 对应ty_spec_goods_price 表',
  `spec_key_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品规格组合名称',
  `bar_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品条码',
  `selected` tinyint(1) NULL DEFAULT 1 COMMENT '购物车选中状态',
  `add_time` int(11) NULL DEFAULT 0 COMMENT '加入购物车的时间',
  `prom_type` tinyint(1) NULL DEFAULT 0 COMMENT '0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠',
  `prom_id` int(11) NULL DEFAULT 0 COMMENT '活动id',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'sku',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `buy_user_id` int(11) NULL DEFAULT 0,
  `order_buy_user_id` int(11) NULL DEFAULT 0 COMMENT '套餐购买',
  `num` int(11) NULL DEFAULT 0 COMMENT '套餐数量',
  `activity_type` int(11) NULL DEFAULT 0 COMMENT '首页活动类型',
  `activity_id` int(11) NULL DEFAULT 0 COMMENT '首页活动id',
  `partner_price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '合伙人拿货价',
  `package_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '套餐id',
  `sgp_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '规格所在id 对应ty_spec_goods_price 表',
  `product_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '作品id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `session_id`(`session_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2456 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_chat_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_chat_log`;
CREATE TABLE `ty_chat_log`  (
  `m_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `f_id` int(10) UNSIGNED NOT NULL COMMENT '会员ID',
  `f_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员名',
  `f_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发自IP',
  `t_id` int(10) UNSIGNED NOT NULL COMMENT '接收会员ID',
  `t_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收会员名',
  `t_msg` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `add_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '添加时间',
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_chat_msg
-- ----------------------------
DROP TABLE IF EXISTS `ty_chat_msg`;
CREATE TABLE `ty_chat_msg`  (
  `m_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `f_id` int(10) UNSIGNED NOT NULL COMMENT '会员ID',
  `f_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员名',
  `f_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发自IP',
  `t_id` int(10) UNSIGNED NOT NULL COMMENT '接收会员ID',
  `t_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收会员名',
  `t_msg` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息内容',
  `r_state` tinyint(1) UNSIGNED NULL DEFAULT 2 COMMENT '状态:1为已读,2为未读,默认为2',
  `add_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '添加时间',
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_class
-- ----------------------------
DROP TABLE IF EXISTS `ty_class`;
CREATE TABLE `ty_class`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `class_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主营类目名字',
  `sort` int(11) UNSIGNED NULL DEFAULT 10 COMMENT '排序',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0-禁用 1-正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺主营类目' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_comment
-- ----------------------------
DROP TABLE IF EXISTS `ty_comment`;
CREATE TABLE `ty_comment`  (
  `comment_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id自增',
  `goods_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `order_id` mediumint(8) NOT NULL COMMENT '订单id',
  `store_id` int(10) NOT NULL DEFAULT 0 COMMENT '店铺id',
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `add_time` int(10) UNSIGNED NOT NULL COMMENT '评论时间',
  `ip_address` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论ip地址',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示;0:不显示；1:显示',
  `img` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '晒单图片',
  `spec_key_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `goods_rank` int(2) NOT NULL DEFAULT 0 COMMENT '商品评价等级 0~5',
  `zan_num` int(10) NOT NULL,
  `zan_userid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `reply_num` int(10) NOT NULL COMMENT '评论回复数',
  `is_anonymous` tinyint(1) NULL DEFAULT 0 COMMENT '是否匿名评价0:是；1不是',
  `impression` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '印象标签',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `parent_id` int(11) UNSIGNED ZEROFILL NOT NULL,
  `admin_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `id_value`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_complain
-- ----------------------------
DROP TABLE IF EXISTS `ty_complain`;
CREATE TABLE `ty_complain`  (
  `complain_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `order_goods_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '订单商品ID',
  `accuser_id` int(11) NOT NULL COMMENT '原告id',
  `accuser_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原告名称',
  `accused_id` int(11) NOT NULL COMMENT '被告id',
  `accused_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '被告名称',
  `complain_subject_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉主题',
  `complain_subject_id` int(11) NOT NULL COMMENT '投诉主题id',
  `complain_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉内容',
  `complain_pic1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉图片1',
  `complain_pic2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉图片2',
  `complain_pic3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉图片3',
  `complain_datetime` int(11) NOT NULL COMMENT '投诉时间',
  `complain_handle_datetime` int(11) NOT NULL COMMENT '投诉处理时间',
  `complain_handle_member_id` int(11) NOT NULL COMMENT '投诉处理人id',
  `appeal_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申诉内容',
  `appeal_datetime` int(11) NOT NULL COMMENT '申诉时间',
  `appeal_pic1` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申诉图片1',
  `appeal_pic2` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申诉图片2',
  `appeal_pic3` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申诉图片3',
  `final_handle_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最终处理意见',
  `final_handle_datetime` int(11) NOT NULL COMMENT '最终处理时间',
  `final_handle_user_id` int(11) NOT NULL COMMENT '最终处理人id',
  `complain_state` tinyint(4) NOT NULL COMMENT '投诉状态(10-新投诉/20-投诉通过转给被投诉人/30-被投诉人已申诉/40-提交仲裁/99-已关闭)',
  `complain_active` tinyint(4) NOT NULL DEFAULT 1 COMMENT '投诉是否通过平台审批(1未通过/2通过)',
  PRIMARY KEY (`complain_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_complain_subject
-- ----------------------------
DROP TABLE IF EXISTS `ty_complain_subject`;
CREATE TABLE `ty_complain_subject`  (
  `subject_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉主题id',
  `subject_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉主题',
  `subject_desc` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '投诉主题描述',
  `subject_state` tinyint(4) NOT NULL DEFAULT 1 COMMENT '投诉主题状态(1-有效/2-失效)',
  PRIMARY KEY (`subject_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉主题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_complain_talk
-- ----------------------------
DROP TABLE IF EXISTS `ty_complain_talk`;
CREATE TABLE `ty_complain_talk`  (
  `talk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉对话id',
  `complain_id` int(11) NOT NULL COMMENT '投诉id',
  `talk_member_id` int(11) NOT NULL COMMENT '发言人id',
  `talk_member_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发言人名称',
  `talk_member_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发言人类型(1-投诉人/2-被投诉人/3-平台)',
  `talk_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发言内容',
  `talk_state` tinyint(4) NOT NULL COMMENT '发言状态(1-显示/2-不显示)',
  `talk_admin` int(11) NOT NULL DEFAULT 0 COMMENT '对话管理员，屏蔽对话人的id',
  `talk_time` int(11) NOT NULL COMMENT '对话发表时间',
  PRIMARY KEY (`talk_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '投诉对话表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_config
-- ----------------------------
DROP TABLE IF EXISTS `ty_config`;
CREATE TABLE `ty_config`  (
  `id` smallint(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inc_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 144 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_config_copy
-- ----------------------------
DROP TABLE IF EXISTS `ty_config_copy`;
CREATE TABLE `ty_config_copy`  (
  `id` smallint(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `value` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `inc_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 131 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_config_designer
-- ----------------------------
DROP TABLE IF EXISTS `ty_config_designer`;
CREATE TABLE `ty_config_designer`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1 作品发布 2 作品购买 3作品点赞，4设计师关注 5关注人数',
  `person` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '人数',
  `point` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '赠送分值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设计师升级规则' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_country
-- ----------------------------
DROP TABLE IF EXISTS `ty_country`;
CREATE TABLE `ty_country`  (
  `id` int(11) NOT NULL COMMENT '国家编号',
  `name_cn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `name_en` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `show_cn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '显示的中文名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '国家数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_coupon
-- ----------------------------
DROP TABLE IF EXISTS `ty_coupon`;
CREATE TABLE `ty_coupon`  (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠券名字',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '发放类型 0面额模板1 按用户发放 2 注册 3 邀请 4 线下发放 5 分享朋友圈发放 6-会员等级发放',
  `money` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠券金额',
  `condition` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '使用条件',
  `createnum` int(11) NULL DEFAULT 0 COMMENT '发放数量',
  `send_num` int(11) NULL DEFAULT 0 COMMENT '已领取数量',
  `use_num` int(11) NULL DEFAULT 0 COMMENT '已使用数量',
  `send_start_time` int(11) NULL DEFAULT NULL COMMENT '发放开始时间',
  `send_end_time` int(11) NULL DEFAULT NULL COMMENT '发放结束时间',
  `use_start_time` int(11) NULL DEFAULT NULL COMMENT '使用开始时间',
  `use_end_time` int(11) NULL DEFAULT NULL COMMENT '使用结束时间',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '添加时间',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `goods_id` int(11) NULL DEFAULT 0,
  `limit_type` tinyint(1) NULL DEFAULT 0 COMMENT '0-无限制 1-指定商品',
  `goodsids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '指定商品id',
  `expiry_date` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '有效期',
  `expiry_type` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '有效期类型 1-设置时间 2-设置有效期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_coupon_list
-- ----------------------------
DROP TABLE IF EXISTS `ty_coupon_list`;
CREATE TABLE `ty_coupon_list`  (
  `id` int(8) NOT NULL AUTO_INCREMENT,
  `cid` int(8) NOT NULL DEFAULT 0 COMMENT '优惠券 对应coupon表id',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '发放类型 0面额模板1 按用户发放 2 注册 3 邀请 4 线下发放 5 分享朋友圈发放 7积分兑换',
  `uid` int(8) NOT NULL DEFAULT 0 COMMENT '用户id',
  `order_id` int(8) NOT NULL DEFAULT 0 COMMENT '订单id',
  `use_time` int(11) NOT NULL DEFAULT 0 COMMENT '使用时间',
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '优惠券兑换码',
  `send_time` int(11) NOT NULL DEFAULT 0 COMMENT '发放时间',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `goods_id` int(11) NULL DEFAULT 0,
  `goodsids` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '指定商品id',
  `is_del` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否删除 0-否 1-是',
  `use_start_time` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '使用开始时间',
  `use_end_time` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '使用结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 125 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_custom
-- ----------------------------
DROP TABLE IF EXISTS `ty_custom`;
CREATE TABLE `ty_custom`  (
  `goods_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '定制id',
  `cat_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '定制分类id',
  `goods_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `click_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点击数',
  `comment_count` smallint(5) NULL DEFAULT 0 COMMENT '商品评论数',
  `shop_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '售价',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品关键词',
  `goods_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品简单描述',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品上传原始图',
  `is_on_sale` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否上架',
  `on_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品上架时间',
  `sort` smallint(4) UNSIGNED NOT NULL DEFAULT 50 COMMENT '商品排序',
  `last_update` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后更新时间',
  `sales_sum` int(11) NULL DEFAULT 0 COMMENT '商品销量',
  `goods_cat_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品一级分类id',
  `pid` int(11) NULL DEFAULT 0 COMMENT '商标关联酒瓶',
  `min_original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品上传原始图',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `cat_id`(`cat_id`) USING BTREE,
  INDEX `last_update`(`last_update`) USING BTREE,
  INDEX `sort_order`(`sort`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定制表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_custom_category
-- ----------------------------
DROP TABLE IF EXISTS `ty_custom_category`;
CREATE TABLE `ty_custom_category`  (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `name` varchar(90) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '定制分类名称',
  `mobile_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '手机端显示的定制分类名',
  `sort_order` int(11) UNSIGNED NOT NULL DEFAULT 50 COMMENT '顺序排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示',
  `image` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '分类图片',
  `is_custom` tinyint(1) NULL DEFAULT 0 COMMENT '1-允许自定义  0-不允许',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '自定义价格',
  `num` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '捆绑数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定制分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_custom_info
-- ----------------------------
DROP TABLE IF EXISTS `ty_custom_info`;
CREATE TABLE `ty_custom_info`  (
  `goods_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '定制id',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0-待审核，1-审核通过，2-审核拒绝',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核拒绝理由',
  `user_id` int(11) NULL DEFAULT NULL,
  `maker_order` tinyint(1) NULL DEFAULT 0 COMMENT '0-未生成订单 1-已生成订单',
  `nums` int(11) NULL DEFAULT 1 COMMENT '数量',
  `address` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '地址',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0-平台定制，1-自定义上传',
  `addtime` int(11) NULL DEFAULT 0 COMMENT '添加时间',
  `pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `update_time` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定制表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_delivery_doc
-- ----------------------------
DROP TABLE IF EXISTS `ty_delivery_doc`;
CREATE TABLE `ty_delivery_doc`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '发货单ID',
  `order_id` int(11) UNSIGNED NOT NULL COMMENT '订单ID',
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户ID',
  `admin_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '管理员ID',
  `consignee` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `zipcode` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系手机',
  `country` int(11) UNSIGNED NOT NULL COMMENT '国ID',
  `province` int(11) UNSIGNED NOT NULL COMMENT '省ID',
  `city` int(11) UNSIGNED NOT NULL COMMENT '市ID',
  `district` int(11) UNSIGNED NOT NULL COMMENT '区ID',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shipping_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流code',
  `shipping_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递名称',
  `shipping_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '运费',
  `invoice_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物流单号',
  `tel` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机电话',
  `note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '管理员添加的备注信息',
  `best_time` int(11) NULL DEFAULT NULL COMMENT '友好收货时间',
  `create_time` int(11) NOT NULL COMMENT '创建时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '是否已经删除',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺商家id',
  `shipping_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流图片',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发货单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_design_apartment
-- ----------------------------
DROP TABLE IF EXISTS `ty_design_apartment`;
CREATE TABLE `ty_design_apartment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务类型',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '页面内导航排序',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `style_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品风格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_design_service
-- ----------------------------
DROP TABLE IF EXISTS `ty_design_service`;
CREATE TABLE `ty_design_service`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务类型',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '页面内导航排序',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `style_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品风格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_design_size
-- ----------------------------
DROP TABLE IF EXISTS `ty_design_size`;
CREATE TABLE `ty_design_size`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务类型',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '页面内导航排序',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `style_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品风格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_design_space
-- ----------------------------
DROP TABLE IF EXISTS `ty_design_space`;
CREATE TABLE `ty_design_space`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '服务类型',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '页面内导航排序',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `style_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品风格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer`;
CREATE TABLE `ty_designer`  (
  `designer_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `enname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '英文名字',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
  `pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '个人封面',
  `head_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像',
  `describe` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `level_id` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '等级id',
  `fans` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '粉丝数',
  `works_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '作品总量',
  `main_skill` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '主技能，擅长',
  `skill` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '其他技能',
  `status` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '1 正常 0冻结',
  `discount` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '分佣比列',
  `point` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '获得的分值',
  `is_tuijian` tinyint(2) NULL DEFAULT 0 COMMENT '是否首页推荐 1 是 0否',
  `tj_sort` int(11) NULL DEFAULT NULL COMMENT '推荐排序',
  `collect_num` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '统计数量',
  `view_num` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '浏览量',
  `points_num` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '点赞人数',
  `country_id` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '国家id',
  `kujiale_level` int(1) UNSIGNED NULL DEFAULT 1 COMMENT '1-酷家乐C类账号，2-酷家乐B类账号',
  PRIMARY KEY (`designer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设计师基本表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_appointment
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_appointment`;
CREATE TABLE `ty_designer_appointment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `designer_id` int(11) UNSIGNED NOT NULL COMMENT '设计师id',
  `contact_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '预约人',
  `contact_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `province_id` int(11) NOT NULL DEFAULT 0 COMMENT '省',
  `city_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '市',
  `district` int(11) NOT NULL DEFAULT 0 COMMENT '县区',
  `meet_time` datetime(0) NOT NULL COMMENT '预约时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `user_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '会员id',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  `sex` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '性别 1-男 2-女',
  `deleted` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '1显示0删除',
  `is_see` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否回访',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '预约设计师表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_collect
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_collect`;
CREATE TABLE `ty_designer_collect`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏者id',
  `designer_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '设计师id',
  `add_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `designer_id`(`designer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 396 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设计师收藏表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_five
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_five`;
CREATE TABLE `ty_designer_five`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `small_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '小图地址',
  `big_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '大图地址',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `con` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `is_show` int(2) NULL DEFAULT 1 COMMENT '是否显示 1是 0否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_info
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_info`;
CREATE TABLE `ty_designer_info`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL COMMENT '用户id',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `home_province` int(10) NULL DEFAULT NULL COMMENT '家庭地址 省',
  `home_city` int(10) NULL DEFAULT NULL COMMENT '家庭地址 市',
  `home_area` int(10) NULL DEFAULT NULL COMMENT '家庭地址 区/县',
  `home_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭详细地址',
  `home_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人联系电话',
  `company_province` int(10) NULL DEFAULT NULL COMMENT '单位地址 省',
  `company_city` int(10) NULL DEFAULT NULL COMMENT '单位地址 市',
  `company_area` int(10) NULL DEFAULT NULL COMMENT '单位地址 区/县',
  `company_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位详细地址',
  `service_province` int(10) NULL DEFAULT NULL COMMENT '服务地区 省',
  `service_city` int(10) NULL DEFAULT NULL COMMENT '服务地区 市',
  `service_area` int(10) NULL DEFAULT NULL COMMENT '服务地区 区/县',
  `job_year` int(10) NULL DEFAULT NULL COMMENT '工作年限',
  `style` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '擅长风格 (逗号连接多个风格 id)',
  `company_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位名称',
  `company_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位电话',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '服务类型 （逗号连接 多个风格id）',
  `fm_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性封面1',
  `con` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人简介',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '描述',
  `country_id` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '国籍id',
  `head_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像',
  `fm_img2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性封面2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_level
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_level`;
CREATE TABLE `ty_designer_level`  (
  `level_id` smallint(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `level_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头衔名称',
  `point` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '等级必要分值',
  `discount` smallint(4) UNSIGNED NULL DEFAULT 0 COMMENT '分佣比列',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`level_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设计师等级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_designer_love
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_love`;
CREATE TABLE `ty_designer_love`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '用户id',
  `designer_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '设计师id',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '设计师点赞' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_points
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_points`;
CREATE TABLE `ty_designer_points`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏者id',
  `designer_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '设计师id',
  `add_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `designer_id`(`designer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '点赞设计师表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_product
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_product`;
CREATE TABLE `ty_designer_product`  (
  `product_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '作品id',
  `designer_id` int(10) UNSIGNED NOT NULL COMMENT '设计师id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作品标题',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '主图',
  `photo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '相册',
  `product_style` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '作品风格',
  `room` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '户型',
  `room_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '户型',
  `room_size` int(6) UNSIGNED NOT NULL COMMENT '房间大小',
  `soft_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '软装价格',
  `stuff_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '硬装价格',
  `love_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数',
  `collect_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收藏数',
  `sale_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '销量',
  `view` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '浏览量',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '酷家乐地址',
  `index_recommend` tinyint(2) UNSIGNED NOT NULL DEFAULT 2 COMMENT '1首页推荐  2不推荐',
  `is_recommend` tinyint(2) UNSIGNED NOT NULL DEFAULT 2 COMMENT '1推荐 2不推荐',
  `describe` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '设计描述',
  `status` tinyint(2) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 1上架 2下架 3删除',
  `crt_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '修改时间',
  `province` int(10) NULL DEFAULT NULL COMMENT '省 id',
  `city` int(10) NULL DEFAULT NULL COMMENT '市 id',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社区 小区',
  `product_space` int(10) NULL DEFAULT NULL COMMENT '设计空间',
  `chapter` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '章节，json数据',
  `designId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '酷家乐作品关联 id',
  `view_num` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '浏览量',
  `urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '高清全景地址',
  `images_qrcode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二维码',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 220 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设计师作品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_product_collect
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_product_collect`;
CREATE TABLE `ty_designer_product_collect`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `product_id` int(10) NULL DEFAULT NULL COMMENT '商品id',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '收藏店铺时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 281 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作品收藏表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_product_goods_spec
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_product_goods_spec`;
CREATE TABLE `ty_designer_product_goods_spec`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '作品id',
  `goods_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `sgp_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT 'ty_spec_goods_price 中的sgp_id',
  `num` int(10) UNSIGNED NOT NULL DEFAULT 1 COMMENT '商品在作品中需要的数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 936 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品商品关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_product_love
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_product_love`;
CREATE TABLE `ty_designer_product_love`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `product_id` int(10) NULL DEFAULT NULL COMMENT '商品id',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '收藏店铺时间',
  `status` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '1 点赞 2取消点赞',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 146 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '作品点赞' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_product_room
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_product_room`;
CREATE TABLE `ty_designer_product_room`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '作品id',
  `room_id` int(10) UNSIGNED NOT NULL COMMENT '房间规格id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品房间规格关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_product_space
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_product_space`;
CREATE TABLE `ty_designer_product_space`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '作品id',
  `space_id` int(10) UNSIGNED NOT NULL COMMENT '设计空间id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 717 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品风格关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_product_style
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_product_style`;
CREATE TABLE `ty_designer_product_style`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` int(10) UNSIGNED NOT NULL COMMENT '作品id',
  `style_id` int(10) UNSIGNED NOT NULL COMMENT '风格id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 836 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '作品风格关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_room
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_room`;
CREATE TABLE `ty_designer_room`  (
  `room_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `category` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '1 室 2厅 3卫 4阳台',
  `room_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '房间类型名字',
  `sort` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  `crt_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '添加时间',
  PRIMARY KEY (`room_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设计房屋类型' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_designer_style
-- ----------------------------
DROP TABLE IF EXISTS `ty_designer_style`;
CREATE TABLE `ty_designer_style`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `designer_id` int(10) UNSIGNED NOT NULL COMMENT '设计师id',
  `style_id` int(10) UNSIGNED NOT NULL COMMENT '风格id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '设计师风格关系表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_distribut
-- ----------------------------
DROP TABLE IF EXISTS `ty_distribut`;
CREATE TABLE `ty_distribut`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id自增',
  `switch` tinyint(1) NULL DEFAULT 0 COMMENT '分销开关',
  `condition` tinyint(1) NULL DEFAULT 0 COMMENT '成为分销商条件 0 直接成为分销商,1成功购买商品后成为分销商',
  `distribut_pattern` tinyint(1) NULL DEFAULT 0 COMMENT '分销模式 0 按商品设置的分成金额 1 按订单设置的分成比例',
  `first_rate` tinyint(1) NULL DEFAULT 0 COMMENT '一级分销商比例',
  `second_rate` tinyint(1) NULL DEFAULT 0 COMMENT '二级分销商名称',
  `third_rate` tinyint(1) NULL DEFAULT 0 COMMENT '三级分销商名称',
  `date` tinyint(1) NULL DEFAULT 15 COMMENT '订单收货确认后多少天可以分成',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_fail_refund
-- ----------------------------
DROP TABLE IF EXISTS `ty_fail_refund`;
CREATE TABLE `ty_fail_refund`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `refund_id` int(11) UNSIGNED NOT NULL COMMENT '与refund_log表关联',
  `refund_money` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '退款金额',
  `is_deal` tinyint(1) UNSIGNED NOT NULL COMMENT '0-未处理 1-已处理',
  `deal_time` datetime(0) NULL DEFAULT NULL COMMENT '处理时间',
  `add_time` datetime(0) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '未成功退款处理表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_feedback
-- ----------------------------
DROP TABLE IF EXISTS `ty_feedback`;
CREATE TABLE `ty_feedback`  (
  `msg_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '默认自增ID',
  `parent_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '回复留言ID',
  `user_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户ID',
  `user_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `msg_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '留言标题',
  `msg_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '留言类型',
  `msg_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '处理状态',
  `msg_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '留言内容',
  `msg_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '留言时间',
  `message_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `order_id` int(11) UNSIGNED NOT NULL DEFAULT 0,
  `msg_area` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`msg_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_flash_sale
-- ----------------------------
DROP TABLE IF EXISTS `ty_flash_sale`;
CREATE TABLE `ty_flash_sale`  (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动标题',
  `goods_id` int(10) NOT NULL COMMENT '参团商品ID',
  `price` float(10, 2) NOT NULL COMMENT '活动价格',
  `goods_num` int(10) NULL DEFAULT 1 COMMENT '商品参加活动数',
  `buy_limit` int(11) NOT NULL DEFAULT 1 COMMENT '每人限购数',
  `buy_num` int(11) NOT NULL DEFAULT 0 COMMENT '已购买人数',
  `order_num` int(10) NULL DEFAULT 0 COMMENT '已下单数',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动描述',
  `start_time` int(11) NOT NULL COMMENT '开始时间',
  `end_time` int(11) NOT NULL COMMENT '结束时间',
  `is_end` tinyint(1) NULL DEFAULT 0 COMMENT '是否已结束',
  `goods_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `store_id` int(10) NULL DEFAULT 0,
  `recommend` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '抢购状态：1正常，0待审核，2审核拒绝，3关闭活动,4已结束 5暂停中',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_footprint
-- ----------------------------
DROP TABLE IF EXISTS `ty_footprint`;
CREATE TABLE `ty_footprint`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '1-商品 2-店铺',
  `pid` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '加入时间',
  `edit_time` int(11) NULL DEFAULT NULL COMMENT '修改时间',
  `footprint_num` int(11) NULL DEFAULT 1,
  `is_del` int(11) NULL DEFAULT 0 COMMENT '1已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1258 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `ty_friend_link`;
CREATE TABLE `ty_friend_link`  (
  `link_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT,
  `link_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `link_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `link_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `orderby` tinyint(3) UNSIGNED NOT NULL DEFAULT 50 COMMENT '排序',
  `is_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示',
  `target` tinyint(1) NULL DEFAULT 1 COMMENT '是否新窗口打开',
  PRIMARY KEY (`link_id`) USING BTREE,
  INDEX `show_order`(`orderby`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods`;
CREATE TABLE `ty_goods`  (
  `goods_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `cat_id1` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '一级分类id',
  `cat_id2` int(11) NULL DEFAULT 0 COMMENT '二级分类',
  `cat_id3` int(11) NULL DEFAULT 0 COMMENT '三级分类',
  `store_cat_id1` int(11) NULL DEFAULT 0 COMMENT '本店一级分类',
  `store_cat_id2` int(11) NULL DEFAULT 0 COMMENT '本店二级分类',
  `goods_sn` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品编号',
  `goods_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `click_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点击数',
  `brand_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '品牌id',
  `store_count` smallint(5) UNSIGNED NOT NULL DEFAULT 10 COMMENT '库存数量',
  `collect_sum` int(10) NULL DEFAULT 0 COMMENT '商品收藏数',
  `comment_count` smallint(5) NULL DEFAULT 0 COMMENT '商品评论数',
  `weight` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '商品重量克为单位',
  `market_price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '市场价',
  `shop_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '本店价',
  `cost_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品成本价',
  `exchange_integral` int(10) NOT NULL DEFAULT 0 COMMENT 'COMMENT \'积分兑换：0不参与积分兑换',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品关键词',
  `goods_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品简单描述',
  `goods_content` text CHARACTER SET utf16 COLLATE utf16_general_ci NULL COMMENT '商品详细描述',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品上传原始图',
  `is_real` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否为实物',
  `is_on_sale` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否上架',
  `is_free_shipping` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否包邮0否1是',
  `on_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品上架时间',
  `sort` smallint(4) UNSIGNED NOT NULL DEFAULT 50 COMMENT '商品排序',
  `is_recommend` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐',
  `is_new` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否新品',
  `is_hot` tinyint(1) NULL DEFAULT 0 COMMENT '是否热卖',
  `last_update` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后更新时间',
  `goods_type` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品所属类型id，取值表goods_type的id',
  `give_integral` int(11) NULL DEFAULT 0 COMMENT '购买商品赠送积分',
  `sales_sum` int(11) NULL DEFAULT 0 COMMENT '商品销量',
  `prom_type` tinyint(1) NULL DEFAULT 0 COMMENT '1抢购2团购3优惠促销 4-打折 5-秒杀',
  `prom_id` int(11) NULL DEFAULT 0 COMMENT '优惠活动id',
  `distribut` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '佣金用于分销分成',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `spu` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'SPU',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'SKU',
  `goods_state` tinyint(1) NULL DEFAULT 1 COMMENT '0待审核1审核通过2审核失败3违规下架',
  `suppliers_id` smallint(5) UNSIGNED NULL DEFAULT NULL COMMENT '供应商ID',
  `shipping_area_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配送物流shipping_area_id,以逗号分隔',
  `business_contacts` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '业务联系人',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '软删除 0-未删除 1-已删除',
  `exist_type` tinyint(1) NULL DEFAULT 0 COMMENT '0-现货 1-期货',
  `other_sort` int(11) NULL DEFAULT 1 COMMENT '参加活动的排序',
  `recommend_sort` int(11) NULL DEFAULT 1 COMMENT '推荐排序',
  `batch_quantity` int(11) NULL DEFAULT 1 COMMENT '起批量',
  `order_goods_spec_id` int(11) NULL DEFAULT 0 COMMENT '订货规格id',
  `store_is_recommend` tinyint(1) NULL DEFAULT 0 COMMENT '0-不推荐 1-推荐',
  `store_recommend_sort` int(11) NULL DEFAULT 10 COMMENT '店铺推荐排序',
  `store_new_sort` int(11) NULL DEFAULT 10 COMMENT '店铺上新排序',
  `store_hot_sort` int(11) NULL DEFAULT 10 COMMENT '店铺热卖排序',
  `view_count` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `max_value` int(11) NULL DEFAULT 1000 COMMENT '最大人气值',
  `off_time` int(11) NULL DEFAULT 0 COMMENT '下架时间(自动上下架用的）',
  `set_on_sale` tinyint(1) NULL DEFAULT 0 COMMENT '0-不设置上架时间 1-设置上架时间',
  `set_off_sale` tinyint(1) NULL DEFAULT 0 COMMENT '0-不设置下架时间 1-设置下架时间',
  `add_time` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '添加时间',
  `partner_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '合伙人拿货价',
  `style_id` int(11) UNSIGNED NOT NULL COMMENT '风格id',
  `tips` tinyint(1) NULL DEFAULT 1 COMMENT '1-新品 2-爆款 3-热卖',
  `activity_type` tinyint(1) NULL DEFAULT 0 COMMENT '暂时没用',
  `activity_id` int(11) NULL DEFAULT 0,
  `series_item_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '系列id',
  `unit` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '单位',
  `xcx_content` text CHARACTER SET utf16 COLLATE utf16_general_ci NULL COMMENT '小程序商品描述',
  `goodtypeotherid` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '产品分类',
  PRIMARY KEY (`goods_id`) USING BTREE,
  INDEX `goods_sn`(`goods_sn`) USING BTREE,
  INDEX `cat_id`(`cat_id1`) USING BTREE,
  INDEX `last_update`(`last_update`) USING BTREE,
  INDEX `brand_id`(`brand_id`) USING BTREE,
  INDEX `goods_number`(`store_count`) USING BTREE,
  INDEX `goods_weight`(`weight`) USING BTREE,
  INDEX `sort_order`(`sort`) USING BTREE,
  INDEX `exist_type`(`exist_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1274 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_goods_attr
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_attr`;
CREATE TABLE `ty_goods_attr`  (
  `goods_attr_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品属性id自增',
  `goods_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `attr_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性id',
  `attr_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  `attr_price` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '属性价格',
  PRIMARY KEY (`goods_attr_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `attr_id`(`attr_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods_attribute
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_attribute`;
CREATE TABLE `ty_goods_attribute`  (
  `attr_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '属性id',
  `attr_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '属性名称',
  `type_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '属性分类id',
  `attr_index` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0不需要检索 1关键字检索',
  `attr_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '下拉框展示给商家选择',
  `attr_input_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '2多行文本框,平台属性录入方式',
  `attr_values` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '可选值列表',
  `order` tinyint(3) UNSIGNED NOT NULL DEFAULT 50 COMMENT '属性排序',
  PRIMARY KEY (`attr_id`) USING BTREE,
  INDEX `cat_id`(`type_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods_browse
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_browse`;
CREATE TABLE `ty_goods_browse`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) UNSIGNED NOT NULL COMMENT '商品id',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `add_time` int(10) UNSIGNED NOT NULL COMMENT '浏览时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1372 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品浏览记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_category`;
CREATE TABLE `ty_goods_category`  (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `name` varchar(90) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '商品分类名称',
  `mobile_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '手机端显示的商品分类名',
  `parent_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `parent_id_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '家族图谱',
  `level` tinyint(1) NULL DEFAULT 0 COMMENT '等级',
  `sort_order` tinyint(1) UNSIGNED NOT NULL DEFAULT 50 COMMENT '顺序排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示',
  `image` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '分类图片',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_hot` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐为热门分类',
  `cat_group` tinyint(1) NULL DEFAULT 0 COMMENT '分类分组默认0',
  `commission` tinyint(2) NULL DEFAULT 0 COMMENT '佣金比例',
  `commission_rate` tinyint(1) NULL DEFAULT 0 COMMENT '分佣比例用于分销',
  `type_id` int(11) NULL DEFAULT 0 COMMENT '对应的类型id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '小图标',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods_collect
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_collect`;
CREATE TABLE `ty_goods_collect`  (
  `collect_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `goods_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `add_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '添加时间',
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '商品类型 1-商品 2-积分商品',
  PRIMARY KEY (`collect_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 236 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_goods_consult
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_consult`;
CREATE TABLE `ty_goods_consult`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品咨询id',
  `goods_id` int(11) NULL DEFAULT 0 COMMENT '商品id',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '网名',
  `add_time` int(11) NULL DEFAULT 0 COMMENT '咨询时间',
  `consult_type` tinyint(1) NULL DEFAULT 1 COMMENT '1 商品咨询 2 支付咨询 3 配送 4 售后',
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '咨询内容',
  `parent_id` int(11) NULL DEFAULT 0 COMMENT '父id 用于管理员回复',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺id',
  `is_show` tinyint(1) NULL DEFAULT 0 COMMENT '是否显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods_images
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_images`;
CREATE TABLE `ty_goods_images`  (
  `img_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '图片id 自增',
  `goods_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片地址',
  `color_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与颜色对应',
  PRIMARY KEY (`img_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12965 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods_search
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_search`;
CREATE TABLE `ty_goods_search`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `session_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` datetime(0) NULL DEFAULT NULL,
  `type` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '1-商品 2-店铺',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods_series
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_series`;
CREATE TABLE `ty_goods_series`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类目表',
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类目名称',
  `sort` int(11) NULL DEFAULT 10 COMMENT '排序',
  `cat_id1` int(11) NULL DEFAULT 0 COMMENT '一级分类',
  `cat_id2` int(11) NULL DEFAULT 0 COMMENT '二级分类',
  `cat_id3` int(11) NULL DEFAULT 0 COMMENT '三级分类',
  `alias_name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_goods_series_item
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_series_item`;
CREATE TABLE `ty_goods_series_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '系列项id',
  `series_id` int(11) NULL DEFAULT NULL COMMENT '系列id',
  `item` varchar(54) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系列项',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `series_id`(`series_id`) USING BTREE,
  INDEX `item`(`item`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系列表规格' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_goods_style
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_style`;
CREATE TABLE `ty_goods_style`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `style_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '风格名字',
  `navigate_sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '头部导航排序',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '页面内导航排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '头部导航是否显示 0-否 1-是',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `style_name`(`style_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品风格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_type`;
CREATE TABLE `ty_goods_type`  (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id自增',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '类型名称',
  `cat_id1` smallint(5) NULL DEFAULT 0 COMMENT '一级分类id',
  `cat_id2` smallint(5) NULL DEFAULT 0 COMMENT '二级分类id',
  `cat_id3` smallint(5) NULL DEFAULT 0 COMMENT '三级分类id',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0-隐藏 1-显示',
  `type_sort` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '属性排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_goods_type_contact
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_type_contact`;
CREATE TABLE `ty_goods_type_contact`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) UNSIGNED NOT NULL COMMENT '商品id',
  `type_id` int(11) NOT NULL,
  `type_item_id` int(11) UNSIGNED NOT NULL COMMENT '商品属性id,goods_type_item表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6459 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_goods_type_item
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_type_item`;
CREATE TABLE `ty_goods_type_item`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_id` int(11) UNSIGNED NOT NULL COMMENT '属性id',
  `item` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '属性值',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 138 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品属性表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_goods_type_other
-- ----------------------------
DROP TABLE IF EXISTS `ty_goods_type_other`;
CREATE TABLE `ty_goods_type_other`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goodtypename` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  `type_level` int(10) NULL DEFAULT NULL COMMENT '分类级1-软装2-硬装',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_group_buy
-- ----------------------------
DROP TABLE IF EXISTS `ty_group_buy`;
CREATE TABLE `ty_group_buy`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '团购ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `start_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '开始时间',
  `end_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '结束时间',
  `goods_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商品ID',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '团购价格',
  `goods_num` int(10) NULL DEFAULT 0 COMMENT '商品参团数',
  `buy_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商品已购买数',
  `order_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '已下单人数',
  `virtual_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '虚拟购买数',
  `rebate` decimal(10, 1) NULL DEFAULT NULL COMMENT '折扣',
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '本团介绍',
  `goods_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品原价',
  `goods_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `recommend` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否推荐 0.未推荐 1.已推荐',
  `views` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '查看次数',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '团购状态，0待审核，1正常2拒绝3关闭',
  `is_del` tinyint(4) NULL DEFAULT 0,
  `spec_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品规格key 对应ty_spec_goods_price 表',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '团购商品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_group_goods_contract
-- ----------------------------
DROP TABLE IF EXISTS `ty_group_goods_contract`;
CREATE TABLE `ty_group_goods_contract`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prom_id` int(11) UNSIGNED NOT NULL COMMENT '活动id',
  `prom_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型  1抢购2团购3优惠促销 4-打折 5-秒杀',
  `goods_id` int(11) UNSIGNED NOT NULL COMMENT '商品id',
  `sgp_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品规格表id，为0时表示无规格',
  `prom_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '活动价格',
  `other_sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '排序',
  `group_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '参数商品数量',
  `sale_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已售数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 59 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '团购商品价格关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_guir_order
-- ----------------------------
DROP TABLE IF EXISTS `ty_guir_order`;
CREATE TABLE `ty_guir_order`  (
  `order_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ordersn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_amount` double(255, 0) NULL DEFAULT NULL,
  `pay_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10001 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_integral
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral`;
CREATE TABLE `ty_integral`  (
  `integral_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `cat_id1` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '一级分类id',
  `cat_id2` int(11) NULL DEFAULT 0 COMMENT '二级分类',
  `cat_id3` int(11) NULL DEFAULT 0 COMMENT '三级分类',
  `store_cat_id1` int(11) NULL DEFAULT 0 COMMENT '本店一级分类',
  `store_cat_id2` int(11) NULL DEFAULT 0 COMMENT '本店二级分类',
  `integral_sn` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品编号',
  `integral_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `click_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点击数',
  `brand_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '品牌id',
  `store_count` smallint(5) UNSIGNED NOT NULL DEFAULT 10 COMMENT '库存数量',
  `collect_sum` int(10) NULL DEFAULT 0 COMMENT '商品收藏数',
  `comment_count` smallint(5) NULL DEFAULT 0 COMMENT '商品评论数',
  `weight` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品重量克为单位',
  `market_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '市场价',
  `shop_price` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '本店价',
  `cost_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品成本价',
  `exchange_integral` int(10) NOT NULL DEFAULT 0 COMMENT 'COMMENT \'积分兑换：0不参与积分兑换',
  `keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品关键词',
  `integral_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品简单描述',
  `integral_content` text CHARACTER SET utf16 COLLATE utf16_general_ci NULL COMMENT '商品详细描述',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_real` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否为实物',
  `is_on_sale` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否上架',
  `is_free_shipping` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否包邮0否1是',
  `on_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品上架时间',
  `sort` smallint(4) UNSIGNED NOT NULL DEFAULT 50 COMMENT '商品排序',
  `is_recommend` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐',
  `is_new` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否新品',
  `is_hot` tinyint(1) NULL DEFAULT 0 COMMENT '是否热卖',
  `last_update` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最后更新时间',
  `integral_type` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品所属类型id，取值表goods_type的id',
  `give_integral` int(11) NULL DEFAULT 0 COMMENT '购买商品赠送积分',
  `sales_sum` int(11) NULL DEFAULT 0 COMMENT '商品销量',
  `prom_type` tinyint(1) NULL DEFAULT 0 COMMENT '1团购2抢购3优惠促销',
  `prom_id` int(11) NULL DEFAULT 0 COMMENT '优惠活动id',
  `distribut` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '佣金用于分销分成',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `spu` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'SPU',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'SKU',
  `integral_state` tinyint(1) NULL DEFAULT 1 COMMENT '0待审核1审核通过2审核失败3违规下架',
  `suppliers_id` smallint(5) UNSIGNED NULL DEFAULT NULL COMMENT '供应商ID',
  `shipping_area_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配送物流shipping_area_id,以逗号分隔',
  `add_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '添加时间',
  `is_blend` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0-积分兑换 1-余额兑换 2-积分+余额兑换',
  PRIMARY KEY (`integral_id`) USING BTREE,
  INDEX `goods_sn`(`integral_sn`) USING BTREE,
  INDEX `cat_id`(`cat_id1`) USING BTREE,
  INDEX `last_update`(`last_update`) USING BTREE,
  INDEX `brand_id`(`brand_id`) USING BTREE,
  INDEX `goods_number`(`store_count`) USING BTREE,
  INDEX `goods_weight`(`weight`) USING BTREE,
  INDEX `sort_order`(`sort`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_integral_cart
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral_cart`;
CREATE TABLE `ty_integral_cart`  (
  `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '购物车表',
  `user_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `integral_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `integral_sn` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品货号',
  `integral_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `market_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '市场价',
  `integral_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '本店价',
  `member_integral_price` decimal(10, 0) NULL DEFAULT 0 COMMENT '会员折扣价',
  `integral_num` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '购买数量',
  `selected` tinyint(1) NULL DEFAULT 1 COMMENT '购物车选中状态',
  `add_time` int(11) NULL DEFAULT 0 COMMENT '加入购物车的时间',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `exchange_integral` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '兑换所需积分',
  `is_blend` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0-积分兑换 1-余额兑换 2-积分+余额兑换',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 559 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_integral_category
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral_category`;
CREATE TABLE `ty_integral_category`  (
  `id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
  `name` varchar(90) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '商品分类名称',
  `mobile_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '手机端显示的商品分类名',
  `parent_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父id',
  `parent_id_path` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '家族图谱',
  `level` tinyint(1) NULL DEFAULT 0 COMMENT '等级',
  `sort_order` tinyint(1) UNSIGNED NOT NULL DEFAULT 50 COMMENT '顺序排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否显示',
  `image` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '分类图片',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_hot` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐为热门分类',
  `cat_group` tinyint(1) NULL DEFAULT 0 COMMENT '分类分组默认0',
  `commission` tinyint(2) NULL DEFAULT 0 COMMENT '佣金比例',
  `commission_rate` tinyint(1) NULL DEFAULT 0 COMMENT '分佣比例用于分销',
  `type_id` int(11) NULL DEFAULT 0 COMMENT '对应的类型id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `parent_id`(`parent_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_integral_delivery_doc
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral_delivery_doc`;
CREATE TABLE `ty_integral_delivery_doc`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '发货单ID',
  `order_id` int(11) UNSIGNED NOT NULL COMMENT '订单ID',
  `order_sn` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户ID',
  `admin_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '管理员ID',
  `consignee` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人',
  `zipcode` varchar(6) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系手机',
  `country` int(11) UNSIGNED NOT NULL COMMENT '国ID',
  `province` int(11) UNSIGNED NOT NULL COMMENT '省ID',
  `city` int(11) UNSIGNED NOT NULL COMMENT '市ID',
  `district` int(11) UNSIGNED NOT NULL COMMENT '区ID',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `shipping_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '物流code',
  `shipping_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '快递名称',
  `shipping_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '运费',
  `invoice_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物流单号',
  `tel` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机电话',
  `note` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '管理员添加的备注信息',
  `best_time` int(11) NULL DEFAULT NULL COMMENT '友好收货时间',
  `create_time` int(11) NOT NULL COMMENT '创建时间',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '是否已经删除',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺商家id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '发货单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_integral_images
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral_images`;
CREATE TABLE `ty_integral_images`  (
  `img_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '图片id 自增',
  `integral_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片地址',
  PRIMARY KEY (`img_id`) USING BTREE,
  INDEX `goods_id`(`integral_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_integral_order
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral_order`;
CREATE TABLE `ty_integral_order`  (
  `order_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `out_trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付号',
  `transaction_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `order_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态 0-待支付 1-待发货(待收货) 2-待评价 3-已取消 4-已完成 5-退货（换货）中',
  `shipping_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发货状态',
  `pay_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付状态',
  `consignee` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人',
  `country` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '国家',
  `province` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '省份',
  `city` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '城市',
  `district` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '县区',
  `twon` int(11) NULL DEFAULT 0 COMMENT '乡镇',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `zipcode` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮政编码',
  `mobile` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮件',
  `shipping_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '物流code',
  `shipping_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '物流名称',
  `pay_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '支付code',
  `pay_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '支付方式名称',
  `invoice_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发票抬头',
  `invoice_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `invoice_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `invoice_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机',
  `invoice_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `invoice_cardid` int(11) NULL DEFAULT NULL COMMENT '银行账号',
  `integral_price` decimal(10, 0) NOT NULL DEFAULT 0 COMMENT '商品总积分',
  `shipping_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '邮费',
  `order_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '应付款金额',
  `total_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单总价',
  `pay_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '支付金额',
  `add_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '下单时间',
  `confirm_time` int(10) NULL DEFAULT 0 COMMENT '收货确认时间',
  `pay_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付时间',
  `shipping_time` int(11) NULL DEFAULT 0 COMMENT '最新发货时间',
  `order_prom_id` smallint(6) NULL DEFAULT 0 COMMENT '订单活动id',
  `order_prom_amount` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '订单活动优惠金额',
  `discount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '价格调整',
  `user_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户备注',
  `admin_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '管理员备注',
  `parent_sn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父单单号',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '店铺ID',
  `is_comment` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否评价（0：未评价；1：已评价）',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  `is_checkout` tinyint(1) NULL DEFAULT NULL COMMENT '0未结算1已结算',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0-商品订单  1-定制订单 2-分享订单 3-推广员订单',
  `share_user_id` int(11) NULL DEFAULT 0,
  `share_coupon_price` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '推广优惠价',
  `share_original_price` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '原价',
  `suppliers_id` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `order_sn`(`order_sn`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `order_status`(`order_status`) USING BTREE,
  INDEX `shipping_status`(`shipping_status`) USING BTREE,
  INDEX `pay_status`(`pay_status`) USING BTREE,
  INDEX `shipping_id`(`shipping_code`) USING BTREE,
  INDEX `pay_id`(`pay_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_integral_order_action
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral_order_action`;
CREATE TABLE `ty_integral_order_action`  (
  `action_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单ID',
  `action_user` int(11) NOT NULL DEFAULT 0 COMMENT '操作人 0 为管理员操作',
  `order_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `shipping_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `pay_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `action_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作备注',
  `log_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '操作时间',
  `status_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `user_type` tinyint(1) NULL DEFAULT 0 COMMENT '0管理员1商家2前台用户',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家店铺ID',
  PRIMARY KEY (`action_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 80 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_integral_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `ty_integral_order_goods`;
CREATE TABLE `ty_integral_order_goods`  (
  `rec_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表id自增',
  `order_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单id',
  `integral_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `integral_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '视频名称',
  `integral_sn` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品货号',
  `integral_num` smallint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '购买数量',
  `market_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '市场价',
  `integral_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '本店价',
  `cost_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品成本价',
  `member_integral_price` decimal(10, 0) NULL DEFAULT 0 COMMENT '会员折扣价',
  `give_integral` mediumint(8) NULL DEFAULT 0 COMMENT '购买商品赠送积分',
  `bar_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '条码',
  `is_comment` tinyint(1) NULL DEFAULT 0 COMMENT '是否评价',
  `prom_type` tinyint(1) NULL DEFAULT 0 COMMENT '0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠',
  `prom_id` int(11) NULL DEFAULT 0 COMMENT '活动id',
  `is_send` tinyint(1) NULL DEFAULT 0 COMMENT '0未发货，1已发货，2已换货，3已退货',
  `delivery_id` int(11) NULL DEFAULT 0 COMMENT '发货单ID',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺id',
  `commission` tinyint(1) NULL DEFAULT 0 COMMENT '商家抽成比例',
  `is_checkout` tinyint(1) NULL DEFAULT 0 COMMENT '是否已跟商家结账0 否1是',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '0:为删除；1：已删除',
  `distribut` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '三级分销的金额',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0-商品  1-定制',
  `is_blend` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0-积分兑换 1-余额兑换 2-积分+余额兑换',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品图片',
  PRIMARY KEY (`rec_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `goods_id`(`integral_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_invoice_list
-- ----------------------------
DROP TABLE IF EXISTS `ty_invoice_list`;
CREATE TABLE `ty_invoice_list`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL COMMENT '用户id',
  `company` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发票抬头',
  `tax_nums` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册电话',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `bank_cardid` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '银行账户',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '0未删除  1已删除',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0-个人发票 1-公司发票 2-专用发票',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 105 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户增值发票表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_job_education_income
-- ----------------------------
DROP TABLE IF EXISTS `ty_job_education_income`;
CREATE TABLE `ty_job_education_income`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` tinyint(3) UNSIGNED NOT NULL COMMENT '1职业，2教育 3收入',
  `content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `crt_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  `sort` int(10) UNSIGNED NOT NULL COMMENT '排序，当前类的排序小靠前',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '职业，教育，收入表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_live
-- ----------------------------
DROP TABLE IF EXISTS `ty_live`;
CREATE TABLE `ty_live`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT 0 COMMENT '申请人会员ID',
  `pushurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `m3u8url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flvurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `rtmpurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addtime` datetime(0) NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请时间',
  `title` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `is_check` tinyint(1) NULL DEFAULT 0 COMMENT '0未审核 1通过  2未通过',
  `roomno` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `on_line` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否在线 0-未在线 1-在线',
  `endtime` datetime(0) NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `snapshot` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频截图',
  `total_view` int(11) NULL DEFAULT 0 COMMENT '总观看人数',
  `oss_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '录播oss地址',
  `goods_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品ids',
  `describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `point_num` int(11) NULL DEFAULT 0 COMMENT '点赞数量',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  `anchors_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主播信息',
  `subanchors_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '副播信息',
  `sort` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '直播申请表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_live_apply
-- ----------------------------
DROP TABLE IF EXISTS `ty_live_apply`;
CREATE TABLE `ty_live_apply`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL DEFAULT 0 COMMENT '申请人会员ID',
  `addtime` datetime(0) NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请时间',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `is_check` tinyint(1) NULL DEFAULT 0 COMMENT '0未审核 1通过  2未通过',
  `result` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '直播申请表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_live_comment
-- ----------------------------
DROP TABLE IF EXISTS `ty_live_comment`;
CREATE TABLE `ty_live_comment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `live_id` int(11) NOT NULL COMMENT '直播id',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '服务商id',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评价内容',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `live_id`(`live_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '直播评论表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_live_notify
-- ----------------------------
DROP TABLE IF EXISTS `ty_live_notify`;
CREATE TABLE `ty_live_notify`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `notify` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '直播返回的回调',
  `addtime` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '直播返回的回调' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_live_point
-- ----------------------------
DROP TABLE IF EXISTS `ty_live_point`;
CREATE TABLE `ty_live_point`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `live_id` int(11) NOT NULL COMMENT '直播id',
  `user_id` int(11) NOT NULL COMMENT '服务商id',
  `point_num` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '点赞数量',
  `add_time` datetime(0) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '直播点赞表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_live_reporting
-- ----------------------------
DROP TABLE IF EXISTS `ty_live_reporting`;
CREATE TABLE `ty_live_reporting`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '举报者id',
  `live_id` int(11) NOT NULL COMMENT '直播id',
  `add_time` int(11) NULL DEFAULT NULL COMMENT '举报时间',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '凭证图片',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '原因描述',
  `enum_id` int(11) NOT NULL COMMENT '违规类型 与t_public_enum表关联',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '审核状态0未审核1审核通过2审核失败',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '违规内容',
  `review_msg` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '操作备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '直播举报审核' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_live_reporting_review
-- ----------------------------
DROP TABLE IF EXISTS `ty_live_reporting_review`;
CREATE TABLE `ty_live_reporting_review`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `reporting_id` int(11) UNSIGNED NOT NULL COMMENT '举报id',
  `admin_id` int(11) UNSIGNED NOT NULL COMMENT '审核者id',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '审核状态 0-审核中 1-审核成功 2-审核失败',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核备注',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '直播举报审核记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_member_msg_tpl
-- ----------------------------
DROP TABLE IF EXISTS `ty_member_msg_tpl`;
CREATE TABLE `ty_member_msg_tpl`  (
  `mmt_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户消息模板编号',
  `mmt_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板名称',
  `mmt_message_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '站内信接收开关',
  `mmt_message_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站内信消息内容',
  `mmt_short_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '短信接收开关',
  `mmt_short_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信接收内容',
  `mmt_mail_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '邮件接收开关',
  `mmt_mail_subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件标题',
  `mmt_mail_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件内容',
  PRIMARY KEY (`mmt_code`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户消息模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_message
-- ----------------------------
DROP TABLE IF EXISTS `ty_message`;
CREATE TABLE `ty_message`  (
  `message_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `admin_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '管理员id',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站内信内容',
  `type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '个体消息：0，全体消息：1',
  `category` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-系统消息，1-活动推送,2-平台爆款消息',
  `send_time` int(10) UNSIGNED NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 33 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_module
-- ----------------------------
DROP TABLE IF EXISTS `ty_module`;
CREATE TABLE `ty_module`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `goods_cat_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联分类id ',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '促销活动名称',
  `type` int(2) NOT NULL DEFAULT 0 COMMENT '活动类型activity_type 表中type_id',
  `expression` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '优惠体现',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动描述',
  `start_time` int(11) NOT NULL COMMENT '活动开始时间',
  `end_time` int(11) NOT NULL COMMENT '活动结束时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '0待审核，1正常2拒绝3关闭 4-已结束 5-暂停',
  `group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用范围',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺id',
  `orderby` int(10) NULL DEFAULT 0 COMMENT '排序',
  `prom_img` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动宣传图片',
  `recommend` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告图',
  `is_del` tinyint(4) NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  `goods_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商品数量',
  `buy_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '商品已购买数',
  `order_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '已下单人数',
  `goods_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '商品id',
  `price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '活动价格',
  `is_deal` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0-未定时任务处理 1-已定时任务处理',
  `type_id1` int(11) NULL DEFAULT 0 COMMENT '一级分类id',
  `type_id2` int(11) NULL DEFAULT 0 COMMENT '二级分类id',
  `type_id3` int(11) NULL DEFAULT 0 COMMENT '三级分类id',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '审核信息',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '5D 访问url',
  `click_num` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '阅读量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动管理表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_module_contract
-- ----------------------------
DROP TABLE IF EXISTS `ty_module_contract`;
CREATE TABLE `ty_module_contract`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模块名字',
  `parent_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '父级id',
  `parent_id_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '家族图谱',
  `level` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '等级',
  `sort` int(11) UNSIGNED NULL DEFAULT 10 COMMENT '排序',
  `is_contract` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否设置关联 0-不设置 1-关联店铺 2-关联商品分类',
  `store_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理店铺id ,多个店铺用逗号隔开',
  `goods_cate_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联商品分类ids,多个id用逗号隔开',
  `is_ad_goods` tinyint(1) NULL DEFAULT 0 COMMENT '是否设置广告商品 0-不设置 1-设置',
  `ad_images` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品广告图片位，多个用逗号隔开',
  `goods_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '商品id，多个用逗号隔开',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '模块关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_module_type
-- ----------------------------
DROP TABLE IF EXISTS `ty_module_type`;
CREATE TABLE `ty_module_type`  (
  `type_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `module_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名字',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态 0-正常 1-禁止',
  `sort` smallint(4) UNSIGNED NOT NULL DEFAULT 10 COMMENT '排序',
  `parent_id` int(11) NOT NULL DEFAULT 0 COMMENT '父级id',
  `show_in_nav` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否导航栏显示 1-是 0-否',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型图片',
  `describe` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `introduce` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  `parent_id_path` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家族图谱',
  `level` tinyint(1) NULL DEFAULT 1 COMMENT '等级',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_navigation
-- ----------------------------
DROP TABLE IF EXISTS `ty_navigation`;
CREATE TABLE `ty_navigation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '前台导航表',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '导航名称',
  `is_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示',
  `is_new` tinyint(1) NULL DEFAULT 1 COMMENT '是否新窗口',
  `sort` smallint(6) NULL DEFAULT 50 COMMENT '排序',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '链接地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_offpay_area
-- ----------------------------
DROP TABLE IF EXISTS `ty_offpay_area`;
CREATE TABLE `ty_offpay_area`  (
  `store_id` int(8) UNSIGNED NOT NULL COMMENT '商家ID',
  `area_id` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '县ID组合',
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '货到付款支持地区表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_order
-- ----------------------------
DROP TABLE IF EXISTS `ty_order`;
CREATE TABLE `ty_order`  (
  `order_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_sn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `master_order_sn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '主订单号',
  `out_trade_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付号',
  `transaction_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_share_id` int(11) NULL DEFAULT NULL,
  `product_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '作品id',
  `order_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单状态 0-待支付 1-待发货(待收货) 2-待评价 3-已取消 4-已完成 5-退货（换货）中',
  `shipping_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '发货状态 0-未发货 1-已发货',
  `pay_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付状态 0-未支付 1-已支付',
  `consignee` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人',
  `country` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '国家',
  `province` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '省份',
  `city` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '城市',
  `district` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '县区',
  `twon` int(11) NULL DEFAULT 0 COMMENT '乡镇',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `zipcode` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮政编码',
  `mobile` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮件',
  `shipping_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '物流code',
  `shipping_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '物流名称',
  `pay_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '支付code',
  `pay_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '支付方式名称',
  `invoice_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发票抬头',
  `invoice_tax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `invoice_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册地址',
  `invoice_mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机',
  `invoice_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户银行',
  `invoice_cardid` int(11) NULL DEFAULT NULL COMMENT '银行账号',
  `goods_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '商品总价',
  `shipping_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '邮费',
  `user_money` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '使用余额',
  `coupon_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '优惠了多少',
  `integral` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用积分',
  `integral_money` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '使用积分抵多少钱',
  `order_amount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '应付款金额',
  `total_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单总价',
  `pay_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '支付金额',
  `add_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '下单时间',
  `confirm_time` int(10) NULL DEFAULT 0 COMMENT '收货确认时间',
  `pay_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付时间',
  `shipping_time` int(11) NULL DEFAULT 0 COMMENT '最新发货时间',
  `order_prom_id` smallint(6) NULL DEFAULT 0 COMMENT '订单活动id',
  `order_prom_amount` decimal(8, 2) NULL DEFAULT 0.00 COMMENT '订单活动优惠金额',
  `discount` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '价格调整',
  `user_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户备注',
  `admin_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '管理员备注',
  `parent_sn` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父单单号',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '店铺ID',
  `is_comment` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否评价（0：未评价；1：已评价）',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '0-未删除 1-已删除',
  `is_checkout` tinyint(1) NULL DEFAULT 0 COMMENT '0未结算1已结算',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0-商品订单  1-订货订单 2-拼团订单 3-推广员订单',
  `share_user_id` int(11) NULL DEFAULT 0 COMMENT '推广员ID',
  `share_coupon_price` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '推广优惠价',
  `share_original_price` decimal(11, 2) NULL DEFAULT 0.00 COMMENT '原价',
  `share_discount` decimal(11, 2) NULL DEFAULT NULL COMMENT '推广折扣',
  `suppliers_id` int(11) NULL DEFAULT 0 COMMENT '经销商ID',
  `prom_id` int(11) NULL DEFAULT 0 COMMENT '0-普通订单，其他-套餐订单',
  `give_suppliers` decimal(11, 2) NULL DEFAULT NULL COMMENT '经销商提成',
  `give_agent` decimal(11, 2) NULL DEFAULT NULL COMMENT '推广员提成',
  `order_goods_status` tinyint(1) NULL DEFAULT 0 COMMENT '订货订单状态 0-代付款 1-已付定金 2-已付中间款 3-已付尾款',
  `order_goods_msg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '订货订单阶段信息',
  `discount_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '合伙人折扣的金额',
  `rebate_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '合伙人订单返利金额',
  `is_check` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否核销 0-否 1-是',
  `is_pick_up` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0-非自提 1-自提',
  `check_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核销吗',
  `pick_up_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '自提时间',
  `pick_up_day` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '自提日期',
  `door_id` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '门店id',
  PRIMARY KEY (`order_id`) USING BTREE,
  UNIQUE INDEX `order_sn`(`order_sn`) USING BTREE,
  INDEX `order_status`(`order_status`) USING BTREE,
  INDEX `shipping_status`(`shipping_status`) USING BTREE,
  INDEX `pay_status`(`pay_status`) USING BTREE,
  INDEX `shipping_id`(`shipping_code`) USING BTREE,
  INDEX `pay_id`(`pay_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 469 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_order_action
-- ----------------------------
DROP TABLE IF EXISTS `ty_order_action`;
CREATE TABLE `ty_order_action`  (
  `action_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单ID',
  `action_user` int(11) NOT NULL DEFAULT 0 COMMENT '操作人 0 为管理员操作',
  `order_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `shipping_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `pay_status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0,
  `action_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '操作备注',
  `log_time` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '操作时间',
  `status_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态描述',
  `user_type` tinyint(1) NULL DEFAULT 0 COMMENT '0管理员1商家2前台用户',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家店铺ID',
  PRIMARY KEY (`action_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1210 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_order_bill
-- ----------------------------
DROP TABLE IF EXISTS `ty_order_bill`;
CREATE TABLE `ty_order_bill`  (
  `ob_no` int(11) NOT NULL AUTO_INCREMENT COMMENT '结算单编号(年月店铺ID)',
  `ob_start_date` int(11) NOT NULL COMMENT '开始日期',
  `ob_end_date` int(11) NOT NULL COMMENT '结束日期',
  `ob_order_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '订单金额',
  `ob_shipping_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '运费',
  `ob_order_return_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '退单金额',
  `ob_commis_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '佣金金额',
  `ob_commis_return_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '退还佣金',
  `ob_store_cost_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '店铺促销活动费用',
  `ob_result_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '应结金额',
  `ob_create_date` int(11) NULL DEFAULT 0 COMMENT '生成结算单日期',
  `os_month` mediumint(6) UNSIGNED NOT NULL COMMENT '结算单年月份',
  `ob_state` enum('1','2','3','4') CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '1' COMMENT '1默认2店家已确认3平台已审核4结算完成',
  `ob_pay_date` int(11) NULL DEFAULT 0 COMMENT '付款日期',
  `ob_pay_content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '支付备注',
  `ob_store_id` int(11) NOT NULL COMMENT '店铺ID',
  `ob_store_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名',
  PRIMARY KEY (`ob_no`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_order_comment
-- ----------------------------
DROP TABLE IF EXISTS `ty_order_comment`;
CREATE TABLE `ty_order_comment`  (
  `order_commemt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单评论索引id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `store_id` int(11) NOT NULL COMMENT '店铺id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `describe_score` decimal(2, 0) NOT NULL COMMENT '描述相符分数（0~5）',
  `seller_score` decimal(2, 0) NOT NULL COMMENT '卖家服务分数（0~5）',
  `logistics_score` decimal(2, 0) NOT NULL COMMENT '物流服务分数（0~5）',
  `commemt_time` int(10) UNSIGNED NOT NULL COMMENT '评分时间',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '不删除0；删除：1',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `goods_rank` int(10) NULL DEFAULT NULL COMMENT '评价等级 0~5',
  PRIMARY KEY (`order_commemt_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '订单评分表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_order_extend
-- ----------------------------
DROP TABLE IF EXISTS `ty_order_extend`;
CREATE TABLE `ty_order_extend`  (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单索引id',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺ID',
  `shipping_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配送时间',
  `shipping_express_id` tinyint(1) NOT NULL DEFAULT 0 COMMENT '配送公司ID',
  `evaluation_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '评价时间',
  `evalseller_state` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '卖家是否已评价买家',
  `evalseller_time` int(10) UNSIGNED NOT NULL COMMENT '卖家评价买家的时间',
  `order_message` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单留言',
  `order_pointscount` int(11) NOT NULL DEFAULT 0 COMMENT '订单赠送积分',
  `voucher_price` int(11) NULL DEFAULT NULL COMMENT '代金券面额',
  `voucher_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '代金券编码',
  `deliver_explain` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '发货备注',
  `daddress_id` mediumint(9) NOT NULL DEFAULT 0 COMMENT '发货地址ID',
  `reciver_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人姓名',
  `reciver_info` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人其它信息',
  `reciver_province_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收货人省级ID',
  `reciver_city_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '收货人市级ID',
  `invoice_info` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '发票信息',
  `promotion_info` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '促销信息备注',
  `dlyo_pickup_code` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '提货码',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `ty_order_goods`;
CREATE TABLE `ty_order_goods`  (
  `rec_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表id自增',
  `order_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单id',
  `goods_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品id',
  `goods_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '视频名称',
  `goods_sn` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品货号',
  `goods_num` smallint(5) UNSIGNED NOT NULL DEFAULT 1 COMMENT '购买数量',
  `market_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '市场价',
  `goods_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '本店价',
  `cost_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '商品成本价',
  `member_goods_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '会员折扣价',
  `give_integral` mediumint(8) NULL DEFAULT 0 COMMENT '购买商品赠送积分',
  `spec_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品规格key',
  `spec_key_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '规格对应的中文名字',
  `bar_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '条码',
  `is_comment` tinyint(1) NULL DEFAULT 0 COMMENT '是否评价 0-未 1-已',
  `prom_type` tinyint(1) NULL DEFAULT 0 COMMENT '0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠',
  `prom_id` int(11) NULL DEFAULT 0 COMMENT '活动id',
  `is_send` tinyint(1) NULL DEFAULT 0 COMMENT '0未发货，1已发货，2已换货，3已退货',
  `delivery_id` int(11) NULL DEFAULT 0 COMMENT '发货单ID',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'sku',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺id',
  `commission` tinyint(1) NULL DEFAULT 0 COMMENT '商家抽成比例',
  `is_checkout` tinyint(1) NULL DEFAULT 0 COMMENT '是否已跟商家结账0 否1是',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '0:为删除；1：已删除',
  `distribut` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '三级分销的金额',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0-商品  1-定制',
  `suppliers_id` int(11) NULL DEFAULT NULL,
  `activity_id` int(11) NULL DEFAULT 0 COMMENT '首页活动id',
  `activity_type` int(11) NULL DEFAULT 0 COMMENT '首页活动类型',
  `user_money` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '使用了多少余额',
  `coupon_price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '优惠了多少',
  `integral` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '使用了多少积分',
  `integral_money` decimal(10, 2) UNSIGNED NULL DEFAULT NULL COMMENT '积分抵扣金额',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品原始图片',
  `really_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品实付价',
  `discount_fee` decimal(10, 2) NULL DEFAULT NULL COMMENT '合伙人优惠金额（针对每类商品）',
  `rebate_fee` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '合伙人实际返利金额（针对每类商品）',
  PRIMARY KEY (`rec_id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 712 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_order_remind
-- ----------------------------
DROP TABLE IF EXISTS `ty_order_remind`;
CREATE TABLE `ty_order_remind`  (
  `remind_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `order_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '订单id',
  `remind_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '提醒信息',
  `crt_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '创建时间',
  PRIMARY KEY (`remind_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '提醒发货' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_order_statis
-- ----------------------------
DROP TABLE IF EXISTS `ty_order_statis`;
CREATE TABLE `ty_order_statis`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_date` int(11) NOT NULL COMMENT '开始日期',
  `end_date` int(11) NOT NULL COMMENT '结束日期',
  `order_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '订单金额',
  `shipping_totals` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '运费',
  `return_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '退单金额',
  `return_integral` int(11) NULL DEFAULT 0 COMMENT '退还积分',
  `commis_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '平台抽成',
  `give_integral` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '送出积分金额',
  `result_totals` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '本期应结',
  `create_date` int(11) NULL DEFAULT NULL COMMENT '创建记录日期',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺id',
  `order_prom_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '优惠价',
  `coupon_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '优惠券抵扣',
  `distribut` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '分销金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商家订单结算表' ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_package
-- ----------------------------
DROP TABLE IF EXISTS `ty_package`;
CREATE TABLE `ty_package`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `package_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '套餐名字',
  `style_id` int(11) UNSIGNED NOT NULL COMMENT '套餐风格id',
  `plan_num` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '方案数量',
  `tip` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '套餐特色',
  `style_alias` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '风格别称',
  `suit_area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '适用面积',
  `describe` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '套餐描述',
  `designer_id` int(11) UNSIGNED NOT NULL COMMENT '设计师id',
  `min_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '最小套餐价格',
  `max_amount` decimal(10, 2) NOT NULL COMMENT '最大套餐价',
  `limit_amount` decimal(10, 2) NOT NULL COMMENT '享受优惠价格的最小金额',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0-下架中 1-上架中',
  `is_del` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '0-未删除 1-删除',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '创建时间',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主图',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_package_category
-- ----------------------------
DROP TABLE IF EXISTS `ty_package_category`;
CREATE TABLE `ty_package_category`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名字',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '状态 0-禁用 1-正常',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '排序 ',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `cate_name`(`cate_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐分区表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_package_collect
-- ----------------------------
DROP TABLE IF EXISTS `ty_package_collect`;
CREATE TABLE `ty_package_collect`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `package_id` int(11) UNSIGNED NOT NULL COMMENT '案例id',
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '会员id',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '收藏时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `package_id`(`package_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '案例收藏表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_package_goods
-- ----------------------------
DROP TABLE IF EXISTS `ty_package_goods`;
CREATE TABLE `ty_package_goods`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `package_id` int(11) UNSIGNED NOT NULL,
  `category_id` int(11) UNSIGNED NOT NULL COMMENT '分类id,0时是丰富区',
  `plan_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '分类方案id',
  `goods_id` int(11) NOT NULL,
  `sgp_id` int(11) NOT NULL DEFAULT 0 COMMENT 'spec_goods_price 主键id',
  `goods_num` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '商品数量',
  `package_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '套餐价格',
  `sort` int(11) UNSIGNED NULL DEFAULT 1 COMMENT '商品排序',
  `is_default` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否默认选中 0-否  1-是（暂时不用了，以plan表排序为准）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `package_id`(`package_id`) USING BTREE,
  INDEX `category`(`category_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `goods_sku`(`sgp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2423 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐商品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_package_images
-- ----------------------------
DROP TABLE IF EXISTS `ty_package_images`;
CREATE TABLE `ty_package_images`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `package_id` int(11) UNSIGNED NOT NULL COMMENT '套餐id',
  `image_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '套餐图片',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `package_id`(`package_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1080 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐图片' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_package_plan
-- ----------------------------
DROP TABLE IF EXISTS `ty_package_plan`;
CREATE TABLE `ty_package_plan`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `plan_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方案名字',
  `package_id` int(11) UNSIGNED NOT NULL DEFAULT 10 COMMENT '套餐id',
  `category_id` int(11) UNSIGNED NOT NULL COMMENT '分类id',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `plan_sort` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '隐形排序 从1开始，调sort时同时将其从1开始调换',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `plan_name`(`plan_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1034 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐分类方案名字记录表 ' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_package_style
-- ----------------------------
DROP TABLE IF EXISTS `ty_package_style`;
CREATE TABLE `ty_package_style`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `style_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '风格名字',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0-禁用 1-正常',
  `add_time` int(11) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '套餐风格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_partner
-- ----------------------------
DROP TABLE IF EXISTS `ty_partner`;
CREATE TABLE `ty_partner`  (
  `rec_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `user_id` mediumint(8) NULL DEFAULT NULL COMMENT '用户id',
  `discount` smallint(4) NULL DEFAULT NULL COMMENT '折扣',
  `rebate` smallint(4) NULL DEFAULT NULL COMMENT '返利',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '合伙人身份 1正常 0已取消',
  `addtime` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册时间',
  PRIMARY KEY (`rec_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_pay_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_pay_log`;
CREATE TABLE `ty_pay_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单order_sn',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0-未成功 1-成功',
  `call_back` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '返回的回调信息',
  `add_time` datetime(0) NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '支付记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_payment
-- ----------------------------
DROP TABLE IF EXISTS `ty_payment`;
CREATE TABLE `ty_payment`  (
  `pay_id` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pay_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `pay_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '支付方式名称',
  `pay_fee` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '手续费',
  `pay_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `pay_order` tinyint(3) UNSIGNED NOT NULL DEFAULT 0,
  `pay_config` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置',
  `enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '开启',
  `is_cod` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否货到付款',
  `is_online` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否在线支付',
  PRIMARY KEY (`pay_id`) USING BTREE,
  UNIQUE INDEX `pay_code`(`pay_code`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_physical_appointment
-- ----------------------------
DROP TABLE IF EXISTS `ty_physical_appointment`;
CREATE TABLE `ty_physical_appointment`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `physical_id` int(11) UNSIGNED NOT NULL COMMENT '体验馆id',
  `contact_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '预约人',
  `contact_mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系电话',
  `testing_num` smallint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '体验人数',
  `testing_time` datetime(0) NOT NULL COMMENT '预约时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `user_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '会员id',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '体验店预约表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_physical_store
-- ----------------------------
DROP TABLE IF EXISTS `ty_physical_store`;
CREATE TABLE `ty_physical_store`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员ids,多个会员用逗号隔开',
  `store_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店名称',
  `contact_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '门店电话',
  `province_id` int(11) NOT NULL DEFAULT 0 COMMENT '省',
  `city_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '市',
  `district` int(11) NOT NULL DEFAULT 0 COMMENT '县区',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `open_time` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '营业时间',
  `is_show` tinyint(1) NULL DEFAULT 1 COMMENT '是否显示 0 不显示  1 显示',
  `addtime` int(11) NULL DEFAULT NULL COMMENT '注册时间',
  `pic` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `erp_store_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应erp系统里门店的id',
  `is_ziti` tinyint(3) NULL DEFAULT 1 COMMENT '1可自提2不可自提',
  `is_delivery` tinyint(3) NULL DEFAULT 1 COMMENT '1可配送2不可配送',
  `lng` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '经度',
  `lat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '纬度',
  `delivery` tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '大区配送站 0不是 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '实体店（线下门店）' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_plugin
-- ----------------------------
DROP TABLE IF EXISTS `ty_plugin`;
CREATE TABLE `ty_plugin`  (
  `code` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插件编码',
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名字',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `author` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `config` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置信息',
  `config_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置值信息',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '是否启用',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插件类型 payment支付 login 登陆 shipping物流',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `bank_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '网银配置信息',
  `scene` tinyint(1) NULL DEFAULT 0 COMMENT '使用场景 0 PC+手机 1 手机 2 PC',
  `tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_prom_action
-- ----------------------------
DROP TABLE IF EXISTS `ty_prom_action`;
CREATE TABLE `ty_prom_action`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prom_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '活动id',
  `prom_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '类型  1抢购2团购3优惠促销 4-打折',
  `admin_id` int(11) UNSIGNED NOT NULL COMMENT '操作者id',
  `user_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '操作者类型 0-总平台 1-商家后台 2-用户',
  `action_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作备注',
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '店铺id',
  `action_time` int(11) UNSIGNED NOT NULL COMMENT '操作时间',
  `status_desc` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型描述',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '活动状态 0-待审核 1-成功 2-失败 3-已关闭 4-已结束 5-暂停中',
  `goods_ids` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ids',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动操作记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_prom_goods
-- ----------------------------
DROP TABLE IF EXISTS `ty_prom_goods`;
CREATE TABLE `ty_prom_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '促销活动名称',
  `type` int(2) NOT NULL DEFAULT 0 COMMENT '促销类型',
  `expression` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '优惠体现',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动描述',
  `start_time` int(11) NOT NULL COMMENT '活动开始时间',
  `end_time` int(11) NOT NULL COMMENT '活动结束时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '1正常，0管理员关闭',
  `group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用范围',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺id',
  `orderby` int(10) NULL DEFAULT 0 COMMENT '排序',
  `prom_img` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动宣传图片',
  `recommend` tinyint(1) NULL DEFAULT 0 COMMENT '是否推荐',
  `original_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '广告图',
  `is_del` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_prom_goods_contract
-- ----------------------------
DROP TABLE IF EXISTS `ty_prom_goods_contract`;
CREATE TABLE `ty_prom_goods_contract`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `prom_id` int(11) UNSIGNED NOT NULL COMMENT '活动id',
  `prom_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型  1抢购2团购3优惠促销 4-打折',
  `goods_id` int(11) UNSIGNED NOT NULL COMMENT '商品id',
  `sgp_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品规格表id，为0时表示无规格',
  `prom_price` decimal(10, 2) UNSIGNED NOT NULL COMMENT '活动价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '活动商品价格关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_prom_order
-- ----------------------------
DROP TABLE IF EXISTS `ty_prom_order`;
CREATE TABLE `ty_prom_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `type` int(2) NOT NULL DEFAULT 0 COMMENT '活动类型',
  `money` float(10, 2) NULL DEFAULT 0.00 COMMENT '最小金额',
  `expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '优惠体现',
  `description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '活动描述',
  `start_time` int(11) NULL DEFAULT NULL COMMENT '活动开始时间',
  `end_time` int(11) NULL DEFAULT NULL COMMENT '活动结束时间',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '1正常，0管理员关闭',
  `group` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '适用范围',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家店铺id',
  `orderby` int(10) NULL DEFAULT 0 COMMENT '排序',
  `recommend` tinyint(4) NULL DEFAULT 0 COMMENT '是否推荐',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_public_enum
-- ----------------------------
DROP TABLE IF EXISTS `ty_public_enum`;
CREATE TABLE `ty_public_enum`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联的表',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名字',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '值',
  `sort` int(11) NULL DEFAULT 1 COMMENT '排序',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '1-正常 0-禁用',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公共枚举值表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_rebate_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_rebate_log`;
CREATE TABLE `ty_rebate_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分成记录表',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '获佣用户',
  `buy_user_id` int(11) NULL DEFAULT 0 COMMENT '购买人id',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '购买人名称',
  `order_sn` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '订单编号',
  `order_id` int(11) NULL DEFAULT 0 COMMENT '订单id',
  `goods_price` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '订单商品总额',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '获佣金额',
  `success_money` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '完成的分佣金额',
  `level` tinyint(1) NULL DEFAULT 1 COMMENT '获佣用户级别',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '分成记录生成时间',
  `confirm` int(11) NULL DEFAULT 0 COMMENT '确定收货时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0未付款,1已付款, 2等待分成(已收货) 3已分成, 4已取消',
  `confirm_time` int(11) NULL DEFAULT 0 COMMENT '确定分成或者取消时间',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '如果是取消, 有取消备注',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺id',
  `type` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0-用户 1-供应商 2-合伙人 3-设计师',
  `log_type` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '1 购买 2退款，退货',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 353 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_recharge
-- ----------------------------
DROP TABLE IF EXISTS `ty_recharge`;
CREATE TABLE `ty_recharge`  (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '会员ID',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `order_sn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '充值单号',
  `account` float(10, 2) NULL DEFAULT 0.00 COMMENT '充值金额',
  `ctime` int(11) NULL DEFAULT NULL COMMENT '充值时间',
  `pay_time` int(11) NULL DEFAULT NULL COMMENT '支付时间',
  `pay_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pay_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `pay_status` tinyint(1) NULL DEFAULT 0 COMMENT '充值状态0:待支付 1:充值成功 2:交易关闭',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_red_packet
-- ----------------------------
DROP TABLE IF EXISTS `ty_red_packet`;
CREATE TABLE `ty_red_packet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `money` decimal(11, 2) NULL DEFAULT NULL COMMENT '金额',
  `condition` decimal(11, 2) NULL DEFAULT 0.00,
  `start_time` int(11) NULL DEFAULT NULL COMMENT '开始使用时间',
  `end_time` int(11) NULL DEFAULT NULL COMMENT '结束使用时间',
  `add_time` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_red_packet_list
-- ----------------------------
DROP TABLE IF EXISTS `ty_red_packet_list`;
CREATE TABLE `ty_red_packet_list`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `r_p_id` int(11) NULL DEFAULT NULL,
  `order_id` int(255) NULL DEFAULT NULL,
  `money` decimal(11, 2) NULL DEFAULT NULL,
  `condition` decimal(11, 2) NULL DEFAULT 0.00,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `start_time` int(11) NULL DEFAULT NULL,
  `end_time` int(11) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '0-未使用 1-已使用',
  `add_time` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_refund_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_refund_log`;
CREATE TABLE `ty_refund_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `return_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '退换货id',
  `return_ids` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '定时任务退款是先退款后创建到return_goods,备用',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0-未成功 1-成功',
  `callback` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '返回的回调信息',
  `add_time` datetime(0) NOT NULL COMMENT '添加时间',
  `refund_money` decimal(10, 2) UNSIGNED NOT NULL COMMENT '退款金额',
  `order_type` tinyint(1) UNSIGNED NOT NULL COMMENT ' 0-普通订单 1-订货订单',
  `out_request_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '此次退款编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退款记录表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_refund_record
-- ----------------------------
DROP TABLE IF EXISTS `ty_refund_record`;
CREATE TABLE `ty_refund_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单号',
  `user_id` int(11) NOT NULL COMMENT '退款用户',
  `reabck_time` int(11) NULL DEFAULT NULL COMMENT '退款时间',
  `return_money` decimal(10, 2) NOT NULL COMMENT '退款金额',
  `admin_id` int(11) NOT NULL COMMENT '操作管理员用户',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '0-退款失败 1-退款成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退款记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_region
-- ----------------------------
DROP TABLE IF EXISTS `ty_region`;
CREATE TABLE `ty_region`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` longtext CHARACTER SET gbk COLLATE gbk_chinese_ci NULL,
  `level` tinyint(4) NULL DEFAULT NULL,
  `parent_id` int(10) NULL DEFAULT NULL,
  `value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `parent_value` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '父级value值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3435 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_remittance
-- ----------------------------
DROP TABLE IF EXISTS `ty_remittance`;
CREATE TABLE `ty_remittance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分销用户转账记录表',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '汇款的用户id',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '收款银行名称',
  `account_bank` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账号',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '开户人名称',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '汇款金额',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0汇款失败 1汇款成功',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '汇款时间',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '汇款备注',
  `admin_id` int(11) NULL DEFAULT 0 COMMENT '管理员id',
  `withdrawals_id` int(11) NULL DEFAULT 0 COMMENT '提现申请表id',
  `type` tinyint(1) NULL DEFAULT NULL,
  `way` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '提现方式 1微信，2支付宝 ，3银行',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_reply
-- ----------------------------
DROP TABLE IF EXISTS `ty_reply`;
CREATE TABLE `ty_reply`  (
  `reply_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '回复id',
  `comment_id` int(10) NOT NULL COMMENT '评论id：关联评论表',
  `parent_id` int(10) NOT NULL DEFAULT 0 COMMENT '父类id，0为最顶级',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复内容',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '回复人的昵称',
  `to_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '被回复人的昵称',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL COMMENT '未删除0；删除：1',
  `reply_time` int(10) UNSIGNED NOT NULL COMMENT '回复时间',
  PRIMARY KEY (`reply_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_return_goods
-- ----------------------------
DROP TABLE IF EXISTS `ty_return_goods`;
CREATE TABLE `ty_return_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '退货申请表id自增',
  `order_id` int(11) NULL DEFAULT 0 COMMENT '订单id',
  `order_sn` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单编号',
  `goods_id` int(11) NULL DEFAULT 0 COMMENT '商品id',
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '1-退款 2-退款退货 3-换货',
  `is_delivery` tinyint(1) NULL DEFAULT NULL COMMENT '0-未收到货 1-已收到货',
  `reason` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '退换货原因',
  `imgs` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '拍照图片路径',
  `addtime` int(11) NULL DEFAULT 0 COMMENT '申请时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '状态:0-待审核 1-审核中 2-供应商审核成功 3-审核失败 4-已发快递 5-已收到快递 6-平台介入 7-供应商同意退款或换货 8-退换完成 9-撤销审核',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客户备注',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '用户id',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `spec_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '商品规格',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '退款说明',
  `return_money` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '退款金额',
  `goods_sum` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '退换货总数量',
  `shipping_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '物流名字',
  `invoice_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '物流单号',
  `return_address_id` int(11) NULL DEFAULT 0 COMMENT '商家地址id',
  `agree_return_money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '同意退款金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_return_goods_detail
-- ----------------------------
DROP TABLE IF EXISTS `ty_return_goods_detail`;
CREATE TABLE `ty_return_goods_detail`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `return_id` int(11) UNSIGNED NOT NULL,
  `rec_id` int(11) NOT NULL COMMENT '订单表自增id',
  `goods_id` int(11) UNSIGNED NOT NULL,
  `goods_num` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '退货商品数量',
  `goods_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名字',
  `goods_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '本店价',
  `member_goods_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '会员折扣价',
  `spec_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '商品规格key',
  `spec_key_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '规格对应的中文名字',
  `prom_type` tinyint(1) NULL DEFAULT 0 COMMENT '0 普通订单,1 限时抢购, 2 团购 , 3 促销优惠',
  `prom_id` int(11) NULL DEFAULT 0 COMMENT '活动id',
  `is_send` tinyint(1) NULL DEFAULT 0 COMMENT '0未发货，1已发货,2-已收到货',
  `delivery_id` int(11) NULL DEFAULT 0 COMMENT '发货单ID',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺id',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0-商品  1-定制',
  `activity_type` int(11) NULL DEFAULT 0 COMMENT '活动类型 1-订货活动',
  `activity_id` int(11) NULL DEFAULT 0 COMMENT '活动id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '退换货详情表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_return_integral
-- ----------------------------
DROP TABLE IF EXISTS `ty_return_integral`;
CREATE TABLE `ty_return_integral`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '退货申请表id自增',
  `order_id` int(11) NULL DEFAULT 0 COMMENT '订单id',
  `order_sn` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '订单编号',
  `integral_id` int(11) NULL DEFAULT 0 COMMENT '商品id',
  `type` tinyint(1) NULL DEFAULT 0 COMMENT '0退货1换货',
  `is_delivery` tinyint(1) NULL DEFAULT NULL,
  `reason` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '退换货原因',
  `imgs` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '拍照图片路径',
  `addtime` int(11) NULL DEFAULT 0 COMMENT '申请时间',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0申请中1客服理中2已完成',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '客服备注',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '用户id',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `spec_key` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '' COMMENT '商品规格',
  `content` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL COMMENT '退款说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_return_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_return_log`;
CREATE TABLE `ty_return_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `activity_id` int(11) UNSIGNED NOT NULL COMMENT '活动id',
  `order_id` int(11) UNSIGNED NOT NULL COMMENT '需要退款的订单',
  `is_reback` tinyint(1) UNSIGNED NOT NULL COMMENT '0-未退款 1-已退款',
  `reback_time` datetime(0) NULL DEFAULT NULL COMMENT '退款时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订货订单退款操作记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_seller
-- ----------------------------
DROP TABLE IF EXISTS `ty_seller`;
CREATE TABLE `ty_seller`  (
  `seller_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '卖家编号',
  `seller_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家用户名',
  `user_id` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '用户编号',
  `group_id` int(10) UNSIGNED NOT NULL COMMENT '卖家组编号',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺编号',
  `is_admin` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否管理员(0-不是 1-是)',
  `seller_quicklink` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖家快捷操作',
  `last_login_time` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '最后登录时间',
  `enabled` tinyint(1) NULL DEFAULT 0 COMMENT '激活状态 0启用1关闭',
  `add_time` int(11) NULL DEFAULT NULL,
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '后台登陆密码',
  PRIMARY KEY (`seller_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '卖家用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_seller_group
-- ----------------------------
DROP TABLE IF EXISTS `ty_seller_group`;
CREATE TABLE `ty_seller_group`  (
  `group_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '卖家组编号',
  `group_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '组名',
  `act_limits` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限',
  `smt_limits` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息权限范围',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺编号',
  PRIMARY KEY (`group_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '卖家用户组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_seller_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_seller_log`;
CREATE TABLE `ty_seller_log`  (
  `log_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '日志编号',
  `log_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志内容',
  `log_time` int(10) UNSIGNED NOT NULL COMMENT '日志时间',
  `log_seller_id` int(10) UNSIGNED NOT NULL COMMENT '卖家编号',
  `log_seller_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家帐号',
  `log_store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺编号',
  `log_seller_ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '卖家ip',
  `log_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日志url',
  `log_state` tinyint(3) UNSIGNED NOT NULL COMMENT '日志状态(0-失败 1-成功)',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 168 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '卖家日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_share
-- ----------------------------
DROP TABLE IF EXISTS `ty_share`;
CREATE TABLE `ty_share`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NULL DEFAULT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  `sku_id` int(11) NULL DEFAULT NULL,
  `add_time` int(11) NULL DEFAULT NULL,
  `is_del` tinyint(4) NULL DEFAULT 0,
  `share_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_shipping
-- ----------------------------
DROP TABLE IF EXISTS `ty_shipping`;
CREATE TABLE `ty_shipping`  (
  `shipping_id` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT,
  `shipping_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '快递代号',
  `shipping_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '快递名称',
  `shipping_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '描述',
  `insure` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '保险',
  `enabled` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否开启',
  PRIMARY KEY (`shipping_id`) USING BTREE,
  INDEX `shipping_code`(`shipping_code`, `enabled`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_shipping_area
-- ----------------------------
DROP TABLE IF EXISTS `ty_shipping_area`;
CREATE TABLE `ty_shipping_area`  (
  `shipping_area_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id自增',
  `shipping_area_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配送模板名称',
  `shipping_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '物流公司',
  `config` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '物流配置',
  `update_time` int(11) NULL DEFAULT NULL,
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '是否默认',
  `is_close` tinyint(1) NULL DEFAULT 1 COMMENT '是否关闭：1开启，0关闭',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家id',
  PRIMARY KEY (`shipping_area_id`) USING BTREE,
  INDEX `shipping_id`(`shipping_code`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_signin
-- ----------------------------
DROP TABLE IF EXISTS `ty_signin`;
CREATE TABLE `ty_signin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '签到表id',
  `user_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `last_signin_time` datetime(0) NOT NULL COMMENT '上次签到',
  `today_signin_time` datetime(0) NOT NULL COMMENT '今日签到',
  `continue_day` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '连续签到天数',
  `seedlings` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '奖励积分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 88 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户签到表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `ty_sms_log`;
CREATE TABLE `ty_sms_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `mobile` varchar(11) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '' COMMENT '手机号',
  `session_id` varchar(128) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '' COMMENT 'session_id',
  `add_time` int(11) NULL DEFAULT 0 COMMENT '发送时间',
  `code` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT '' COMMENT '验证码',
  `seller_id` int(10) NULL DEFAULT 0,
  `is_check` tinyint(1) NULL DEFAULT 0 COMMENT '0-未验证 1-已验证',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 295 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '短信验证码记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_spec
-- ----------------------------
DROP TABLE IF EXISTS `ty_spec`;
CREATE TABLE `ty_spec`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格表',
  `name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格名称',
  `order` int(11) NULL DEFAULT 10 COMMENT '排序',
  `search_index` tinyint(1) NULL DEFAULT 0 COMMENT '是否需要检索',
  `cat_id1` int(11) NULL DEFAULT 0 COMMENT '一级分类',
  `cat_id2` int(11) NULL DEFAULT 0 COMMENT '二级分类',
  `cat_id3` int(11) NULL DEFAULT 0 COMMENT '三级分类',
  `alias_name` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '别名',
  `store_id` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_spec_goods_price
-- ----------------------------
DROP TABLE IF EXISTS `ty_spec_goods_price`;
CREATE TABLE `ty_spec_goods_price`  (
  `sgp_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NULL DEFAULT 0 COMMENT '商品id',
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '规格键名',
  `key_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '规格键名中文',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `partner_price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '合伙人拿货价',
  `store_count` int(11) UNSIGNED NULL DEFAULT 10 COMMENT '库存数量',
  `bar_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '商品条形码',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'SKU',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺商家id',
  `cost_price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '成本价',
  PRIMARY KEY (`sgp_id`) USING BTREE,
  INDEX `gk`(`goods_id`, `key`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3164 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_spec_image
-- ----------------------------
DROP TABLE IF EXISTS `ty_spec_image`;
CREATE TABLE `ty_spec_image`  (
  `goods_id` int(11) NULL DEFAULT 0 COMMENT '商品规格图片表id',
  `spec_image_id` int(11) NULL DEFAULT 0 COMMENT '规格项id',
  `src` varchar(512) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '商品规格图片路径',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家id',
  INDEX `goods_id`(`goods_id`) USING BTREE,
  INDEX `spec_img_id`(`spec_image_id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_spec_integral_price
-- ----------------------------
DROP TABLE IF EXISTS `ty_spec_integral_price`;
CREATE TABLE `ty_spec_integral_price`  (
  `sgp_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `integral_id` int(11) NULL DEFAULT 0 COMMENT '商品id',
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '规格键名',
  `key_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '规格键名中文',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `partner_price` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '合伙人拿货价',
  `store_count` int(11) UNSIGNED NULL DEFAULT 10 COMMENT '库存数量',
  `bar_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '商品条形码',
  `sku` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'SKU',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺商家id',
  PRIMARY KEY (`sgp_id`) USING BTREE,
  INDEX `gk`(`integral_id`, `key`) USING BTREE,
  INDEX `integral_id`(`integral_id`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_spec_item
-- ----------------------------
DROP TABLE IF EXISTS `ty_spec_item`;
CREATE TABLE `ty_spec_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '规格项id',
  `spec_id` int(11) NULL DEFAULT NULL COMMENT '规格id',
  `item` varchar(54) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '规格项',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `spec_id`(`spec_id`) USING BTREE,
  INDEX `item`(`item`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1823 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_spec_type
-- ----------------------------
DROP TABLE IF EXISTS `ty_spec_type`;
CREATE TABLE `ty_spec_type`  (
  `type_id` int(10) UNSIGNED NOT NULL COMMENT '类型id',
  `spec_id` int(10) UNSIGNED NOT NULL COMMENT '规格id'
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类型与规格对应表' ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_spike_buy
-- ----------------------------
DROP TABLE IF EXISTS `ty_spike_buy`;
CREATE TABLE `ty_spike_buy`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '秒杀商品表ID',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '活动名称',
  `order_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '已下单人数',
  `virtual_num` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '虚拟购买数',
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '秒杀介绍',
  `recommend` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否推荐 0.未推荐 1.已推荐',
  `store_id` int(10) NULL DEFAULT 0 COMMENT '商家店铺ID',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '秒杀状态，0待审核，1正常2拒绝3关闭',
  `start_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '开始时间',
  `end_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '结束时间',
  `is_del` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '秒杀活动表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_store
-- ----------------------------
DROP TABLE IF EXISTS `ty_store`;
CREATE TABLE `ty_store`  (
  `store_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺索引id',
  `style_id` int(10) NULL DEFAULT NULL COMMENT '风格id',
  `store_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺名称',
  `grade_id` int(11) NULL DEFAULT 0 COMMENT '店铺等级',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员名称',
  `real_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `seller_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店主卖家用户名',
  `sc_id` int(11) NOT NULL DEFAULT 0 COMMENT '店铺分类',
  `company_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺公司名称',
  `province_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺所在省份ID',
  `city_id` mediumint(8) NOT NULL DEFAULT 0 COMMENT '店铺所在城市ID',
  `district` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺所在地区ID',
  `store_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '详细地区',
  `store_zip` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '邮政编码',
  `store_state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '店铺状态，0关闭，1开启，2审核中',
  `store_close_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺关闭原因',
  `store_sort` int(11) NOT NULL DEFAULT 0 COMMENT '店铺排序',
  `store_rebate_paytime` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '店铺结算类型',
  `store_time` int(11) NULL DEFAULT NULL COMMENT '开店时间',
  `store_end_time` int(11) NULL DEFAULT NULL COMMENT '店铺关闭时间',
  `store_logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺logo',
  `store_banner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺横幅',
  `store_avatar` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺头像',
  `seo_keywords` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺seo关键字',
  `seo_description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺seo描述',
  `store_aliwangwang` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '阿里旺旺',
  `store_qq` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `store_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家电话',
  `store_zy` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '主营商品',
  `store_domain` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺二级域名',
  `store_recommend` tinyint(1) NOT NULL DEFAULT 0 COMMENT '推荐，0为否，1为是，默认为0',
  `store_theme` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'default' COMMENT '店铺当前主题',
  `store_credit` int(10) NOT NULL DEFAULT 0 COMMENT '店铺信用',
  `store_desccredit` decimal(3, 2) NOT NULL DEFAULT 0.00 COMMENT '描述相符度分数',
  `store_servicecredit` decimal(3, 2) NOT NULL DEFAULT 0.00 COMMENT '服务态度分数',
  `store_deliverycredit` decimal(3, 2) NOT NULL DEFAULT 0.00 COMMENT '发货速度分数',
  `store_collect` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺收藏数量',
  `store_slide` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '店铺幻灯片',
  `store_slide_url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '店铺幻灯片链接',
  `store_printdesc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '打印订单页面下方说明文字',
  `store_sales` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺销量',
  `store_presales` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '售前客服',
  `store_aftersales` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '售后客服',
  `store_workingtime` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工作时间',
  `store_free_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '超出该金额免运费，大于0才表示该值有效',
  `store_decoration_switch` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺装修开关(0-关闭 装修编号-开启)',
  `store_decoration_only` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '开启店铺装修时，仅显示店铺装修(1-是 0-否',
  `is_own_shop` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否自营店铺 1是 0否',
  `bind_all_gc` tinyint(1) NULL DEFAULT 0 COMMENT '自营店是否绑定全部分类 0否1是',
  `qitian` tinyint(1) NULL DEFAULT 0 COMMENT '7天退换',
  `certified` tinyint(1) NULL DEFAULT 0 COMMENT '正品保障',
  `returned` tinyint(1) NULL DEFAULT 0 COMMENT '退货承诺',
  `store_free_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商家配送时间',
  `mb_slide` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '手机店铺 轮播图链接地址',
  `mb_slide_url` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '手机版广告链接',
  `deliver_region` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺默认配送区域',
  `cod` tinyint(1) NULL DEFAULT 0 COMMENT '货到付款',
  `two_hour` tinyint(1) NULL DEFAULT 0 COMMENT '两小时发货',
  `ensure` tinyint(1) NULL DEFAULT 0 COMMENT '保证服务开关',
  `deposit` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '保证金额',
  `deposit_icon` tinyint(1) NULL DEFAULT 0 COMMENT '保证金显示开关',
  `store_money` decimal(12, 2) NULL DEFAULT 0.00 COMMENT '店铺可用资金',
  `pending_money` decimal(12, 2) NULL DEFAULT 0.00 COMMENT '待结算资金',
  `goods_examine` tinyint(1) NULL DEFAULT 0 COMMENT '店铺发布商品是否需要审核',
  `store_order_promotion_type` tinyint(1) NULL DEFAULT 0 COMMENT '订单促销类型',
  `is_check` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '审核状态 0-待审核 1-审核成功 2-审核失败',
  `view_num` int(11) NULL DEFAULT 0 COMMENT '浏览人数',
  `batch_quantity` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '起批量',
  `store_description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '店铺描述',
  `class_id` int(11) NULL DEFAULT NULL COMMENT '主营类目id  与ty_class关联',
  `web_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'web端客服url',
  `web_goods_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'web端商品客服',
  `web_order_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'web端订单客服',
  `app_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app端普通客服链接',
  `app_goods_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app端商品客服链接',
  `app_order_service` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'app端订单客服链接',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '未删除0，已删除1',
  PRIMARY KEY (`store_id`) USING BTREE,
  INDEX `store_name`(`store_name`) USING BTREE,
  INDEX `sc_id`(`sc_id`) USING BTREE,
  INDEX `store_state`(`store_state`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '供应商数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_store_bind_class
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_bind_class`;
CREATE TABLE `ty_store_bind_class`  (
  `bid` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_id` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '店铺ID',
  `commis_rate` tinyint(4) UNSIGNED NULL DEFAULT 0 COMMENT '佣金比例',
  `class_1` mediumint(9) UNSIGNED NULL DEFAULT 0 COMMENT '一级分类',
  `class_2` mediumint(9) UNSIGNED NULL DEFAULT 0 COMMENT '二级分类',
  `class_3` mediumint(9) UNSIGNED NULL DEFAULT 0 COMMENT '三级分类',
  `state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态0审核中1审核通过 2审核失败',
  PRIMARY KEY (`bid`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺可发布商品类目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_store_class
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_class`;
CREATE TABLE `ty_store_class`  (
  `sc_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '索引ID',
  `sc_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `sc_bail` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '保证金数额',
  `sc_sort` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`sc_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_collect
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_collect`;
CREATE TABLE `ty_store_collect`  (
  `log_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NULL DEFAULT NULL,
  `store_id` int(10) NULL DEFAULT NULL,
  `add_time` int(11) NULL DEFAULT NULL COMMENT '收藏店铺时间',
  `store_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收藏会员名称',
  PRIMARY KEY (`log_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 668 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_decoration
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_decoration`;
CREATE TABLE `ty_store_decoration`  (
  `decoration_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '装修编号',
  `decoration_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '装修名称',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺编号',
  `decoration_setting` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '装修整体设置(背景、边距等)',
  `decoration_nav` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '装修导航',
  `decoration_banner` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '装修头部banner',
  PRIMARY KEY (`decoration_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺装修表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_decoration_block
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_decoration_block`;
CREATE TABLE `ty_store_decoration_block`  (
  `block_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '装修块编号',
  `decoration_id` int(10) UNSIGNED NOT NULL COMMENT '装修编号',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺编号',
  `block_layout` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '块布局',
  `block_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '块内容',
  `block_module_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '装修块模块类型',
  `block_full_width` tinyint(3) UNSIGNED NULL DEFAULT NULL COMMENT '是否100%宽度(0-否 1-是)',
  `block_sort` tinyint(3) UNSIGNED NOT NULL DEFAULT 1 COMMENT '块排序',
  PRIMARY KEY (`block_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺装修块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_distribut
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_distribut`;
CREATE TABLE `ty_store_distribut`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id自增',
  `switch` tinyint(1) NULL DEFAULT 0 COMMENT '分销开关',
  `condition` tinyint(1) NULL DEFAULT 0 COMMENT '成为分销商条件 0 直接成为分销商,1成功购买商品后成为分销商',
  `distribut_pattern` tinyint(1) NULL DEFAULT 0 COMMENT '分销模式 0 按商品设置的分成金额 1 按订单设置的分成比例',
  `first_rate` tinyint(1) NULL DEFAULT 0 COMMENT '一级分销商比例',
  `second_rate` tinyint(1) NULL DEFAULT 0 COMMENT '二级分销商名称',
  `third_rate` tinyint(1) NULL DEFAULT 0 COMMENT '三级分销商名称',
  `date` tinyint(1) NULL DEFAULT 15 COMMENT '订单收货确认后多少天可以分成',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '店铺id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_store_extend
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_extend`;
CREATE TABLE `ty_store_extend`  (
  `store_id` mediumint(8) UNSIGNED NOT NULL COMMENT '店铺ID',
  `express` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '快递公司ID的组合',
  `pricerange` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '店铺统计设置的商品价格区间',
  `orderpricerange` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '店铺统计设置的订单价格区间',
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_goods_class
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_goods_class`;
CREATE TABLE `ty_store_goods_class`  (
  `cat_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '索引ID',
  `cat_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '店铺商品分类名称',
  `parent_id` int(11) NOT NULL COMMENT '父级id',
  `store_id` int(11) NOT NULL DEFAULT 0 COMMENT '店铺id',
  `cat_sort` int(11) NOT NULL DEFAULT 0 COMMENT '商品分类排序',
  `is_show` tinyint(1) NOT NULL DEFAULT 0 COMMENT '分类显示状态 0-不显示 1-显示',
  `is_nav_show` tinyint(1) NULL DEFAULT 0 COMMENT '是否显示在导航栏',
  `is_recommend` tinyint(1) NULL DEFAULT 0 COMMENT '是否首页推荐',
  `show_num` smallint(4) NULL DEFAULT 4 COMMENT '首页此类商品显示数量',
  PRIMARY KEY (`cat_id`) USING BTREE,
  INDEX `stc_parent_id`(`parent_id`, `cat_sort`) USING BTREE,
  INDEX `store_id`(`store_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_grade
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_grade`;
CREATE TABLE `ty_store_grade`  (
  `sg_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '索引ID',
  `sg_name` char(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `sg_goods_limit` mediumint(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '允许发布的商品数量',
  `sg_album_limit` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '允许上传图片数量',
  `sg_space_limit` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '上传空间大小，单位MB',
  `sg_template_limit` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '选择店铺模板套数',
  `sg_template` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '模板内容',
  `sg_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '开店费用(元/年)',
  `sg_description` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '申请说明',
  `sg_function` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附加功能',
  `sg_sort` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '级别，数目越大级别越高',
  PRIMARY KEY (`sg_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺等级表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_info
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_info`;
CREATE TABLE `ty_store_info`  (
  `id` int(30) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_id` int(100) UNSIGNED NULL DEFAULT NULL,
  `mb_slide` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `mb_slide_url` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `edit_time` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `review_status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_store_msg
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_msg`;
CREATE TABLE `ty_store_msg`  (
  `sm_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '店铺消息id',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺id',
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '消息内容',
  `addtime` int(10) UNSIGNED NOT NULL COMMENT '发送时间',
  `open` tinyint(1) NOT NULL DEFAULT 0 COMMENT '消息是否已被查看',
  PRIMARY KEY (`sm_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1558 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺消息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_msg_setting
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_msg_setting`;
CREATE TABLE `ty_store_msg_setting`  (
  `smt_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板编码',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺id',
  `sms_message_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '站内信接收开关，0关闭，1开启',
  `sms_short_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '短消息接收开关，0关闭，1开启',
  `sms_mail_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '邮件接收开关，0关闭，1开启',
  `sms_short_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `sms_mail_number` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱号码',
  PRIMARY KEY (`smt_code`, `store_id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺消息接收设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_msg_tpl
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_msg_tpl`;
CREATE TABLE `ty_store_msg_tpl`  (
  `smt_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板编码',
  `smt_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '模板名称',
  `smt_message_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '站内信默认开关，0关，1开',
  `smt_message_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '站内信内容',
  `smt_message_forced` tinyint(3) UNSIGNED NOT NULL COMMENT '站内信强制接收，0否，1是',
  `smt_short_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '短信默认开关，0关，1开',
  `smt_short_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信内容',
  `smt_short_forced` tinyint(3) UNSIGNED NOT NULL COMMENT '短信强制接收，0否，1是',
  `smt_mail_switch` tinyint(3) UNSIGNED NOT NULL COMMENT '邮件默认开，0关，1开',
  `smt_mail_subject` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件标题',
  `smt_mail_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件内容',
  `smt_mail_forced` tinyint(3) UNSIGNED NOT NULL COMMENT '邮件强制接收，0否，1是',
  PRIMARY KEY (`smt_code`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商家消息模板' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_navigation
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_navigation`;
CREATE TABLE `ty_store_navigation`  (
  `sn_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '导航ID',
  `sn_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '导航名称',
  `sn_store_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '卖家店铺ID',
  `sn_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '导航内容',
  `sn_sort` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '导航排序',
  `sn_is_show` tinyint(1) NOT NULL DEFAULT 0 COMMENT '导航是否显示',
  `sn_add_time` int(11) NOT NULL COMMENT '添加时间',
  `sn_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺导航的外链URL',
  `sn_new_open` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺导航外链是否在新窗口打开：0不开新窗口1开新窗口，默认是0',
  PRIMARY KEY (`sn_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '卖家店铺导航信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_remittance
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_remittance`;
CREATE TABLE `ty_store_remittance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家转账记录表',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '汇款的商家id',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '收款银行名称',
  `account_bank` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账号',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '开户人名称',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '汇款金额',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0汇款失败 1汇款成功',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '汇款时间',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '汇款备注',
  `admin_id` int(11) NULL DEFAULT 0 COMMENT '管理员id',
  `withdrawals_id` int(11) NULL DEFAULT 0 COMMENT '提现申请表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_reopen
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_reopen`;
CREATE TABLE `ty_store_reopen`  (
  `re_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `re_grade_id` smallint(5) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺等级ID',
  `re_grade_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `re_grade_price` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '等级收费(元/年)',
  `re_year` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '续签时长(年)',
  `re_pay_amount` decimal(10, 2) UNSIGNED NOT NULL DEFAULT 0.00 COMMENT '应付总金额',
  `re_store_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名字',
  `re_store_id` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺ID',
  `re_state` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态0默认，未上传凭证1审核中2审核通过',
  `re_start_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '有效期开始时间',
  `re_end_time` int(10) UNSIGNED NULL DEFAULT NULL COMMENT '有效期结束时间',
  `re_create_time` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '记录创建时间',
  `re_pay_cert` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款凭证',
  `re_pay_cert_explain` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款凭证说明',
  PRIMARY KEY (`re_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '续签内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_return_address
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_return_address`;
CREATE TABLE `ty_store_return_address`  (
  `return_address_id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `store_id` int(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '店铺id',
  `consignee` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `country` int(11) NOT NULL DEFAULT 0 COMMENT '国家',
  `province` int(11) NOT NULL DEFAULT 0 COMMENT '省份',
  `city` int(11) NOT NULL DEFAULT 0 COMMENT '城市',
  `district` int(11) NOT NULL DEFAULT 0 COMMENT '地区',
  `twon` int(11) NULL DEFAULT 0 COMMENT '乡镇',
  `address` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `zipcode` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮政编码',
  `mobile` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '默认退货地址',
  PRIMARY KEY (`return_address_id`) USING BTREE,
  INDEX `user_id`(`store_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商家退货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_style
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_style`;
CREATE TABLE `ty_store_style`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `style_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '风格名字',
  `name_alias` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文别称',
  `sort` int(11) UNSIGNED NOT NULL DEFAULT 1 COMMENT '排序',
  `is_show` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '0-隐藏 1-显示',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '风格图片',
  `add_time` int(11) UNSIGNED NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `style_name`(`style_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺风格表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_store_watermark
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_watermark`;
CREATE TABLE `ty_store_watermark`  (
  `wm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '水印id',
  `jpeg_quality` int(3) NOT NULL DEFAULT 90 COMMENT 'jpeg图片质量',
  `wm_image_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '水印图片的路径以及文件名',
  `wm_image_pos` tinyint(1) NOT NULL DEFAULT 1 COMMENT '水印图片放置的位置',
  `wm_image_transition` int(3) NOT NULL DEFAULT 20 COMMENT '水印图片与原图片的融合度 ',
  `wm_text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '水印文字',
  `wm_text_size` int(3) NOT NULL DEFAULT 20 COMMENT '水印文字大小',
  `wm_text_angle` tinyint(1) NOT NULL DEFAULT 4 COMMENT '水印文字角度',
  `wm_text_pos` tinyint(1) NOT NULL DEFAULT 3 COMMENT '水印文字放置位置',
  `wm_text_font` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '水印文字的字体',
  `wm_text_color` varchar(7) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '#CCCCCC' COMMENT '水印字体的颜色值',
  `wm_is_open` tinyint(1) NOT NULL DEFAULT 0 COMMENT '水印是否开启 0关闭 1开启',
  `store_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  PRIMARY KEY (`wm_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺水印图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_waybill
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_waybill`;
CREATE TABLE `ty_store_waybill`  (
  `store_waybill_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '店铺运单模板编号',
  `store_id` int(10) UNSIGNED NOT NULL COMMENT '店铺编号',
  `express_id` int(10) UNSIGNED NOT NULL COMMENT '物流公司编号',
  `waybill_id` int(10) UNSIGNED NOT NULL COMMENT '运单模板编号',
  `waybill_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运单模板名称',
  `store_waybill_data` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺自定义设置',
  `is_default` tinyint(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否默认模板',
  `store_waybill_left` int(11) NOT NULL DEFAULT 0 COMMENT '店铺运单左偏移',
  `store_waybill_top` int(11) NOT NULL DEFAULT 0 COMMENT '店铺运单上偏移',
  PRIMARY KEY (`store_waybill_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '店铺运单模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_store_withdrawals
-- ----------------------------
DROP TABLE IF EXISTS `ty_store_withdrawals`;
CREATE TABLE `ty_store_withdrawals`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家提现申请表',
  `store_id` int(11) NULL DEFAULT 0 COMMENT '商家id',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '申请日期',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '提现金额',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行名称 如支付宝 微信 中国银行 农业银行等',
  `account_bank` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账号',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账户名 可以是支付宝可以其他银行',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '提现备注',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '提现状态0申请中1申请成功2申请失败',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '0未删除，1已删除',
  `deleted_time` int(11) NULL DEFAULT NULL COMMENT '何时删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_suppliers
-- ----------------------------
DROP TABLE IF EXISTS `ty_suppliers`;
CREATE TABLE `ty_suppliers`  (
  `suppliers_id` smallint(5) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `suppliers_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '供应商名称',
  `suppliers_desc` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '供应商描述',
  `is_lock` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '是否被锁定冻结 1-正常 0-冻结',
  `suppliers_contacts` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '供应商联系人',
  `suppliers_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '供应商名字',
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `store_id` int(11) NULL DEFAULT 0,
  `province_id` int(11) NULL DEFAULT NULL,
  `city_id` int(11) NULL DEFAULT NULL,
  `county_id` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `add_time` int(11) NULL DEFAULT NULL,
  `discount` decimal(11, 2) NULL DEFAULT 1.00,
  `last_login_time` int(11) NULL DEFAULT NULL,
  `suppliers_distribut` decimal(11, 2) NULL DEFAULT 0.10 COMMENT '提成比列',
  `supper_total_money` decimal(12, 2) NULL DEFAULT 0.00,
  `supper_money` decimal(12, 2) NULL DEFAULT 0.00,
  `supper_frozen_money` decimal(12, 2) NULL DEFAULT 0.00,
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '账号审核状态 0-审核中 1-审核成功 2-审核失败',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  `reg_time` int(11) UNSIGNED NOT NULL COMMENT '注册时间',
  `company_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`suppliers_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_suppliers_remittance
-- ----------------------------
DROP TABLE IF EXISTS `ty_suppliers_remittance`;
CREATE TABLE `ty_suppliers_remittance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家转账记录表',
  `suppliers_id` int(11) NULL DEFAULT 0 COMMENT '汇款的商家id',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '收款银行名称',
  `account_bank` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账号',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '开户人名称',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '汇款金额',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '0汇款失败 1汇款成功',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '汇款时间',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '汇款备注',
  `admin_id` int(11) NULL DEFAULT 0 COMMENT '管理员id',
  `withdrawals_id` int(11) NULL DEFAULT 0 COMMENT '提现申请表id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_suppliers_withdrawals
-- ----------------------------
DROP TABLE IF EXISTS `ty_suppliers_withdrawals`;
CREATE TABLE `ty_suppliers_withdrawals`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商家提现申请表',
  `suppliers_id` int(11) NULL DEFAULT 0 COMMENT '商家id',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '申请日期',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '提现金额',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行名称 如支付宝 微信 中国银行 农业银行等',
  `account_bank` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账号',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账户名 可以是支付宝可以其他银行',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '提现备注',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '提现状态0申请中1申请成功2申请失败',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_system_menu
-- ----------------------------
DROP TABLE IF EXISTS `ty_system_menu`;
CREATE TABLE `ty_system_menu`  (
  `id` smallint(6) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名字',
  `group` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属分组',
  `right` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '权限码(控制器+动作)',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '删除状态 1删除,0正常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 184 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_topic
-- ----------------------------
DROP TABLE IF EXISTS `ty_topic`;
CREATE TABLE `ty_topic`  (
  `topic_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `topic_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专题标题',
  `topic_image` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专题封面',
  `topic_background_color` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专题背景颜色',
  `topic_background` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专题背景图',
  `topic_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '专题详情',
  `topic_repeat` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '背景重复方式',
  `topic_state` tinyint(1) NULL DEFAULT 1 COMMENT '专题状态1-草稿、2-已发布',
  `topic_margin_top` tinyint(3) NULL DEFAULT 0 COMMENT '正文距顶部距离',
  `ctime` int(11) NULL DEFAULT NULL COMMENT '专题创建时间',
  PRIMARY KEY (`topic_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_transport
-- ----------------------------
DROP TABLE IF EXISTS `ty_transport`;
CREATE TABLE `ty_transport`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '售卖区域ID',
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '售卖区域名称',
  `send_tpl_id` mediumint(8) UNSIGNED NULL DEFAULT NULL COMMENT '发货地区模板ID',
  `store_id` mediumint(8) UNSIGNED NOT NULL COMMENT '店铺ID',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '售卖区域' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_transport_extend
-- ----------------------------
DROP TABLE IF EXISTS `ty_transport_extend`;
CREATE TABLE `ty_transport_extend`  (
  `id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '运费模板扩展ID',
  `area_id` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '市级地区ID组成的串，以，隔开，两端也有，',
  `top_area_id` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '省级地区ID组成的串，以，隔开，两端也有，',
  `area_name` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '地区name组成的串，以，隔开',
  `sprice` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '首件运费',
  `transport_id` mediumint(8) UNSIGNED NOT NULL COMMENT '运费模板ID',
  `transport_title` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运费模板',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运费模板扩展表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_user_address
-- ----------------------------
DROP TABLE IF EXISTS `ty_user_address`;
CREATE TABLE `ty_user_address`  (
  `address_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0,
  `consignee` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '收货人',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮箱地址',
  `country` int(11) NOT NULL DEFAULT 0 COMMENT '国家',
  `province` int(11) NOT NULL DEFAULT 0 COMMENT '省份',
  `city` int(11) NOT NULL DEFAULT 0 COMMENT '城市',
  `district` int(11) NOT NULL DEFAULT 0 COMMENT '地区',
  `twon` int(11) NULL DEFAULT 0 COMMENT '乡镇',
  `address` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `zipcode` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '邮政编码',
  `mobile` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机',
  `is_default` tinyint(1) NULL DEFAULT 0 COMMENT '默认收货地址',
  `is_pickup` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`address_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 186 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_user_level
-- ----------------------------
DROP TABLE IF EXISTS `ty_user_level`;
CREATE TABLE `ty_user_level`  (
  `level_id` smallint(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `level_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头衔名称',
  `amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '等级必要金额',
  `discount` smallint(4) NULL DEFAULT NULL COMMENT '折扣',
  `describe` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`level_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_user_message
-- ----------------------------
DROP TABLE IF EXISTS `ty_user_message`;
CREATE TABLE `ty_user_message`  (
  `rec_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` mediumint(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
  `message_id` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '消息id',
  `category` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '1=>\'系统消息\',2=>\'充值消息\',3=>\'消费消息\',4=>\'积分消息\'\r\n\r\n\r\n//0：审核 1：购买订单2：订货 3活动推送 4订单售后5密码修改6平台爆款消息7资金8系统消息',
  `status` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '查看状态：0未查看，1已查看',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `add_time` int(10) NOT NULL COMMENT '添加时间',
  `del` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '0删除，1未删',
  PRIMARY KEY (`rec_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `message_id`(`message_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 842 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Table structure for ty_user_ruzhu
-- ----------------------------
DROP TABLE IF EXISTS `ty_user_ruzhu`;
CREATE TABLE `ty_user_ruzhu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reg_time` int(11) NOT NULL DEFAULT 0 COMMENT '申请时间',
  `user_id` int(11) NOT NULL DEFAULT 0,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `mobile` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `lbtype` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_users
-- ----------------------------
DROP TABLE IF EXISTS `ty_users`;
CREATE TABLE `ty_users`  (
  `user_id` mediumint(8) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '表id',
  `suppliers_id` int(11) NULL DEFAULT 0 COMMENT '经销商ID',
  `type` tinyint(1) NULL DEFAULT NULL COMMENT '0-用户 1-供应商 2-合伙人 3-设计师 ',
  `suppliers_time` int(11) NULL DEFAULT NULL COMMENT '成为经销商时间',
  `suppliers_distribut` decimal(11, 2) NULL DEFAULT NULL COMMENT '提成比列',
  `email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '邮件',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '密码',
  `sex` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0 保密 1 男 2 女',
  `birthday` int(11) NULL DEFAULT 0 COMMENT '生日',
  `total_money` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '累计消费',
  `user_money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '用户金额',
  `partner_money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '合伙人累计返利金额',
  `frozen_money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '冻结金额',
  `distribut_money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '累积分佣金额',
  `designer_distribut_money` decimal(10, 2) UNSIGNED NULL DEFAULT 0.00 COMMENT '设计师累积分成金额',
  `pay_points` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '消费积分',
  `address_id` mediumint(8) UNSIGNED NULL DEFAULT 0 COMMENT '默认收货地址',
  `reg_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '注册时间',
  `last_login` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '最后登录时间',
  `last_ip` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '最后登录ip',
  `qq` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `mobile_validated` tinyint(3) UNSIGNED NULL DEFAULT 0 COMMENT '是否验证手机',
  `oauth` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '第三方来源 wx weibo alipay',
  `openid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方唯一标示',
  `unionid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `head_pic` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `province` int(6) NULL DEFAULT 0 COMMENT '省份',
  `city` int(6) NULL DEFAULT 0 COMMENT '市区',
  `district` int(6) NULL DEFAULT 0 COMMENT '县',
  `email_validated` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '是否验证电子邮箱',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员用户名',
  `nickname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '第三方返回昵称',
  `level` tinyint(1) NULL DEFAULT 1 COMMENT '会员等级',
  `discount` decimal(10, 2) NULL DEFAULT 1.00 COMMENT '会员折扣，默认1不享受',
  `total_amount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '消费累计额度',
  `is_lock` tinyint(1) NULL DEFAULT 0 COMMENT '是否被锁定冻结',
  `is_distribut` tinyint(1) NULL DEFAULT 0 COMMENT '是否为分销商 0 否 1 是',
  `first_leader` int(11) NULL DEFAULT 0 COMMENT '第一个上级',
  `second_leader` int(11) NULL DEFAULT 0 COMMENT '第二个上级',
  `third_leader` int(11) NULL DEFAULT 0 COMMENT '第三个上级',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用于app 授权类似于session_id',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `country` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `seat` int(255) NULL DEFAULT 0,
  `call_cate` int(11) NULL DEFAULT 0,
  `call_service` int(11) NULL DEFAULT 0,
  `call_mf` int(11) NULL DEFAULT 0,
  `call_s` int(11) NULL DEFAULT 0,
  `wait_num` int(11) NULL DEFAULT 0,
  `wait_type` int(255) NULL DEFAULT 0,
  `invite_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '要请码',
  `supper_total_money` decimal(12, 2) NULL DEFAULT 0.00,
  `supper_money` decimal(12, 2) NULL DEFAULT 0.00,
  `supper_frozen_money` decimal(12, 2) NULL DEFAULT 0.00,
  `status` tinyint(1) NOT NULL DEFAULT 3 COMMENT '账号审核状态 0-审核中 1-审核成功 2-审核失败 3-待传资料',
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '审核意见',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户名',
  `store_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店铺名字',
  `is_live` tinyint(1) NULL DEFAULT 0 COMMENT '0没有直播权限  1 有  2 禁用  3 审核中',
  `live_room` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '直播房间号',
  `live_apply_time` datetime(0) NULL DEFAULT NULL COMMENT '主播申请日期',
  `ry_token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '融云token',
  `esign` tinyint(1) NULL DEFAULT 0 COMMENT '0-未签订合同 1-已签订合同',
  `real_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '真实姓名',
  `id_card` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '身份证号码',
  `doc_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '合同地址',
  `account_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'e签宝账号',
  `flow_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '用于查询合同签署详情',
  `sign_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '签署地址,链接永久有效',
  `sign_short_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '签署短链地址，链接30天有效',
  `view_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '签署任务查看地址',
  `download_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '合同下载地址',
  `authorization_org_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '企业账户id',
  `seal_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '印章id',
  `contract_type` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '合同类型 1-个人对公司 2-公司对公司',
  `llpay_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '连连支付链接',
  `job` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '职业',
  `income` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '收入',
  `education` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '教育',
  `industry` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '所在行业',
  `marriage` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '0 保密 1已婚 2未婚',
  `update_time` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '修改时间',
  `year` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '年',
  `month` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '月',
  `day` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '日',
  `appuid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '与酷家乐绑定账号用',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 221 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '品牌服务商及供应商主表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_web_message
-- ----------------------------
DROP TABLE IF EXISTS `ty_web_message`;
CREATE TABLE `ty_web_message`  (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '短消息索引id',
  `message_parent_id` int(11) NOT NULL COMMENT '回复短消息message_id',
  `from_member_id` int(11) NOT NULL COMMENT '短消息发送人',
  `to_member_id` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短消息接收人',
  `message_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短消息标题',
  `message_body` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短消息内容',
  `message_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短消息发送时间',
  `message_update_time` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短消息回复更新时间',
  `message_open` tinyint(1) NOT NULL DEFAULT 0 COMMENT '短消息打开状态',
  `message_state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '短消息状态，0为正常状态，1为发送人删除状态，2为接收人删除状态',
  `message_type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0为私信、1为系统消息、2为留言',
  `read_member_id` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已经读过该消息的会员id',
  `del_member_id` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已经删除该消息的会员id',
  `message_ismore` tinyint(1) NOT NULL DEFAULT 0 COMMENT '站内信是否为一条发给多个用户 0为否 1为多条 ',
  `from_member_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发信息人用户名',
  `to_member_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人用户名',
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `from_member_id`(`from_member_id`) USING BTREE,
  INDEX `to_member_id`(`to_member_id`(255)) USING BTREE,
  INDEX `message_ismore`(`message_ismore`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '短消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_withdrawals
-- ----------------------------
DROP TABLE IF EXISTS `ty_withdrawals`;
CREATE TABLE `ty_withdrawals`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提现申请表',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '用户id',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '申请日期',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '提现金额',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行名称 如支付宝 微信 中国银行 农业银行等',
  `account_bank` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账号',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账户名 可以是支付宝可以其他银行',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '提现备注',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '提现状态0申请中1申请成功2申请失败',
  `type` tinyint(1) UNSIGNED NULL DEFAULT NULL,
  `way` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '提现方式 1微信，2支付宝 ，3银行',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '打款凭证',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_withdrawals0
-- ----------------------------
DROP TABLE IF EXISTS `ty_withdrawals0`;
CREATE TABLE `ty_withdrawals0`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '提现申请表',
  `partner_trade_no` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商户订单号',
  `user_id` int(11) NULL DEFAULT 0 COMMENT '用户id',
  `create_time` int(11) NULL DEFAULT 0 COMMENT '申请日期',
  `money` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '提现金额',
  `bank_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行名称 如支付宝 微信 中国银行 农业银行等',
  `account_bank` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账号',
  `account_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '银行账户名 可以是支付宝可以其他银行',
  `remark` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '提现备注',
  `status` tinyint(1) NULL DEFAULT 0 COMMENT '提现状态0申请中1申请成功2申请失败',
  `type` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '提现方式 1微信，2支付宝 ，3银行',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `partner_trade_no`(`partner_trade_no`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_wx_img
-- ----------------------------
DROP TABLE IF EXISTS `ty_wx_img`;
CREATE TABLE `ty_wx_img`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介',
  `pic` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '封面图片',
  `url` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图文外链地址',
  `createtime` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uptatetime` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `goods_id` int(11) NOT NULL DEFAULT 0,
  `goods_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信图文' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_wx_keyword
-- ----------------------------
DROP TABLE IF EXISTS `ty_wx_keyword`;
CREATE TABLE `ty_wx_keyword`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` int(11) NOT NULL COMMENT '对应表ID',
  `token` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'TEXT' COMMENT '关键词操作类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pid`(`pid`) USING BTREE,
  INDEX `token`(`token`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 325 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_wx_menu
-- ----------------------------
DROP TABLE IF EXISTS `ty_wx_menu`;
CREATE TABLE `ty_wx_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` tinyint(1) NULL DEFAULT 1 COMMENT '菜单级别',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sort` int(5) NULL DEFAULT 0 COMMENT '排序',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '0 view 1 click',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pid` int(11) NULL DEFAULT 0 COMMENT '上级菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_wx_msg
-- ----------------------------
DROP TABLE IF EXISTS `ty_wx_msg`;
CREATE TABLE `ty_wx_msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '微信小程序消息',
  `order_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `form_id` int(11) NULL DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 340 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for ty_wx_news
-- ----------------------------
DROP TABLE IF EXISTS `ty_wx_news`;
CREATE TABLE `ty_wx_news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `uptatetime` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `token` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图文组合id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信图文' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_wx_text
-- ----------------------------
DROP TABLE IF EXISTS `ty_wx_text`;
CREATE TABLE `ty_wx_text`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `uname` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `keyword` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `precisions` tinyint(1) NOT NULL DEFAULT 0,
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `updatetime` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `click` int(11) NOT NULL,
  `token` char(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文本回复表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_wx_user
-- ----------------------------
DROP TABLE IF EXISTS `ty_wx_user`;
CREATE TABLE `ty_wx_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '表id',
  `uid` int(11) NOT NULL COMMENT 'uid',
  `wxname` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公众号名称',
  `aeskey` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'aeskey',
  `encode` tinyint(1) NOT NULL DEFAULT 0 COMMENT 'encode',
  `appid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'appid',
  `appsecret` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'appsecret',
  `wxid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公众号原始ID',
  `weixin` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信号',
  `headerpic` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像地址',
  `token` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
  `w_token` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '微信对接token',
  `create_time` int(11) NOT NULL COMMENT 'create_time',
  `updatetime` int(11) NOT NULL COMMENT 'updatetime',
  `tplcontentid` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容模版ID',
  `share_ticket` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分享ticket',
  `share_dated` char(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'share_dated',
  `authorizer_access_token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'authorizer_access_token',
  `authorizer_refresh_token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'authorizer_refresh_token',
  `authorizer_expires` char(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'authorizer_expires',
  `type` tinyint(1) NOT NULL DEFAULT 0 COMMENT '类型',
  `web_access_token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 网页授权token',
  `web_refresh_token` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'web_refresh_token',
  `web_expires` int(11) NOT NULL COMMENT '过期时间',
  `qr` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qr',
  `menu_config` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '菜单',
  `wait_access` tinyint(1) NULL DEFAULT 0 COMMENT '微信接入状态,0待接入1已接入',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`uid`) USING BTREE,
  INDEX `uid_2`(`uid`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微信公共帐号配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ty_yuyue
-- ----------------------------
DROP TABLE IF EXISTS `ty_yuyue`;
CREATE TABLE `ty_yuyue`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `con` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户留言',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机号',
  `designer_id` int(11) NULL DEFAULT NULL COMMENT '设计师id',
  `add_time` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '插入时间',
  `yue_time` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预约时间',
  `is_see` int(1) NULL DEFAULT 0 COMMENT '是否已处理 1是 0否',
  `deleted` int(2) NOT NULL DEFAULT 1 COMMENT '1未删除 0已删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
