## Mybatis 29道练习题

### 数据库smbms.sql

------



```sql
CREATE DATABASE smbms CHARACTER SET utf8 COLLATE utf8_general_ci;

/*
 Navicat Premium Data Transfer

 Source Server         : admin
 Source Server Type    : MySQL
 Source Server Version : 50556
 Source Host           : localhost:3306
 Source Schema         : smbms

 Target Server Type    : MySQL
 Target Server Version : 50556
 File Encoding         : 65001

 Date: 14/10/2018 19:35:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for smbms_address
-- ----------------------------
DROP TABLE IF EXISTS `smbms_address`;
CREATE TABLE `smbms_address`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `contact` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系人姓名',
    `addressDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '收货地址明细',
    `postCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮编',
    `tel` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系人电话',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '修改者',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
    `userId` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_address
-- ----------------------------
INSERT INTO `smbms_address` VALUES (1, '王丽', '北京市东城区东交民巷44号', '100010', '13678789999', 1, '2016-04-13 00:00:00', NULL, NULL, 1);
INSERT INTO `smbms_address` VALUES (2, '张红丽', '北京市海淀区丹棱街3号', '100000', '18567672312', 1, '2016-04-13 00:00:00', NULL, NULL, 1);
INSERT INTO `smbms_address` VALUES (3, '任志强', '北京市东城区美术馆后街23号', '100021', '13387906742', 1, '2016-04-13 00:00:00', NULL, NULL, 1);
INSERT INTO `smbms_address` VALUES (4, '曹颖', '北京市朝阳区朝阳门南大街14号', '100053', '13568902323', 1, '2016-04-13 00:00:00', NULL, NULL, 2);
INSERT INTO `smbms_address` VALUES (5, '李慧', '北京市西城区三里河路南三巷3号', '100032', '18032356666', 1, '2016-04-13 00:00:00', NULL, NULL, 3);
INSERT INTO `smbms_address` VALUES (6, '王国强', '北京市顺义区高丽营镇金马工业区18号', '100061', '13787882222', 1, '2016-04-13 00:00:00', NULL, NULL, 3);

-- ----------------------------
-- Table structure for smbms_bill
-- ----------------------------
DROP TABLE IF EXISTS `smbms_bill`;
CREATE TABLE `smbms_bill`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `billCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '账单编码',
    `productName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品名称',
    `productDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品描述',
    `productUnit` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '商品单位',
    `productCount` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品数量',
    `totalPrice` decimal(20, 2) NULL DEFAULT NULL COMMENT '商品总额',
    `isPayment` int(10) NULL DEFAULT NULL COMMENT '是否支付（1：未支付 2：已支付）',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者（userId）',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者（userId）',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `providerId` int(20) NULL DEFAULT NULL COMMENT '供应商ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_bill
-- ----------------------------
INSERT INTO `smbms_bill` VALUES (1, 'BILL2016_001', '洗发水、护发素', '日用品-洗发、护发', '瓶', 500.00, 25000.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 13);
INSERT INTO `smbms_bill` VALUES (2, 'BILL2016_002', '香皂、肥皂、药皂', '日用品-皂类', '块', 1000.00, 10000.00, 2, 1, '2016-03-23 04:20:40', NULL, NULL, 13);
INSERT INTO `smbms_bill` VALUES (3, 'BILL2016_003', '大豆油', '食品-食用油', '斤', 300.00, 5890.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 6);
INSERT INTO `smbms_bill` VALUES (4, 'BILL2016_004', '橄榄油', '食品-进口食用油', '斤', 200.00, 9800.00, 2, 1, '2013-10-10 03:12:13', NULL, NULL, 7);
INSERT INTO `smbms_bill` VALUES (5, 'BILL2016_005', '洗洁精', '日用品-厨房清洁', '瓶', 500.00, 7000.00, 2, 1, '2014-12-14 13:02:03', NULL, NULL, 9);
INSERT INTO `smbms_bill` VALUES (6, 'BILL2016_006', '美国大杏仁', '食品-坚果', '袋', 300.00, 5000.00, 2, 1, '2016-04-14 06:08:09', NULL, NULL, 4);
INSERT INTO `smbms_bill` VALUES (7, 'BILL2016_007', '沐浴液、精油', '日用品-沐浴类', '瓶', 500.00, 23000.00, 1, 1, '2016-07-22 10:10:22', NULL, NULL, 14);
INSERT INTO `smbms_bill` VALUES (8, 'BILL2016_008', '不锈钢盘碗', '日用品-厨房用具', '个', 600.00, 6000.00, 2, 1, '2016-04-14 05:12:13', NULL, NULL, 14);
INSERT INTO `smbms_bill` VALUES (9, 'BILL2016_009', '塑料杯', '日用品-杯子', '个', 350.00, 1750.00, 2, 1, '2016-02-04 11:40:20', NULL, NULL, 14);
INSERT INTO `smbms_bill` VALUES (10, 'BILL2016_010', '豆瓣酱', '食品-调料', '瓶', 200.00, 2000.00, 2, 1, '2013-10-29 05:07:03', NULL, NULL, 8);
INSERT INTO `smbms_bill` VALUES (11, 'BILL2016_011', '海之蓝', '饮料-国酒', '瓶', 50.00, 10000.00, 1, 1, '2016-04-14 16:16:00', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (12, 'BILL2016_012', '芝华士', '饮料-洋酒', '瓶', 20.00, 6000.00, 1, 1, '2016-09-09 17:00:00', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (13, 'BILL2016_013', '长城红葡萄酒', '饮料-红酒', '瓶', 60.00, 800.00, 2, 1, '2016-11-14 15:23:00', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (14, 'BILL2016_014', '泰国香米', '食品-大米', '斤', 400.00, 5000.00, 2, 1, '2016-10-09 15:20:00', NULL, NULL, 3);
INSERT INTO `smbms_bill` VALUES (15, 'BILL2016_015', '东北大米', '食品-大米', '斤', 600.00, 4000.00, 2, 1, '2016-11-14 14:00:00', NULL, NULL, 3);
INSERT INTO `smbms_bill` VALUES (16, 'BILL2016_016', '可口可乐', '饮料', '瓶', 2000.00, 6000.00, 2, 1, '2012-03-27 13:03:01', NULL, NULL, 2);
INSERT INTO `smbms_bill` VALUES (17, 'BILL2016_017', '脉动', '饮料', '瓶', 1500.00, 4500.00, 2, 1, '2016-05-10 12:00:00', NULL, NULL, 2);
INSERT INTO `smbms_bill` VALUES (18, 'BILL2016_018', '哇哈哈', '饮料', '瓶', 2000.00, 4000.00, 2, 1, '2015-11-24 15:12:03', NULL, NULL, 2);
INSERT INTO `smbms_bill` VALUES (20, 'BILL2016_020', '洗洁精', '日用品-厨房清洁', '瓶', 500.00, 7000.00, 2, 1, '2018-08-27 13:19:12', NULL, NULL, 9);
INSERT INTO `smbms_bill` VALUES (21, '123', '123', NULL, '个', 123.00, 123.00, 1, 1, '2018-09-26 21:38:08', NULL, NULL, 1);
INSERT INTO `smbms_bill` VALUES (23, '12312', '1231', NULL, '123', 5000.00, 123.00, 2, 1, '2018-09-26 21:46:39', 1, '2018-09-26 22:26:30', 18);

-- ----------------------------
-- Table structure for smbms_provider
-- ----------------------------
DROP TABLE IF EXISTS `smbms_provider`;
CREATE TABLE `smbms_provider`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `proCode` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商编码',
    `proName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商名称',
    `proDesc` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商详细描述',
    `proContact` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '供应商联系人',
    `proPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
    `proAddress` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
    `proFax` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '传真',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者（userId）',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者（userId）',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_provider
-- ----------------------------
INSERT INTO `smbms_provider` VALUES (1, 'BJ_GYS001', '北京三木堂商贸有限公司', '长期合作伙伴，主营产品:茅台、五粮液、郎酒、酒鬼酒、泸州老窖、赖茅酒、法国红酒等', '张国强', '13566667777', '北京市丰台区育芳园北路', '010-58858787', 1, '2013-03-21 16:52:07', NULL, NULL);
INSERT INTO `smbms_provider` VALUES (4, 'GZ_GYS002', '深圳市喜来客商贸有限公司', '长期合作伙伴，主营产品：坚果炒货.果脯蜜饯.天然花茶.营养豆豆.特色美食.进口食品.海味零食.肉脯肉', '林妮1', '18599897645', '广东省深圳市福龙工业区B2栋3楼西', '0755-67772341', 1, '2013-03-22 16:52:07', '2018-09-24 21:28:07', 1);
INSERT INTO `smbms_provider` VALUES (8, 'ZJ_GYS001', '慈溪市广和绿色食品厂', '长期合作伙伴，主营产品：豆瓣酱、黄豆酱、甜面酱，辣椒，大蒜等农产品', '薛圣丹1', '18099953223', '浙江省宁波市慈溪周巷小安村', '0574-34449090', 1, '2013-11-21 06:02:07', '2018-09-28 19:54:31', 1);
INSERT INTO `smbms_provider` VALUES (9, 'GX_GYS001', '优百商贸有限公司', '长期合作伙伴，主营产品：日化产品', '李立国', '13323566543', '广西南宁市秀厢大道42-1号', '0771-98861134', 1, '2013-03-21 19:52:07', NULL, NULL);
INSERT INTO `smbms_provider` VALUES (18, '12', '234', '123', '234', '15815891967', '213', '123', 1, '2018-09-26 12:55:42', NULL, NULL);

-- ----------------------------
-- Table structure for smbms_role
-- ----------------------------
DROP TABLE IF EXISTS `smbms_role`;
CREATE TABLE `smbms_role`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `roleCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色编码',
    `roleName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '修改者',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_role
-- ----------------------------
INSERT INTO `smbms_role` VALUES (1, 'SMBMS_ADMIN', '系统管理员', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (2, 'SMBMS_MANAGER', '经理', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (3, 'SMBMS_EMPLOYEE', '普通员工', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (5, '11', '测试员', 1, '2016-04-13 00:00:00', NULL, NULL);
INSERT INTO `smbms_role` VALUES (7, 'SMBMS_MANA', '董事长', 1, '2018-09-30 20:11:48', 1, '2018-10-08 13:53:02');

-- ----------------------------
-- Table structure for smbms_user
-- ----------------------------
DROP TABLE IF EXISTS `smbms_user`;
CREATE TABLE `smbms_user`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `userCode` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户编码',
    `userName` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名称',
    `userPassword` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户密码',
    `gender` int(10) NULL DEFAULT NULL COMMENT '性别（1:女、 2:男）',
    `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
    `phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机',
    `address` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '地址',
    `userRole` int(10) NULL DEFAULT NULL COMMENT '用户角色（取自角色表-角色id）',
    `createdBy` bigint(20) NULL DEFAULT NULL COMMENT '创建者（userId）',
    `creationDate` datetime NULL DEFAULT NULL COMMENT '创建时间',
    `modifyBy` bigint(20) NULL DEFAULT NULL COMMENT '更新者（userId）',
    `modifyDate` datetime NULL DEFAULT NULL COMMENT '更新时间',
    `idPicPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '证件照路径',
    `workPicPath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '工作证照片路径',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 52 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of smbms_user
-- ----------------------------
INSERT INTO `smbms_user` VALUES (1, 'admin', '系统管理员', '1234567', 1, '1983-10-28', '13688889999', '北京市海淀区成府路207号', 1, 1, '2013-03-21 16:52:07', 1, '2018-09-28 13:21:12', NULL, NULL);
INSERT INTO `smbms_user` VALUES (2, 'liming', '李明', '0000000', 2, '1983-12-10', '13688884457', '北京市东城区前门东大街9号', 2, 1, '2013-03-21 00:00:00', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (5, 'hanlubiao', '韩路彪', '0000000', 2, '1984-06-05', '18567542321', '北京市朝阳区北辰中心12号', 2, 1, '2014-12-31 19:52:09', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (6, 'zhanghua', '张华', '0000000', 1, '1983-06-15', '13544561111', '北京市海淀区学院路61号', 3, 1, '2013-02-11 10:51:17', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (7, 'wangyang', '王洋', '0000000', 2, '1982-12-31', '13444561124', '北京市海淀区西二旗辉煌国际16层', 3, 1, '2014-06-11 19:09:07', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (10, 'sunlei', '孙磊', '0000000', 2, '1981-01-30', '13387676765', '北京市朝阳区管庄新月小区12楼', 2, 1, '2015-05-06 10:52:07', 1, '2018-09-28 13:20:56', NULL, NULL);
INSERT INTO `smbms_user` VALUES (14, 'yangguo', '杨过', '0000000', 2, '1980-01-01', '13388886623', '北京市朝阳区北苑家园茉莉园20号楼', 3, 1, '2015-02-01 03:52:07', NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (39, 'ass', '邓振良', '1231231', 2, '2018-09-10', '15815891967', '北大青鸟', 1, 1, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `smbms_user` VALUES (47, '测试1', '测试1', '123123123', 1, '2018-10-23', '15815891967', '测试1', 5, 1, '2018-09-29 14:01:46', 1, '2018-09-30 17:27:26', '1538201180302_Personal.jpg', '1538201110952_Personal.jpg');
INSERT INTO `smbms_user` VALUES (48, 'admin1', '张晨12', '123123123', 1, '2018-09-28', '15815891967', '测试1', 5, 1, '2018-09-29 16:15:34', NULL, NULL, '1538209618900_Personal.jpg', '1538209900746_Personal.jpg');
INSERT INTO `smbms_user` VALUES (49, 'aa', '1231', '123123123', 1, '2018-09-12', '15815189167', '北大青鸟', 5, 1, '2018-09-29 17:19:54', NULL, NULL, '1538213548680_Personal.jpg', '1538213001444_Personal.jpg');
INSERT INTO `smbms_user` VALUES (50, '测试11111', '12312312', '123123123', 1, '2018-10-09', '15815891967', '测试1', 7, 1, '2018-10-08 14:03:44', NULL, NULL, '1538979518768_Personal.jpg', '1538978729725_Personal.jpg');
INSERT INTO `smbms_user` VALUES (51, 'ADMINs', 'huaa', '1231231', 1, '2018-10-08', '15815891967', '测试1', 3, 1, '2018-10-08 14:21:02', NULL, NULL, '1538979676349_Personal.jpg', '1538979979076_Personal.jpg');

SET FOREIGN_KEY_CHECKS = 1;

```



### 配置文件

------

**pom.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Mybatis-Study</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.47</version>
        </dependency>
        <!--mybatis-->
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.9</version>
        </dependency>
        <!--junit-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>userTest</scope>
        </dependency>
        <!--        &lt;!&ndash; https://mvnrepository.com/artifact/log4j/log4j &ndash;&gt;-->
        <!--        <dependency>-->
        <!--            <groupId>log4j</groupId>-->
        <!--            <artifactId>log4j</artifactId>-->
        <!--            <version>1.2.17</version>-->
        <!--        </dependency>-->
        <!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
        <dependency>
            <groupId>org.mybatis.caches</groupId>
            <artifactId>mybatis-ehcache</artifactId>
            <version>1.2.2</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.22</version>
        </dependency>

    </dependencies>

    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>false</filtering>
            </resource>
        </resources>
    </build>
</project>
```



**用户名、密码用自己的**

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/smbms?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT
username=root
password=
```

**mybatis-config.xml**

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <!-- 引入外部配置文件-->
    <properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="password" value=""/>
    </properties>
    <settings>
        <!--标准的日志工厂实现-->
        <setting name="logImpl" value="STDOUT_LOGGING"/>
        <!--        <setting name="logImpl" value="LOG4J"/>-->
        <!--实现驼峰命名转换-->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
    <!--可以给实体类起别名-->
    <typeAliases>
        <package name="com.peng.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="com/peng/dao/Bill/BillMapper.xml"/>
        <mapper resource="com/peng/dao/Provider/ProviderMapper.xml"/>
        <mapper resource="com/peng/dao/Role/RoleMapper.xml"/>
        <mapper resource="com/peng/dao/User/UserMapper.xml"/>
    </mappers>
</configuration>
```



**MybatisUtils.java**

```java
package com.peng.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

//sqlSessionFactory -- >sqlSession
public class MybatisUtils {
    private  static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            //使用Mybatis第一步，获取sqlSessionFactory对象
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //    既然有了 SqlSessionFactory，顾名思义，我们可以从中获得 SqlSession 的实例。
    //    SqlSession完全包含了面向数据库执行sql命令所需的所有方法

    public  static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }

}
```



### 接口、测试、实体类

------

#### Bill

> BillMapper.java

```java
package com.peng.dao.Bill;

import com.peng.pojo.Bill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BillMapper {
    //根据供应商ID查询订单数量
    public int getBillCountByProviderId(@Param("providerId") Integer providerId);

    //增加订单
    public int add(Bill bill);

    //通过查询条件获取供应商列表-getBillList
    public List<Bill> getBillList(@Param("productName") String productName, @Param("providerId") Integer providerId, @Param("isPayment") Integer isPayment, @Param("from") Integer currentPageNo, @Param("pageSize") Integer pageSize);

    //通过条件查询-订单表记录数
    public int getBillCount(@Param("productName") String productName, @Param("providerId") Integer providerId, @Param("isPayment") Integer isPayment);

    //通过delId删除Bill
    public int deleteBillById(@Param("id") Integer delId);

    //通过billId获取Bill
    public Bill getBillById(@Param("id") Integer id);

    //修改订单信息
    public int modify(Bill bill);

    //根据供应商ID删除订单信息
    public int deleteBillByProviderId(@Param("providerId") Integer providerId);
}
```



> BillMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.peng.dao.Bill.BillMapper">
    <!--根据供应商ID查询订单数量-->
    <select id="getBillCountByProviderId" resultType="int" parameterType="int">
        select count(productCount) from smbms.smbms_bill
        <where>
            providerId=#{providerId}
        </where>
    </select>

    <!--添加订单-->
    <insert id="add" parameterType="bill">
        insert into smbms_bill (id,billCode, productName, productDesc, productUnit, productCount, totalPrice, isPayment, createdBy, creationDate, modifyBy, modifyDate, providerId) values(#{id},#{billCode},#{productName},#{productDesc},#{productUnit},#{productCount},#{totalPrice},#{isPayment},#{createdBy},#{creationDate},#{modifyBy},#{modifyDate},#{providerId});
    </insert>

    <!--通过查询条件获取供应商列表 模糊查询-getBillList-->
    <select id="getBillList" resultType="Bill">
        select * from smbms_bill
        <where>
            <if test="productName != null">
                and productName like concat ('%',#{productName},'%')
            </if>
            <if test="providerId != null">
                and providerId like concat ('%',#{providerId},'%')
            </if>
            <if test="isPayment != null">
                and isPayment like concat ('%',#{isPayment},'%')
            </if>
        </where>
        limit #{from},#{pageSize}
    </select>

    <!--通过条件查询-订单表记录数-->
    <select id="getBillCount" resultType="int" parameterType="int">
        SELECT count(*)
        FROM `smbms_bill`
        <where>
            <if test="productName!=null">
                productName=#{productName}
            </if>
            <if test="providerId!=null">and
                providerId=#{providerId}</if>
            <if test="isPayment!=null">
                and isPayment=#{isPayment}
            </if>
        </where>
    </select>
    <!-- 通过delId删除Bill-->
    <delete id="deleteBillById" parameterType="int">
        delete from smbms.smbms_bill
        <where>
            id = #{id}
        </where>
    </delete>
    <!-- 通过billId获取Bill  -->
    <select id="getBillById" resultType="bill">
        select * from smbms.smbms_bill
        <where>
            id=#{id}
        </where>
    </select>
    <!--修改订单信息-->
    <update id="modify" parameterType="bill">
        update smbms.smbms_bill
        <set>
            <if test="billCode!=null">billCode=#{billCode},</if>
            <if test="productName!=null">productName=#{productName},</if>
            <if test="productDesc!=null">productDesc=#{productDesc},</if>
            <if test="productUnit!=null">productUnit=#{productUnit},</if>
            <if test="productCount!=null">productCount=#{productCount},</if>
            <if test="totalPrice!=null">totalPrice=#{totalPrice},</if>
            <if test="isPayment!=null">isPayment=#{isPayment},</if>
            <if test="providerId!=null">providerId=#{providerId},</if>
            <if test="createdBy!=null">createdBy=#{createdBy},</if>
            <if test="creationDate!=null">creationDate=#{creationDate},</if>
            <if test="modifyBy!=null">modifyBy=#{modifyBy},</if>
            <if test="modifyDate!=null">modifyDate=#{modifyDate}</if>
        </set>
        where id=#{id}
    </update>
    <!--根据供应商ID删除订单信息 -->
    <delete id="deleteBillByProviderId" parameterType="int">
        delete from smbms.smbms_bill
        <where>
            providerId=#{providerId}
        </where>
    </delete>
</mapper>
```



> BillTest.java

```java
package com.peng.Mapper;

import com.peng.dao.Bill.BillMapper;
import com.peng.pojo.Bill;
import com.peng.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class billTest {
    //根据供应商ID查询订单数量
    @Test
    public void getBillCountByProviderId(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        mapper.getBillCountByProviderId(14);
    }
    //增加订单
    @Test
    public void add(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        mapper.add(new Bill(22,"BILL2016_022","标婷","真不戳","瓶",new BigDecimal("600.00"),new BigDecimal("80000.00"),1,1,1,new Date(),null,new Date(),"鹏飞"));
    }

    //通过查询条件获取供应商列表-getBillList
    @Test
    public void getBillList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        List<Bill> list = mapper.getBillList("海之蓝", 1, 1, 0, 5);
        for (Bill bill : list) {
            System.out.println(bill);
        }
        sqlSession.close();
    }

    //通过条件查询-订单表记录数
    @Test
    public void getBillCount(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);

        mapper.getBillCount("大豆油",6,2);

        sqlSession.close();
    }
    //通过delId删除Bill
    @Test
    public void deleteBillById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        mapper.deleteBillById(22);

        sqlSession.close();
    }

    //通过billId获取Bill
    @Test
    public void getBillById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        mapper.getBillById(2);

        sqlSession.close();
    }

    //    修改订单信息
    @Test
    public void modifyDate(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);

        mapper.modify(new Bill(21,"BILL2016_022","标婷","真不戳","瓶",new BigDecimal("600.00"),new BigDecimal("80000.00"),1,1,1,new Date(),null,new Date(),"鹏飞"));

        sqlSession.close();
    }
    //根据供应商ID删除订单信息
    @Test
    public void deleteBillByProviderId(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BillMapper mapper = sqlSession.getMapper(BillMapper.class);
        mapper.deleteBillByProviderId(6);

        sqlSession.close();
    }
}
```



> Bill.java

get、set方法就不写上了

```java
package com.peng.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Bill {
    private Integer id;   //id
    private String billCode; //账单编码
    private String productName; //商品名称
    private String productDesc; //商品描述
    private String productUnit; //商品单位
    private BigDecimal productCount; //商品数量
    private BigDecimal totalPrice; //总金额
    private Integer isPayment; //是否支付
    private Integer providerId; //供应商ID
    private Integer createdBy; //创建者
    private Date creationDate; //创建时间
    private Integer modifyBy; //更新者
    private Date modifyDate;//更新时间


    private String providerName;//供应商名称


    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public BigDecimal getProductCount() {
        return productCount;
    }

    public void setProductCount(BigDecimal productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Integer isPayment) {
        this.isPayment = isPayment;
    }

    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

}
```



#### Provider

> ProviderMapper.java

```java
package com.peng.dao.Provider;

import com.peng.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {

    //通过条件查询供应商列表
    List<Provider> getProviderList(@Param("proCode")String proCode, @Param("proName")String proName,
                                   @Param("from") Integer currentPageNo, @Param("pageSize")Integer pageSize);

    //增加用户信息
    int add(Provider provider);

    //获得供应商list
    List<Provider> getList();

    //通过条件查询供应商表记录数
    int getProCount(@Param("proName") String proName, @Param("proCode")String proCode);

    //通过id删除信息
    int deleteProById(@Param("id") Integer delId);

    //根据provider id获取供应商信息
    Provider getProById(@Param("id") Integer id);

    //修改供应商
    int modify(Provider provider);
}
```



> ProviderMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.peng.dao.Provider.ProviderMapper">

    <!--通过条件查询供应商列表-->
    <select id="getProviderList" resultType="provider" parameterType="list">
        select proCode,proName
        from smbms.smbms_provider
        <where>
            <if test="proCode!=null">proCode=#{proCode}</if>
            <if test="proName!=null and proName = ''">
                and proName Like concat('%',#{proName},'%')
            </if>
        </where>
    </select>
    <!--增加用户信息-->
    <insert id="add" parameterType="provider">
        insert into smbms_provider (id, proCode, proName, proDesc, proContact, proPhone, proAddress, proFax, createdBy, creationDate, modifyDate, modifyBy) values(#{id},#{proCode},#{proName},#{proDesc},#{proContact},#{proPhone},#{proAddress},#{proFax},#{createdBy},#{creationDate},#{modifyDate},#{modifyBy});
    </insert>
    <!--获得供应商list-->
    <select id="getList" resultType="provider" parameterType="list">
        select * from smbms.smbms_provider
    </select>
    <!--通过条件查询供应商表记录数-->
    <select id="getProCount" resultType="int" parameterType="int">
        select count(1)
        from smbms.smbms_provider
        <where>
            <if test="proName!=null and proName = ''">
                proName like concat('%',#{proName},'%')
            </if>
            <if test="proCode!=null">and proCode=#{proCode}</if>
        </where>
    </select>
    <!-- 通过id删除信息-->
    <delete id="deleteProById" parameterType="int">
        delete from smbms_provider
        <where>
            id=#{id}
        </where>
    </delete>
    <!--根据provider id获取供应商信息-->
    <select id="getProById" resultType="provider">
        select * from smbms_provider
        <where>
            id = #{id}
        </where>
    </select>

    <!--修改供应商-->
    <update id="modify" parameterType="int">
        update smbms_provider
        <set>
            <if test="proCode!=null">proCode=#{proCode},</if>
            <if test="proName!=null">proName=#{proName},</if>
            <if test="proDesc!=null">proDesc=#{proDesc},</if>
            <if test="proContact!=null">proContact=#{proContact},</if>
            <if test="proPhone!=null">proPhone=#{proPhone},</if>
            <if test="proAddress!=null">proAddress=#{proAddress},</if>
            <if test="proFax!=null">proFax=#{proFax},</if>
            <if test="createdBy!=null">createdBy=#{createdBy},</if>
            <if test="creationDate!=null">creationDate=#{creationDate},</if>
            <if test="modifyBy!=null">modifyBy=#{modifyBy},</if>
            <if test="modifyDate!=null">modifyDate=#{modifyDate}</if>
        </set>
        where id=#{id}
    </update>
</mapper>
```



> ProviderTest.java

```java
package com.peng.Mapper;

import com.peng.dao.Provider.ProviderMapper;
import com.peng.pojo.Provider;
import com.peng.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class providerTest {
    //    通过条件查询供应商列表
    @Test
    public void getProviderList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        List<Provider> list = mapper.getProviderList("BJ_GYS001", "北京", 0, 3);
        for (Provider provider : list) {
            System.out.println(provider);
        }
        sqlSession.close();
    }
    //增加用户信息
    @Test
    public void add(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        mapper.add(new Provider(2,"asbdgua","北京晓东加","长期合作伙伴，主营产品：日化产品","鹏哥","646516846","山东聊城","486415",1,new Date(),1,new Date()));
        sqlSession.close();
    }
    //获得供应商list
    @Test
    public void getList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        List<Provider> list = mapper.getList();
        for (Provider provider : list) {
            System.out.println(provider);
        }
        sqlSession.close();
    }
    //    通过条件查询供应商表记录数
    @Test
    public void getProCount(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        mapper.getProCount("北京","BJ_GYS001");

        sqlSession.close();
    }
    //通过id删除信息
    @Test
    public void deleteProById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        mapper.deleteProById(18);

        sqlSession.close();
    }
    //根据provider id获取供应商信息
    @Test
    public void getProById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        mapper.getProById(1);

        sqlSession.close();
    }
    //修改供应商
    @Test
    public void modify(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        ProviderMapper mapper = sqlSession.getMapper(ProviderMapper.class);
        mapper.modify(new Provider(2,"BJ_GYS001","北京晓东加","长期合作伙伴，主营产品：日化产品","鹏哥","646516846","山东聊城","486415",1,new Date(),1,new Date()));
        sqlSession.close();
    }
}
```



> Provider.java

```java
package com.peng.pojo;

import java.util.Date;


public class Provider {
    private Integer id; // id
    private String proCode; // 供应商编码
    private String proName; // 供应商名称
    private String proDesc; // 供应商描述
    private String proContact; // 供应商联系人
    private String proPhone; // 供应商电话
    private String proAddress; // 供应商地址
    private String proFax; // 供应商传真
    private Integer createdBy; // 创建者
    private Date creationDate; // 创建时间
    private Integer modifyBy; // 更新者
    private Date modifyDate;// 更新时间

    public Provider() {
    }

    public Provider(Integer id, String proCode, String proName, String proDesc, String proContact, String proPhone, String proAddress, String proFax, Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate) {
        this.id = id;
        this.proCode = proCode;
        this.proName = proName;
        this.proDesc = proDesc;
        this.proContact = proContact;
        this.proPhone = proPhone;
        this.proAddress = proAddress;
        this.proFax = proFax;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getProContact() {
        return proContact;
    }

    public void setProContact(String proContact) {
        this.proContact = proContact;
    }

    public String getProPhone() {
        return proPhone;
    }

    public void setProPhone(String proPhone) {
        this.proPhone = proPhone;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getProFax() {
        return proFax;
    }

    public void setProFax(String proFax) {
        this.proFax = proFax;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```





#### Role



> RoleMapper.java

```java
package com.peng.dao.Role;

import com.peng.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    //获取角色列表
    public List<Role> getRoleList();
    //增加角色信息
    public int add(Role role);
    //通过ID删除role
    public int deleteRoleById(@Param("id") Integer delId);
    //修改角色信息
    public int modify(Role role);
    //通过Id获取role
    public Role getRoleById(@Param("id") Integer id);
    //根据RoleCode，进行角色编码得唯一性验证
    public int roleCodeIsExist(@Param("roleCode") String roleCode);
}
```



> RoleMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.peng.dao.Role.RoleMapper">

    <!--获取角色列表-->
    <select id="getRoleList" parameterType="Role" resultType="role">
        select * from smbms.smbms_role
    </select>
    <!--增加用户信息-->
    <insert id="add" parameterType="role">
        insert into smbms.smbms_role (id, roleCode, roleName, createdBy, creationDate, modifyBy, modifyDate) values(#{id},#{roleCode},#{roleName},#{createdBy},#{creationDate},#{modifyBy},#{modifyDate});
    </insert>
    <!--通过id删除role-->
    <delete id="deleteRoleById" parameterType="int">
        delete from smbms.smbms_role
        where id = #{id}
    </delete>
    <!-- 修改角色信息-->
    <update id="modify" parameterType="role">
        update smbms.smbms_role
        <set>
            <if test="roleCode!=null">roleCode=#{roleCode},</if>
            <if test="roleName!=null">roleName=#{roleName},</if>
            <if test="createdBy!=null">createdBy=#{createdBy},</if>
            <if test="creationDate!=null">creationDate=#{creationDate},</if>
            <if test="modifyBy!=null">modifyBy=#{modifyBy},</if>
            <if test="modifyDate!=null">modifyDate=#{modifyDate}</if>
        </set>
        where id = #{id}
    </update>
    <!--通过id获取role-->
    <select id="getRoleById" resultType="role" parameterType="role">
        select * from smbms.smbms_role
        where id = #{id}
    </select>
    <!--根据RoleCode，进行角色编码得唯一性验证 -->
    <select id="roleCodeIsExist" resultType="int">
        select count(*) from smbms.smbms_role
        <where>
            roleCode=#{roleCode}
        </where>
    </select>
</mapper>
```



> RoleTest.java

```java
package com.peng.Mapper;

import com.peng.dao.Role.RoleMapper;
import com.peng.pojo.Role;
import com.peng.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class roleTest {
    //获取角色列表
    @Test
    public void getRoleList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        List<Role> list = mapper.getRoleList();
        for (Role role : list) {
            System.out.println(role);
        }
        sqlSession.close();
    }
    //增加角色信息
    @Test
    public void add(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        mapper.add(new Role(4,"11","经理",1,null,null,new Date()));

    }
    //通过Id删除role
    @Test
    public void deleteRoleById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        mapper.deleteRoleById(4);

        sqlSession.close();
    }
    //修改角色信息
    @Test
    public void modify(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        mapper.modify(new Role(5,"role11","测试员",1,new Date(),null,null));

        sqlSession.close();
    }
    //通过id获取role
    @Test
    public void getRoleById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        mapper.getRoleById(1);

        sqlSession.close();
    }
    //    根据RoleCode，进行角色编码得唯一性验证
    @Test
    public void roleCodeIsExist(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        mapper.roleCodeIsExist("role11");
        sqlSession.close();
    }
}
```



> Role.java

```java
package com.peng.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Integer id; // id
    private String roleCode; // 角色编码
    private String roleName; // 角色名称
    private Integer createdBy; // 创建者

    private Date creationDate; // 创建时间
    private Integer modifyBy; // 更新者

    private Date modifyDate;// 更新时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```



#### User



> UserMapper.java

```java
package com.peng.dao.User;

import com.peng.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    //通过userCode获取User
    public User getLoginUser(@Param("userCode") String userCode);

    //增加用户信息
    public int add(User user);

    //通过条件查询userList
    public List<User> getUserList(@Param("userName") String userName, @Param("userRole") Integer userRole, @Param("from") Integer currentPageNo, @Param("pageSize") Integer pageSize);

    //通过条件查询-用户表记录数
    public int getUserCount(@Param("userName") String userName, @Param("userRole") Integer userRole);

    //通过userId删除user
    public int deleteUserById(@Param("id") Integer delId);

    //通过userId获取user
    public User getUserById(@Param("id") Integer id);

    //修改用户信息
    public int modify(User user);

    //修改当前用户密码
    public int updatePwd(@Param("id") Integer id,@Param("userPassword") String pwd);
}
```



> UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.peng.dao.User.UserMapper">
    <!--通过userCode获取User-->
    <select id="getLoginUser" resultType="user">
        select * from smbms_user u
        <where>
            <if test="userCode!=null">
                and u.userCode=#{userCode}
            </if>
        </where>
    </select>

    <!--添加用户信息-->
    <insert id="add" parameterType="user">
        insert into smbms_user (id,userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) values (#{id},#{userCode},#{userName},#{userPassword},#{gender},#{birthday},#{phone},#{address},#{userRole},#{createdBy},#{creationDate},#{modifyBy},#{modifyDate})
    </insert>

    <!--通过条件查询userList-->
    <select id="getUserList" resultMap="userList">
        SELECT u.*,r.`roleName`
        FROM smbms.smbms_user u ,`smbms_role` r
        WHERE u.`userRole`=r.`id`
        <if test="userRole!=null">
            and u.userRole=#{userRole}
        </if>
        <if test="userName!=null and userName!=''">
            and u.userName like concat('%',#{userName},'%')
        </if>
        order by creationDate Desc limit #{from},#{pageSize}
    </select>
    <resultMap id="userList" type="User">
        <result property="id" column="id"/>
        <result property="userCode" column="userCode"/>
        <result property="userName" column="userName"/>
        <result property="gender" column="gender"/>
        <result property="birthday" column="birthday"/>
        <result property="phone" column="phone"/>
        <result property="userRole" column="userRole"/>
        <result property="userRoleName" column="roleName"/>
    </resultMap>

    <select id="getUserCount" resultType="Int">
        SELECT count(1) as count
        FROM `smbms_user` u ,`smbms_role` r
        WHERE u.userRole = r.id
        <if test="userRole!=null">
            and u.userRole=#{userRole}
        </if>
        <if test="userName!=null and userName!=''">
            and u.userName like concat('%',#{userName},'%')
        </if>
    </select>

    <!--通过userId删除user-->
    <delete id="deleteUserById" parameterType="int">
        delete from smbms.smbms_user where id=#{id}
    </delete>

    <!--  通过userId获取user  -->
    <select id="getUserById" resultType="user">
        select * from smbms.smbms_user where id = #{id}
    </select>

    <!--修改用户信息-->
    <update id="modify" parameterType="int">
        update smbms.smbms_user
        <set>
            <if test="userCode!=null">userCode=#{userCode},</if>
            <if test="userName!=null">userName=#{userName},</if>
            <if test="userPassword!=null">userPassword=#{userPassword},</if>
            <if test="gender!=null">gender=#{gender},</if>
            <if test="birthday!=null">birthday=#{birthday},</if>
            <if test="phone!=null">phone=#{phone},</if>
            <if test="address!=null">address=#{address},</if>
            <if test="userRole!=null">userRole=#{userRole},</if>
            <if test="createdBy!=null">createdBy=#{createdBy},</if>
            <if test="creationDate!=null">creationDate=#{creationDate},</if>
            <if test="modifyDate!=null">modifyDate=#{modifyDate},</if>
            <if test="modifyBy!=null">modifyBy=#{modifyBy},</if>
            <if test="idPicPath!=null">idPicPath=#{idPicPath},</if>
            <if test="workPicPath!=null">workPicPath=#{workPicPath}</if>
        </set>
        where id = #{id}
    </update>

    <!--修改当前用户密码-->
    <update id="updatePwd" parameterType="int">
        update smbms.smbms_user
        <set>
            <if test="userPassword!=null">userPassword=#{userPassword}</if>
        </set>
        where id = #{id}
    </update>
</mapper>
```



> UserTest.java

```java
package com.peng.Mapper;

import com.peng.dao.User.UserMapper;
import com.peng.pojo.User;
import com.peng.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class userTest {
    //根据userCode获取user
    @Test
    public void getLoginUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.getLoginUser("admin");
        sqlSession.close();
    }

    //添加用户
    @Test
    public void adduser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //        User user = new User(3,"admin","鹏飞12","123456",1,new Date(),"12345678922","山东",1,null,null,null,null,null,null,null);
        //        mapper.add(user);
        sqlSession.commit();
        sqlSession.close();
    }

    //通过条件查询userList
    @Test
    public void getList(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = mapper.getUserList("系统管理员",1,0,5);
        for (User user : userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    //通过条件查询-用户表记录数
    @Test
    public void getUserCount(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int count = mapper.getUserCount("系统管理员", 1);
        System.out.println(count);

        sqlSession.close();
    }

    //通过userId删除user
    @Test
    public void deleteUserId(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.deleteUserById(51);

        sqlSession.close();
    }

    //通过userId获取user
    @Test
    public void getUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.getUserById(1);
        sqlSession.close();
    }

    //修改用户信息
    @Test
    public void modify(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.modify(new User(10,"admins","含师兄","12138",1,new Date(),"123456789","山东",2,2,new Date(),3,new Date()));

        sqlSession.commit();
        sqlSession.close();
    }

    //修改当前用户密码
    @Test
    public void updatePwd(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.updatePwd(5,"12138");

        sqlSession.commit();
        sqlSession.close();
    }
}
```



> User

```java
package com.peng.pojo;

import java.util.Date;


public class User {
    private Integer id; // id
    private String userCode; // 用户编码
    private String userName; // 用户名称
    private String userPassword; // 用户密码
    private Integer gender; // 性别
    private Date birthday; // 出生日期
    private String phone; // 电话
    private String address; // 地址
    private Integer userRole; // 用户角色
    private Integer createdBy; // 创建者
    private Date creationDate; // 创建时间
    private Integer modifyBy; // 更新者
    private Date modifyDate; // 更新时间
    private Integer age;// 年龄
    private String userRoleName; // 用户角色名称

    private String idPicPath; // 证件照路径

    private String workPicPath; // 工作证照片路径

    public String getIdPicPath() {
        return idPicPath;
    }

    public void setIdPicPath(String idPicPath) {
        this.idPicPath = idPicPath;
    }

    public String getWorkPicPath() {
        return workPicPath;
    }

    public void setWorkPicPath(String workPicPath) {
        this.workPicPath = workPicPath;
    }

    public User() {
    }

    public User(Integer id, String userCode, String userName, String userPassword, Integer gender, Date birthday,
                String phone, String address, Integer userRole, Integer createdBy, Date creationDate, Integer modifyBy,
                Date modifyDate) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole = userRole;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public Integer getAge() {
        /*
         * long time = System.currentTimeMillis()-birthday.getTime(); Integer age =
         * Long.valueOf(time/365/24/60/60/1000).IntegerValue();
         */
        Date date = new Date();
        Integer age = date.getYear() - birthday.getYear();
        return age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Integer modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
```



**`注意`**

**resultType和parameterType一定不要搞混**

resultType：sql语句执行的返回值！

parameterType：参数类型！

