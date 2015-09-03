package com.github.albrechtf.pmd.lang.vb;

import java.io.Reader;
import java.util.Collections;
import java.util.Map;

import net.sourceforge.pmd.lang.AbstractParser;
import net.sourceforge.pmd.lang.ParserOptions;
import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.ParseException;
import com.github.albrechtf.pmd.lang.vb.ast.VisualBasicParser;

public class VB6Parser extends AbstractParser {

	private VisualBasicParser parser;

	public VB6Parser(ParserOptions parserOptions) {
		super(parserOptions);
	}

	public VisualBasicParser getInternalParser() {
		return parser;
	}

	@Override
	public boolean canParse() {
		return true;
	}

	@Override
	public Node parse(String fileName, Reader source) throws ParseException {
		VisualBasicParser parser = new VisualBasicParser(source);
		try {
			return parser.CompilationUnit();
		}
		catch (com.github.albrechtf.pmd.lang.vb.ast.ParseException e) {
			throw new ParseException(e);
		}
	}


	@Override
	public Map<Integer, String> getSuppressMap() {
		// MAP Line Number -> ?
		return Collections.emptyMap();
	}

	@Override
	protected TokenManager createTokenManager(Reader source) {
		return new VB6TokenManager(source);
	}


}
