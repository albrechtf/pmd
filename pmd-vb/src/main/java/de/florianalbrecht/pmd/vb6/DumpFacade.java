/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package de.florianalbrecht.pmd.vb6;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import de.florianalbrecht.pmd.vb6.ast.VB6Node;
import de.florianalbrecht.pmd.vb6.ast.VB6ParserVisitorAdapter;

public class DumpFacade extends VB6ParserVisitorAdapter {

	private PrintWriter writer;
	private boolean recurse;

	public void initializeWith(Writer writer, String prefix, boolean recurse, VB6Node node) {
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
	public Object visit(VB6Node node, Object data) {
		dump(node, (String) data);
		if (recurse) {
			return super.visit(node, data + " ");
		} else {
			return data;
		}
	}

	private void dump(VB6Node node, String prefix) {
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
