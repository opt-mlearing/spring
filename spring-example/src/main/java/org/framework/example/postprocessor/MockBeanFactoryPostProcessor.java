package org.framework.example.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * mock BeanFactoryPostProcessor.
 *
 * @author caogaoli
 * @date 2022/2/17下午3:15
 */
@Slf4j
public class MockBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		log.debug("MockBeanFactoryPostProcessor#postProcessBeanFactory");
	}

}
