package com.github.albrechtf.pmd.lang.vb.ast;

import java.io.PrintWriter;

import net.sourceforge.pmd.lang.ast.AbstractNode;
import com.github.albrechtf.pmd.lang.vb.ast.ParseException;
import com.github.albrechtf.pmd.lang.vb.ast.Token;
import com.github.albrechtf.pmd.lang.vb.ast.VisualBasicParser;
import com.github.albrechtf.pmd.lang.vb.ast.VisualBasicParserTreeConstants;
import com.github.albrechtf.pmd.lang.vb.ast.VisualBasicParserVisitor;

public class VBNode extends AbstractNode {

	protected VisualBasicParser parser;

	public VBNode(int i) {
		super(i);
		id = i;
	}

	public VBNode(VisualBasicParser p, int i) {
    this(i);
    parser = p;
  }

  public void jjtOpen() {
		if (parser != null && beginLine == -1 && parser.token.next != null) {
			beginLine = parser.token.next.beginLine;
			beginColumn = parser.token.next.beginColumn;
		}
  }

  public void jjtClose() {
		if (parser != null && beginLine == -1 && (children == null || children.length == 0)) {
			beginColumn = parser.token.beginColumn;
		}
		if (parser != null && beginLine == -1) {
			beginLine = parser.token.beginLine;
		}
		if (parser != null) {
			endLine = parser.token.endLine;
			endColumn = parser.token.endColumn;
		}
  }

	public VBNode jjtGetParent() {
		return (VBNode)parent;
	}

	public void jjtAddChild(VBNode n, int i) {
		super.jjtAddChild(n, i);
		// copy line information if undefined yet
		if (beginLine == -1 && i == 0) {
			beginLine = n.beginLine;
			beginColumn = n.beginColumn;
		}
  }

	public VBNode jjtGetChild(int i) {
		return (VBNode)super.jjtGetChild(i);
  }

  /** Accept the visitor. **/
	public Object jjtAccept(VisualBasicParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }

  /** Accept the visitor. **/
	public Object childrenAccept(VisualBasicParserVisitor visitor, Object data) {
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
				((VBNode)children[i]).jjtAccept(visitor, data);
      }
    }
    return data;
  }

  public String toString()
  {
		return VisualBasicParserTreeConstants.jjtNodeName[id];
  }

  public String toString(String prefix) { return prefix + toString(); }

  /* Override this method if you want to customize how the node dumps
     out its children. */

  public void dump(String prefix) {
    System.out.println(toString(prefix));
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
				VBNode n = (VBNode)children[i];
	if (n != null) {
	  n.dump(prefix + "    ");
	}
      }
    }
  }

	static public String getTokenText(Token t)
	{
		return getTokenText(t, false);
	}

	static public String getTokenText(Token t, boolean includeSpecialText)
	{
		Token tt = t.specialToken;

		if (!includeSpecialText || tt == null)
			return t.image;

		StringBuffer buff = new StringBuffer();

		// Find the start of the special token chain.
		while (tt.specialToken != null)
			tt = tt.specialToken;
		// Now follow the chain forwards, collecting text
		while (tt != null)
		{
			buff.append(tt.image);
			tt = tt.next;
		}

		buff.append(t.image);

		return buff.toString();
	}

	private static final String spaces = "                                                           ";

	public String allText() throws ParseException
	{
		return allText(false);
	}

	public String allText(boolean includeSpecialText) throws ParseException
	{
		Token t = begin;
		StringBuffer buff = new StringBuffer();

		while (t != null)
		{
			buff.append(getTokenText(t, includeSpecialText));

			if (t == end)
				break;

			t = t.next;
		}

		return buff.toString();
	}

  protected Token begin, end;

  public void setFirstToken(Token t) { begin = t;}
  public void setLastToken(Token t) { end = t;}

  public Token getFirstToken() { return begin;}
  public Token getLastToken() { return end;}

  public ParseException parseException = null;

  private String name;
  public String getName()
  {
	  return name;
  }
  public void setName(String name)
  {
	  this.name = name;
  }

  protected void print(Token t, PrintWriter ostr)
  {
	  Token tt = t.specialToken;

	  if (tt != null)
	  {
		  while (tt.specialToken != null) tt = tt.specialToken;

		  while (tt != null) {
			  ostr.print(tt.image);
			  tt = tt.next;
		  }
	  }

	  ostr.print(t.image);
  }
}

