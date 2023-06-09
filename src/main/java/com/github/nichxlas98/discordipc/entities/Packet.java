package com.github.nichxlas98.discordipc.entities;

import java.nio.ByteBuffer;
import org.json.JSONObject;

public class Packet {

    /* renamed from: op */
    private final OpCode f5op;
    private final JSONObject data;

    public enum OpCode {
        HANDSHAKE,
        FRAME,
        CLOSE,
        PING,
        PONG
    }

    public Packet(OpCode op, JSONObject data) {
        this.f5op = op;
        this.data = data;
    }

    public byte[] toBytes() {
        byte[] d = this.data.toString().getBytes();
        ByteBuffer packet = ByteBuffer.allocate(d.length + 8);
        packet.putInt(Integer.reverseBytes(this.f5op.ordinal()));
        packet.putInt(Integer.reverseBytes(d.length));
        packet.put(d);
        return packet.array();
    }

    public OpCode getOp() {
        return this.f5op;
    }

    public JSONObject getJson() {
        return this.data;
    }

    public String toString() {
        return "Pkt:" + getOp() + getJson().toString();
    }
}
