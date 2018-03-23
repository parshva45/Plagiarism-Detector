package edu.northeastern.cs5500.strategies.implementations.ast.pythonast;

/**
 * @author namratabilurkar
 */

import edu.northeastern.cs5500.strategies.implementations.ast.pythonparser.Python3Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.stereotype.Component;

@Component
public class AstBuilder {

    private boolean ignoringWrappers = true;
    // String storing the AST built for the file.
    private String astString = "";

    /**
     *
     * @param ignoringWrappers is the wrapper that need to be ignored.
     */
    public void setIgnoringWrappers(boolean ignoringWrappers) {
        this.ignoringWrappers = ignoringWrappers;
    }

    /**
     *
     * @param ctx is the rule context
     * @return AST built of the file in the form of a string.
     */
    public String build(RuleContext ctx) {
        explore(ctx, 0);
        return astString;
    }

    /**
     *
     * @param ctx is the rule context
     * @param indentation is the indentation that needs to be added while
     *                    building the AST
     */
    private void explore(RuleContext ctx, int indentation) {
        boolean toBeIgnored = ignoringWrappers
                && ctx.getChildCount() == 1
                && ctx.getChild(0) instanceof ParserRuleContext;
        if (!toBeIgnored) {
            String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
            for (int i = 0; i < indentation; i++) {
                //System.out.print("  ");
                this.astString += "  ";
            }
            //System.out.println(ruleName);
            this.astString += ruleName + "\n";
        }
        for (int i=0;i<ctx.getChildCount();i++) {
            ParseTree element = ctx.getChild(i);
            if (element instanceof RuleContext) {
                explore((RuleContext)element, indentation + (toBeIgnored ? 0 : 1));
            }
        }
    }

}

