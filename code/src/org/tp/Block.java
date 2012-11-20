package org.tp;

public class Block extends AST {
	
	public Block(AST left, AST right) {
		super(left, right, EnumTag.BLOCK);		
	}

	public String toJava() {
    CodeGenerator.tabLevel++;
    String s = tab() + getLeft().toJava() + "\n" + getRight().toJava();
    CodeGenerator.tabLevel--;
    return s;
	}
	
}