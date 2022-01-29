package org.springframework.example.domain;

import java.util.Objects;

import org.framework.example.domain.ForInjectBean;
import org.framework.example.domain.UseForAutowiredModel;
import org.framework.example.postprocessor.ResetUserForAutowiredModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/1/28下午4:16
 */
@Slf4j
public class BeanInjectModelTest {

	private static final String TEST_INJECT_PATH = "org.framework.example.domain";

	@Test
	public void testInjectModel0() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(TEST_INJECT_PATH);
		AbstractBeanDefinition useForAutowiredModel =
				(AbstractBeanDefinition) context.getBeanDefinition("useForAutowiredModel");
		int autowireMode = useForAutowiredModel.getAutowireMode();
		log.info("autowire mode {}", autowireMode);
		Assertions.assertEquals(AbstractBeanDefinition.AUTOWIRE_NO, autowireMode);
	}

	@Test
	public void testInjectModel1() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan(TEST_INJECT_PATH);
		/* inject by hard code, because the origin class has Ignore by auto scan process. */
		context.register(ForInjectBean.class);
		context.register(ResetUserForAutowiredModel.class);
		context.refresh();
		AbstractBeanDefinition forInjectBean =
				(AbstractBeanDefinition) context.getBeanDefinition("useForAutowiredModel");
		log.info("bean {}", forInjectBean);
		final ConstructorArgumentValues constructorArgumentValues =
				forInjectBean.getConstructorArgumentValues();
		
		final UseForAutowiredModel useForAutowiredModel =
				(UseForAutowiredModel) context.getBean("useForAutowiredModel");
		final ForInjectBean injectBean = useForAutowiredModel.getInjectBean();
		Assertions.assertTrue(Objects.nonNull(injectBean));
		log.info("{}", injectBean);
		Assertions.assertEquals(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR, forInjectBean.getAutowireMode());
	}


}
