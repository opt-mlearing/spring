package org.springframework.example.postprocessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.example.domain.A;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/1/28下午3:47
 */
public class GetOrResetModeBeanFactoryPostProcessorTest {

	@Test
	public void getAutoWiredMode() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.framework.example.postprocessor");
		context.register(A.class);
		context.refresh();
		AbstractBeanDefinition beanDefinition = (AbstractBeanDefinition) context.getBeanDefinition("a");
		Assertions.assertEquals(AbstractBeanDefinition.AUTOWIRE_BY_TYPE, beanDefinition.getAutowireMode());
	}

}
