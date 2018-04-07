package edu.northeastern.cs5500.strategies.implementations.ast.pythonast;

/**
 * @author namratabilurkar
 */

import edu.northeastern.cs5500.strategies.implementations.ast.pythonparser.Python3Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class AstBuilder {

    private boolean ignoringWrappers = true;
    /**
     * String storing the AST built for the file.
     */
//    private String astString = "";
    private StringBuilder astString = new StringBuilder();
    private int previous = -1;

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
        return explore(ctx, 0);
    }

    /**
     * Method to explore the rulenames and build the AST
     * @param ctx is the rule context
     * @param indentation is the indentation that needs to be added while
     *                    building the AST
     */
    private String explore(RuleContext ctx, int indentation) {
        boolean toBeIgnored = ignoringWrappers
                && ctx.getChildCount() == 1
                && ctx.getChild(0) instanceof ParserRuleContext;

        if (!toBeIgnored) {
            String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
            if (previous >= indentation) {
                for (int i=0;i<=previous-indentation;i++) {
//                    astString += "}";
                    astString.append("}");
                }
            }
//            astString += "{" + ruleName;
            astString.append("{");
            astString.append(ruleName);
            previous = indentation;

        }
        for (int i=0;i<ctx.getChildCount();i++) {
            ParseTree element = ctx.getChild(i);
            if (element instanceof RuleContext) {
                explore((RuleContext)element, indentation + (toBeIgnored ? 0 : 1));
            }
        }
        return completeAstString(astString.toString());
    }

    /**
     * Complete the AST being built
     * @param astInput is the partially constructed AST
     * @return a completed AST as a string
     */
    private String completeAstString(String astInput) {
        int temp = 0;
        for (int i=0; i<astInput.length(); i++) {
            if (astInput.charAt(i) == '{') {
                temp++;
            }
            if (astInput.charAt(i) == '}') {
                temp--;
            }
        }
        while (temp>0) {
            astInput += "}";
            temp--;
        }
        return astInput;
    }

}

