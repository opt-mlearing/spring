package org.framework.example.mock.beandefinition;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * mock class.
 *
 * @author caogaoli
 * @date 2022/2/21上午11:21
 */
@AllArgsConstructor
@Component
@NoArgsConstructor
public class MockClass {

	@Getter
	private Object privateProperties;
	protected Object protectedProperties;
	public Object publicProperties;

	private void privateMethod() {

	}

	public Object publicMethod(Object obj) {
		return obj;
	}

}
