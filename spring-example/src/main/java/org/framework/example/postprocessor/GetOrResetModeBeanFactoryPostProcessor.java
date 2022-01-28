package org.framework.example.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * with bean autowired model type.
 *
 * @author caogaoli
 * @date 2022/1/28下午3:17
 */
@Slf4j
@Component
public class GetOrResetModeBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition("a");
		int autowireMode = beanDefinition.getAutowireMode();
		/* print 注入模型 */
		log.info("autowired mode is {}", autowireMode);
		beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
	}

}
