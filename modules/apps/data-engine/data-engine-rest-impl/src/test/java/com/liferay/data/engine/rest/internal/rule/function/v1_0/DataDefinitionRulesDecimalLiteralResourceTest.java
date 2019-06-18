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
public class DataDefinitionRulesDecimalLiteralResourceTest
	extends BaseDataDefinitionRulesTestCase {

	@Before
	public void setUp() {
		_dataRecord = new DataRecord();
	}

	@Test
	public void testDecimalValue() {
		_dataRecord.setDataRecordValues(
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
		_dataRecord.setDataRecordValues(
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
		_dataRecord.setDataRecordValues(
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
		_dataRecord.setDataRecordValues(
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

	protected DataRuleFunctionResult getDataRuleFunctionResult() {
		DataRuleFunction dataRuleFunction =
			new DecimalLiteralDataRuleFunction();

		DataDefinitionField dataDefinitionField =
			randomDataDefinitionFields("salary", "numeric")[0];

		return dataRuleFunction.validate(
			_dataDefinitionDecimalLiteralRules()[0].
				getDataDefinitionRuleParameters(),
			DataDefinitionFieldUtil.toSPIDataDefinitionField(
				dataDefinitionField),
			_dataRecord.getDataRecordValues(
			).get(
				dataDefinitionField.getName()
			));
	}

	private DataDefinitionRule[] _dataDefinitionDecimalLiteralRules() {
		return new DataDefinitionRule[] {
			new DataDefinitionRule() {
				{
					dataDefinitionFieldNames = new String[] {"salary"};
					dataDefinitionRuleParameters = new HashMap() {
						{
							put(
								DataDefinitionRuleConstants.EXPRESSION,
								"^[0-9]+(\\.[0-9]{1,2})?");
						}
					};
					name = "decimalLiteral";
				}
			}
		};
	}

	private DataRecord _dataRecord;

}