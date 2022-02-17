package org.framework.example.mock.ignore;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * compose mock bean.
 *
 * @author caogaoli
 * @date 2022/2/17下午4:11
 */
@Component
public class ComposeMockBean implements MockInterface {

	@Getter
	private MockA mockA;
	@Getter
	@Setter
	private MockB mockB;
	@Getter
	@Setter
	private MockC mockC;

	@Override
	public void setMockA(MockA mockA) {
		this.mockA = mockA;
	}

}
