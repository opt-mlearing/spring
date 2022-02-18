package org.framework.example.mock.merge;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * merge bean definition used. concrete class.
 *
 * @author caogaoli
 * @date 2022/2/18下午3:08
 */
@Component
@Slf4j
public class MockConcreteClass extends MockAbstractClass {

	@Override
	public void methodNoParam() {
		log.debug("execute MockConcreteClass#methodNoParam");
	}

	@Override
	public Object methodWithParam(Object input) {
		log.debug("execute MockConcreteClass#methodWithParam");
		return input;
	}

}
