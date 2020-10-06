// By: Fnu Alisha
// SE 311-001
// HW 4

import java.util.ArrayList;

// This class is part of the state pattern and inherits from abstract State class
// It represents the FirstOperand state for the calculator
public class FirstOperandState extends State {
    //--Constructor--
    public FirstOperandState(ContextController context) {
        super(context);
    }

    //--Methods--

    //The method is used to determine the next state for a given value
    // and used for transitioning from one state to another
    @Override
    public void getNextState(String value) {
        if(value.matches("[0-9]")){
            //set the first operand value
            String newValue= context.getFirstOperand() + value;
            context.calculator.setOutput(newValue);

            //update calculator view and state
            context.setFirstOperand(newValue);
            context.trackOperation.set(context.trackOperation.size()-1, new Operand(newValue));
        }
        else if(value.matches("[+-/*]")){
            //set the first operator value
            context.setOperator(value);
            context.trackOperation.add(new Operator(value));

            //update calculator view and state
            String newValue= context.getFirstOperand() + value;
            context.calculator.setOutput(newValue);
            context.setCurrentState(new NextOperandState(context));
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
        else if(value.matches("=")){
            //update calculator view and state
            context.calculator.setOutput("ERROR");
            context.setCurrentState(new ErrorState(context));
        }
    }
}
