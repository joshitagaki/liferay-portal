/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.data.engine.rest.internal.rule.function.v1_0.util;

import com.liferay.data.engine.rest.dto.v1_0.DataDefinitionField;
import com.liferay.portal.kernel.test.util.RandomTestUtil;

import java.util.HashMap;

/**
 * @author Marcelo Mello
 */
public abstract class BaseDataDefinitionRulesTestCase {

	protected DataDefinitionField[] randomDataDefinitionFields(
		String fieldName, String type) {

		return new DataDefinitionField[] {
			new DataDefinitionField() {
				{
					id = RandomTestUtil.randomLong();
					indexable = false;
					label = new HashMap();
					localizable = false;
					name = fieldName;
					fieldType = type;
					repeatable = false;
					tip = new HashMap();
				}
			}
		};
	}

}