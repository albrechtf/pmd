package net.sourceforge.pmd.lang.vb.ast;


public abstract class AbstractVisualBasicParserVisitor implements VisualBasicParserVisitor
{
	public Object visit(VBNode node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

  public Object visit(ASTCompilationUnit node, Object data)
  {
	  start();
	  node.childrenAccept(this, data);
	  end();
	  return data;
  }

  public Object visit(ASTProcDeclaration node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTStatements node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTStatement node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTAssignment node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTMethodCall node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTOption node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTDeclaration node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTReDim node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTEventDeclaration node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTDeclare node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTConstDeclaration node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTTypeDeclaration node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTEnumDeclaration node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTSetStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTOnError node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTIfStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTImplements node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTDoWhileStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTDoCondition node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTWhileWendStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTForEachStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTForStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTWithStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTCaseStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTArgList node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTExitStatement node, Object data){ return visit((ASTStatement)node, data); }
  public Object visit(ASTName node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTExpression node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTBinOp node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTUnaryOp node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTPrimaryExpression node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTLiteral node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTLabel node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTTypeName node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTFormalParamList node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTVarDim node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTArgSpec node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTVarDecl node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTArguments node, Object data){ node.childrenAccept(this, data); return data; }
  public Object visit(ASTCaseExpr node, Object data){ node.childrenAccept(this, data); return data; }

  public void start() { }
  public void end() { }

}
