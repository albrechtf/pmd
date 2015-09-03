#!/bin/bash

diff -c target/generated-sources/com/github/albrechtf/pmd/lang/vb/ast/$1.java src/gen/java-customized-ast/$1.java > src/gen/java-fragments/ast/$1.patch