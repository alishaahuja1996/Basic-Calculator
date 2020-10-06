// By: Fnu Alisha
// SE 311-001
// HW 4

import java.util.ArrayList;

// This class is part of the state pattern and inherits from abstract State class
// It represents the nextOperand state for the calculator
public class NextOperandState extends State {
    //--Constructor--
    public NextOperandState(ContextController context) {
        super(context);
    }

    //--Methods--

    //The method is used to determine the next state for a given value
    // and used for transitioning from one state to another
    @Override
    public void getNextState(String value) {
        if(value.matches("[0-9]")){
            //set the second operand value
            context.setSecondOperand(value);
            context.trackOperation.add(new Operand(value));

            //update calculator view and state
            context.calculator.setOutput(context.calculator.getOutput() + value);
            context.setCurrentState(new SecondOperandState(context));
        }
        else if(value.matches("[+-/*=]")){
            //update calculator view and state
            context.setCurrentState(new ErrorState(context));
            context.calculator.setOutput("ERROR");
        }
        else if(value.matches("C")){
            //update calculator view and reset to start state
            context.setFirstOperand(null);
            context.setOperator(null);
            context.setSecondOperand(null);
            context.trackOperation = new ArrayList<OperationModel>();
            context.subExpressionTree = new Operator(null);
            context.expressionTree = new Operator(null);
            context.setCurrentState(new StartState(context));
            context.calculator.setOutput("");
        }
    }

}
