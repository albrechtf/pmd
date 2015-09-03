package com.github.albrechtf.pmd.lang.vb;

import java.io.Reader;

import net.sourceforge.pmd.lang.TokenManager;
import com.github.albrechtf.pmd.lang.vb.ast.SimpleCharStream;
import com.github.albrechtf.pmd.lang.vb.ast.VisualBasicParserTokenManager;

public class VB6TokenManager implements TokenManager {
	private final VisualBasicParserTokenManager tokenManager;

	public VB6TokenManager(Reader source) {
		tokenManager = new VisualBasicParserTokenManager(new SimpleCharStream(source));
	}

	public Object getNextToken() {
		return tokenManager.getNextToken();
	}

	public void setFileName(String fileName) {
// tokenManager.setFileName(fileName);
	}
}
