/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {loginTest} from '../../fixtures/loginTest';
import {abTestPanelPagesTest} from './fixtures/abTestPanelPagesTest';

export const test = mergeTests(
	abTestPanelPagesTest,
	loginTest(),
);

test('open ab test panel', async ({
	abTestPanelPages,
	page
}) => {
	await abTestPanelPages.openPanel();
});