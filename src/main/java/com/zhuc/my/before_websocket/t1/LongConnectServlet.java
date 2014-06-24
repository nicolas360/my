package com.zhuc.my.before_websocket.t1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

/**
 * @title 		iframe 长连接
 * @description	
 * @usage		
 * @copyright	Copyright 2012  HUAXIA Corporation. All rights reserved.
 * @company		上海华夏通信有限公司
 * @author		ZHUC
 * @create		2014-6-17 上午10:24:16
 */
public class LongConnectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3752700206057058129L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		json.put("name", "zhuc");
		json.put("age", 25);

		resp.setContentType("text/html;charset=UTF-8");

		//		while (true) {
		//		try {
		//			Thread.sleep(2000l);
		//		} catch (InterruptedException e) {
		//			e.printStackTrace();
		//		}

		StringBuilder sb = new StringBuilder();
		sb.append("<script type=\"text/javascript\">");
		//		sb.append("alert('" + result + "');");
		sb.append("parent.msg(" + json.toJSONString() + ");"); // 调用jsp中的父页面的msg
		sb.append("</script>");

		resp.getWriter().write(sb.toString());
		resp.flushBuffer();

		//		}

	}
}
