package com.mine.core.service.product;

public interface UploadService {

	/**
	 * 上传文件
	 * 
	 * @param pic
	 * @param name
	 * @param size
	 * @return
	 */
	String uploadPic(byte[] pic, String name, long size);
}
