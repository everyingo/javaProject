package com.mine.core.service.product;

import org.springframework.stereotype.Service;

import com.mine.core.common.fastdfs.FastDFSUtils;

@Service("uploadService")
public class UploadServiceImpl implements UploadService{

	@Override
	public String uploadPic(byte[] pic, String name, long size) {
		return FastDFSUtils.uploadPic(pic, name, size);
	}

}
