package com.zhuc.my.netty.http.t1;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhuc
 * @create 2013-12-20 下午1:33:12
 */
public class HttpChunkServerHandler extends SimpleChannelHandler {

	private static final Logger logger = LoggerFactory.getLogger(HttpChunkServerHandler.class);

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelHandler#exceptionCaught(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ExceptionEvent)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
		logger.error("客户端异常", e.getCause());
		e.getFuture().addListener(ChannelFutureListener.CLOSE);
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		logger.debug("<== " + e.getMessage());

		if (!(e.getMessage() instanceof HttpRequest)) {
			logger.error("not httpRequest!");
			return;
		}

		logger.debug("HttpRequest");

		HttpRequest request = (HttpRequest) e.getMessage();
		ChannelBuffer buffer = request.getContent();
		logger.debug("content: " + buffer.toString(Charset.defaultCharset()));

		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		ChannelBuffer writeBuffer = new DynamicChannelBuffer(2048);
		writeBuffer.writeBytes("hello, i'm netty".getBytes());
		response.setContent(writeBuffer);

		response.setHeader(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=utf-8");
		response.setHeader(HttpHeaders.Names.CONTENT_LENGTH, response.getContent().writerIndex());

		ctx.getChannel().write(response);
	}

}
