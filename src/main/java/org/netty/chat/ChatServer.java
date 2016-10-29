package org.netty.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ChatServer {
	static final int PORT = Integer.parseInt("8026");
	
	public static void main(String[] arg) throws Exception{
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		
		try{
			ServerBootstrap bootStrap = new ServerBootstrap()
					.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChatServerInitializer());
			System.out.println("Server is started");
			
			ChannelFuture future = bootStrap.bind(PORT).sync();
			future.channel().closeFuture().sync();
			
			
		}catch(Exception e){
			
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
}
