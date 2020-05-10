package protocol;

/**
 * @Author Cherry
 * @Date 2020/3/19
 * @Time 21:36
 * @Brief
 */

public interface IICMPErrorMsgHandler {
    boolean handleICMPErrorMsg(int type, int code, byte[] data);
}
