INSERT INTO tbl_card_network VALUES (1, 'MasterCard');
INSERT INTO tbl_card_network VALUES (2, 'Visa');
INSERT INTO tbl_card_network VALUES (3, 'Discover');
INSERT INTO tbl_card_network VALUES (4, 'AmericanExpress');

INSERT INTO tbl_account_type VALUES(1, 'Credit');
INSERT INTO tbl_account_type VALUES(2, 'Checking');
INSERT INTO tbl_account_type VALUES(3, 'Savings');

INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (1, 2, 4, 400, '2022-07-01', 0);
INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (2, 3, 4, 400, '2022-07-01', 0);
INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (3, 2, 4, 5000, '2022-07-01', 1);
INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (4, 3, 4, 4000, '2022-07-01', 1);
INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (5, 2, 2, 400, '2022-07-01', 0);
INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (6, 3, 2, 400, '2022-07-01', 0);
INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (7, 2, 2, 5000, '2022-07-01', 1);
INSERT INTO tbl_account (accountId, typeId, userId, balance, statementDate, closed) VALUES (8, 3, 2, 4000, '2022-07-01', 1);

INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (1, 'IBM', 5);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (1, 'MSC', 13);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (1, 'TST', 16);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (2, 'IBM', 10);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (2, 'ABC', 0);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (2, '123', 5);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (3, 'IBMA', 10);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (3, 'TST4', 20);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (4, 'STK', 100);
INSERT INTO tbl_stock (`userId`, `stockId`, `count`)  VALUES (5, 'STK3', 20);
