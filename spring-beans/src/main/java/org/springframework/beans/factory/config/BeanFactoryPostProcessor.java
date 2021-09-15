/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * Factory hook that allows for custom modification of an application context's
 * bean definitions, adapting the bean property values of the context's underlying
 * bean factory.
 *
 * <p>Useful for custom config files targeted at system administrators that
 * override bean properties configured in the application context. See
 * {@link PropertyResourceConfigurer} and its concrete implementations for
 * out-of-the-box solutions that address such configuration needs.
 *
 * <p>A {@code BeanFactoryPostProcessor} may interact with and modify bean
 * definitions, but never bean instances. Doing so may cause premature bean
 * instantiation, violating the container and causing unintended side-effects.
 * If bean instance interaction is required, consider implementing
 * {@link BeanPostProcessor} instead.
 *
 * <h3>Registration</h3>
 * <p>An {@code ApplicationContext} auto-detects {@code BeanFactoryPostProcessor}
 * beans in its bean definitions and applies them before any other beans get created.
 * A {@code BeanFactoryPostProcessor} may also be registered programmatically
 * with a {@code ConfigurableApplicationContext}.
 *
 * <h3>Ordering</h3>
 * <p>{@code BeanFactoryPostProcessor} beans that are autodetected in an
 * {@code ApplicationContext} will be ordered according to
 * {@link org.springframework.core.PriorityOrdered} and
 * {@link org.springframework.core.Ordered} semantics. In contrast,
 * {@code BeanFactoryPostProcessor} beans that are registered programmatically
 * with a {@code ConfigurableApplicationContext} will be applied in the order of
 * registration; any ordering semantics expressed through implementing the
 * {@code PriorityOrdered} or {@code Ordered} interface will be ignored for
 * programmatically registered post-processors. Furthermore, the
 * {@link org.springframework.core.annotation.Order @Order} annotation is not
 * taken into account for {@code BeanFactoryPostProcessor} beans.
 *
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @see BeanPostProcessor
 * @see PropertyResourceConfigurer
 * @since 06.07.2003
 * <p>
 * 关于spring的生命周期
 * <p>
 * 关于spring的生命周期
 */
/**
 * 关于spring的生命周期的理解：
 * 主要分为四个步骤：
 * （1）实例化 Instantiation
 * （2）属性赋值 Populate
 * （3）初始化 Initialization
 * （4）销毁 Destruction
 * 实例化 -> 属性赋值 -> 初始化 -> 销毁
 *
 */

/**
 * 『注意』对比 BeanFactoryPostProcessor和{@link BeanPostProcessor}
 * 这两个接口，都属于Spring初始化bean时对外暴露的扩展点. 这两个接口名称看起很相似，但是作用及其使用场景却不同。
 * 『BeanFactoryPostProcessor』
 * <></>实现该接口，可以在spring的bean创建之前，修改bean的定义属性。也就是说,Spring允许BeanFactoryPostProcessor在容器实例任何其bean
 * <></>之前读取配置元数据，并可以根据需要进行修改，例如可以把bean的scope从single改位prototype，也可以把property的值修改掉。
 * <></>同时可以配置多个 BeanFactoryPostProcessor，并通过设置"order"属性来控制各个 BeanFactoryPostProcessor的执行次序.
 * <></>beanFactoryPostFactory是在 spring 容器加载了bean的定文件之后，在bean实例化之前执行的没.
 * {@link ConfigurableListableBeanFactory} 可以获取到相关Bean的信息.
 * Spring中内置『BeanFactoryPostProcessor』三个重要的实现类：用来注册自定义的属性编辑器.
 * BeanFactoryPostProcessor
 *         |————> {@link PropertyPlaceholderConfigurer}
 *         |————> {@link PropertyOverrideConfigurer}
 *         |————> {@link CustomEditorConfigurer}
 * <p></p>
 * BeanPostProcessor 可以在spring容器实例化bean之后，在执行bean的初始化方法之
 *
 */
@FunctionalInterface
public interface BeanFactoryPostProcessor {

	/**
	 * Modify the application context's internal bean factory after its standard
	 * initialization. All bean definitions will have been loaded, but no beans
	 * will have been instantiated yet. This allows for overriding or adding
	 * properties even to eager-initializing beans.
	 * @param beanFactory the bean factory used by the application context
	 * @throws org.springframework.beans.BeansException in case of errors
	 */
	void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
