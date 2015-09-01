package net.sourceforge.pmd.lang.vb.rule;

import java.util.List;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRule;
import net.sourceforge.pmd.lang.rule.ImmutableLanguage;
import net.sourceforge.pmd.lang.vb.VisualBasicLanguageModule;
import net.sourceforge.pmd.lang.vb.ast.ASTArgList;
import net.sourceforge.pmd.lang.vb.ast.ASTArgSpec;
import net.sourceforge.pmd.lang.vb.ast.ASTArguments;
import net.sourceforge.pmd.lang.vb.ast.ASTAssignment;
import net.sourceforge.pmd.lang.vb.ast.ASTBinOp;
import net.sourceforge.pmd.lang.vb.ast.ASTCaseExpr;
import net.sourceforge.pmd.lang.vb.ast.ASTCaseStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.vb.ast.ASTConstDeclaration;
import net.sourceforge.pmd.lang.vb.ast.ASTDeclaration;
import net.sourceforge.pmd.lang.vb.ast.ASTDeclare;
import net.sourceforge.pmd.lang.vb.ast.ASTDoCondition;
import net.sourceforge.pmd.lang.vb.ast.ASTDoWhileStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTEnumDeclaration;
import net.sourceforge.pmd.lang.vb.ast.ASTEventDeclaration;
import net.sourceforge.pmd.lang.vb.ast.ASTExitStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTExpression;
import net.sourceforge.pmd.lang.vb.ast.ASTForEachStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTForStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTFormalParamList;
import net.sourceforge.pmd.lang.vb.ast.ASTIfStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTImplements;
import net.sourceforge.pmd.lang.vb.ast.ASTLabel;
import net.sourceforge.pmd.lang.vb.ast.ASTLiteral;
import net.sourceforge.pmd.lang.vb.ast.ASTMethodCall;
import net.sourceforge.pmd.lang.vb.ast.ASTName;
import net.sourceforge.pmd.lang.vb.ast.ASTOnError;
import net.sourceforge.pmd.lang.vb.ast.ASTOption;
import net.sourceforge.pmd.lang.vb.ast.ASTPrimaryExpression;
import net.sourceforge.pmd.lang.vb.ast.ASTProcDeclaration;
import net.sourceforge.pmd.lang.vb.ast.ASTReDim;
import net.sourceforge.pmd.lang.vb.ast.ASTSetStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTStatements;
import net.sourceforge.pmd.lang.vb.ast.ASTTypeDeclaration;
import net.sourceforge.pmd.lang.vb.ast.ASTTypeName;
import net.sourceforge.pmd.lang.vb.ast.ASTUnaryOp;
import net.sourceforge.pmd.lang.vb.ast.ASTVarDecl;
import net.sourceforge.pmd.lang.vb.ast.ASTVarDim;
import net.sourceforge.pmd.lang.vb.ast.ASTWhileWendStatement;
import net.sourceforge.pmd.lang.vb.ast.ASTWithStatement;
import net.sourceforge.pmd.lang.vb.ast.VisualBasicParserVisitor;

public abstract class AbstractVisualBasicRule extends AbstractRule implements VisualBasicParserVisitor, ImmutableLanguage {

	public AbstractVisualBasicRule() {
		super.setLanguage(LanguageRegistry.getLanguage(VisualBasicLanguageModule.NAME));
	}

	public void apply(List<? extends Node> nodes, RuleContext ctx) {
		visitAll(nodes, ctx);
	}

	protected void visitAll(List<? extends Node> nodes, RuleContext ctx) {
		for (Object element : nodes) {
			ASTCompilationUnit node = (ASTCompilationUnit)element;
			visit(node, ctx);
		}
	}

	@Override
	public Object visit(ASTCompilationUnit node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTProcDeclaration node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTStatements node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTStatement node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTAssignment node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTMethodCall node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTOption node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTDeclaration node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTReDim node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTEventDeclaration node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTDeclare node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTConstDeclaration node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTTypeDeclaration node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTEnumDeclaration node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTSetStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTOnError node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTIfStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTImplements node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTDoWhileStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTDoCondition node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTWhileWendStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTForEachStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTForStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTWithStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTCaseStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTArgList node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTExitStatement node, Object data) {
		return visit((ASTStatement)node, data);
	}

	public Object visit(ASTName node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTExpression node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTBinOp node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTUnaryOp node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTPrimaryExpression node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTLiteral node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTLabel node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTTypeName node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTFormalParamList node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTVarDim node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTArgSpec node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTVarDecl node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTArguments node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}

	public Object visit(ASTCaseExpr node, Object data) {
		node.childrenAccept(this, data);
		return data;
	}
}
