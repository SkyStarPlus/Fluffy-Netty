//package org.netty.chat;
//
//import java.net.InetAddress;
//import java.nio.channels.Channel;
//import java.util.Date;
//
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelFutureListener;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import io.netty.channel.DefaultChannelConfig;
//import io.netty.channel.SimpleChannelInboundHandler;
//import io.netty.channel.group.ChannelGroup;
//import io.netty.channel.group.DefaultChannelGroup;
//import io.netty.util.concurrent.GlobalEventExecutor;
//import routing.Pipe.CommandMessage;
//
//public class ChatServerHandler extends SimpleChannelInboundHandler<String> {
//
//	public final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
////	public final ChannelGroup channels = new DefaultChannelGroup("aaa",GlobalEventExecutor.INSTANCE);
////	public final ChannelGroup channelConf = new Def
//	String MyServer = "localhost";
//	@Override
//	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//		io.netty.channel.Channel incoming = ctx.channel();
//		System.out.println(channels.size());
//		for(io.netty.channel.Channel channel : channels){
//			System.out.println("ITERATING FOR "+ channel.remoteAddress());
////			if(channel != incoming){
//				channel.write("["+ incoming.remoteAddress() +"]"+ msg + "\r\n");
////			}
//		}
////		System.out.println("--> got incoming message");
////		io.netty.channel.Channel currentChannel = ctx.channel();
////		System.out.println("[INFO] - " + ((io.netty.channel.Channel) currentChannel).remoteAddress() + " - " + msg);
////		ctx.write("[Server] - Success \r\n");
//		
////		ctx.channel().writeAndFlush("[Server] - Success");
////		ctx.writeAndFlush("[Server] - Success");
////		ChannelFuture future = ctx.write("[Server] - Success");
//		
//		
////		String response;
////		Boolean close = false;
////		if(msg.isEmpty()){
////			response = "Please type something.\r\n";
////		} else if("bye".equals(msg.toLowerCase())){
////			response = "Have a good day!\r\n";
////			close = true;
////		}else{
////			response = "Did you say '" + msg + "'?\r\n";
////		}
////		
////		ChannelFuture future = ctx.write(response);
////		if(close){
////			future.addListener(ChannelFutureListener.CLOSE);
////		}
//		
//	}
//
//	
//
//	@Override
//	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//		
//		io.netty.channel.Channel incoming = ctx.channel();
//		
//		
//		for(io.netty.channel.Channel channel : channels){			
//			channel.write("[Server]"+ incoming.remoteAddress() +"has joined! \r\n");
//		}
//		channels.add(ctx.channel());
//		super.handlerAdded(ctx);
//	}
//
//
//
//	@Override
//	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//		super.handlerAdded(ctx);
//		io.netty.channel.Channel incoming = ctx.channel();
//		
//		for(io.netty.channel.Channel channel : channels){
//			channel.write("[Server]"+ incoming.remoteAddress() +"has removed! \r\n");
//		}
//		channels.remove(ctx.channel());
//		super.handlerRemoved(ctx);
//	}
//
//
//
//	@Override
//	public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		// TODO Auto-generated method stub
//		super.channelActive(ctx);
//		channels.add(ctx.channel());
//		System.out.println("Join "+ ctx.channel().remoteAddress() + "!\r\n");
////		ctx.write("Welcome to "+ InetAddress.getLocalHost().getHostName() + "!\r\n");
////		ctx.write("It is "+ new Date() + " now \r\n");
//		ctx.writeAndFlush("Welcome to "+ InetAddress.getLocalHost().getHostName() + "!\r\n");
//		ctx.writeAndFlush("It is "+ new Date() + " now \r\n");
//	}
//
//	@Override
//	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//		// TODO Auto-generated method stub
//		super.channelReadComplete(ctx);
//		ctx.flush();
//	}
//
//	@Override
//	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//		// TODO Auto-generated method stub
//		System.out.println("EXCEPTION");
//		super.exceptionCaught(ctx, cause);
//		cause.printStackTrace();
//		ctx.close();
//	}
//	
//	
//}








package org.netty.chat;

import java.net.InetAddress;
import java.nio.channels.Channel;
import java.util.Date;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import routing.Pipe.CommandMessage;

public class ChatServerHandler extends ChannelInboundMessageHandlerAdapter<String> {
	private static final ChannelGroup channels = new DefaultChannelGroup();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    	io.netty.channel.Channel incoming = ctx.channel();
        for (io.netty.channel.Channel channel : channels) {
            channel.write("[SERVER] - " + incoming.remoteAddress() + " has joined\n");
        }
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    	io.netty.channel.Channel incoming = ctx.channel();
        for (io.netty.channel.Channel channel : channels) {
            channel.write("[SERVER] - " + incoming.remoteAddress() + " has left\n");
        }
        channels.remove(ctx.channel());
    }

    
    public void messageReceived(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
    	io.netty.channel.Channel incoming = channelHandlerContext.channel();
    	System.out.println(channels.size());
        for (io.netty.channel.Channel channel : channels) {
            if (channel != incoming){
                channel.write("[" + incoming.remoteAddress() + "]" + s + "\n");
            }
        }

    }	
	
}

