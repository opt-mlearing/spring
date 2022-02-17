package org.framework.example.postprocessor;

import org.framework.example.mock.MockNoComponentBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.util.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * TODO
 *
 * @author caogaoli
 * @date 2022/2/17下午2:14
 */
@Slf4j
public class MockBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// empty implement.
		log.debug("MockBeanFactoryPostRegistry#postProcessBeanFactory empty implement.");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		log.debug("MockBeanFactoryPostRegistry#postProcessBeanDefinitionRegistry not empty implement.");
		final RootBeanDefinition beanDefinition = new RootBeanDefinition(MockNoComponentBean.class);
		BeanNameGenerator beanNameGenerator = AnnotationBeanNameGenerator.INSTANCE;
		final String beanName = beanNameGenerator.generateBeanName(beanDefinition, registry);
		if (StringUtils.hasLength(beanName)) {
			registry.registerBeanDefinition(beanName, beanDefinition);
		} else {
			log.error("from bean Name Generator get Empty name && registry failed.");
		}
	}
}
