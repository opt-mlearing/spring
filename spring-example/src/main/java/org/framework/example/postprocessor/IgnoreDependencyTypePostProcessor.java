package org.framework.example.postprocessor;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT;
import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_NAME;
import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;
import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR;
import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_NO;

import org.framework.example.mock.ignore.MockB;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * ignore dependency type postprocessor.
 *
 * @author caogaoli
 * @date 2022/2/17下午5:23
 */
@Slf4j
@AllArgsConstructor
public class IgnoreDependencyTypePostProcessor implements BeanFactoryPostProcessor {

	private String beanName;
	private int modifyAutowireMode = 0;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		verifyModifyAutowireMode();
		addIgnoreDependencyType(beanFactory);
		modifyTargetBeanDefinitionAutowireMode(beanFactory);
	}

	/* add ignore instance type */
	private void addIgnoreDependencyType(ConfigurableListableBeanFactory beanFactory) {
		beanFactory.ignoreDependencyType(MockB.class);
	}

	/* modify target instance  */
	private void modifyTargetBeanDefinitionAutowireMode(ConfigurableListableBeanFactory beanFactory) {
		log.debug("modify beanDefinition name is {}", beanName);
		final AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) beanFactory.getBeanDefinition(beanName);
		final int currentAutowireMode = beanDefinition.getAutowireMode();
		if (currentAutowireMode != modifyAutowireMode) {
			beanDefinition.setAutowireMode(modifyAutowireMode);
		}
	}

	/* verify customer setting autowire mode effective */
	private void verifyModifyAutowireMode() {
		switch (modifyAutowireMode) {
			case AUTOWIRE_NO:
				log.info("autowire mode is null");
				break;
			case AUTOWIRE_BY_NAME:
				log.info("autowire mode by name");
				break;
			case AUTOWIRE_BY_TYPE:
				log.info("autowire mode by type");
				break;
			case AUTOWIRE_CONSTRUCTOR:
				log.info("autowire mode by constructor");
				break;
			case AUTOWIRE_AUTODETECT:
				log.info("autowire mode by autodetect");
				break;
			default:
				throw new IllegalArgumentException();
		}
	}

}
