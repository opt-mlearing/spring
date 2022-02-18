package org.framework.example.mock.merge;

import org.springframework.stereotype.Component;

/**
 * merge bean definition used. mock interface.
 *
 * @author caogaoli
 * @date 2022/2/18下午3:09
 */
@Component
public interface MockInterface {

	void methodNoParam();

	Object methodWithParam(Object input);

}
