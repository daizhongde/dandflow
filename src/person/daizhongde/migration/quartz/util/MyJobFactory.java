package person.daizhongde.migration.quartz.util;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

public class MyJobFactory extends AdaptableJobFactory {

	//这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
//	@Autowired
	private AutowireCapableBeanFactory capableBeanFactory;
	
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		//调用父类的方法
		Object jobInstance = super.createJobInstance(bundle);
		//进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}

	public void setCapableBeanFactory(AutowireCapableBeanFactory capableBeanFactory) {
		this.capableBeanFactory = capableBeanFactory;
	}
	
}