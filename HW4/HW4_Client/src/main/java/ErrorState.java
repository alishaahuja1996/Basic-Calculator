// By: Fnu Alisha
// SE 311-001
// HW 4

import java.util.ArrayList;

// This class is part of the state pattern and inherits from abstract State class
// It represents the Error state for the calculator
public class ErrorState extends State {
    //--Constructor--
    public ErrorState(ContextController context) {
        super(context);
    }

    //--Methods--

    //The method is used to determine the next state for a given value
    // and used for transitioning from one state to another
    @Override
    public void getNextState(String value) {
        if(value.matches("C")){
            //This represents if the user choses 'Reset' option
            //update the calculator view and reset to start state
            context.trackOperation = new ArrayList<OperationModel>();
            context.setFirstOperand(null);
            context.setOperator(null);
            context.setSecondOperand(null);
            context.subExpressionTree = new Operator(null);
            context.expressionTree = new Operator(null);
            context.setCurrentState(new StartState(context));
            context.calculator.setOutput("");
        }
        else {
            //This represents if the user choses 'Discard' option
            //update the calculator view and reset to previous state
            if(context.previousState instanceof FirstOperandState)
                context.setCurrentState(new FirstOperandState(context.previousContext));
            else
                context.setCurrentState(new NextOperandState(context.previousContext));
            context.calculator.setOutput(context.previousValue);
        }
    }

}
