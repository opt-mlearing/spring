/*
 * Copyright 2002-2020 the original author or authors.
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

package org.springframework.core;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Support class for {@link AttributeAccessor AttributeAccessors}, providing
 * a base implementation of all methods. To be extended by subclasses.
 *
 * <p>{@link Serializable} if subclasses and all attribute values are {@link Serializable}.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @since 2.0
 */
@SuppressWarnings("serial")
public abstract class AttributeAccessorSupport implements AttributeAccessor, Serializable {

	/** Map with String keys and Object values. */
	/** 注意才用的数据结构，LinkedHashMap 保持者插入的顺序，难道是希望装配的顺序和解析属性的顺序一致，这个顺序有什么用途？	*/
	/** 注意，这里全部的属性信息以LickedHashMap的形式组织并存储 */
	private final Map<String, Object> attributes = new LinkedHashMap<>();

	/**
	 * 往map当中put一个key/value，如果传一个为null的value等同于remove操作.
	 * @param name the unique attribute key
	 * @param value the attribute value to be attached
	 */
	@Override
	public void setAttribute(String name, @Nullable Object value) {
		Assert.notNull(name, "Name must not be null");
		if (value != null) {
			this.attributes.put(name, value);
		}
		else {
			// if {@param value} == remove operation.
			removeAttribute(name);
		}
	}

	@Override
	@Nullable
	public Object getAttribute(String name) {
		Assert.notNull(name, "Name must not be null");
		return this.attributes.get(name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T computeAttribute(String name, Function<String, T> computeFunction) {
		Assert.notNull(name, "Name must not be null");
		Assert.notNull(computeFunction, "Compute function must not be null");
		Object value = this.attributes.computeIfAbsent(name, computeFunction);
		Assert.state(value != null,
				() -> String.format("Compute function must not return null for attribute named '%s'", name));
		return (T) value;
	}

	@Override
	@Nullable
	public Object removeAttribute(String name) {
		Assert.notNull(name, "Name must not be null");
		return this.attributes.remove(name);
	}

	@Override
	public boolean hasAttribute(String name) {
		Assert.notNull(name, "Name must not be null");
		return this.attributes.containsKey(name);
	}

	@Override
	public String[] attributeNames() {
		return StringUtils.toStringArray(this.attributes.keySet());
	}


	/**
	 * Copy the attributes from the supplied AttributeAccessor to this accessor.
	 * @param source the AttributeAccessor to copy from
	 */
	protected void copyAttributesFrom(AttributeAccessor source) {
		Assert.notNull(source, "Source must not be null");
		String[] attributeNames = source.attributeNames();
		for (String attributeName : attributeNames) {
			setAttribute(attributeName, source.getAttribute(attributeName));
		}
	}


	@Override
	public boolean equals(@Nullable Object other) {
		return (this == other || (other instanceof AttributeAccessorSupport &&
				this.attributes.equals(((AttributeAccessorSupport) other).attributes)));
	}

	@Override
	public int hashCode() {
		return this.attributes.hashCode();
	}

}
