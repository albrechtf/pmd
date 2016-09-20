#!/bin/bash

for fn in ASTBinOp ASTDoCondition ASTProcDeclaration ASTStatement ASTUnaryOp Token
do
  diff -c target/generated-sources/com/github/albrechtf/pmd/lang/vb/ast/$fn.java src/gen/java-customized-ast/$fn.java > src/gen/java-fragments/ast/$fn.diff
done
