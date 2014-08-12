package com.zhuc.my.httpclient.t2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收上传文件流并本地保存
 * @author zhuc
 * @create 2013-6-18 下午4:25:01
 */
public class MultipartServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5507741766545252553L;

	private static final Logger logger = LoggerFactory.getLogger(MultipartServlet.class);

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		logger.info("isMultipart:" + isMultipart);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);

			try {
				List<FileItem> items = upload.parseRequest(req);
				for (FileItem fileItem : items) {
					if (fileItem.isFormField()) {
						// 普通表单文本信息处理
						logger.info("fieldName:" + fileItem.getFieldName());
						logger.info("string:" + fileItem.getString("UTF-8"));
					} else {
						// 上传文件信息处理
						logger.info("上传文件名:" + fileItem.getName());
						logger.info("part:" + fileItem.getFieldName());

						// 写文件 方法1
						FileOutputStream fos = new FileOutputStream("e:\\b.png");
						fos.write(fileItem.get());
						fos.close();

						// 写文件 方法2
						FileUtils.copyInputStreamToFile(fileItem.getInputStream(), new File("e:\\c.png"));

						// 写文件 方法3
						// 方法3其实就是对方法1的封装
						fileItem.write(new File("e:\\d.png"));
					}
				}

				resp.getWriter().write("hello");
			} catch (Exception e) {
				logger.error("", e);
			}

		}
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
