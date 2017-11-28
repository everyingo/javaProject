package com.mine.server;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class parseDemo1 {

	public static void main(String[] args) throws ParserConfigurationException,
			SAXException, IOException {
		// 1.��ȡ��������
		SAXParserFactory factory = SAXParserFactory.newInstance();
		// 2.�ӽ��������л�ȡ������
		SAXParser parser = factory.newSAXParser();
		PersonHandler handler = new PersonHandler();
		parser.parse(Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("web.xml"), handler);
		Map<String, String> servlet = handler.getServlet();
		Map<String, String> mapping = handler.getMapping();
		System.out.println(servlet);
		System.out.println(mapping);
		System.out.println(mapping.get("/log"));
		
	}

}
