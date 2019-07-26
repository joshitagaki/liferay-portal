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
import React, {Fragment, useState} from 'react';
import lang from '../../utils/lang.es';

export default function Search({keywords, onSearch, totalCount}) {
	const [value, setValue] = useState(keywords);

	const search = () => {
		const trimmed = value.trim();

		if (trimmed !== '') {
			onSearch(trimmed);
		}
	};

	const clear = () => {
		onSearch('');
	};

	return (
		<Fragment>
			<nav className="management-bar management-bar-light navbar navbar-expand-md">
				<div className="container-fluid container-fluid-max-xl">
					<div className="navbar-form navbar-form-autofit navbar-overlay navbar-overlay-sm-down">
						<div className="container-fluid container-fluid-max-xl">
							<div className="input-group">
								<div className="input-group-item">
									<input
										aria-label="Search for"
										className="form-control input-group-inset input-group-inset-after"
										onChange={event =>
											setValue(event.target.value)
										}
										onKeyDown={event =>
											event.key === 'Enter' && search()
										}
										placeholder="Search for"
										type="text"
										value={value}
									/>

									<div className="input-group-inset-item input-group-inset-item-after">
										<button
											className="btn btn-unstyled"
											onClick={search}
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
			{keywords !== '' && (
				<nav className="tbar tbar-inline-xs-down subnav-tbar subnav-tbar-primary">
					<div className="container-fluid container-fluid-max-xl">
						<ul className="tbar-nav tbar-nav-wrap">
							<li className="tbar-item tbar-item-expand">
								<div className="tbar-section">
									<span className="component-text text-truncate-inline">
										<span className="text-truncate">
											{lang.sub(
												Liferay.Language.get(
													'x-results-for-x'
												),
												[totalCount, keywords]
											)}
										</span>
									</span>
								</div>
							</li>
							<li className="tbar-item">
								<div className="tbar-section">
									<a
										className=" component-link tbar-link"
										href="javascript:;"
										onClick={clear}
									>
										Clear
									</a>
								</div>
							</li>
						</ul>
					</div>
				</nav>
			)}
		</Fragment>
	);
}
