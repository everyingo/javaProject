package com.mine.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mine.util.FastDFSUtils;

@Controller
public class IndexController {

	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}

	@RequestMapping("/upload.do")
	public void uploadPic(@RequestParam(required = false) MultipartFile pic, HttpServletResponse response)
			throws IOException {
		String path = FastDFSUtils.uploadPic(pic.getBytes(), pic.getOriginalFilename(), pic.getSize());
		String url = path;
		JSONObject jo = new JSONObject();
		jo.put("imgUrl", url);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jo.toString());
	}
}