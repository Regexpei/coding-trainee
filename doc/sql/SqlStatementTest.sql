-- ----------------------------
-- limit 500000, 10 和 limit 10 速度一样快？
-- ----------------------------

# 从结果集中跳过前500000行，然后返回接下来的10行数据
SELECT * FROM `coding_trainee`.`tb_user` LIMIT 500000, 10;

# 从结果集中取前10条数据
SELECT * FROM `coding_trainee`.`tb_user` LIMIT 10;

# 使用子查询的结果作为过滤条件，从指定位置行的主键开始获取10行数据
SELECT
    *
FROM
    `coding_trainee`.`tb_user`
WHERE
    `id` >= ( SELECT `id` FROM `coding_trainee`.`tb_user` ORDER BY `id` LIMIT 1 OFFSET 500000 )
ORDER BY
    `id`
LIMIT 10;



