package app.com.example.grace.currencycalculator;

import android.test.ActivityInstrumentationTestCase2;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import app.com.example.grace.currencycalculator.Controller.Calculator;
import app.com.example.grace.currencycalculator.models.Expression;
import app.com.example.grace.currencycalculator.models.ExpressionPart;
import app.com.example.grace.currencycalculator.models.Operand;
import app.com.example.grace.currencycalculator.models.Operator;

public class CalculatorTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public CalculatorTest() {
        super(MainActivity.class);
    }

    Calculator calculator;
    Expression expression;

    public void setUp() throws Exception {
        super.setUp();

        calculator = new Calculator();

        expression = new Expression();
    }

    public void testComputeExpressionWhenExpressionNeedsNoPrecedenceRule() throws Exception {
        String expression = "12+6-3";
        assertEquals(15.0, calculator.compute(expression));
    }

    public void testComputeExpressionWhenExpressionNeedsPrecedenceRule() throws Exception {
        String expression = "12+6*4-9/3";
        assertEquals(33.0, calculator.compute(expression));

    }

    public void testComputeExpressionWhenExpressionHasBracketWithAPrecedingOperator() throws Exception {
        String expression = "12+(6)*4";
        assertEquals(36.0, calculator.compute(expression));
    }

    public void testComputeExpression1() throws Exception {
        expression.setExpressionParts(Arrays.asList(
                new Operand("2"),
                new Operator("*"),
                new Operand("3"),
                new Operator("+"),
                new Operand("5")));

        double expressionResult = calculator.compute(expression.getValue());

        assertEquals(11.0, expressionResult);
    }

    public void testComputeExpressionWhenExpressionHasBracketWithNoPrecedingOperator() throws Exception {
        List<ExpressionPart> expressionParts = new ArrayList<>();
        expressionParts.add(new Operand("12"));
        expressionParts.add(new Operand("(3)"));
        expression.setExpressionParts(expressionParts);

        assertEquals(36.0, calculator.compute(expression.getValue()));
    }

    public void testComputeExpressionWhenExpressionHasSubExpression() throws Exception {
        expression.setExpressionParts(Arrays.asList(
                new Operand("2"),
                new Operator("*"),
                new Operand("4"),
                new Operator("+"),
                new Operand("(3+5*2-1)"),
                new Operator("/"),
                new Operand("2")
        ));
        assertEquals(14.0, calculator.compute(expression.getValue()));
    }

}