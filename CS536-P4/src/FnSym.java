import java.util.List;

public class FnSym extends SemSym {
	    //TODO: Actually store formals list instead of count
	    private List<FormalDeclNode> formals;
	    public FnSym(String type, List<FormalDeclNode> formals){
	        super(type);
	        this.formals = formals;
	    }

	    public List<FormalDeclNode> getFormals(){
	        return this.formals;
	    }
}