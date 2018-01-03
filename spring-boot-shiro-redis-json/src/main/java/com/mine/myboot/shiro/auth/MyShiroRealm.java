package com.mine.myboot.shiro.auth;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.mine.myboot.shiro.pojo.Permission;
import com.mine.myboot.shiro.pojo.User;
import com.mine.myboot.shiro.service.PermissionService;
import com.mine.myboot.shiro.service.UserService;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("MyShiroRealm.doGetAuthorizationInfo()-授权");
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		// 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<Permission> permissionList = permissionService.selectPermissionListByUserId(user.getId());
		for (Permission permission : permissionList) {
			info.addStringPermission(permission.getUrl());
		}
		return info;
	}

	/**
	 * 登录认证
	 * 
	 * @param authcToken
	 * @return
	 * @throws AuthenticationException
	 */
	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()-登录认证");
		// 获取用户的输入的账号.
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String username = token.getUsername();
		String password = token.getPassword() == null ? null : String.valueOf(token.getPassword());
		User user = userService.selectUserByUserName(username);
		if (user == null)
			throw new AccountException("帐号不存在！");
		else if (!user.getPassword().equals(password.trim()))
			throw new AccountException("密码错误！");
		/*
		 * if (0==user.getEnable()) { throw new LockedAccountException(); }
		 */
		/*
		 * SimpleAuthenticationInfo authenticationInfo = new
		 * SimpleAuthenticationInfo(user, // 用户 user.getPassword(), // 密码
		 * ByteSource.Util.bytes(username), getName() // realm name );
		 */
		// 当验证都通过后，把用户信息放在session里
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("userSession", user);
		session.setAttribute("userSessionId", user.getId());
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

}
