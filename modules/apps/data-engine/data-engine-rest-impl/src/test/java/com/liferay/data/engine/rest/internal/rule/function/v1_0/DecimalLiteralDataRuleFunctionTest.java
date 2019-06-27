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

package com.liferay.data.engine.rest.internal.rule.function.v1_0;

import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.BaseDataRuleFunctionTest;
import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.constants.DataDefinitionRuleConstants;
import com.liferay.data.engine.spi.rule.function.DataRuleFunction;
import com.liferay.data.engine.spi.rule.function.DataRuleFunctionResult;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Marcelo Mello
 */
public class DecimalLiteralDataRuleFunctionTest
	extends BaseDataRuleFunctionTest {

	@Test
	public void testDecimalValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("salary", "1.2");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertTrue(dataRuleFunctionResult.isValid());
		Assert.assertNull(dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testIntegerValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("salary", "1");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertTrue(dataRuleFunctionResult.isValid());
		Assert.assertNull(dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testNullValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("salary", null);
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_BE_A_DECIMAL_VALUE,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testStringValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("salary", "NUMBER");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_BE_A_DECIMAL_VALUE,
			dataRuleFunctionResult.getErrorCode());
	}

	@Override
	protected DataRuleFunction getDataRuleFunction() {
		return new DecimalLiteralDataRuleFunction();
	}

	@Override
	protected String getFieldName() {
		return "salary";
	}

	@Override
	protected String getFieldType() {
		return "numeric";
	}

}