package org.seckill.dao;
import javax.annotation.Resource;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Successkilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccesskilledDaoTest {
    @Resource
    private SuccesskilledDao successkilledDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testInsertSuccesskilled() {
		//INSERT ignore INTO success_killed( seckill_id,user_phone,state,create_time ) VALUES(?,?,0,now()) 
		//联合主键 主键重复插入t=0，而没有抛出异常
		long seckillId=1000l;
		long userPhone=13245678l;
		int t = successkilledDao.insertSuccesskilled(seckillId, userPhone);
		System.out.println(t);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		/*2个实体类压实现serializable接口，否则会报错NotSerializableException
		 * 14:00:42.527 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.state, sk.create_time, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id=s.seckill_id where sk.seckill_id=? and sk.user_phone=? 
14:00:42.598 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 1000(Long), 13245678(Long)
14:00:42.625 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
		 */
		long seckillId=1000l;
		long userPhone=13245678l;
		Successkilled s = successkilledDao.queryByIdWithSeckill(seckillId,userPhone);
		System.out.println(s);
		System.out.println(s.getSeckill());
	}

}
