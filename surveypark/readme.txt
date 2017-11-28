一.項目结构
  struts2+hibernate4+spring4 + MySQL

二.业务组成
  1.用户注册
  2.用户登录
  3.新建调查
  4.我的调查
  5.参与调查
  6.权限管理
  7.角色管理
  8.用户授权
  9.日志管理

三.项目亮点
  1.业务分析，和解决问题的思路：
           体现在survey page question answer 类的建设上，以及参与调查 涉及到的调查统计  questionStatistics 和  optionStatistics。
  
  2.struts2拦截器的应用 ：RightIntercepter（登录和权限拦截器）CatchUrlIntercepter（权限url地址 捕获拦截器）
  
  3.spring aop 的应用： <1>声明式事务  <2>日志
  
  4.松耦合式注入：UserAware接口，注入session中的user对象
  
  5.spring 的缓存应用：CacheKeyGenerator
  
  6.定时器：LogTask 定时完成logs表的创建，并且使用分表存储日志（动态日志表的生成）
  
  7.分库：SurveyparkDataSourceRouter 利用数据源路由器 实现分库，进行选择性存储
  
  8.spring的监听器：listener spring容器初始化完成后 加载到资源到上下文中  
  
  9.spring的远程调用 基于socket  HttpInvokerServiceExporter
  
           
  
  
  