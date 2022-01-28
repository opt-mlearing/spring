package org.springframework.example.context;

import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.example.config.ApplicationConfig;
import org.springframework.example.domain.A;
import org.springframework.example.domain.B;

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
	public void testConnect() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		log.info("bean is {}", context.getBean(A.class));
	}

	@Test
	public void testInitContextAndGetBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(A.class);
		context.registerBean(B.class);
		context.refresh();
		A beanA = context.getBean(A.class);
		Assert.assertTrue(Objects.nonNull(beanA));
		log.info("get bean a {} by Type", beanA);
		A a = (A) context.getBean("a");
		log.info("get bean a {} by name", a);
		Assert.assertTrue(beanA.hashCode() == a.hashCode());
		B beanB = context.getBean(B.class);
		log.info("get bean b {} by Type", beanB);
		B b = (B) context.getBean("b");
		log.info("get bean b {} by Name", b);
		Assert.assertTrue(beanB.hashCode() == b.hashCode());
	}

}
