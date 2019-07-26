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

import ClayIcon from '@clayui/icon';
import React from 'react';

export default function Search() {
	return (
		<nav className="management-bar management-bar-light navbar navbar-expand-md">
			<div className="container-fluid container-fluid-max-xl">
				<div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
					<div className="container-fluid container-fluid-max-xl">
						<div className="input-group">
							<div className="input-group-item">
								<input
									aria-label="Search for"
									className="form-control input-group-inset input-group-inset-after"
									placeholder="Search for"
									type="text"
								/>
								<div className="input-group-inset-item input-group-inset-item-after">
									<button
										className="btn btn-unstyled"
										type="button"
									>
										<ClayIcon
											spritemap={`${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`}
											symbol="search"
										/>
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</nav>
	);
}
