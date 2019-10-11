package com.exxk.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.Date;
@Component
@ChannelHandler.Sharable
public class BinaryWebSocketFrameHandler extends SimpleChannelInboundHandler<BinaryWebSocketFrame> {
    private final Logger log = LoggerFactory.getLogger(BinaryWebSocketFrameHandler.class);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BinaryWebSocketFrame msg) throws Exception {
        log.info("接收" + ctx.channel().id().asLongText() + "的数据类型：{}", msg);
        log.info("binary {}", msg.content());
        ByteBuf content = msg.content();
        byte[] bytes = new byte[content.capacity()];
        content.readBytes(bytes);

        MegBoxBinaryUtil megBoxBinaryUtil = new MegBoxBinaryUtil(bytes);
        if (megBoxBinaryUtil.getCaptureFaceLen() != null && megBoxBinaryUtil.getCaptureFaceLen() != 0) {
            try (FileOutputStream stream = new FileOutputStream("/Users/xuanleung/Downloads/megbox/"+megBoxBinaryUtil.getType()+new Date().getTime()+".jpg")) {
                stream.write(megBoxBinaryUtil.getCaptureFace());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (megBoxBinaryUtil.getFullImageLen() != null && megBoxBinaryUtil.getFullImageLen() != 0) {
            try (FileOutputStream stream = new FileOutputStream("/Users/xuanleung/Downloads/megbox/"+megBoxBinaryUtil.getType()+new Date().getTime()+"full.jpg")) {
                stream.write(megBoxBinaryUtil.getFullImage());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if (megBoxBinaryUtil.getTop1Len() != null && megBoxBinaryUtil.getTop1Len() != 0) {
            try (FileOutputStream stream = new FileOutputStream("/Users/xuanleung/Downloads/megbox/"+megBoxBinaryUtil.getType()+new Date().getTime()+"top.jpg")) {
                stream.write(megBoxBinaryUtil.getTop1());
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    /**
     * 连接建立时
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerAddred {}", ctx.channel().id().asLongText());
    }

    /**
     * 连接关闭时
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info("handlerRemoved {}", ctx.channel().id().asLongText());
    }

    /**
     * 异常发生时
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(ctx.channel().id().asLongText() + "异常发生 {}", cause.getMessage());
        ctx.close();
    }

    public int byteArrayToInt(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }


}
