package org.framework.example.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mock class instance.
 *
 * @author caogaoli
 * @date 2022/1/29下午5:24
 */
@Component
public class MockClass {

	@Autowired
	private MockInjectClass mockInjectClass;

}
