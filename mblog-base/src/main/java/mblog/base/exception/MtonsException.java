package mblog.base.exception;

/**
 * Created by zhuzhaolin .
 * Created in2017/12/5 23:00.
 */
public class MtonsException extends RuntimeException {
    private static final long serialVersionUID = -7443213283905815106L;
    private int code;

    public MtonsException() {
    }

    public MtonsException(int code) {
        super("code=" + code);
        this.code = code;
    }

    public MtonsException(String message) {
        super(message);
    }

    public MtonsException(Throwable cause) {
        super(cause);
    }

    public MtonsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MtonsException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}