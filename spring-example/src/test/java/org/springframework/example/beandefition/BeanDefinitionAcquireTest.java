package org.springframework.example.beandefition;

import java.io.File;
import java.util.Objects;

import org.framework.example.mock.MockConfigure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;

import lombok.extern.slf4j.Slf4j;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/2/21上午11:17
 */
@Slf4j
public class BeanDefinitionAcquireTest {

	@Test
	public void testBeanDefinitionProcess() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("org.framework.example.mock.beandefinition");
		final BeanDefinition mockClass = context.getBeanDefinition("mockClass");
		Assertions.assertTrue(Objects.nonNull(mockClass));
		final int role = mockClass.getRole();
		Assertions.assertEquals(BeanDefinition.ROLE_APPLICATION, role);
		final Object source = mockClass.getSource();
		Assertions.assertTrue(source instanceof FileSystemResource);
		FileSystemResource fileSystemResource = (FileSystemResource) source;
		final String path = fileSystemResource.getPath();
		// 注意，这里获取的文件路径是编译后产生的 .class 文件的路径.
		Assertions.assertEquals("/Users/caogaoli/IdeaProjects/spring-framework/spring-example/out/production/classes/org/framework/example/mock/beandefinition/MockClass.class", path);
		final File file = fileSystemResource.getFile();
		Assertions.assertEquals("/Users/caogaoli/IdeaProjects/spring-framework/spring-example/out/production/classes/org/framework/example/mock/beandefinition/MockClass.class", file.getPath());
	}


	@Test
	public void testBeanDefinitionRoleDifferent() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MockConfigure.class);
		// role application.
		final BeanDefinition mockClass = context.getBeanDefinition("mockClass");
		Assertions.assertTrue(Objects.nonNull(mockClass));
		final int roleMockClass = mockClass.getRole();
		Assertions.assertEquals(BeanDefinition.ROLE_APPLICATION, roleMockClass);
		// role application.
		final BeanDefinition mockConfig = context.getBeanDefinition("mockConfigure");
		Assertions.assertTrue(Objects.nonNull(mockConfig));
		final int roleMockConfig = mockConfig.getRole();
		Assertions.assertEquals(BeanDefinition.ROLE_APPLICATION, roleMockConfig);
		// role infrastructure.
		final BeanDefinition internalEventListenerFactory = context.getBeanDefinition("org.springframework.context.event.internalEventListenerFactory");
		final int roleInternalEventListenerFactory = internalEventListenerFactory.getRole();
		Assertions.assertEquals(BeanDefinition.ROLE_INFRASTRUCTURE, roleInternalEventListenerFactory);
	}

	@Test
	public void testAcquireBeanDefinition(){
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MockConfigure.class);
		//////
	}

}
