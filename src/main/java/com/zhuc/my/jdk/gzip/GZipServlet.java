package com.zhuc.my.jdk.gzip;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GZipServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(GZipServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -5813142033127987901L;

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		byte[] data = "我是一个中国人！".getBytes("UTF-8");

		OutputStream out = null;
		try {
			byte[] output = compress(data);

			// 设置Content-Encoding，这是关键点！  
			resp.setHeader("Content-Encoding", "gzip");
			resp.setContentType("text/plain;charset=utf-8");
			// 设定输出流中内容长度 
			resp.setContentLength(output.length);

			out = resp.getOutputStream();
			out.write(output);
			out.flush();

		} catch (Exception e) {
			logger.error("", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}

	/** 
	 * 压缩 
	 *  
	 * @param data 
	 * @return 
	 * @throws Exception 
	 */
	private byte[] compress(byte[] data) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 压缩  
		GZIPOutputStream gos = new GZIPOutputStream(baos);
		gos.write(data, 0, data.length);
		gos.finish();

		byte[] output = baos.toByteArray();
		baos.flush();
		baos.close();

		return output;
	}

}
