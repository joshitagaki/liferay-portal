/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {expect, mergeTests} from '@playwright/test';

import {apiHelpersTest} from '../../fixtures/apiHelpersTest';
import {loginTest} from '../../fixtures/loginTest';
import {sitesPageTest} from '../../fixtures/sitesPageTest';
import {checkAccessibility} from '../../utils/checkAccessibility';
import {selectSiteInitializerPagesTest} from './fixtures/selectSiteInitializerPagesTest';

const test = mergeTests(
	apiHelpersTest,
	loginTest(),
	selectSiteInitializerPagesTest,
	sitesPageTest
);

test('Check select site initializers accessibility', async ({
	page,
	selectSiteInitializerPage,
}) => {

	// Go to site initializers selection page

	await selectSiteInitializerPage.goto();

	// Check all of them have correct label

	const cards = await page.locator('.card').all();

	for (const card of cards) {
		await expect(card.getByLabel('Select Template:')).toBeVisible();
	}

	// Check accessibility

	await checkAccessibility({
		page,
		selectors: ['.portlet-content-container'],
	});
});
