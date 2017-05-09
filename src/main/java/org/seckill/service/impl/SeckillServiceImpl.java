package org.seckill.service.impl;

import dto.Exposer;
import dto.SeckillExecution;

import org.apache.commons.collections.MapUtils;
import org.seckill.dao.SeckillDao;
import org.seckill.dao.SuccesskilledDao;
import org.seckill.dao.cache.RedisDao;
import org.seckill.entity.Seckill;
import org.seckill.entity.Successkilled;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exceptions.ReaptKillExcption;
import org.seckill.exceptions.SeckillCloseException;
import org.seckill.exceptions.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lsx on 2016/8/3.
 */
@Service//@Component
@Transactional
/**
 * 使用事务控制事务方法的优点：
 *  1：开发团队达成一致约定，明确标注事务方法的编程风格
 *  2：保证事务方法的执行时间尽可能短，不要穿插网络操作http请求等
 *     或者放到事务方法外
 *  3：不是所有方法都需要事务 如：只有一条修改操作 只读操作 不需要事务控制
 *     2条sql同进退的情况才需要
 */
public class SeckillServiceImpl implements SeckillService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired//@Resource@Inject
    private SeckillDao seckillDao;
    @Autowired
    private SuccesskilledDao successkilledDao;
    @Autowired
    private RedisDao redisDao;
    //混淆
    private final String slat="fawegaesg234ddd42g";
    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }
    //优化点：缓存处理  建立在超时的基础上维护一致性  线上是集群
    public Exposer exportSeckillUrl(long seckillId) {
        /**
         * get from cache
         * if null get db
         * else put cache  降低数据库的访问量
         */
        Seckill seckill=redisDao.getSeckill(seckillId);
        if(seckill==null){
            seckill = seckillDao.queryById(seckillId);
            if (seckill==null){
                return new Exposer(false,seckillId);
            }else {
                redisDao.putSeckill(seckill);
            }

        }

       Date starttime=seckill.getStartTime();
       Date endtime=seckill.getEndTime();
       Date now=new Date();
       if(now.getTime()<starttime.getTime()||now.getTime()>endtime.getTime()){
    	   return new Exposer(false,seckillId,now.getTime(),starttime.getTime(),endtime.getTime());
       }
       //转化特定字符串的过程  不可逆
       String md5=getMD5(seckillId);
       return new Exposer(true,md5, seckillId);
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, ReaptKillExcption, SeckillCloseException {
       if(md5==null||!md5.equals(getMD5(seckillId))){
    	   throw new SeckillException("seckill data rewrite");
    	   
       }
       try {
           //减库存成功  记录购买行为
           int insertCount = successkilledDao.insertSuccesskilled(seckillId, userPhone);
           //唯一验证  之前已经买过
           if(insertCount<=0){
               //重复秒杀
               throw new ReaptKillExcption("seckill repeat");
           }else{

               //执行秒杀逻辑  减库存  记录购买行为
               int updateCount=seckillDao.reduceNumber(seckillId, new Date());
               if(updateCount<=0){
                   //没有更新记录  秒杀结束
                   throw new SeckillCloseException("seckill is closed");
               }
               //秒杀成功
               Successkilled successkill = successkilledDao.queryByIdWithSeckill(seckillId, userPhone);
               return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successkill);
           }

	}catch (ReaptKillExcption e) {
	       logger.error(e.getMessage(), e);
	       throw new ReaptKillExcption("seckill inner error:"+e.getMessage());
	}catch (Exception e) {
       logger.error(e.getMessage(), e);
       //所有编译期间异常转化为运行期异常  spring会把异常回滚
       throw new SeckillException("seckill inner error:"+e.getMessage());
	}
      
    }
    public SeckillExecution executeSeckillProcedure(long seckillId,long userPhone, String md5){
        if (md5==null||!md5.equals(getMD5(seckillId))){
            return new SeckillExecution(seckillId,SeckillStateEnum.DATA_REWRITE);
        }
        Date killTime=new Date();
        Map<String,Object>map=new HashMap<String, Object>();
        map.put("seckillId",seckillId);
        map.put("phone",userPhone);
        map.put("killTime",killTime);
        map.put("result",null);
        //执行存储过程，result被赋值
        try{
            seckillDao.killByProcedure(map);
           int result= MapUtils.getInteger(map,"result",-2);
            if (result==1){
                Successkilled sk=successkilledDao.queryByIdWithSeckill(seckillId,userPhone);
                return new SeckillExecution(seckillId,SeckillStateEnum.SUCCESS,sk);

            }else{
                return new SeckillExecution(seckillId,SeckillStateEnum.stateOf(result));
            }

        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return new SeckillExecution(seckillId,SeckillStateEnum.INNER_ERROR);
        }
        //return null;
    }
    /**
     * 自定义生成MD5串的规则
     * @param seckillId
     * @return
     */
    private String getMD5(long seckillId){
    	String base=seckillId+"/"+slat;
    	String md5=DigestUtils.md5DigestAsHex(base.getBytes());
    	return md5;
    }

}
