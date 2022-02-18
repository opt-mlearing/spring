package org.springframework.example.merge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/2/18下午3:20
 */
public class BeanDefinitionResolveTest {

	@Test
	public void testBeanDefinitionMergeProcess() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.framework.example.mock.merge");
		context.refresh();
		//final BeanDefinition mockAbstractClass = context.getBeanDefinition("mockAbstractClass");
		final BeanDefinition mockConcreteClass = context.getBeanDefinition("mockConcreteClass");
		//final BeanDefinition mockInterface = context.getBeanDefinition("mockInterface");
	}

}
