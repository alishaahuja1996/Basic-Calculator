// By: Fnu Alisha
// SE 311-001
// HW 4


import java.util.ArrayList;

// This class is part of the state pattern and inherits from abstract State class
// It represents the secondOperand state for the calculator
public class SecondOperandState extends State {
    //--constructor--
    public SecondOperandState(ContextController context) {
        super(context);
    }

    //--Methods--

    //The method is used to determine the next state for a given value
    // and used for transitioning from one state to another
    @Override
    public void getNextState(String value) {
        if(value.matches("[0-9]")){
            //set the second operand value
            String newValue= context.getSecondOperand() + value;
            context.calculator.setOutput(context.calculator.getOutput() + value);

            //update calculator view and state
            context.setSecondOperand(newValue);
            context.trackOperation.set(context.trackOperation.size()-1, new Operand(newValue));
        }
        else if(value.matches("[+-/*]")){
            //update calculator view and state
            context.calculator.setOutput(context.calculator.getOutput() + value);
            context.trackOperation.add(new Operator(value));
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
            //update state
            context.setCurrentState(new CalculateState(context));
        }
    }
}
