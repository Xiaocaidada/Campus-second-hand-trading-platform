CREATE DATABASE IF NOT EXISTS campus_market DEFAULT CHARSET utf8mb4;
USE campus_market;

-- 用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` BIGINT NOT NULL,
                        `username` VARCHAR(50) NOT NULL,
                        `password` VARCHAR(255) NOT NULL,
                        `role` VARCHAR(20) NOT NULL DEFAULT 'USER' COMMENT 'USER/ADMIN',
                        `nickname` VARCHAR(50),
                        `avatar` VARCHAR(255),
                        `status` TINYINT DEFAULT 1 COMMENT '0禁用 1启用',
                        `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB;

-- 商品分类表
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
                            `id` BIGINT NOT NULL,
                            `name` VARCHAR(50) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB;
INSERT INTO `category` VALUES (1,'电子产品'),(2,'图书教材'),(3,'生活用品'),(4,'运动器材');

-- 商品表
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
                         `id` BIGINT NOT NULL,
                         `user_id` BIGINT NOT NULL COMMENT '发布者',
                         `category_id` BIGINT,
                         `title` VARCHAR(100) NOT NULL,
                         `description` TEXT,
                         `price` DECIMAL(10,2) NOT NULL,
                         `images` VARCHAR(1000) COMMENT '图片URL,逗号分隔',
                         `status` VARCHAR(20) DEFAULT 'PENDING' COMMENT 'PENDING-待审核, NORMAL-正常, BANNED-已下架',
                         `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         KEY `idx_user_id` (`user_id`),
                         KEY `idx_category` (`category_id`)
) ENGINE=InnoDB;

-- 订单表
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
                         `id` BIGINT NOT NULL,
                         `buyer_id` BIGINT NOT NULL,
                         `goods_id` BIGINT NOT NULL,
                         `seller_id` BIGINT NOT NULL,
                         `status` VARCHAR(20) DEFAULT 'WAIT_DELIVER' COMMENT 'WAIT_DELIVER, DELIVERED, COMPLETED, CANCELED',
                         `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
                         PRIMARY KEY (`id`),
                         KEY `idx_buyer` (`buyer_id`),
                         KEY `idx_seller` (`seller_id`)
) ENGINE=InnoDB;

-- 收藏表
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
                            `id` BIGINT NOT NULL,
                            `user_id` BIGINT NOT NULL,
                            `goods_id` BIGINT NOT NULL,
                            PRIMARY KEY (`id`),
                            UNIQUE KEY `uk_user_goods` (`user_id`,`goods_id`)
) ENGINE=InnoDB;

-- 私信表
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
                           `id` BIGINT NOT NULL,
                           `from_id` BIGINT NOT NULL,
                           `to_id` BIGINT NOT NULL,
                           `content` TEXT NOT NULL,
                           `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB;