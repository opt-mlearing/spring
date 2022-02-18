package org.springframework.example.ignore;

import static org.springframework.beans.factory.config.AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE;

import java.util.Objects;

import org.framework.example.mock.ignore.ComposeMockBean;
import org.framework.example.mock.ignore.MockA;
import org.framework.example.mock.ignore.MockB;
import org.framework.example.mock.ignore.MockC;
import org.framework.example.postprocessor.IgnoreDependencyInterfacePostProcessor;
import org.framework.example.postprocessor.IgnoreDependencyTypePostProcessor;
import org.framework.example.postprocessor.MockBeanMergedBeanDefinitionPostProcessor;
import org.framework.example.postprocessor.MockBeanPostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/2/17下午4:15
 */
@Slf4j
public class IgnoreMockBeanTest {

	@Test
	public void testIgnoreInterface() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.framework.example.mock.ignore");
		// context.getBeanFactory().ignoreDependencyInterface(MockInterface.class);
		context.addBeanFactoryPostProcessor(new IgnoreDependencyInterfacePostProcessor());
		context.refresh();
		log.debug("finish bean factory container refresh.");
		final MockA beanA = context.getBean(MockA.class);
		Assertions.assertTrue(Objects.nonNull(beanA));
		final MockB beanB = context.getBean(MockB.class);
		Assertions.assertTrue(Objects.nonNull(beanB));
		final MockC beanC = context.getBean(MockC.class);
		Assertions.assertTrue(Objects.nonNull(beanC));
		final ComposeMockBean composeInstance = context.getBean(ComposeMockBean.class);
		Assertions.assertTrue(Objects.nonNull(composeInstance));
		final MockA mockA = composeInstance.getMockA();
		final MockB mockB = composeInstance.getMockB();
		final MockC mockC = composeInstance.getMockC();
		Assertions.assertTrue(Objects.isNull(mockA));
		Assertions.assertTrue(Objects.nonNull(mockB));
		Assertions.assertTrue(Objects.nonNull(mockC));
	}

	@Test
	public void testIgnoreType() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.framework.example.mock.ignore");
		context.addBeanFactoryPostProcessor(new IgnoreDependencyInterfacePostProcessor());
		context.addBeanFactoryPostProcessor(
				new IgnoreDependencyTypePostProcessor("composeMockBean", AUTOWIRE_BY_TYPE));
		context.refresh();
		log.debug("finish bean factory container refresh.");
		final MockA beanA = context.getBean(MockA.class);
		Assertions.assertTrue(Objects.nonNull(beanA));
		final MockB beanB = context.getBean(MockB.class);
		Assertions.assertTrue(Objects.nonNull(beanB));
		final MockC beanC = context.getBean(MockC.class);
		Assertions.assertTrue(Objects.nonNull(beanC));
		final ComposeMockBean composeInstance = context.getBean(ComposeMockBean.class);
		Assertions.assertTrue(Objects.nonNull(composeInstance));
		final MockA mockA = composeInstance.getMockA();
		final MockB mockB = composeInstance.getMockB();
		final MockC mockC = composeInstance.getMockC();
		Assertions.assertTrue(Objects.isNull(mockA));
		Assertions.assertTrue(Objects.isNull(mockB));
		Assertions.assertTrue(Objects.nonNull(mockC));
	}

	@Test
	public void testGetCurrentStackTrace() {
		final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		for (int index = 0; index < stackTrace.length; ++index) {
			StackTraceElement element = stackTrace[index];
			log.debug("invoker class {} -- method {}", element.getClassName(), element.getMethodName());
		}
	}

	@Test
	public void testBeanPostProcessorInvokerSequence() {
		// 获取BeanPostProcessor相关的执行顺序.
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.framework.example.mock.ignore");
		// acquire bean factory.
		final ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		beanFactory.addBeanPostProcessor(new MockBeanPostProcessor(false));
		final MockBeanMergedBeanDefinitionPostProcessor composeBeanFactoryAndBeanPostProcessor =
				new MockBeanMergedBeanDefinitionPostProcessor(false);
		beanFactory.addBeanPostProcessor(composeBeanFactoryAndBeanPostProcessor);
		context.addBeanFactoryPostProcessor(composeBeanFactoryAndBeanPostProcessor);
		context.refresh();
	}

}
