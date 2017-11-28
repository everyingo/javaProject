package com.mine.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mine.model.Page;

@Controller
@Scope("prototype")
public class ChartStatictisAction extends BaseAction<Page> {

	
	private static final long serialVersionUID = 1239027186245483455L;

}
