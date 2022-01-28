package org.springframework.example.context;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.example.config.ApplicationConfig;
import org.springframework.example.domain.A;

import lombok.extern.slf4j.Slf4j;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/1/28上午11:04
 */
@Slf4j
public class ConnectTest {

	@Test
	public void test() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		log.info("bean is {}", context.getBean(A.class));
	}

}
