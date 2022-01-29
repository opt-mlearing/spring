package org.framework.example.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 定向改变 UserForAutowiredModel的注入模型.
 *
 * @author caogaoli
 * @date 2022/1/28下午5:00
 */
@Slf4j
@Component
public class ResetUserForAutowiredModel implements BeanFactoryPostProcessor {

	private static final String BEAN_NAME = "useForAutowiredModel";

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition(BEAN_NAME);
		log.info("bean definition origin mode {}", beanDefinition.getAutowireMode());
		beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
	}

}