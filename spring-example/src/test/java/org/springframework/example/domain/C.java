package org.springframework.example.domain;

import lombok.extern.slf4j.Slf4j;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/1/28下午2:12
 */
@Slf4j
public class C {

	/* inner class E for D */
	/* keep note: when get inner class's bean, should make sure, has register the outer Class d at first */
	public class D {
		public D(C c) {
			log.info("init D instance, construct arguments {}|{}", c.hashCode(), c.getClass().getSimpleName());
		}
	}

	public static class E {
		public E(C c) {
			log.info("init E instance.");
		}
	}

	public static class F {

	}

}
