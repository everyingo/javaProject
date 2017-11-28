package com.mine.aware;

import com.mine.model.User;

/**
 * User 自动注入接口
 * @author Administrator
 *
 */
public interface UserAware {

	public void setUser(User user);
}
