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

import com.liferay.data.engine.rest.dto.v1_0.DataDefinitionField;
import com.liferay.data.engine.rest.dto.v1_0.DataDefinitionRule;
import com.liferay.data.engine.rest.dto.v1_0.DataRecord;
import com.liferay.data.engine.rest.internal.dto.v1_0.util.DataDefinitionFieldUtil;
import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.BaseDataDefinitionRulesTestCase;
import com.liferay.data.engine.rest.internal.rule.function.v1_0.util.constants.DataDefinitionRuleConstants;
import com.liferay.data.engine.spi.rule.function.DataRuleFunction;
import com.liferay.data.engine.spi.rule.function.DataRuleFunctionResult;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Marcelo Mello
 */
public class DataDefinitionRulesEmptyResourceTest
	extends BaseDataDefinitionRulesTestCase {

	@Before
	public void setUp() {
		_dataRecord = new DataRecord();
	}

	@Test
	public void testEmpty() {
		_dataRecord.setDataRecordValues(
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
		_dataRecord.setDataRecordValues(
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
		_dataRecord.setDataRecordValues(
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
		_dataRecord.setDataRecordValues(
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
		_dataRecord.setDataRecordValues(
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

	protected DataRuleFunctionResult getDataRuleFunctionResult() {
		DataDefinitionRule dataDefinitionRule = _dataDefinitionEmptyRules()[0];

		DataRuleFunction dataRuleFunction = new EmptyDataRuleFunction();

		DataDefinitionField dataDefinitionField = randomDataDefinitionFields(
			"text", "textField");

		return dataRuleFunction.validate(
			dataDefinitionRule.getDataDefinitionRuleParameters(),
			DataDefinitionFieldUtil.toSPIDataDefinitionField(
				dataDefinitionField),
			_dataRecord.getDataRecordValues(
			).get(
				dataDefinitionField.getName()
			));
	}

	private DataDefinitionRule[] _dataDefinitionEmptyRules() {
		return new DataDefinitionRule[] {
			new DataDefinitionRule() {
				{
					dataDefinitionFieldNames = new String[] {"textField"};
					name = "empty";
				}
			}
		};
	}

	private DataRecord _dataRecord;

}