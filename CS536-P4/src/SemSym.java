import java.util.*;

/* TODO COMMENT
 */
public class SemSym {
	private String type;

	/*
	 * TODO COMMENT
	 */
	public SemSym(String type) {
		this.type = type;
	}

	/*
	 * TODO COMMENT
	 */
	public String getType() {
		return this.type;
	}

	/*
	 * TODO COMMENT
	 */
	public String toString() {
		return this.type;
	}
}

/*
 * TODO COMMENT
 */
class StructDeclSym extends SemSym {
	private SymTable fields;
	private String type;

	/*
	 * TODO COMMENT
	 */
	public StructDeclSym(SymTable fields, String type) {
		super(type);
		this.fields = fields;
	}

	/*
	 * TODO COMMENT
	 */
	public SymTable getFields() {
		return fields;
	}

	/*
	 * TODO COMMENT
	 */
	public String toString() {
		return super.getType();
	}
}

/*
 * TODO COMMENT
 */
class StructVarSym extends SemSym {
	private StructDeclSym structDeclId;
	private String type;

	/*
	 * TODO COMMENT
	 */
	public StructVarSym(StructDeclSym id, String type) {
		super(type);
		this.structDeclId = id;
	}

	/*
	 * TODO COMMENT
	 */ public StructDeclSym getDecl() {
		return structDeclId;
	}

	/*
	 * TODO COMMENT
	 */
	public String toString() {
		return super.getType();
	}
}

/*
 * TODO COMMENT
 */
class FnDeclSym extends SemSym {
	private String returnType;
	private List<String> formalsTypes;
	private String type;

	/*
	 * TODO COMMENT
	 */
	public FnDeclSym(List<String> formalsTypes, String returnType) {
		super(null);
		this.returnType = returnType;
		this.formalsTypes = formalsTypes;
	}

	/*
	 * TODO COMMENT
	 */
	public String getReturnType() {
		return returnType;
	}

	/*
	 * TODO COMMENT
	 */
	public List<String> getFormalsTypes() {
		return this.formalsTypes;
	}

	/*
	 * TODO COMMENT
	 */
	public String toString() {
		String sol = "";
		Iterator<String> itr = this.formalsTypes.iterator();
		if (itr.hasNext()) {
			sol += itr.next();
		}
		while (itr.hasNext()) {
			sol += "," + itr.next();
		}
		return sol + "->" + this.returnType;
	}

}