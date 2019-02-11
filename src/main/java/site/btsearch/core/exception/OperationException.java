package site.btsearch.core.exception;

/***
 * 用于表示业务异常,抛出此异常会被当作系统抛出提示抛向前台展示
 * 其他异常则表示系统内部错误,不会被抛向前台,会被写入日志表
 *
 * 标识类,不做具体实现
 *
 */

public class OperationException extends RuntimeException {


    public OperationException() {
        super();
    }

    public OperationException(String s) {
        super(s);
    }


}
