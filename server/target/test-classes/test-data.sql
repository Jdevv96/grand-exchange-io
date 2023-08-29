/*
    The test-data file creates a temporary database used to run the DAO tests.
 */

BEGIN TRANSACTION;

-- Users
INSERT INTO users (username,password_hash,role, name, address, city, state_code, zip) VALUES ('user1','user1','ROLE_USER', 'John Doe', '555 User1 Way', 'Cincinnati', 'OH', '00003');
INSERT INTO users (username,password_hash,role, name, address, city, state_code, zip) VALUES ('user2','user2','ROLE_USER', 'Jane Doe', null, 'Cleveland', 'OH', '00002');
INSERT INTO users (username,password_hash,role, name, address, city, state_code, zip) VALUES ('user3','user3','ROLE_USER', 'Junior Doe', '555 User3 Way', 'Columbus', 'OH', '00001');

-- Products
INSERT INTO product (product_sku, name, description, price, image_name) VALUES
    ('SKU-001', 'Product 1', 'Product description 1.', 1.99, 'Product001.jpg'),
    ('SKU-002', 'Product 2', 'Product description 2.', 2.99, 'Product002.jpg'),
    ('SKU-003', 'Product 3', 'Product description 3.',  3.99, 'Product003.jpg'),
    ('SKU-004', 'Product 4', 'Product description 4.',  4.99, 'Product004.jpg'),
    ('SKU-005', 'Product 5', 'Product description 5.',  5.99, 'Product005.jpg'),
    ('SKU-006', 'Product 6', 'Product description 6.',  6.99, 'Product006.jpg'),
    ('SKU-007', 'Product 7', 'Product description 7.',  7.99, 'Product007.jpg');
COMMIT TRANSACTION;
