/**
 *  Copyright (c) 2015-2017 Angelo ZERR.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *  Angelo Zerr <angelo.zerr@gmail.com> - initial API and implementation
 */
package org.eclipse.tm4e.ui.themes;

import org.eclipse.swt.custom.StyledText;

/**
 * TextMate theme.
 *
 */
public interface ITheme extends ITokenProvider {

	/**
	 * Returns the id of the theme.
	 * 
	 * @return the id of the theme.
	 */
	String getId();

	/**
	 * Returns the name of the theme.
	 * 
	 * @return the name of the theme.
	 */
	String getName();

	/**
	 * Returns the path of the theme.
	 * 
	 * @return the path of the theme.
	 */
	String getPath();

	/**
	 * Returns the plugin id.
	 * 
	 * @return the plugin id
	 */
	String getPluginId();

	/**
	 * Returns the theme content as CSS style sheet.
	 * 
	 * @return the theme content as CSS style sheet.
	 */
	String toCSSStyleSheet();

	boolean isDark();

	boolean isDefault();

	/**
	 * Initialize foreground, background color of the given {@link StyledText} with
	 * theme.
	 * 
	 * @param textWidget
	 */
	void initializeViewerColors(StyledText styledText);

}
