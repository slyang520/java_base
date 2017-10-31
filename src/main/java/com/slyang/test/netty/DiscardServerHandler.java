package com.slyang.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by slyang on 17/6/14.
 * <p>
 * * Handles a server-side channel.
 * <p>
 * 通道
 * 入站
 * 处理程序
 * 适配器
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)


	//	每当从客户端接收到新数据时，就会调用该方法。
// 在本例中，接收到的消息的类型是ByteBuf。
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)


		/** 1.服务端丢弃消息 **/
//		ByteBuf in = (ByteBuf) msg;
//		try {
//			while (in.isReadable()) { // (1)
//				System.out.print((char) in.readByte());
//			}
//		} finally {
//			// 丢弃
//			ReferenceCountUtil.release(msg); // (2)
//		}

		/** 2.服务端向客服端，响应消息 **/
		ctx.write(msg); // (1)
		ctx.flush(); // (2)


	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}