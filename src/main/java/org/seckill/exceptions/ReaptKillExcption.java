package org.seckill.exceptions;

/**重复秒杀异常 运行期异常
 * Created by lsx on 2016/8/3.
 */
public class ReaptKillExcption extends SeckillException{
    public ReaptKillExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaptKillExcption(String message) {
        super(message);
    }
}
