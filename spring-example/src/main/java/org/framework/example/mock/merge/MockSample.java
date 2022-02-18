package org.framework.example.mock.merge;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * mock sample.
 *
 * @author caogaoli
 * @date 2022/2/18下午3:58
 */
@Component
@Scope(value = SCOPE_PROTOTYPE)
public class MockSample {

}
