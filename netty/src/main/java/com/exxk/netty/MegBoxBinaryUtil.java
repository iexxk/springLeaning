package com.exxk.netty;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;

public class MegBoxBinaryUtil {
    private final Logger log = LoggerFactory.getLogger(MegBoxBinaryUtil.class);

    public MegBoxBinaryUtil(byte[] orign) {
        this.orign = orign;

        //type前八位 43 61 70 74 75 72 65 00
        byte[] type = new byte[8];
        System.arraycopy(this.orign, 0, type, 0, type.length);
        this.type = new String(type).trim();
        log.info("type result:" + this.type + " ,byte: {}", DatatypeConverter.printHexBinary(type));

        //head 4位 7E 01 00 00
        byte[] headLen = new byte[4];
        System.arraycopy(this.orign, 8, headLen, 0, headLen.length);
        this.headLen = bytesToIntLittle(headLen, 0);
        log.info("headLen result:" + this.headLen + " ,byte: {}", DatatypeConverter.printHexBinary(headLen));

        //seq 4位 97 01 00 00
        byte[] seq = new byte[4];
        System.arraycopy(this.orign, 8 + 4, seq, 0, seq.length);
        this.seq = bytesToIntLittle(seq, 0);
        log.info("seq result: " + this.seq + " ,byte: {}", DatatypeConverter.printHexBinary(seq));

        //json head位
        byte[] json = new byte[this.headLen];
        System.arraycopy(this.orign, 8 + 4 + 4, json, 0, json.length);
        this.jsonObject = new JsonParser().parse(new String(json).trim()).getAsJsonObject();
        log.info("json result: " + this.jsonObject.getAsString() + " ,byte: {}", DatatypeConverter.printHexBinary(json));

        //captureFace length
        if (this.type.equals("Capture")) {
            this.captureFaceLen = this.jsonObject.get("captureFace").getAsInt();
        } else if (this.type.equals("Alert")) {
            this.captureFaceLen = this.jsonObject.get("images").getAsJsonObject().get("captureFace").getAsInt();
        }
        //captureFace byte
        this.captureFace = new byte[this.captureFaceLen];
        System.arraycopy(this.orign, 8 + 4 + 4 + this.headLen, this.captureFace, 0, this.captureFace.length);

        log.info("captureFaceLen result: " + this.captureFaceLen + " ,byte: {}", DatatypeConverter.printHexBinary(this.captureFace));


        //fullImage length
        if (this.type.equals("Alert")) {
            this.fullImageLen = this.jsonObject.get("images").getAsJsonObject().get("fullImage").getAsInt();
            //fullImage byte
            this.fullImage = new byte[this.fullImageLen];
            System.arraycopy(this.orign, 8 + 4 + 4 + this.headLen + this.captureFaceLen, this.captureFace, 0, this.fullImage.length);
            log.info("fullImage result: " + this.fullImageLen + " ,byte: {}", DatatypeConverter.printHexBinary(this.fullImage));
        }

        //top1 length
        if (this.type.equals("Alert")) {
            this.top1Len = this.jsonObject.get("images").getAsJsonObject().get("top1").getAsInt();
            //fullImage byte
            this.top1 = new byte[this.top1Len];
            System.arraycopy(this.orign, 8 + 4 + 4 + this.headLen + this.captureFaceLen + this.fullImageLen, this.top1, 0, this.top1.length);
            log.info("fullImage result: " + this.top1Len + " ,byte: {}", DatatypeConverter.printHexBinary(this.top1));
        }


    }

    byte[] orign;
    String type;
    Integer headLen;
    Integer seq;
    JsonObject jsonObject;
    Integer captureFaceLen;
    byte[] captureFace;
    Integer fullImageLen;
    byte[] fullImage;
    Integer top1Len;
    byte[] top1;

    public String getType() {
        return type;
    }

    public Integer getHeadLen() {
        return headLen;
    }

    public Integer getSeq() {
        return seq;
    }

    public JsonObject getJsonObject() {
        return jsonObject;
    }

    public Integer getCaptureFaceLen() {
        return captureFaceLen;
    }

    public byte[] getCaptureFace() {
        return captureFace;
    }

    public Integer getFullImageLen() {
        return fullImageLen;
    }

    public byte[] getFullImage() {
        return fullImage;
    }

    public Integer getTop1Len() {
        return top1Len;
    }

    public byte[] getTop1() {
        return top1;
    }

    /**
     * 以小端模式将byte[]转成int
     */
    public static int bytesToIntLittle(byte[] src, int offset) {
        int value;
        value = (int) ((src[offset] & 0xFF)
                | ((src[offset + 1] & 0xFF) << 8)
                | ((src[offset + 2] & 0xFF) << 16)
                | ((src[offset + 3] & 0xFF) << 24));
        return value;
    }
}
