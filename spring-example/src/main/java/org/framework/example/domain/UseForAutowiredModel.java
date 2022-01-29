package org.framework.example.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * test sample.
 *
 * @author caogaoli
 * @date 2022/1/28下午4:12
 */
@Component(value = "useForAutowiredModel")
@Slf4j
public class UseForAutowiredModel {

	@Getter
	@Autowired
	private ForInjectBean injectBean;

	public UseForAutowiredModel() {
		log.info("no argument construct method.");
		log.info("injectBean {}", this.injectBean);
	}

	public UseForAutowiredModel(ForInjectBean injectBean) {
		this.injectBean = injectBean;
		log.info("one argument construct method");
		log.info("injectBean {}", this.injectBean);
	}

}
