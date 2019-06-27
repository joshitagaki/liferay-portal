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

import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.BaseDataDefinitionRulesTestCase;
import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.constants.DataDefinitionRuleConstants;
import com.liferay.data.engine.spi.rule.function.DataRuleFunction;
import com.liferay.data.engine.spi.rule.function.DataRuleFunctionResult;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Marcelo Mello
 */
public class DataDefinitionRulesEmptyResourceTest
	extends BaseDataDefinitionRulesTestCase {

	@Test
	public void testEmpty() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put(" ", " ");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_NOT_BE_EMPTY,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testNotEmpty() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("textField", "text");
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
					put("textField", null);
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_NOT_BE_EMPTY,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testWithEmptyValue() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("textField", " ");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_NOT_BE_EMPTY,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testWithMultipleValues() {
		dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("textField", "text1");
					put("textField", "text2");
					put("textField", "text3");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertTrue(dataRuleFunctionResult.isValid());
		Assert.assertNull(dataRuleFunctionResult.getErrorCode());
	}

	@Override
	protected DataRuleFunction getDataRuleFunction() {
		return new EmptyDataRuleFunction();
	}

	@Override
	protected String getFieldName() {
		return "textField";
	}

	@Override
	protected String getFieldType() {
		return "text";
	}

}