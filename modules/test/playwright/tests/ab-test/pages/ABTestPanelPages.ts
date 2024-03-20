/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {Locator, Page, expect} from '@playwright/test';

export class ABTestPanelPages {
    readonly page: Page;
    readonly testPanelButton: Locator;
    readonly testPanelHeader: Locator;

    constructor(page: Page) {
        this.page = page;
        this.testPanelButton = page.getByLabel('A/B Test');
        this.testPanelHeader = page.getByText('Tests Panel');
    }

    async openPanel() {
        if (await this.testPanelHeader.isHidden()){
            await this.testPanelButton.click();
        }

        await expect(this.testPanelHeader).toBeVisible();
    }
}