make: scanner\DecafLexer.java scanner\DecafLexer.class scanner\Scanner.class	\
	parser\DecafParser.java parser\DecafParser.class parser\CC4Parser.class 	\
	semantic\Tipos.class semantic\Table.class semantic\SymbolTable.class semantic\Semantic.class \
	ast\Ast.class \
	irt\Irt.class codegen\Codegen.class		\
	opt\Algebraic.class opt\ConstantFolding.class 	\
	lib\Debug.class lib\ErrorHandler.class lib\OutputFile.class lib\Configuracion.class	\
	Compiler.class

Compiler.class: Compiler.java
	javac Compiler.java

# Scanner
scanner\Scanner.class: scanner\Scanner.java
	javac scanner\Scanner.java

scanner\DecafLexer.java: scanner\DecafLexer.g
	java org.antlr.v4.Tool scanner\DecafLexer.g

scanner\DecafLexer.class: scanner\DecafLexer.java
	javac scanner\DecafLexer.java

# Parser
parser\CC4Parser.class: parser\CC4Parser.java
	javac parser\CC4Parser.java

parser\DecafParser.java: parser\DecafParser.g
	java org.antlr.v4.Tool -visitor -lib scanner parser\DecafParser.g

parser\DecafParser.class: parser\DecafErrorListener.java parser\ParseListener.java parser\DecafParser.java 
	javac parser\ParseListener.java
	javac parser\DecafErrorListener.java
	javac parser\DecafParser.java

# ast	
ast\Ast.class: 	ast\nodes\Node.java \
				ast\nodes\Asign.java \
				ast\nodes\BinOp.java \
				ast\nodes\Cond.java \
				ast\nodes\Cycle.java \
				ast\nodes\Declaracion.java \
				ast\nodes\Exp.java \
				ast\nodes\Literal.java \
				ast\nodes\MethodCall.java \
				ast\nodes\Root.java \
				ast\nodes\Statement.java \
				ast\nodes\VarLiteral.java \
				ast\AstVisitor.java ast\Ast.java ast\GraphicTree.java 
	javac ast\nodes/*.java
	javac ast/*.java

# Semantic
semantic\Semantic.class: semantic\Semantic.java
	javac semantic\Semantic.java

semantic\Table.class: semantic\Table.java
	javac semantic\Table.java

semantic\Tipos.class: semantic\Tipos.java
	javac semantic\Tipos.java

semantic\SymbolTable.class: semantic\SymbolTable.java
	javac semantic\SymbolTable.java

# Irt
irt\Irt.class: 	irt\instructions\Instruction.java 	\
				irt\instructions\ALU.java 			\
				irt\instructions\Immediate.java		\
				irt\instructions\Jump.java 			\
				irt\instructions\Label.java			\
				irt\instructions\LoadStore.java		\
				irt\Irt.java irt\IrtList.java irt\Register.java irt\RegisterManager.java
	javac irt\instructions/*.java
	javac irt/*.java

# codegen
codegen\Codegen.class: codegen\Codegen.java
	javac codegen\Codegen.java


# optimizacion
opt\Algebraic.class: opt\Algebraic.java
	javac opt\Algebraic.java

opt\ConstantFolding.class: opt\ConstantFolding.java
	javac opt\ConstantFolding.java

# Lib
lib\Debug.class: lib\Debug.java
	javac lib\Debug.java

lib\ErrorHandler.class: lib\ErrorHandler.java
	javac lib\ErrorHandler.java

lib\OutputFile.class: lib\OutputFile.java
	javac lib\OutputFile.java

lib\Configuracion.class: lib\Configuracion.java 
	javac lib\Configuracion.java

clean:
	del Compiler.class
	del scanner\*.class
	del scanner\DecafLexer.java
	del scanner\*.tokens
	del parser\*.class
	del parser\DecafParser.java
	del parser\DecafParser.tokens
	del ast\*.class
	del ast\nodes\*.class
	del semantic\*.class
	del irt\*.class
	del irt\instructions\*.class
	del codegen\*.class
	del opt\*.class
	del lib\*.class