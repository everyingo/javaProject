package com.mine.listener;




import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;




@Component
@SuppressWarnings("rawtypes")
public class InitRightListener implements ApplicationListener {

	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/*@Resource(name = "rightServiceImpl")
	private RightService rightService;


	public void onApplicationEvent(ApplicationEvent arg0) {
		// 上下文刷新事件
		if (arg0 instanceof ContextRefreshedEvent) {
			// 查询出所有权限
			List<Right> rights = rightService.findAllEntities();
			Map<String, Right> map = new HashMap<String, Right>();
			for (Right r : rights) {
				map.put(r.getRightUrl(), r);
			}
			ActionContext ac=ActionContext.getContext();
			Map<String,Object> apmap=ac.getApplication();
			if (apmap != null) {
				apmap.put("all_rights_map", map);
				System.out.println("---------------------权限初始化完成！！----------------------------");
			}
		}
	}*/

	

}
