// By: Fnu Alisha
// SE 311-001
// HW 4

import java.util.ArrayList;

// This class is part of the state pattern and inherits from abstract State class
// It represents the calculate state for the calculator
public class CalculateState extends State {
    //--Constructor--
    public CalculateState(ContextController context) {
        super(context);
    }

    //--Methods--

    //The method is used to determine the next state for a given value
    // and used for transitioning from one state to another
    @Override
    public void getNextState(String value) {
        if(value.matches("[0-9]")){
            //start a new calculation, and reset
            //update calculator view and transition to FirstOperand state
            //set the first operator value
            context.calculator.setOutput(value);
            context.setFirstOperand(value);
            context.trackOperation.add(new Operand(value));
            context.subExpressionTree = new Operator(null);
            context.expressionTree = new Operator(null);
            context.setOperator(null);
            context.setSecondOperand(null);
            context.setCurrentState(new FirstOperandState(context));
        }
        else {
            //update calculator view and reset to start state
            context.setFirstOperand(null);
            context.setOperator(null);
            context.setSecondOperand(null);
            context.trackOperation = new ArrayList<OperationModel>();
            context.subExpressionTree.setValue(null);
            context.expressionTree.setValue(null);
            context.setCurrentState(new StartState(context));
            context.calculator.setOutput("");
        }
    }
}

