package org.seckill.exceptions;

/**秒杀相关业务异常
 * Created by lsx on 2016/8/3.
 */
public class SeckillException extends  RuntimeException{
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
