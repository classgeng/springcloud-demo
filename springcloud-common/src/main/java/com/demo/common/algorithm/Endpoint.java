package com.demo.common.algorithm;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

public class Endpoint {

    public static void main(String[] args) throws Exception {
        String ipStr = "127.0.0.1:8080";
        String[] ipArray = ipStr.split(":");
        // ip格式（255.255.255.255）12位,int32存不了
        int ip = ipToUint64(ipArray[0]);
        // 端口是0-65535
        int port = Integer.parseInt(ipArray[1]);

        // ip转64位，向左移32位
        long ip64 = Long.valueOf(ip) << 32;

        System.out.println(ip64 | port);

        System.out.println(endpointToUint64(ipStr));
    }


    public static long convertUint64(String ipstr,int port){
        InetSocketAddress inetSocketAddress = new InetSocketAddress(ipstr,port);
        InetAddress addressIp = inetSocketAddress.getAddress();
        int addressPort = inetSocketAddress.getPort();

        byte[] ipByte = addressIp.getAddress();
        byte[] portByte = BigInteger.valueOf(addressPort).toByteArray();

        System.out.println(ipByte.length);
        System.out.println(portByte.length);

        byte[] unit64Bytes = new byte[8];
        System.arraycopy(ipByte,0,unit64Bytes,0,ipByte.length);
        System.arraycopy(portByte,0,unit64Bytes,0,portByte.length);

        BigInteger ipunit64 = new BigInteger(unit64Bytes);

        return ipunit64.longValue();
    }


    public static long endpointToUint64(String endpoint) throws Exception {
        String[] parts = endpoint.split(":");
        byte[] ipBytes = InetAddress.getByName(parts[0]).getAddress();
        int port = Integer.parseInt(parts[1]);

        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.put(ipBytes);
        buffer.putInt(port);

        return buffer.getLong(0);
    }


    public static int ipToUint64(String ip) throws Exception {
        int tempIp = 0;
        String[] arr = ip.split("\\.");
        for (int i=0; i<arr.length; i++){
            int aa = Integer.parseInt(arr[i]);
            tempIp |= aa << 8 * (3-i);
        }
        return tempIp;
    }


}
