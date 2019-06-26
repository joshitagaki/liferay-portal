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

import com.liferay.data.engine.rest.dto.v1_0.DataDefinition;
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
public class DataDefinitionRulesMatchExpressionResourceTest
	extends BaseDataDefinitionRulesTestCase {

	@Before
	public void setUp() {
		_dataDefinition = new DataDefinition();
		_dataRecord = new DataRecord();
	}

	@Test
	public void testInvalidRegex() {
		_dataDefinition.setDataDefinitionRules(
			new DataDefinitionRule[] {
				new DataDefinitionRule() {
					{
						dataDefinitionFieldNames = new String[] {"field"};
						dataDefinitionRuleParameters = new HashMap() {
							{
								put(
									DataDefinitionRuleConstants.EXPRESSION,
									"\\\\\\\\S+[@\\\\S+\\\\.\\\\S+");
							}
						};
						name = DataDefinitionRuleConstants.MATCH_EXPRESSION;
					}
				}
			});

		_dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("field", "test@liferay");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_MATCH_EXPRESSION,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testNotMatch() {
		_dataDefinition.setDataDefinitionRules(
			new DataDefinitionRule[] {
				new DataDefinitionRule() {
					{
						dataDefinitionFieldNames = new String[] {"field"};
						dataDefinitionRuleParameters = new HashMap() {
							{
								put(
									DataDefinitionRuleConstants.EXPRESSION,
									"\\S+@\\S+\\.\\S+");
							}
						};
						name = DataDefinitionRuleConstants.MATCH_EXPRESSION;
					}
				}
			});

		_dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("field", "test@liferay");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertFalse(dataRuleFunctionResult.isValid());
		Assert.assertEquals(
			DataDefinitionRuleConstants.VALUE_MUST_MATCH_EXPRESSION,
			dataRuleFunctionResult.getErrorCode());
	}

	@Test
	public void testValidMatch() {
		_dataDefinition.setDataDefinitionRules(
			new DataDefinitionRule[] {
				new DataDefinitionRule() {
					{
						dataDefinitionFieldNames = new String[] {"field"};
						dataDefinitionRuleParameters = new HashMap() {
							{
								put(
									DataDefinitionRuleConstants.EXPRESSION,
									"\\S+@\\S+\\.\\S+");
							}
						};
						name = DataDefinitionRuleConstants.MATCH_EXPRESSION;
					}
				}
			});

		_dataRecord.setDataRecordValues(
			new HashMap() {
				{
					put("field", "test@liferay.com");
				}
			});

		DataRuleFunctionResult dataRuleFunctionResult =
			getDataRuleFunctionResult();

		Assert.assertTrue(dataRuleFunctionResult.isValid());
		Assert.assertNull(dataRuleFunctionResult.getErrorCode());
	}

	protected DataRuleFunctionResult getDataRuleFunctionResult() {
		DataDefinitionRule dataDefinitionRule =
			_dataDefinition.getDataDefinitionRules()[0];

		DataRuleFunction dataRuleFunction =
			new MatchExpressionDataRuleFunction();

		DataDefinitionField dataDefinitionField = randomDataDefinitionFields(
			"text", "field");

		return dataRuleFunction.validate(
			dataDefinitionRule.getDataDefinitionRuleParameters(),
			DataDefinitionFieldUtil.toSPIDataDefinitionField(
				dataDefinitionField),
			_dataRecord.getDataRecordValues(
			).get(
				dataDefinitionField.getName()
			));
	}

	private DataDefinition _dataDefinition;
	private DataRecord _dataRecord;

}