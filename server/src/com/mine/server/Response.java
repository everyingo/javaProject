package com.mine.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

public class Response {

	public static String CRLF = "\r\n";
	public static String BLANK = " ";

	private BufferedWriter bw;
	private StringBuilder headInfo;
	private StringBuilder responseContext;
	private int len;
	
    
	public Response(){
		headInfo=new StringBuilder();
		responseContext=new StringBuilder();
		len=0;
	}
	public Response(OutputStream os){
		this();
		bw=new BufferedWriter(new OutputStreamWriter(os));
	}
	private void creatHeadInfo(int code){
		//1.HTTP协议版本、状态码、描述信息
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
		case 200:
			headInfo.append("OK");
			break;
        case 404:
        	headInfo.append("Not Found");
			break;
        case 500:
        	headInfo.append("SEVER ERROR");
			break;

		default:
			headInfo.append("Dot Know");
			break;
		}
		headInfo.append(CRLF);
		//2.响应头（Response Head）
		headInfo.append("Server:mine Server/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		//正文长度
		headInfo.append("Content-Length:").append(len).append(CRLF);
		//3.正文前的空格
		headInfo.append(CRLF);
	}
	
	//添加正文
	public Response print(String info){
		responseContext.append(info);
		len+=info.getBytes().length;
		return this;
	}
	
	//添加正文 + 换行
	public Response println(String info){
		responseContext.append(info).append(CRLF);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	//输出IO至客户端
	public void pushToClient(int code) throws IOException{
		creatHeadInfo(code);
		//添加响应头信息+换行
		bw.append(headInfo.toString());
		//添加响应正文
		bw.append(responseContext.toString());
		//System.out.println(headInfo.toString());
		bw.flush();
	}
	
}
