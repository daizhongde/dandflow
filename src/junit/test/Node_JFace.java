package junit.test;


public class Node_JFace{
	private String name;
	
	private Node_JFace[] children = new Node_JFace[0];
	private Node_JFace parent = null;
	
	public Node_JFace(String name) {
		this.name = name;
	}
	public Node_JFace(String name, Node_JFace[] children) {
		this(name);
		this.children = children;
		for(int i=0; i<children.length; i++){
			children[i].parent = this;
		}
	}
	
	public static Node_JFace example(){
		return new Node_JFace("AuthorityModule",new Node_JFace[]{
			new Node_JFace("Query",new Node_JFace[]{
				new Node_JFace("query",new Node_JFace[]{
					new Node_JFace("SQL"),
					new Node_JFace("HQL"),
					new Node_JFace("JPQL")
				})
			}),
			new Node_JFace("Read",new Node_JFace[]{
				new Node_JFace("read",new Node_JFace[]{
					new Node_JFace("SQL"),
					new Node_JFace("HQL"),
					new Node_JFace("JPQL")
				})
			})
		});
	}
	
	public String toString(){
		return this.name;
	}
	
}
