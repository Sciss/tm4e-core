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
package org.eclipse.tm4e.ui.themes.css;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.tm4e.core.theme.IStyle;
import org.eclipse.tm4e.core.theme.RGB;
import org.eclipse.tm4e.core.theme.css.CSSParser;
import org.eclipse.tm4e.ui.themes.AbstractTokenProvider;
import org.eclipse.tm4e.ui.themes.ColorManager;

public class CSSTokenProvider extends AbstractTokenProvider {

	private Map<IStyle, IToken> tokenMaps;
	private CSSParser parser;

	public CSSTokenProvider(InputStream in) {
		tokenMaps = new HashMap<>();
		try {
			parser = new CSSParser(in);
			for (IStyle style : parser.getStyles()) {
				RGB color = style.getColor();
				if (color != null) {
					int s = SWT.NORMAL;
					if (style.isBold()) {
						s = s | SWT.BOLD;
					}
					if (style.isItalic()) {
						s = s | SWT.ITALIC;
					}
					if (style.isUnderline()) {
						s = s | TextAttribute.UNDERLINE;
					}
					if (style.isStrikeThrough()) {
						s = s | TextAttribute.STRIKETHROUGH;
					}
					tokenMaps.put(style,
							new Token(new TextAttribute(ColorManager.getInstance().getColor(color), null, s)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public IToken getToken(String type) {
		if (type == null) {
			return null;
		}
		IStyle style = parser.getBestStyle(type.split("[.]"));
		if (style != null) {
			IToken t = tokenMaps.get(style);
			if (t != null) {
				return t;
			}
		}
		return null;
	}

	@Override
	public Color getEditorForeground() {
		IStyle style = parser.getBestStyle("editor");
		if (style != null && style.getColor() != null) {
			return ColorManager.getInstance().getColor(style.getColor());
		}
		return null;
	}

	@Override
	public Color getEditorBackground() {
		IStyle style = parser.getBestStyle("editor");
		if (style != null && style.getBackgroundColor() != null) {
			return ColorManager.getInstance().getColor(style.getBackgroundColor());
		}
		return null;
	}

	@Override
	public Color getEditorSelectionForeground() {
		IStyle style = parser.getBestStyle("editor", "selection");
		if (style != null && style.getColor() != null) {
			return ColorManager.getInstance().getColor(style.getColor());
		}
		return null;
	}

	@Override
	public Color getEditorSelectionBackground() {
		IStyle style = parser.getBestStyle("editor", "selection");
		if (style != null && style.getBackgroundColor() != null) {
			return ColorManager.getInstance().getColor(style.getBackgroundColor());
		}
		return null;
	}

}
