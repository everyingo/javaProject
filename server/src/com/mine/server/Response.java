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
		//1.HTTPЭ��汾��״̬�롢������Ϣ
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
		//2.��Ӧͷ��Response Head��
		headInfo.append("Server:mine Server/0.0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset=GBK").append(CRLF);
		//���ĳ���
		headInfo.append("Content-Length:").append(len).append(CRLF);
		//3.����ǰ�Ŀո�
		headInfo.append(CRLF);
	}
	
	//�������
	public Response print(String info){
		responseContext.append(info);
		len+=info.getBytes().length;
		return this;
	}
	
	//������� + ����
	public Response println(String info){
		responseContext.append(info).append(CRLF);
		len+=(info+CRLF).getBytes().length;
		return this;
	}
	//���IO���ͻ���
	public void pushToClient(int code) throws IOException{
		creatHeadInfo(code);
		//�����Ӧͷ��Ϣ+����
		bw.append(headInfo.toString());
		//�����Ӧ����
		bw.append(responseContext.toString());
		//System.out.println(headInfo.toString());
		bw.flush();
	}
	
}
