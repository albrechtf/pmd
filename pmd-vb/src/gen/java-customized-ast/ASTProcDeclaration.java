/* Generated By:JJTree: Do not edit this line. ASTProcDeclaration.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.github.albrechtf.pmd.lang.vb.ast;

public
class ASTProcDeclaration extends com.github.albrechtf.pmd.lang.vb.ast.VBNode {
  public ASTProcDeclaration(int id) {
    super(id);
  }

  public ASTProcDeclaration(VisualBasicParser p, int id) {
    super(p, id);
  }
  
	/**
	**	The type of the procedure: Sub, Function, Property
	*/
	private int procType;
	public void setProcType(int procType)
	{
		this.procType = procType;
	}
	public int getProcType()
	{
		return procType;
	}

	// The name is the first child of type Name.
	public String getName()
	{
		for (int i = 0; i < jjtGetNumChildren(); i++)
		{
			if (jjtGetChild(i) instanceof ASTName)
				return jjtGetChild(i).getName();
		}
		
		return null;
	}

	// The formal parameter list (if any) will be second
	public ASTFormalParamList getParams()
	{
		for (int i = 0; i < jjtGetNumChildren(); i++)
		{
			if (jjtGetChild(i) instanceof ASTFormalParamList)
				return (ASTFormalParamList)jjtGetChild(i);
		}

		return null;
	}

	// The function return type (if any) is next.
	public ASTTypeName getReturnType()
	{
		for (int i = 0; i < jjtGetNumChildren(); i++)
		{
			if (jjtGetChild(i) instanceof ASTTypeName)
				return (ASTTypeName)jjtGetChild(i);
		}

		return null;
	}

	// The Statements are always the last entry
	public ASTStatements getStatements()
	{
		return (ASTStatements)jjtGetChild(jjtGetNumChildren()-1);
	}  


  /** Accept the visitor. **/
  public Object jjtAccept(VisualBasicParserVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=e9f4ea83e1acf109b9ceee09a5024f2c (do not edit this line) */
