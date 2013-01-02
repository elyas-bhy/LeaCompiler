package org.tree;

import org.gen.*;

public class Block extends AST {

    public Block(AST left, AST right) {
        super(left, right, EnumTag.BLOCK);
        Verificator.checkUnreachableStmts(right);
    }

    public String toJava() {
        if (Main.currentEnv.equals(Main.firstEnv)) {
            Main.currentEnv = Main.functionEnvs.get(Main.envNum);
            Main.envNum++;
        } else {
            Main.currentEnv = Main.currentEnv.getNext();
        }
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