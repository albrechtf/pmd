package com.github.albrechtf.pmd.lang.vb.rule;

import java.util.List;

import com.github.albrechtf.pmd.lang.vb.VisualBasicLanguageModule;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.rule.AbstractRule;
import net.sourceforge.pmd.lang.rule.ImmutableLanguage;
import com.github.albrechtf.pmd.lang.vb.ast.ASTArgList;
import com.github.albrechtf.pmd.lang.vb.ast.ASTArgSpec;
import com.github.albrechtf.pmd.lang.vb.ast.ASTArguments;
import com.github.albrechtf.pmd.lang.vb.ast.ASTAssignment;
import com.github.albrechtf.pmd.lang.vb.ast.ASTBinOp;
import com.github.albrechtf.pmd.lang.vb.ast.ASTCaseExpr;
import com.github.albrechtf.pmd.lang.vb.ast.ASTCaseStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTCompilationUnit;
import com.github.albrechtf.pmd.lang.vb.ast.ASTConstDeclaration;
import com.github.albrechtf.pmd.lang.vb.ast.ASTDeclaration;
import com.github.albrechtf.pmd.lang.vb.ast.ASTDeclare;
import com.github.albrechtf.pmd.lang.vb.ast.ASTDoCondition;
import com.github.albrechtf.pmd.lang.vb.ast.ASTDoWhileStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTEnumDeclaration;
import com.github.albrechtf.pmd.lang.vb.ast.ASTEventDeclaration;
import com.github.albrechtf.pmd.lang.vb.ast.ASTExitStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTExpression;
import com.github.albrechtf.pmd.lang.vb.ast.ASTForEachStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTForStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTFormalParamList;
import com.github.albrechtf.pmd.lang.vb.ast.ASTIfStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTImplements;
import com.github.albrechtf.pmd.lang.vb.ast.ASTLabel;
import com.github.albrechtf.pmd.lang.vb.ast.ASTLiteral;
import com.github.albrechtf.pmd.lang.vb.ast.ASTMethodCall;
import com.github.albrechtf.pmd.lang.vb.ast.ASTName;
import com.github.albrechtf.pmd.lang.vb.ast.ASTOnError;
import com.github.albrechtf.pmd.lang.vb.ast.ASTOption;
import com.github.albrechtf.pmd.lang.vb.ast.ASTPrimaryExpression;
import com.github.albrechtf.pmd.lang.vb.ast.ASTProcDeclaration;
import com.github.albrechtf.pmd.lang.vb.ast.ASTReDim;
import com.github.albrechtf.pmd.lang.vb.ast.ASTSetStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTStatements;
import com.github.albrechtf.pmd.lang.vb.ast.ASTTypeDeclaration;
import com.github.albrechtf.pmd.lang.vb.ast.ASTTypeName;
import com.github.albrechtf.pmd.lang.vb.ast.ASTUnaryOp;
import com.github.albrechtf.pmd.lang.vb.ast.ASTVarDecl;
import com.github.albrechtf.pmd.lang.vb.ast.ASTVarDim;
import com.github.albrechtf.pmd.lang.vb.ast.ASTWhileWendStatement;
import com.github.albrechtf.pmd.lang.vb.ast.ASTWithStatement;
import com.github.albrechtf.pmd.lang.vb.ast.VisualBasicParserVisitor;

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
