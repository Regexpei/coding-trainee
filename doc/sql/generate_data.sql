-- 创建存储过程来插入数据
DELIMITER //
CREATE PROCEDURE InsertMillionRows()
BEGIN
    --  定义一个变量 i
    DECLARE i INT DEFAULT 1;
    --  循环一百万次
    WHILE i <= 1000000
        DO
        -- 使用 CONCAT() 函数来生成一个随机的 name，并计算一个随机的 age
        -- 执行 insert 语句
            INSERT INTO user (name, age) VALUES (CONCAT('Name', FLOOR(RAND() * 1000000)), FLOOR(RAND() * 100));
            -- 将 i 加一并重新赋值给 i
            SET i = i + 1;
        END WHILE;
END //
DELIMITER ;

-- 调用存储过程
CALL InsertMillionRows();