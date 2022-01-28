package org.springframework.example.context;

import java.util.Objects;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.example.config.ApplicationConfig;
import org.springframework.example.domain.A;
import org.springframework.example.domain.B;
import org.springframework.example.domain.C;

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

	@Test
	public void testGetInnerClassBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(C.class);
		context.register(C.D.class);
		context.refresh();
		C.D bean = context.getBean(C.D.class);
		log.info("bean {}", bean);
	}

	@Test
	public void testGetInnerStaticClassBean() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(C.class);
		context.register(C.E.class);
		context.refresh();
		C.E bean = context.getBean(C.E.class);
		log.info("bean {}", bean);
	}

	@Test
	public void testGetInnerStaticClassBeanWithOutConstructArgument() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(C.F.class);
		context.refresh();
		C.F bean = context.getBean(C.F.class);
		log.info("bean {}", bean);
	}

}
