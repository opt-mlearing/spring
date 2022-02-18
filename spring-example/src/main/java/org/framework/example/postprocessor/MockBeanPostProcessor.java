package org.framework.example.postprocessor;

import static org.framework.example.utils.StackTracePrintTools.printInvokerStack;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * bean post processor.
 *
 * @author caogaoli
 * @date 2022/2/18上午9:52
 */
@Slf4j
public class MockBeanPostProcessor implements BeanPostProcessor {

	private boolean isPrintStack;

	public MockBeanPostProcessor(boolean isPrintStack) {
		this.isPrintStack = isPrintStack;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals(TargetInfo.TARGET_BEAN_NAME)) {
			log.debug("bean name {} call back invoker method {}",
					beanName, "MockBeanPostProcessor#postProcessBeforeInitialization");
			if (isPrintStack) {
				printInvokerStack();
			}
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (beanName.equals(TargetInfo.TARGET_BEAN_NAME)) {
			log.debug("bean name {} call back invoker method {}",
					beanName, "MockBeanPostProcessor#postProcessAfterInitialization");
			if (isPrintStack) {
				printInvokerStack();
			}
		}
		return bean;
	}

}
