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
		// ������ˢ���¼�
		if (arg0 instanceof ContextRefreshedEvent) {
			// ��ѯ������Ȩ��
			List<Right> rights = rightService.findAllEntities();
			Map<String, Right> map = new HashMap<String, Right>();
			for (Right r : rights) {
				map.put(r.getRightUrl(), r);
			}
			ActionContext ac=ActionContext.getContext();
			Map<String,Object> apmap=ac.getApplication();
			if (apmap != null) {
				apmap.put("all_rights_map", map);
				System.out.println("---------------------Ȩ�޳�ʼ����ɣ���----------------------------");
			}
		}
	}*/

	

}
