package app.com.example.grace.currencycalculator.Controller;


import java.util.ArrayList;
import java.util.List;

import app.com.example.grace.currencycalculator.models.Expression;
import app.com.example.grace.currencycalculator.models.ExpressionPart;
import app.com.example.grace.currencycalculator.models.Operand;
import app.com.example.grace.currencycalculator.models.Operator;

public class ExpressionAnalyzer {

    public ExpressionAnalyzer() {

    }

    public Expression breakDownExpression(String expressionString) {
        boolean isBracket = false;

        Expression expression = new Expression();
        Validator validator = new Validator();
        String str = "";
        String subexpression = "";
        List<ExpressionPart> expressionParts = new ArrayList<>();

        for (int i = 0; i < expressionString.length(); i++) {
            char current = expressionString.charAt(i);

            if (validator.isOperator(current) & !isBracket) {
                if (str != "") {
                    expressionParts.add(new Operand(str));
                }
                expressionParts.add(new Operator(current + ""));
                str = "";
            }
            else if(current == '(' && i != 0) {
                isBracket = true;
                subexpression += current;
            }
            else if (isBracket && current != ')') {
                subexpression += expressionString.charAt(i);
            }

            else if(current == ')' && isBracket) {
                isBracket = false;
                subexpression += current;
                expressionParts.add(new Operand(subexpression));
                subexpression = "";
            }
            else if (!isBracket && !validator.isOperator(current) & current != ')' & current != '(') {
                str = str + expressionString.charAt(i);
            }
        }

        if(str != "") {
            expressionParts.add(new Operand(str));
        }
        expression.setExpressionParts(expressionParts);

        return expression;
    }


}
