// By: Fnu Alisha
// SE 311-001
// HW 4


// This class is part of the state pattern and inherits from abstract State class
// It represents the start state for the calculator
public class StartState extends State {
    //--Constructor--
    public StartState(ContextController context) {
        super(context);
        previousState = "none";
    }

    //--Methods--

    //The method is used to determine the next state for a given value
    // and used for transitioning from one state to another
    @Override
    public void getNextState(String value) {
        if(value.matches("[0-9]")){
            //set the first operand value
            context.setFirstOperand(value);
            context.trackOperation.add(new Operand(value));

            //update calculator view and state
            context.calculator.setOutput(value);
            context.setCurrentState(new FirstOperandState(context));
        }
        else {
            //update calculator view and state
            context.setCurrentState(new StartState(context));
            context.calculator.setOutput("");
        }

    }

}
