package com.demo.common;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class EndPoint {

    public static ByteBuffer ipAddressToUint64(String endpoint) throws UnknownHostException {
        String[] endpoints = endpoint.split(":");
        InetAddress ipAddress = InetAddress.getByName(endpoints[0]);
        int port = Integer.parseInt(endpoints[1]);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.put(ipAddress.getAddress());
        byteBuffer.putInt(port);
        byteBuffer.flip();

        return byteBuffer;
    }

    public static long endpointToUint64(String endpoint) {
        String[] parts = endpoint.split(":");
        int ipAsInt = ipToInt(parts[0]);
        int port = Integer.parseInt(parts[1]);

        long ipAsLong = Long.valueOf(ipAsInt) << 32;

        return ipAsLong | port;
    }

    public static int ipToInt(String ipAddress) {
        String[] octets = ipAddress.split("\\.");
        int result = 0;

        for (int i = 0; i < octets.length; i++) {
            int octet = Integer.parseInt(octets[i]);
            result |= octet << (8 * (3 - i));
        }

        return result;
    }

    public static void main(String[] args) throws UnknownHostException {
        String endpoint = "127.0.0.1:8080";

        // ByteBuffer byteBuffer = ipAddressToUint64(endpoint);
        //long uint64 = byteBuffer.getLong();

        long uint64 = endpointToUint64(endpoint);

        System.out.println("getLong: " + uint64); // 21307064338080
    }

}
