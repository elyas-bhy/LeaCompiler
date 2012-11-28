package org.gen;

public class Block extends AST {

    public Block(AST left, AST right) {
        super(left, right, EnumTag.BLOCK);
    }

    public String toJava() {
        Main.currentEnv = Main.currentEnv.getNext();
        CodeGenerator.tabLevel++;
        String s = getLeft().toJava();
        if (s.equals(""))
            s = getRight().toJava();
        else
            s += "\n" + getRight().toJava();
        CodeGenerator.tabLevel--;
        Main.currentEnv = Main.currentEnv.getPrev();
        return s;
    }
}