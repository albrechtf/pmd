/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package net.sourceforge.pmd.lang.vb;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import net.sourceforge.pmd.lang.vb.ast.AbstractVisualBasicParserVisitor;
import net.sourceforge.pmd.lang.vb.ast.VBNode;

public class DumpFacade extends AbstractVisualBasicParserVisitor {

	private PrintWriter writer;
	private boolean recurse;

	public void initializeWith(Writer writer, String prefix, boolean recurse, VBNode node) {
		this.writer = writer instanceof PrintWriter ? (PrintWriter) writer : new PrintWriter(writer);
		this.recurse = recurse;
		this.visit(node, prefix);
		try {
			writer.flush();
		} catch (IOException e) {
			throw new RuntimeException("Problem flushing PrintWriter.", e);
		}
	}

	@Override
	public Object visit(VBNode node, Object data) {
		dump(node, (String) data);
		if (recurse) {
			return super.visit(node, data + " ");
		} else {
			return data;
		}
	}

	private void dump(VBNode node, String prefix) {
		//
		// Dump format is generally composed of the following items...
		//

		// 1) Dump prefix
		writer.print(prefix);

		// 2) JJT Name of the Node
		writer.print(node.toString());

		//
		// If there are any additional details, then:
		// 1) A colon
		// 2) The Node.getImage() if it is non-empty
		// 3) Extras in parentheses
		//

		writer.println();
	}

}
