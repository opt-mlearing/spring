package org.framework.example.mock;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * mock configure.
 *
 * @author caogaoli
 * @date 2022/2/21下午12:10
 */
@Configuration
@ComponentScan(value = "org.framework.example.mock.beandefinition")
public class MockConfigure {

}
