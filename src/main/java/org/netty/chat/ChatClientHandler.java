package org.netty.chat;

import io.netty.channel.ChannelHandler;
import routing.Pipe.CommandMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

//public class ChatClientHandler extends SimpleChannelInboundHandler<String> {
public class ChatClientHandler extends ChannelInboundMessageHandlerAdapter<String> {

	public void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
		System.out.println(msg);
		
	}

//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
////		System.err.println(msg);
//		System.out.println(msg);
//		
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		// TODO Auto-generated method stub
//		super.exceptionCaught(ctx, cause);
//		cause.printStackTrace();
//		ctx.close();
//	}
	
	
	

	
	
}
