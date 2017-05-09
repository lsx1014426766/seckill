package org.seckill.service;

import dto.Exposer;
import dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exceptions.ReaptKillExcption;
import org.seckill.exceptions.SeckillCloseException;
import org.seckill.exceptions.SeckillException;

import java.util.List;

/**业务接口站在使用者角度   具体实现是开发者角度
 * Created by lsx on 2016/8/3.
 */
public interface SeckillService {
    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开始时输出秒杀的接口地址，
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException,ReaptKillExcption,SeckillCloseException;

    SeckillExecution executeSeckillProcedure(long seckillId,long userPhone, String md5);

}
