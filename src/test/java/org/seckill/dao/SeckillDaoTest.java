package org.seckill.dao;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Resource
    private SeckillDao seckillDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testReduceNumber() {
		long id=1000l;
		Date killTime=new Date();
		int c = seckillDao.reduceNumber(id, killTime);
		System.out.println(c);
	}

	@Test
	public void testQueryById() {
		long id=1000l;
		
		Seckill kill = seckillDao.queryById(id);
		System.out.println(kill);
	}

	@Test
	public void testQueryAll() {
		int offset=0;
		int limit=100;
		List<Seckill> list = seckillDao.queryAll(offset, limit);
	   for(Seckill s:list){
		   System.out.println(s);
	   }
	}

}
