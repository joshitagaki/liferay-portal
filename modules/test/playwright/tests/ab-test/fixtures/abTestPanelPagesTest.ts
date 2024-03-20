/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {test} from '@playwright/test';

import {ABTestPanelPages} from '../pages/ABTestPanelPages';

const abTestPanelPagesTest = test.extend<{
	abTestPanelPages: ABTestPanelPages;
}>({
	abTestPanelPages: async ({page}, use) => {
		await use(new ABTestPanelPages(page));
	},
});

export {abTestPanelPagesTest};