package org.seckill.exceptions;

/**秒杀关闭异常
 * Created by lsx on 2016/8/3.
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
