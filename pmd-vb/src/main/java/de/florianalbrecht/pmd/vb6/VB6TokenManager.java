package de.florianalbrecht.pmd.vb6;

import java.io.IOException;
import java.io.Reader;

import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.ParseException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.TokenStream;

import de.florianalbrecht.pmd.vb6.antlr.VisualBasic6Lexer;

public class VB6TokenManager implements TokenManager {

	private TokenStream tokenStream;

	public VB6TokenManager(Reader source) {
		try {
			Lexer lexer = new VisualBasic6Lexer(new ANTLRInputStream(source));
			tokenStream = new CommonTokenStream(lexer);
		}
		catch (IOException e) {
			throw new ParseException(e);
		}
	}

	@Override
	public Object getNextToken() {
		return tokenStream.LT(1);
	}

	@Override
	public void setFileName(String fileName) {
		// NOOP for Visual Basic
	}

}
