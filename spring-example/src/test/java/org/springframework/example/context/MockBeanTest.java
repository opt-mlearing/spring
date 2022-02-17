package org.springframework.example.context;

import org.framework.example.mock.MockClass;
import org.framework.example.mock.MockConfig;
import org.framework.example.mock.MockNoComponentBean;
import org.framework.example.postprocessor.MockBeanDefinitionRegistryPostProcessor;
import org.framework.example.postprocessor.MockBeanFactoryPostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * test case.
 *
 * @author caogaoli
 * @date 2022/1/29下午5:27
 */
@Slf4j
public class MockBeanTest {

	@Test
	public void testMock() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MockConfig.class);
		final MockClass bean = context.getBean(MockClass.class);
		log.info("{}", bean);
		context.refresh();
	}

	@Test
	public void testAddBeanFactoryRelevantPostProcessor() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.framework.example.mock");
		context.addBeanFactoryPostProcessor(new MockBeanDefinitionRegistryPostProcessor());
		context.addBeanFactoryPostProcessor(new MockBeanFactoryPostProcessor());
		context.refresh();
		log.debug("finished bean factory contain refresh ...");
		final Object mockBeanInstanceByName = context.getBean("mockNoComponentBean");
		Assertions.assertTrue(mockBeanInstanceByName instanceof MockNoComponentBean);
		final MockNoComponentBean mockBeanInstanceByType = context.getBean(MockNoComponentBean.class);
		Assertions.assertEquals(mockBeanInstanceByType.hashCode(), mockBeanInstanceByType.hashCode());
	}

}
