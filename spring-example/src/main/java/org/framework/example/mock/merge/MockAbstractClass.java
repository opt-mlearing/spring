package org.framework.example.mock.merge;

import org.springframework.stereotype.Component;

/**
 * merge bean definition used. mock abstract class.
 *
 * @author caogaoli
 * @date 2022/2/18下午3:11
 */
@Component
public abstract class MockAbstractClass implements MockInterface {

	private Object privateParam;

	protected Object protectedParam;

	public Object publicParam;

}
