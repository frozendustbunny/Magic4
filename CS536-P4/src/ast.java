import java.io.*;
import java.util.*;

// **********************************************************************
// The ASTnode class defines the nodes of the abstract-syntax tree that
// represents a Mini program.
//
// Internal nodes of the tree contain pointers to children, organized
// either in a list (for nodes that may have a variable number of 
// children) or as a fixed set of fields.
//
// The nodes for literals and ids contain line and character number
// information; for string literals and identifiers, they also contain a
// string; for integer literals, they also contain an integer value.
//
// Here are all the different kinds of AST nodes and what kinds of children
// they have.  All of these kinds of AST nodes are subclasses of "ASTnode".
// Indentation indicates further subclassing:
//
//     Subclass            Kids
//     --------            ----
//     ProgramNode         DeclListNode
//     DeclListNode        linked list of DeclNode
//     DeclNode:
//       VarDeclNode       TypeNode, IdNode, int
//       FnDeclNode        TypeNode, IdNode, FormalsListNode, FnBodyNode
//       FormalDeclNode    TypeNode, IdNode
//       StructDeclNode    IdNode, DeclListNode
//
//     FormalsListNode     linked list of FormalDeclNode
//     FnBodyNode          DeclListNode, StmtListNode
//     StmtListNode        linked list of StmtNode
//     ExpListNode         linked list of ExpNode
//
//     TypeNode:
//       IntNode           -- none --
//       BoolNode          -- none --
//       VoidNode          -- none --
//       StructNode        IdNode
//
//     StmtNode:
//       AssignStmtNode      AssignNode
//       PostIncStmtNode     ExpNode
//       PostDecStmtNode     ExpNode
//       ReadStmtNode        ExpNode
//       WriteStmtNode       ExpNode
//       IfStmtNode          ExpNode, DeclListNode, StmtListNode
//       IfElseStmtNode      ExpNode, DeclListNode, StmtListNode,
//                                    DeclListNode, StmtListNode
//       WhileStmtNode       ExpNode, DeclListNode, StmtListNode
//       CallStmtNode        CallExpNode
//       ReturnStmtNode      ExpNode
//
//     ExpNode:
//       IntLitNode          -- none --
//       StrLitNode          -- none --
//       TrueNode            -- none --
//       FalseNode           -- none --
//       IdNode              -- none --
//       DotAccessNode       ExpNode, IdNode
//       AssignNode          ExpNode, ExpNode
//       CallExpNode         IdNode, ExpListNode
//       UnaryExpNode        ExpNode
//         UnaryMinusNode
//         NotNode
//       BinaryExpNode       ExpNode ExpNode
//         PlusNode     
//         MinusNode
//         TimesNode
//         DivideNode
//         AndNode
//         OrNode
//         EqualsNode
//         NotEqualsNode
//         LessNode
//         GreaterNode
//         LessEqNode
//         GreaterEqNode
//
// Here are the different kinds of AST nodes again, organized according to
// whether they are leaves, internal nodes with linked lists of kids, or
// internal nodes with a fixed number of kids:
//
// (1) Leaf nodes:
//        IntNode,   BoolNode,  VoidNode,  IntLitNode,  StrLitNode,
//        TrueNode,  FalseNode, IdNode
//
// (2) Internal nodes with (possibly empty) linked lists of children:
//        DeclListNode, FormalsListNode, StmtListNode, ExpListNode
//
// (3) Internal nodes with fixed numbers of kids:
//        ProgramNode,     VarDeclNode,     FnDeclNode,     FormalDeclNode,
//        StructDeclNode,  FnBodyNode,      StructNode,     AssignStmtNode,
//        PostIncStmtNode, PostDecStmtNode, ReadStmtNode,   WriteStmtNode   
//        IfStmtNode,      IfElseStmtNode,  WhileStmtNode,  CallStmtNode
//        ReturnStmtNode,  DotAccessNode,   AssignExpNode,  CallExpNode,
//        UnaryExpNode,    BinaryExpNode,   UnaryMinusNode, NotNode,
//        PlusNode,        MinusNode,       TimesNode,      DivideNode,
//        AndNode,         OrNode,          EqualsNode,     NotEqualsNode,
//        LessNode,        GreaterNode,     LessEqNode,     GreaterEqNode
//
// **********************************************************************

// **********************************************************************
// Type & ErrorType Classes
//	Types: Bool, Void, Int, String, Struct, StructVar, Function
//	Error: Error
// **********************************************************************
abstract class Type {
	public abstract int getMyLineNum(); // gets line number

	public abstract int getMyCharNum(); // gets char number

	public abstract String toString(); // gets string
}

// **********************************************************************
// BoolType defines type Boolean.
// **********************************************************************
class BoolType extends Type {
	// Constructor
	public BoolType() {

	}

	// Constructor with variables
	public BoolType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	// To String
	public String toString() {
		return "bool";
	}

	// Char Num
	public int getMyLineNum() {
		return this.myLineNum;
	}

	// Line Num
	public int getMyCharNum() {
		return this.myCharNum;
	}

	private int myLineNum;
	private int myCharNum;

}
// **********************************************************************
// VoidType defines type Void.
// **********************************************************************

class VoidType extends Type {
	// Constructor
	public VoidType() {

	}

	// Constructor with variables
	public VoidType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	// To String
	public String toString() {
		return "void";
	}

	// Char Num
	public int getMyLineNum() {
		return this.myLineNum;
	}

	// Line Num
	public int getMyCharNum() {
		return this.myCharNum;
	}

	private int myLineNum;
	private int myCharNum;

}
// **********************************************************************
// IntType defines type Int
// **********************************************************************

class IntType extends Type {
	// Constructor
	public IntType() {

	}

	// Constructor with variables
	public IntType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	// To String
	public String toString() {
		return "int";
	}

	// Char Num
	public int getMyLineNum() {
		return this.myLineNum;
	}

	// Line Num
	public int getMyCharNum() {
		return this.myCharNum;
	}

	private int myLineNum;
	private int myCharNum;

}

// **********************************************************************
// StringType defines type String.
// **********************************************************************
class StringType extends Type {
	// Constructor
	public StringType() {

	}

	// Constructor with variables
	public StringType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	// To String
	public String toString() {
		return "String"; // capitalized S?
	}

	// Char Num
	public int getMyLineNum() {
		return this.myLineNum;
	}

	// Line Num
	public int getMyCharNum() {
		return this.myCharNum;
	}

	private int myLineNum;
	private int myCharNum;

}
// **********************************************************************
// StructType defines a struct.
// **********************************************************************

class StructType extends Type {
	// Constructor
	public StructType() {

	}

	// Getter & Setter
	public StructType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	// To String
	public String toString() {
		return "struct";
	}

	// Char Num
	public int getMyLineNum() {
		return this.myLineNum;
	}

	// Line Num
	public int getMyCharNum() {
		return this.myCharNum;
	}

	private int myLineNum;
	private int myCharNum;

}
// **********************************************************************
// StructVarType defines an instance of a struct.
// **********************************************************************

class StructVarType extends Type {
	// Constructor
	public StructVarType() {

	}

	// Constructor with variables
	public StructVarType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	// To String
	public String toString() {
		return "structvar";
	}

	// Char Num
	public int getMyLineNum() {
		return this.myLineNum;
	}

	// Line Num
	public int getMyCharNum() {
		return this.myCharNum;
	}

	private int myLineNum;
	private int myCharNum;

}
// **********************************************************************
// FunctionType defines the name of a function.
// **********************************************************************

class FunctionType extends Type {
	// Constructor
	public FunctionType() {

	}

	// Constructor with Variables
	public FunctionType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	// To String
	public String toString() {
		return "function";
	}

	// Char Num
	public int getMyCharNum() {
		return this.myCharNum;
	}

	// Line Num
	public int getMyLineNum() {
		return this.myLineNum;
	}

	private int myLineNum;
	private int myCharNum;

}
// **********************************************************************
// ErrorType defines the type of error.
// **********************************************************************

class ErrorType extends Type {
	public ErrorType() {

	}

	public ErrorType(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	public int getMyLineNum() {
		return this.myLineNum;
	}

	public int getMyCharNum() {
		return this.myCharNum;
	}

	public String toString() {
		return "error";
	}

	private int myLineNum;
	private int myCharNum;

}

// **********************************************************************
// Success Condition for reporting no errors
// **********************************************************************
class Success extends Type {
	public Success() {

	}

	public Success(int lineNum, int charNum) {
		this.myLineNum = lineNum;
		this.myCharNum = charNum;
	}

	public int getMyLineNum() {
		return this.myLineNum;
	}

	public int getMyCharNum() {
		return this.myCharNum;
	}

	public String toString() {
		return "Success";
	}

	private int myLineNum;
	private int myCharNum;

}

// **********************************************************************
// ASTnode class (base class for all other kinds of nodes)
// **********************************************************************

abstract class ASTnode {
	// every subclass must provide an unparse operation
	abstract public void unparse(PrintWriter p, int indent);

	// every subclass must provide a name analysis operation
	// executes name analysis to check for duplicates, undeclared uses, bad
	// declarations, bad structs
	abstract public void nameAnalysis(SymTable st);

	// every subclass must perform type analysis
	// executes type analysis
	abstract public Type typeAnalysis();

	// this method can be used by the unparse methods to do indenting
	protected void doIndent(PrintWriter p, int indent) {
		for (int k = 0; k < indent; k++)
			p.print(" ");
	}
}

// **********************************************************************
// ProgramNode, DeclListNode, FormalsListNode, FnBodyNode,
// StmtListNode, ExpListNode
// **********************************************************************

class ProgramNode extends ASTnode {
	public ProgramNode(DeclListNode L) {
		myDeclList = L;
	}

	/**
	 * Name Analysis executes name Analysis for this list of declarations given
	 * symtable st.
	 */
	public void nameAnalysis(SymTable st) {
		this.myDeclList.nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis for this list of declarations.
	 */
	public Type typeAnalysis() {
		return this.myDeclList.typeAnalysis();
	}

	public void unparse(PrintWriter p, int indent) {
		myDeclList.unparse(p, indent);
	}

	// 1 kid
	private DeclListNode myDeclList;
}

class DeclListNode extends ASTnode {
	public DeclListNode(List<DeclNode> S) {
		myDecls = S;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st, SymTable localSt) {
		Iterator iter = myDecls.iterator();
		try {
			while (iter.hasNext()) {
				((DeclNode) iter.next()).nameAnalysis(st, localSt);
			}
		} catch (NoSuchElementException e) {
			System.err.println(
					"unexpected NoSuchElementException in DeclListNode.nameAnalysis()");
			System.exit(-1);
			;
		}
	}

	public void nameAnalysis(SymTable st) {
		Iterator iter = myDecls.iterator();
		try {
			while (iter.hasNext()) {
				((DeclNode) iter.next()).nameAnalysis(st);
			}
		} catch (NoSuchElementException e) {
			System.err.println(
					"unexpected NoSuchElementException in DeclListNode.nameAnalysis()");
			System.exit(-1);
		}

	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		Iterator iter = myDecls.iterator();
		try {
			while (iter.hasNext()) {
				((DeclNode) iter.next()).typeAnalysis();
			}
		} catch (NoSuchElementException e) {
			System.err.println(
					"unexpected NoSuchElementException in DeclListNode.typeAnalysis()");
			System.exit(-1);

		}
		return new Success();
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator it = myDecls.iterator();
		try {
			while (it.hasNext()) {
				((DeclNode) it.next()).unparse(p, indent);
			}
		} catch (NoSuchElementException ex) {
			System.err.println(
					"unexpected NoSuchElementException in DeclListNode.print");
			System.exit(-1);
		}
	}

	// list of kids (DeclNodes)
	private List<DeclNode> myDecls;
}

class FormalsListNode extends ASTnode {
	public FormalsListNode(List<FormalDeclNode> S) {
		myFormals = S;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		Iterator<FormalDeclNode> iter = myFormals.iterator();
		iter.next().nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new Success();
	}

	public List<FormalDeclNode> getFormals() {
		return this.myFormals;
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<FormalDeclNode> it = myFormals.iterator();
		if (it.hasNext()) { // if there is at least one element
			it.next().unparse(p, indent);
			while (it.hasNext()) { // print the rest of the list
				p.print(", ");
				it.next().unparse(p, indent);
			}
		}
	}

	// list of kids (FormalDeclNodes)
	private List<FormalDeclNode> myFormals;
}

class FnBodyNode extends ASTnode {
	public FnBodyNode(DeclListNode declList, StmtListNode stmtList) {
		myDeclList = declList;
		myStmtList = stmtList;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.myDeclList.nameAnalysis(st);
		this.myDeclList.typeAnalysis();
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		myDeclList.typeAnalysis();
		return myStmtList.typeAnalysis();
	}

	public void unparse(PrintWriter p, int indent) {
		myDeclList.unparse(p, indent);
		myStmtList.unparse(p, indent);
	}

	// 2 kids
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
}

class StmtListNode extends ASTnode {
	public StmtListNode(List<StmtNode> S) {
		myStmts = S;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		Iterator<StmtNode> iter = myStmts.iterator();
		while (iter.hasNext()) {
			iter.next().nameAnalysis(st);
		}
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		Iterator<StmtNode> iter = myStmts.iterator();
		Type retType = null;
		StmtNode node = null;

		while (iter.hasNext()) {
			node = iter.next();
			if (!(node instanceof ReturnStmtNode)) {
				return new VoidType();
			}
		}
		return retType;

	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<StmtNode> it = myStmts.iterator();
		while (it.hasNext()) {
			it.next().unparse(p, indent, true);
		}
	}

	// list of kids (StmtNodes)
	private List<StmtNode> myStmts;
}

class ExpListNode extends ASTnode {
	public ExpListNode(List<ExpNode> S) {
		myExps = S;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		Iterator<ExpNode> iter = myExps.iterator();
		while (iter.hasNext()) {
			iter.next().nameAnalysis(st);
		}
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		Iterator<ExpNode> iter = myExps.iterator();
		while (iter.hasNext()) {
			iter.next().typeAnalysis();
		}
		return new Success();
	}

	public void unparse(PrintWriter p, int indent) {
		Iterator<ExpNode> it = myExps.iterator();
		if (it.hasNext()) { // if there is at least one element
			it.next().unparse(p, indent, true);
			while (it.hasNext()) { // print the rest of the list
				p.print(", ");
				it.next().unparse(p, indent, true);
			}
		}
	}

	public int getLength() {
		return this.myExps.size();
	}

	public List<ExpNode> getList() {
		return this.myExps;
	}

	// list of kids (ExpNodes)
	private List<ExpNode> myExps;
}

// **********************************************************************
// DeclNode and its subclasses
// **********************************************************************

abstract class DeclNode extends ASTnode {
	public abstract void nameAnalysis(SymTable s);

	public abstract void nameAnalysis(SymTable s, SymTable localS);

}

class VarDeclNode extends DeclNode {
	public VarDeclNode(TypeNode type, IdNode id, int size) {
		myType = type;
		myId = id;
		mySize = size;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {

	}

	public void nameAnalysis(SymTable st, SymTable localSt) {

	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		Type temp = this.myType.getType();
		switch (temp) {
		case "bool":
			return new BoolType();
		case "int":
			return new IntType();
		case "struct":
			return new StructType();
		default:
			System.err.println(
					"VarDeclNode Type Analysis for " + temp + "failed.");
			return new ErrorType();

		}
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myType.unparse(p, 0);
		p.print(" ");
		myId.unparse(p, 0);
		p.println(";");
	}

	// 3 kids
	private TypeNode myType;
	private IdNode myId;
	private int mySize; // use value NOT_STRUCT if this is not a struct type

	public static int NOT_STRUCT = -1;
}

class FnDeclNode extends DeclNode {
	public FnDeclNode(TypeNode type, IdNode id, FormalsListNode formalList,
			FnBodyNode body) {
		myType = type;
		myId = id;
		myFormalsList = formalList;
		myBody = body;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.nameAnalysis(st);
	}

	public void nameAnalysis(SymTable st, SymTable localSt) {
		SemSym s = new FnSym(this.myType.getType(),
				this.myFormalsList.getFormals());

		try {
			st.addDecl(this.myId.getID(), s);
			this.myId.setSym(s);
		} catch (DuplicateSymException e) {
			ErrMsg.fatal(this.myId.getLineNum(), this.myId.getCharNum(),
					"Multiply declared identifier");
		} catch (EmptySymTableException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myType.unparse(p, 0);
		p.print(" ");
		myId.unparse(p, 0);
		p.print("(");
		myFormalsList.unparse(p, 0);
		p.println(") {");
		myBody.unparse(p, indent + 4);
		p.println("}\n");
	}

	// 4 kids
	private TypeNode myType;
	private IdNode myId;
	private FormalsListNode myFormalsList;
	private FnBodyNode myBody;
}

class FormalDeclNode extends DeclNode {
	public FormalDeclNode(TypeNode type, IdNode id) {
		myType = type;
		myId = id;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// TODO
	}

	public void nameAnalysis(SymTable st, SymTable localSt) {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return this.myType.typeAnalysis();
	}

	public TypeNode getType() {
		return this.myType;
	}

	public void unparse(PrintWriter p, int indent) {
		myType.unparse(p, 0);
		p.print(" ");
		myId.unparse(p, 0);
	}

	// 2 kids
	private TypeNode myType;
	private IdNode myId;
}

class StructDeclNode extends DeclNode {
	public StructDeclNode(IdNode id, DeclListNode declList) {
		myId = id;
		myDeclList = declList;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("struct ");
		myId.unparse(p, 0);
		p.println("{");
		myDeclList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("};\n");

	}

	// 2 kids
	private IdNode myId;
	private DeclListNode myDeclList;
}

// **********************************************************************
// TypeNode and its Subclasses
// **********************************************************************

abstract class TypeNode extends ASTnode {
	abstract public String getType();
}

class IntNode extends TypeNode {
	public IntNode() {
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// No Name Analysis
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new IntType();
	}

	public String getType() {
		return "int";
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("int");
	}
}

class BoolNode extends TypeNode {
	public BoolNode() {
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// No Name Analysis
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new BoolType();
	}

	public String getType() {
		return "bool";
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("bool");
	}
}

class VoidNode extends TypeNode {
	public VoidNode() {
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// Do nothing
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new VoidType();
	}

	public String getType() {
		return "void";
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("void");
	}
}

class StructNode extends TypeNode {
	public StructNode(IdNode id) {
		myId = id;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new StructType(this.myId.getLineNum(), this.myId.getCharNum());
	}

	public String getType() {
		return "struct";
	}

	public String getStructType() {
		return this.myId.getId();
	}

	public IdNode getId() {
		return this.myId;
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("struct ");
		myId.unparse(p, 0);
	}

	// 1 kid
	private IdNode myId;
}

// **********************************************************************
// StmtNode and its subclasses
// **********************************************************************

abstract class StmtNode extends ASTnode {

	public abstract void unparse(PrintWriter p, int indent, boolean use);
}

class AssignStmtNode extends StmtNode {
	public AssignStmtNode(AssignNode assign) {
		myAssign = assign;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.myAssign.nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return this.myAssign.typeAnalysis();
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myAssign.unparse(p, -1, true); // no parentheses
		p.println(";");
	}

	// 1 kid
	private AssignNode myAssign;
}

class PostIncStmtNode extends StmtNode {
	public PostIncStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.myExp.nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myExp.unparse(p, 0, true);
		p.println("++;");
	}

	// 1 kid
	private ExpNode myExp;
}

class PostDecStmtNode extends StmtNode {
	public PostDecStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.myExp.nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myExp.unparse(p, 0, true);
		p.println("--;");
	}

	// 1 kid
	private ExpNode myExp;
}

class ReadStmtNode extends StmtNode {
	public ReadStmtNode(ExpNode e) {
		myExp = e;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.myExp.nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cin >> ");
		myExp.unparse(p, 0, true);
		p.println(";");
	}

	// 1 kid (actually can only be an IdNode or an ArrayExpNode)
	private ExpNode myExp;
}

class WriteStmtNode extends StmtNode {
	public WriteStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("cout << ");
		myExp.unparse(p, 0, true);
		p.println(";");
	}

	// 1 kid
	private ExpNode myExp;
}

class IfStmtNode extends StmtNode {
	public IfStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
		myDeclList = dlist;
		myExp = exp;
		myStmtList = slist;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("if (");
		myExp.unparse(p, 0, true);
		p.println(") {");
		myDeclList.unparse(p, indent + 4);
		myStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// e kids
	private ExpNode myExp;
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
}

class IfElseStmtNode extends StmtNode {
	public IfElseStmtNode(ExpNode exp, DeclListNode dlist1, StmtListNode slist1,
			DeclListNode dlist2, StmtListNode slist2) {
		myExp = exp;
		myThenDeclList = dlist1;
		myThenStmtList = slist1;
		myElseDeclList = dlist2;
		myElseStmtList = slist2;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("if (");
		myExp.unparse(p, 0, true);
		p.println(") {");
		myThenDeclList.unparse(p, indent + 4);
		myThenStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
		doIndent(p, indent);
		p.println("else {");
		myElseDeclList.unparse(p, indent + 4);
		myElseStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// 5 kids
	private ExpNode myExp;
	private DeclListNode myThenDeclList;
	private StmtListNode myThenStmtList;
	private StmtListNode myElseStmtList;
	private DeclListNode myElseDeclList;
}

class WhileStmtNode extends StmtNode {
	public WhileStmtNode(ExpNode exp, DeclListNode dlist, StmtListNode slist) {
		myExp = exp;
		myDeclList = dlist;
		myStmtList = slist;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("while (");
		myExp.unparse(p, 0, true);
		p.println(") {");
		myDeclList.unparse(p, indent + 4);
		myStmtList.unparse(p, indent + 4);
		doIndent(p, indent);
		p.println("}");
	}

	// 3 kids
	private ExpNode myExp;
	private DeclListNode myDeclList;
	private StmtListNode myStmtList;
}

class CallStmtNode extends StmtNode {
	public CallStmtNode(CallExpNode call) {
		myCall = call;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.myCall.nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return this.myCall.typeAnalysis();
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		myCall.unparse(p, indent, true);
		p.println(";");
	}

	// 1 kid
	private CallExpNode myCall;
}

class ReturnStmtNode extends StmtNode {
	public ReturnStmtNode(ExpNode exp) {
		myExp = exp;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		if (myExp != null) {
			this.myExp.nameAnalysis(st);
		}
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		if (myExp != null) {
			return this.myExp.typeAnalysis();
		} else {
			return new VoidType(0, 0);
		}
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		doIndent(p, indent);
		p.print("return");
		if (myExp != null) {
			p.print(" ");
			myExp.unparse(p, 0, true);
		}
		p.println(";");
	}

	// 1 kid
	private ExpNode myExp; // possibly null
}

// **********************************************************************
// ExpNode and its subclasses
// **********************************************************************

abstract class ExpNode extends ASTnode {
	public abstract void unparse(PrintWriter p, int indent, boolean use);
}

class IntLitNode extends ExpNode {
	public IntLitNode(int lineNum, int charNum, int intVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myIntVal = intVal;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// Do nothing
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new IntType(this.myLineNum, this.myCharNum);
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myIntVal);
	}

	private int myLineNum;
	private int myCharNum;
	private int myIntVal;
}

class StringLitNode extends ExpNode {
	public StringLitNode(int lineNum, int charNum, String strVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myStrVal = strVal;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// Do nothing
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new StringType(this.myLineNum, this.myCharNum);
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print(myStrVal);
	}

	private int myLineNum;
	private int myCharNum;
	private String myStrVal;
}

class TrueNode extends ExpNode {
	public TrueNode(int lineNum, int charNum) {
		myLineNum = lineNum;
		myCharNum = charNum;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// Do Nothing
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new BoolType(this.myLineNum, this.myCharNum);
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("true");
	}

	private int myLineNum;
	private int myCharNum;
}

class FalseNode extends ExpNode {
	public FalseNode(int lineNum, int charNum) {
		myLineNum = lineNum;
		myCharNum = charNum;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// Do nothing
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		return new BoolType(this.myLineNum, this.myCharNum);
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("false");
	}

	private int myLineNum;
	private int myCharNum;
}

class IdNode extends ExpNode {
	public IdNode(int lineNum, int charNum, String strVal) {
		myLineNum = lineNum;
		myCharNum = charNum;
		myStrVal = strVal;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		SemSym s = st.lookupGlobal(myStrVal);
		if (s == null) {
			ErrMsg.fatal(myLineNum, myCharNum, "Undeclared identifier");
		} else {
			this.setSym(s);
		}
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		if (this.sym instanceof FnSym) {
			return new FunctionType(this.myLineNum, this.myCharNum);
		}

		else if (this.sym instanceof StructSym) {
			return new StructType(this.myLineNum, this.myCharNum);
		}

		else if (this.sym instanceof StructVarSym) {
			return new StructVarType(this.myLineNum, this.myCharNum);
		}

		switch (this.sym.getType()) {
		case "int":
			return new IntType(this.myLineNum, this.myCharNum);
		case "bool":
			return new BoolType(this.myLineNum, this.myCharNum);
		default:
			return new ErrType(this.myLineNum, this.myCharNum);
		}
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		p.print(myStrVal);
		if (sym != null && use) {
			p.println("(" + sym.toString() + ")");
		}
	}

	public void unparse(PrintWriter p, int indent) {
		unparse(p, indent, false);
	}

	public int getLineNum() {
		return this.myLineNum;
	}

	public int getCharNum() {
		return this.myCharNum;
	}

	public String getId() {
		return this.myStrVal;
	}

	public SemSym getSym() {
		return this.sym;
	}

	public void setSym(SemSym sym) {
		this.sym = sym;
	}

	private int myLineNum;
	private int myCharNum;
	private String myStrVal;
	private SemSym sym;
}

class DotAccessExpNode extends ExpNode {
	public DotAccessExpNode(ExpNode loc, IdNode id) {
		myLoc = loc;
		myId = id;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myLoc.unparse(p, 0, true);
		p.print(").");
		myId.unparse(p, 0, true);
	}

	// 2 kids
	private ExpNode myLoc;
	private IdNode myId;
}

class AssignNode extends ExpNode {
	public AssignNode(ExpNode lhs, ExpNode exp) {
		myLhs = lhs;
		myExp = exp;
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		if (indent != -1)
			p.print("(");
		myLhs.unparse(p, 0, true);
		p.print(" = ");
		myExp.unparse(p, 0, true);
		if (indent != -1)
			p.print(")");
	}

	// 2 kids
	private ExpNode myLhs;
	private ExpNode myExp;
}

class CallExpNode extends ExpNode {
	public CallExpNode(IdNode name, ExpListNode elist) {
		myId = name;
		myExpList = elist;
	}

	public CallExpNode(IdNode name) {
		myId = name;
		myExpList = new ExpListNode(new LinkedList<ExpNode>());
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		// TODO MORE
		myId.unparse(p, 0, true);
		p.print("(");
		if (myExpList != null) {
			myExpList.unparse(p, 0, true);
		}
		p.print(")");
	}

	// 2 kids
	private IdNode myId;
	private ExpListNode myExpList; // possibly null
}

abstract class UnaryExpNode extends ExpNode {
	public UnaryExpNode(ExpNode exp) {
		myExp = exp;
	}

	// one child
	protected ExpNode myExp;
}

abstract class BinaryExpNode extends ExpNode {
	public BinaryExpNode(ExpNode exp1, ExpNode exp2) {
		myExp1 = exp1;
		myExp2 = exp2;
	}

	public int getLineNum() {
		return this.myLineNum;
	}

	public int getCharNum() {
		return this.myCharNum;
	}

	// two kids
	protected ExpNode myExp1;
	protected ExpNode myExp2;
	private int myLineNum;
	private int myCharNum;
}

// **********************************************************************
// Subclasses of UnaryExpNode
// **********************************************************************

class UnaryMinusNode extends UnaryExpNode {
	public UnaryMinusNode(ExpNode exp) {
		super(exp);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		this.myExp.nameAnalysis(st);
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(-");
		myExp.unparse(p, 0, true);
		p.print(")");
	}
}

class NotNode extends UnaryExpNode {
	public NotNode(ExpNode exp) {
		super(exp);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis(SymTable st) {
		// Do nothing
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(!");
		myExp.unparse(p, 0, true);
		p.print(")");
	}
}

// **********************************************************************
// Subclasses of BinaryExpNode
// **********************************************************************

class PlusNode extends BinaryExpNode {
	public PlusNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent, boolean use) {
		unparse(p, indent);
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0, true);
		p.print(" + ");
		myExp2.unparse(p, 0, true);
		p.print(")");
	}
}

class MinusNode extends BinaryExpNode {
	public MinusNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" - ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class TimesNode extends BinaryExpNode {
	public TimesNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" * ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class DivideNode extends BinaryExpNode {
	public DivideNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" / ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class AndNode extends BinaryExpNode {
	public AndNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" && ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class OrNode extends BinaryExpNode {
	public OrNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" || ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class EqualsNode extends BinaryExpNode {
	public EqualsNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" == ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class NotEqualsNode extends BinaryExpNode {
	public NotEqualsNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" != ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class LessNode extends BinaryExpNode {
	public LessNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" < ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class GreaterNode extends BinaryExpNode {
	public GreaterNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" > ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class LessEqNode extends BinaryExpNode {
	public LessEqNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" <= ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}

class GreaterEqNode extends BinaryExpNode {
	public GreaterEqNode(ExpNode exp1, ExpNode exp2) {
		super(exp1, exp2);
	}

	/**
	 * Name Analysis executes name Analysis
	 */
	public void nameAnalysis() {
		// TODO
	}

	/**
	 * Type Analysis executes Type Analysis
	 */
	public Type typeAnalysis() {
		// TODO
	}

	public void unparse(PrintWriter p, int indent) {
		p.print("(");
		myExp1.unparse(p, 0);
		p.print(" >= ");
		myExp2.unparse(p, 0);
		p.print(")");
	}
}
