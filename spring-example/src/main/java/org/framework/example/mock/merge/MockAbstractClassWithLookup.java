package org.framework.example.mock.merge;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * merge bean definition used.
 *
 * @author caogaoli
 * @date 2022/2/18下午3:57
 */
@Component
public abstract class MockAbstractClassWithLookup implements MockInterface {

	@Lookup
	public MockSample findMockSample(Object input) {
		if (input instanceof MockSample) {
			return (MockSample) input;
		}
		return null;
	}

}
