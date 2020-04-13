package Application;

import protocol.ICMPProtocolLayer;
import protocol.IProtocol;
import protocol.ProtocolManager;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Random;

/**
 * @Author Cherry
 * @Date 2020/4/8
 * @Time 13:36
 * @Brief Ping 应用实现
 */

public class PingApp extends Application {
    protected int echo_times = 0;
    protected short identifier = 0;
    protected short sequence = 0;
    protected byte[] destIp = null;

    /**
     * @param times  连续发送多少次数据包
     * @param destIP ping 的对象
     */
    public PingApp(int times, byte[] destIP) {
        if (times > 0) {
            echo_times = times;
        } else {
            throw new IllegalArgumentException("Echo times must above 0");
        }

        Random random = new Random();
        identifier = (short) (random.nextInt() & 0x0000FFFF);
        this.destIp = destIP;
        this.port = identifier;
    }

    public void startPing() {
        for (int i = 0; i < this.echo_times; i++) {
            try {
                byte[] packet = createPackage(null);
                ProtocolManager.getInstance().sendData(packet, destIp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] createPackage(byte[] data) throws Exception {
        byte[] icmpEchoHeader = this.createICMPHeader();
        if (icmpEchoHeader == null) {
            throw new Exception("ICMP Header create fail");
        }
        byte[] ipHeader = this.createIP4Header(icmpEchoHeader.length);

        //分别构建ip包头和icmp echo包头后，将两个包头结合在一起
        byte[] packet = new byte[icmpEchoHeader.length + ipHeader.length];
        ByteBuffer packetBuffer = ByteBuffer.wrap(packet);
        packetBuffer.put(ipHeader);
        packetBuffer.put(icmpEchoHeader);

        return packetBuffer.array();
    }

    protected byte[] createICMPHeader() {
        IProtocol icmpProto = ProtocolManager.getInstance().getProtocol("icmp");
        if (icmpProto == null) {
            return null;
        }
        //构造icmp echo 包头
        HashMap<String, Object> headerInfo = new HashMap<String, Object>();
        headerInfo.put("header", "echo");
        headerInfo.put("identifier", identifier);
        headerInfo.put("sequence_number", sequence);
        sequence++;
        //附带当前时间
        long time = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(time);
        byte[] timeBuffer = buffer.array();
        headerInfo.put("data", timeBuffer);
        byte[] icmpEchoHeader = icmpProto.createHeader(headerInfo);

        return icmpEchoHeader;
    }

    private byte[] createIP4Header(int dataLength) {
        IProtocol ip4Proto = ProtocolManager.getInstance().getProtocol("ip");
        if (ip4Proto == null || dataLength <= 0) {
            return null;
        }
        //创建IP包头默认情况下只需要发送数据长度,下层协议号，接收方ip地址
        HashMap<String, Object> headerInfo = new HashMap<String, Object>();
        headerInfo.put("data_length", dataLength);
        ByteBuffer destIP = ByteBuffer.wrap(this.destIp);
        headerInfo.put("destination_ip", destIP.getInt());
        byte protocol = ICMPProtocolLayer.PROTOCL_ICMP;
        headerInfo.put("protocol", protocol);
        headerInfo.put("identification", (short) this.port);
        byte[] ipHeader = ip4Proto.createHeader(headerInfo);

        return ipHeader;

    }

    @Override
    public void handleData(HashMap<String, Object> data) {
        long time = System.currentTimeMillis();
        short sequence = (short) data.get("sequence");
        byte[] time_buf = (byte[]) data.get("data");
        ByteBuffer buf = ByteBuffer.wrap(time_buf);
        long send_time = buf.getLong();
        System.out.println("Receive reply for ping request " +
                sequence + " for " + (time - send_time) + " millis");
    }

}
