package org.seckill.service.impl;

import dto.Exposer;
import dto.SeckillExecution;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.seckill.exceptions.ReaptKillExcption;
import org.seckill.exceptions.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by lsx on 2016/8/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
    "classpath:spring/spring-dao.xml",
    "classpath:spring/spring-service.xml"
})
public class SeckillServiceImplTest {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() throws Exception {
       List<Seckill>list=seckillService.getSeckillList();
        //14:09:32.273 [main] INFO  o.s.s.impl.SeckillServiceImplTest - list=[Seckill [seckillId=1000, name=1000元iphone6, number=99, startTime=Wed Aug 03 13:43:55 CST 2016, endTime=Wed Nov 02 00:00:00 CST 2016, createTime=Mon Aug 01 18:32:39 CST 2016], Seckill [seckillId=1001, name=500元ipad2, number=200, startTime=Sun Nov 01 00:00:00 CST 2015, endTime=Mon Nov 02 00:00:00 CST 2015, createTime=Mon Aug 01 18:32:39 CST 2016], Seckill [seckillId=1002, name=300元小米5, number=300, startTime=Sun Nov 01 00:00:00 CST 2015, endTime=Mon Nov 02 00:00:00 CST 2015, createTime=Mon Aug 01 18:32:39 CST 2016], Seckill [seckillId=1003, name=200元红米2, number=400, startTime=Sun Nov 01 00:00:00 CST 2015, endTime=Mon Nov 02 00:00:00 CST 2015, createTime=Mon Aug 01 18:32:39 CST 2016]]
       logger.info("list={}",list);
      // System.out.println(list);
    }

    @Test
    public void getById() throws Exception {
        long id=1000l;
        //14:09:59.847 [main] INFO  o.s.s.impl.SeckillServiceImplTest - seckill=Seckill [seckillId=1000, name=1000元iphone6, number=99, startTime=Wed Aug 03 13:43:55 CST 2016, endTime=Wed Nov 02 00:00:00 CST 2016, createTime=Mon Aug 01 18:32:39 CST 2016]
        Seckill seckill=seckillService.getById(id);
        logger.info("seckill={}",seckill);//使用占位符  日志级别
    }
    @Test
    public void exportSeckillLogic() throws Exception {
        long id=1000;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        //Exposer{exposed=true, md5='db770a2f3825a7dd95b1056ba6704f98', seckillId=1000, now=0, start=0, end=0}

        if (exposer.isExposed()){
            logger.info("exposer={}",exposer);
            String md5=exposer.getMd5();
            long userPhone=135013434323L;

            try{
                SeckillExecution seckillExecution=seckillService.executeSeckill(id,userPhone,md5);
                logger.info("seckillExecution={}",seckillExecution);
            }catch (ReaptKillExcption e){
                logger.error("exception:"+e.getMessage());
            }catch (SeckillCloseException e){
                logger.error("exception:"+e.getMessage());
            }
        }else{
            logger.warn("exposer={}",exposer);
        }

    }
    @Test
    public void exportSeckillUrl() throws Exception {
         long id=1000;
        Exposer exposer=seckillService.exportSeckillUrl(id);
        //Exposer{exposed=true, md5='db770a2f3825a7dd95b1056ba6704f98', seckillId=1000, now=0, start=0, end=0}
        logger.info("exposer={}",exposer);
    }

    @Test
    public void executeSeckill() throws Exception {
        long id=1000;
        long userPhone=135013434323L;
        String md5="db770a2f3825a7dd95b1056ba6704f98";
        SeckillExecution seckillExecution=seckillService.executeSeckill(id,userPhone,md5);
        logger.info("seckillExecution={}",seckillExecution);
//Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 2, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 2000, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 1hge1vr9indgh9f14xh8cc|7a538050, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 1hge1vr9indgh9f14xh8cc|7a538050, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf-8, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 30, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 10, numHelperThreads -> 3, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ]
//14:29:02.572 [main] INFO  o.s.s.impl.SeckillServiceImplTest - seckillExecution=SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功', successkilled=Successkilld [seckillId=1000, userPhone=1350134343, state=0, seckill=Seckill [seckillId=1000, name=1000元iphone6, number=98, startTime=Thu Aug 04 14:29:02 CST 2016, endTime=Wed Nov 02 00:00:00 CST 2016, createTime=Mon Aug 01 18:32:39 CST 2016]]}
/**
 * 八月 04, 2016 2:42:03 下午 com.mchange.v2.c3p0.impl.AbstractPoolBackedDataSource getPoolManager
信息: Initializing c3p0 pool... com.mchange.v2.c3p0.ComboPooledDataSource [ acquireIncrement -> 3, acquireRetryAttempts -> 2, acquireRetryDelay -> 1000, autoCommitOnClose -> false, automaticTestTable -> null, breakAfterAcquireFailure -> false, checkoutTimeout -> 2000, connectionCustomizerClassName -> null, connectionTesterClassName -> com.mchange.v2.c3p0.impl.DefaultConnectionTester, dataSourceName -> 1hge1vr9indx8fb1gbrxq4|1769e8e3, debugUnreturnedConnectionStackTraces -> false, description -> null, driverClass -> com.mysql.jdbc.Driver, factoryClassLocation -> null, forceIgnoreUnresolvedTransactions -> false, identityToken -> 1hge1vr9indx8fb1gbrxq4|1769e8e3, idleConnectionTestPeriod -> 0, initialPoolSize -> 3, jdbcUrl -> jdbc:mysql://127.0.0.1:3306/seckill?useUnicode=true&characterEncoding=utf-8, maxAdministrativeTaskTime -> 0, maxConnectionAge -> 0, maxIdleTime -> 0, maxIdleTimeExcessConnections -> 0, maxPoolSize -> 30, maxStatements -> 0, maxStatementsPerConnection -> 0, minPoolSize -> 10, numHelperThreads -> 3, numThreadsAwaitingCheckoutDefaultUser -> 0, preferredTestQuery -> null, properties -> {user=******, password=******}, propertyCycle -> 0, testConnectionOnCheckin -> false, testConnectionOnCheckout -> false, unreturnedConnectionTimeout -> 0, usesTraditionalReflectiveProxies -> false ]
14:42:04.460 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Creating a new SqlSession
14:42:04.478 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Registering transaction synchronization for SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec]
14:42:04.495 [main] DEBUG o.m.s.t.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@63cab724] will be managed by Spring
14:42:04.512 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>  Preparing: UPDATE seckill SET number=number-1 where seckill_id=? and start_time <= ? and end_time >= ? and number>0; 
14:42:04.602 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==> Parameters: 1000(Long), 2016-08-04 14:42:04.448(Timestamp), 2016-08-04 14:42:04.448(Timestamp)
14:42:04.605 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - <==    Updates: 1
14:42:04.606 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec]
14:42:04.606 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec] from current transaction
14:42:04.617 [main] DEBUG o.s.d.S.insertSuccesskilled - ==>  Preparing: INSERT ignore INTO success_killed( seckill_id,user_phone,state,create_time ) VALUES(?,?,0,now()) 
14:42:04.619 [main] DEBUG o.s.d.S.insertSuccesskilled - ==> Parameters: 1000(Long), 13501343432(Long)
14:42:04.621 [main] DEBUG o.s.d.S.insertSuccesskilled - <==    Updates: 1
14:42:04.630 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec]
14:42:04.632 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Fetched SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec] from current transaction
14:42:04.634 [main] DEBUG org.seckill.dao.SuccesskilledDao - Cache Hit Ratio [org.seckill.dao.SuccesskilledDao]: 0.0
14:42:04.636 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==>  Preparing: select sk.seckill_id, sk.user_phone, sk.state, sk.create_time, s.seckill_id "seckill.seckill_id", s.name "seckill.name", s.number "seckill.number", s.start_time "seckill.start_time", s.end_time "seckill.end_time", s.create_time "seckill.create_time" from success_killed sk inner join seckill s on sk.seckill_id=s.seckill_id where sk.seckill_id=? and sk.user_phone=? 
14:42:04.636 [main] DEBUG o.s.d.S.queryByIdWithSeckill - ==> Parameters: 1000(Long), 13501343432(Long)
14:42:04.667 [main] DEBUG o.s.d.S.queryByIdWithSeckill - <==      Total: 1
14:42:04.674 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Releasing transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec]
14:42:04.676 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization committing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec]
14:42:04.691 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization deregistering SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec]
14:42:04.691 [main] DEBUG org.mybatis.spring.SqlSessionUtils - Transaction synchronization closing SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@68716dec]
14:42:04.735 [main] INFO  o.s.s.impl.SeckillServiceImplTest - seckillExecution=SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功', successkilled=Successkilld [seckillId=1000, userPhone=13501343432, state=0, seckill=Seckill [seckillId=1000, name=1000元iphone6, number=97, startTime=Thu Aug 04 14:42:04 CST 2016, endTime=Wed Nov 02 00:00:00 CST 2016, createTime=Mon Aug 01 18:32:39 CST 2016]]}

 */
    }
    @Test
    public  void testExecuteProcedure(){
        long seckillId=1000l;
        long phone=123455455l;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            String md5=exposer.getMd5();
            SeckillExecution se=seckillService.executeSeckillProcedure(seckillId,phone,md5);
            logger.info("result info:"+se.getStateInfo());
        }
    }

}