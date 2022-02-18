package org.framework.example.postprocessor;

import java.util.List;

import org.framework.example.utils.StackTracePrintTools;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import lombok.extern.slf4j.Slf4j;

/**
 * bean merged bean definition post processor.
 *
 * @author caogaoli
 * @date 2022/2/18上午10:56
 */
@Slf4j
public class MockBeanMergedBeanDefinitionPostProcessor implements
		MergedBeanDefinitionPostProcessor, BeanFactoryPostProcessor {

	private boolean isPrintStack;

	public MockBeanMergedBeanDefinitionPostProcessor(boolean isPrintStack) {
		this.isPrintStack = isPrintStack;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals(TargetInfo.TARGET_BEAN_NAME)) {
			log.debug("MockBeanMergedBeanDefinitionPostProcessor#postProcessBeforeInitialization");
			if (isPrintStack) {
				StackTracePrintTools.printInvokerStack();
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals(TargetInfo.TARGET_BEAN_NAME)) {
			log.debug("MockBeanMergedBeanDefinitionPostProcessor#postProcessAfterInitialization");
			if (isPrintStack) {
				StackTracePrintTools.printInvokerStack();
			}
		}
		return bean;
	}

	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (beanName.equals(TargetInfo.TARGET_BEAN_NAME)) {
			log.debug("MockBeanMergedBeanDefinitionPostProcessor#postProcessMergedBeanDefinition");
			if (isPrintStack) {
				StackTracePrintTools.printInvokerStack();
			}
		}
	}

	@Override
	public void resetBeanDefinition(String beanName) {
		if (beanName.equals(TargetInfo.TARGET_BEAN_NAME)) {
			log.debug("MockBeanMergedBeanDefinitionPostProcessor#resetBeanDefinition");
			if (isPrintStack) {
				StackTracePrintTools.printInvokerStack();
			}
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if (beanFactory instanceof DefaultListableBeanFactory) {
			final DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;
			final List<BeanPostProcessor> beanPostProcessors = defaultListableBeanFactory.getBeanPostProcessors();
			log.debug("bean post processors size is {}", beanPostProcessors.size());
			for (int index = 0; index < beanPostProcessors.size(); ++index) {
				final BeanPostProcessor beanPostProcessor = beanPostProcessors.get(index);
				log.debug("BeanPostProcessor invoker Name {}", beanPostProcessor.getClass().getSimpleName());
			}
		}
	}

}
