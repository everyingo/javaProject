package com.mine.myboot.shiro.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.mine.myboot.shiro.auth.MyShiroRealm;
import com.mine.myboot.shiro.pojo.Permission;
import com.mine.myboot.shiro.service.PermissionService;

@Configuration
public class ShiroConfig {

	@Autowired
	private PermissionService permissionService;

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		// 拦截器.
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/font-awesome/**", "anon");
		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		// 自定义加载权限资源关系
		List<Permission> permissionList = permissionService.initPermission();
		for (Permission permission : permissionList) {

			if (!StringUtils.isEmpty(permission.getUrl())) {
				String permsUrl = "perms[" + permission.getUrl() + "]";
				filterChainDefinitionMap.put(permission.getUrl(), permsUrl);
			}
		}
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		System.out.println("ShiroFilterFactoryBean加载完毕...");
		return shiroFilterFactoryBean;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	/**
	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
	 * 
	 * @return
	 */
	@Bean
	public MyShiroRealm myShiroRealm() {
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		return myShiroRealm;
	}
}
