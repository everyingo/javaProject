package com.mine.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mine.core.bean.TestTb;
import com.mine.core.dao.TestTbDao;

@Service("testTbService")
@Transactional
public class TestTbServiceImpl implements TestTbService {

	@Autowired
	private TestTbDao testTbDao;

	@Override
	public int insertTestTb(TestTb testTb) {
		return testTbDao.insertTestTb(testTb);
	}

	@Override
	public int getCount() {
		return testTbDao.getCount();
	}

}
