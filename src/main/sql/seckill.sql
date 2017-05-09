-- 秒杀执行存储过程
DELIMITER $$  --console ; 为 $$
--定义存储过程
--row_count();返回上一条修改类型sql(delete,insert,update)影响行数
--row_count 0未修改数据 >0修改的行数 <0错误未执行修改
---- -1重复秒杀 --0秒杀结束 -2系统异常  --插入明细成功，执行更新库存
CREATE PROCEDURE `seckill`.`execute_seckill`
(in v_seckill_id bigint,
 in v_phone bigint,
 in v_kill_time timestamp,
 out r_result int)
 BEGIN
  DECLARE insert_count int DEFAULT 0;
  START TRANSACTION;
  insert ignore into success_killed
  (seckill_id,user_phone,create_time,state)
  values(v_seckill_id,v_phone,v_kill_time,0);
  select row_count() into insert_count;
  IF (insert_count=0)THEN
    ROLLBACK;
    set r_result=-1;
  ELSEIF (insert_count<0)THEN
    ROLLBACK;
    SET r_result=-2;
  ELSE

    update seckill
    set number=number-1
    where seckill_id=v_seckill_id
    and end_time>v_kill_time
    and start_time<v_kill_time
    and number>0;
    select row_count() into insert_count;
    IF (insert_count=0)THEN
     ROLLBACK;
     set r_result=0;
    ELSEIF (insert_count<0)THEN
     ROLLBACK;
     set r_result=-2;
    ELSE
     COMMIT;
     set r_result=1;
    END IF;
  END IF;
  END;
  $$
  --存储过程定义结束
--调用存储过程
  DELIMITER ;
  set @r_result=-3;
  call execute_seckill(1003,13501234567,now(),@r_result);
  --获取结果
  select @r_result;
  
  
SET @c=0;
CALL add1(5,2,@c);
SELECT @c;