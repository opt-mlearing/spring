package org.framework.example.postprocessor;

import org.framework.example.mock.ignore.MockInterface;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * ignore dependency interface post processor.
 *
 * @author caogaoli
 * @date 2022/2/17下午4:18
 */
public class IgnoreDependencyInterfacePostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		AbstractBeanDefinition composeMockBean =
				(AbstractBeanDefinition) beanFactory.getBeanDefinition("composeMockBean");
		final int autowireMode = composeMockBean.getAutowireMode();
		if (autowireMode == AutowireCapableBeanFactory.AUTOWIRE_NO) {
			composeMockBean.setAutowireMode(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
		}
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		if (registry instanceof ConfigurableListableBeanFactory) {
			ConfigurableListableBeanFactory beanFactory = (ConfigurableListableBeanFactory) registry;
			// 增加忽略接口类型.
			beanFactory.ignoreDependencyInterface(MockInterface.class);
		}
	}

}
