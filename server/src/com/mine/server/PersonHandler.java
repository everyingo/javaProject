package com.mine.server;

import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PersonHandler extends DefaultHandler {

	private Map<String, String> servlet;
	private Map<String, String> mapping;
	private String servlet_name;
	private String servlet_class;
	private String url_pattern;
	private String tag;

	public PersonHandler() {
		servlet = new HashMap<String, String>();
		mapping = new HashMap<String, String>();
		tag = "";
		servlet_name = "";
		servlet_class = "";
		url_pattern = "";
	}

	@Override
	public void startDocument() throws SAXException {

	}

	@Override
	public void endDocument() throws SAXException {

	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName == null || "".equals(qName)) {
			return;
		}
		if (qName.equals("servlet")) {
			tag = "servlet";
		} else if (qName.equals("servlet-name")) {
			tag = "servlet-name";
		} else if (qName.equals("servlet-class")) {
			tag = "servlet-class";
		} else if (qName.equals("servlet-mapping")) {
			tag = "servlet-mapping";
		} else if (qName.equals("url-pattern")) {
			tag = "url-pattern";
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (qName == null || "".equals(qName)) {
			return;
		}
		if (qName.equals("servlet")) {
			servlet.put(servlet_name, servlet_class);
		} else if (qName.equals("servlet-mapping")) {
			mapping.put(url_pattern, servlet_name);
		}
		tag = "";
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (tag.equals("")) {
			return;
		}
		if (tag.equals("servlet-name")) {
			String str = new String(ch, start, length);
			this.servlet_name = str;

		} else if (tag.equals("servlet-class")) {
			String str = new String(ch, start, length);
			this.servlet_class = str;

		} else if (tag.equals("url-pattern")) {
			String str = new String(ch, start, length);
			this.url_pattern = str;
		}
	}

	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

}
