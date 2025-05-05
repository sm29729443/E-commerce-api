CREATE DATABASE IF NOT EXISTS my_e_commerce;
USE my_e_commerce;
-- 會員表
CREATE TABLE member (
                        member_id INT PRIMARY KEY AUTO_INCREMENT,
                        username VARCHAR(50) NOT NULL UNIQUE,
                        password VARCHAR(100) NOT NULL,
                        email VARCHAR(100) NOT NULL UNIQUE,
                        phone VARCHAR(20) UNIQUE,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP NOT NULL,
                        status TINYINT NOT NULL COMMENT '0:禁用,1:正常',
                        is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 角色表
CREATE TABLE role(
                     role_id INT PRIMARY KEY AUTO_INCREMENT,
                     role_name VARCHAR(50) NOT NULL UNIQUE,
                     is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 會員-角色-連接表
CREATE TABLE member_role(
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            member_id INT NOT NULL,
                            role_id INT NOT NULL,
                            is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 第三方登入表
CREATE TABLE oauth_connection (
                                  id INT PRIMARY KEY AUTO_INCREMENT,
                                  member_id INT NOT NULL,
                                  provider VARCHAR(20) NOT NULL COMMENT '第三方登入來源平台',
                                  provider_user_id VARCHAR(255) NOT NULL COMMENT 'user在第三方平台的user_id',
                                  is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
                                  UNIQUE KEY uk_provider_id (provider, provider_user_id)
);

-- 商品表
CREATE TABLE product (
                         product_id INT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         description TEXT COMMENT '商品描述',
                         price INT NOT NULL,
                         status TINYINT NOT NULL COMMENT '0:未上架,1:上架,2:商品待審核',
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL,
                         is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 商品SKU表
CREATE TABLE product_sku (
                             product_sku_id INT PRIMARY KEY AUTO_INCREMENT,
                             product_id INT NOT NULL,
                             sku_code VARCHAR(50) NOT NULL UNIQUE,
                             attributes JSON NOT NULL,
                             price INT NOT NULL,
                             stock INT NOT NULL,
                             status TINYINT NOT NULL COMMENT '0:停售,1:正常銷售',
                             is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);
-- 商品圖片表
CREATE TABLE product_image (
                               id INT PRIMARY KEY AUTO_INCREMENT,
                               product_id INT NOT NULL,
                               product_sku_id INT NULL,
                               image_url VARCHAR(255) NOT NULL,
                               image_type TINYINT NOT NULL COMMENT '1:主圖,2:詳情圖,3:規格圖',
                               sort_order INT NOT NULL DEFAULT 0 COMMENT '圖片排序權重',
                               created_at TIMESTAMP NOT NULL,
                               updated_at TIMESTAMP NOT NULL,
                               is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 商品分類表 (多級分類)
CREATE TABLE product_category (
                                  id INT PRIMARY KEY AUTO_INCREMENT,
                                  name VARCHAR(50) NOT NULL,
                                  parent_id INT COMMENT '這個分類的父分類',
                                  level INT NOT NULL COMMENT '分類的層級,1=頂級，2=二級，依此類推',
                                  path VARCHAR(255) NULL COMMENT '存儲完整路徑，如 "1,4,10"，便於未來查詢',
                                  sort_order INT NOT NULL DEFAULT 0,
                                  created_at TIMESTAMP NOT NULL,
                                  updated_at TIMESTAMP NOT NULL,
                                  is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 商品表新增分類欄位
ALTER TABLE product ADD COLUMN category_id INT NOT NULL;

-- 訂單表
CREATE TABLE orders (
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        order_no VARCHAR(50) NOT NULL UNIQUE,
                        member_id INT NOT NULL,
                        total_amount INT NOT NULL,
                        recipient_name VARCHAR(50) NOT NULL,
                        recipient_phone VARCHAR(20) NOT NULL,
                        shipping_address TEXT NOT NULL,
                        payment_method VARCHAR(20) NOT NULL,
                        status TINYINT NOT NULL COMMENT '0:待付款,1:已付款,2:已發貨,3:已完成',
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP NOT NULL,
                        is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);

-- 訂單項目表
CREATE TABLE order_item (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            order_id INT NOT NULL,
                            product_id INT NOT NULL,
                            product_sku_id INT NOT NULL,
                            product_name VARCHAR(100) NOT NULL,
                            product_attributes JSON,
                            price INT NOT NULL,
                            quantity INT NOT NULL,
                            is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);
-- 購物車表
CREATE TABLE cart(
                     cart_id INT PRIMARY KEY AUTO_INCREMENT,
                     member_id INT NOT NULL
);
-- 購物車項目表
CREATE TABLE cart_item (
                           cart_item_id INT PRIMARY KEY AUTO_INCREMENT,
                           cart_id INT NOT NULL,
                           product_id INT NOT NULL,
                           product_sku_id INT NOT NULL,
                           quantity INT NOT NULL,
                           created_at TIMESTAMP NOT NULL,
                           updated_at TIMESTAMP NOT NULL,
                           is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);
-- 支付紀錄表
CREATE TABLE payment_transaction (
                                     id INT PRIMARY KEY AUTO_INCREMENT,
                                     order_id INT NOT NULL,
                                     transaction_no VARCHAR(100) NOT NULL COMMENT '支付平台交易編號',
                                     amount INT NOT NULL,
                                     payment_method VARCHAR(20) NOT NULL,
                                     status TINYINT NOT NULL COMMENT '0:處理中,1:成功,2:失敗',
                                     created_at TIMESTAMP NOT NULL,
                                     updated_at TIMESTAMP NOT NULL,
                                     is_deleted BOOLEAN DEFAULT FALSE NOT NULL
);