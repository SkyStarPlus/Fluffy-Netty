package org.netty.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.BufferUnderflowException;
import java.nio.channels.Channel;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.net.ssl.SSLSessionBindingEvent;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.AbstractChannel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;

public class ChatClient {
	static final String HOST = System.getProperty("host", "127.0.0.1");
	static final int PORT = Integer.parseInt("8026");
	
	public static void main(String[] args) throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		
		try{
			Bootstrap b = new Bootstrap()
					.group(group)
					.channel(NioSocketChannel.class)
					.handler(new ChatClientInitializer());
			io.netty.channel.Channel ch = b.connect(HOST, PORT).sync().channel();
			
//			ChannelFuture lastWriteFuture = null;
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			for (;;) {
				String line = in.readLine();
				if (line == null) {
					break;
				}
				
//				lastWriteFuture = ch.writeAndFlush(line + "\r\n");
				ch.write(line + "\r\n");
				
				
				if ("bye".equals(line.toLowerCase())) {
					ch.closeFuture().sync();
					break;
				}
			}
			
//			if(lastWriteFuture!= null){
//				lastWriteFuture.sync();
//			}
		}catch(Exception e){
			
		}finally{
			group.shutdownGracefully();
		}
	}
	
	
}
