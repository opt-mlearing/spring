package org.springframework.example.context;

import org.framework.example.mock.MockClass;
import org.framework.example.mock.MockConfig;
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

}
