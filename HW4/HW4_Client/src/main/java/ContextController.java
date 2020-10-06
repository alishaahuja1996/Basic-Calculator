// By: Fnu Alisha
// SE 311-001
// HW 4

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.net.*;
import java.io.*;
import javax.swing.JOptionPane;

// This class acts as a Controller in the MVC Architecture
// It acts as a concrete observer in Observer pattern, and its purpose is to listen to the events and update the subject Calculator view
// It acts as as a context in State pattern, and its purpose is to keep track of state transitions and perform actions based on states
// It acts as a component class in Composite pattern and its purpose is to generate a expression tree
// Once, a tree is generated, it sends object tree to the server
public class ContextController implements ActionListener{
    //--Attributes--

    protected CalculatorView calculator;
    protected State currentState= new StartState(this);

    //for previous state tracking purposes
    protected State previousState= null;
    protected String previousValue;
    protected ContextController previousContext;

    //for tracking the current operation
    protected ArrayList<OperationModel> trackOperation = new ArrayList<OperationModel>();

    //for saving the operation tree and sending it to the server
    // generated using composite pattern
    protected Operator expressionTree = new Operator(null);
    protected Operator subExpressionTree = new Operator(null);

    //to keep track of operator and operand in an operation
    protected OperationModel firstOperand = new Operand(null);
    protected OperationModel secondOperand = new Operand(null);
    protected OperationModel operator = new Operator(null);

    //--constructor--
    ContextController(CalculatorView cv){
        calculator = cv;
    }

    //--Accessors and Mutators--
    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public CalculatorView getCalculator() {
        return calculator;
    }

    public void setFirstOperand(String firstOperand) {
        this.firstOperand.setValue(firstOperand);
    }

    public void setSecondOperand(String secondOperand) {
        this.secondOperand.setValue(secondOperand);
    }

    public void setOperator(String operator) {
        this.operator.setValue(operator);
    }

    public String getFirstOperand() {
        return firstOperand.getValue();
    }

    public String getSecondOperand() {
        return secondOperand.getValue();
    }

    public String getOperator() {
        return operator.getValue();
    }

    //--Methods--

    //The funtion handles each event
    @Override
    public void actionPerformed(ActionEvent e) {
        //get the subject event's value
        String value = e.getActionCommand();

        //keep track of previous states except Error state
        if(currentState instanceof ErrorState == false) {
            previousState = currentState;
            previousValue = calculator.getOutput();
            previousContext = this;
        }

        //get next state for the event value
        // and update the current state
        currentState.getNextState(value);

        // This represents a calculation for a subexpression like "5-3" in a expression "5-3+6"
        if (currentState instanceof NextOperandState) {
            if(!(secondOperand.getValue() == null)){
                //calculate the subexpression and get the result
                double subExpressionResult = calculateOperation(firstOperand, operator, secondOperand);
                //set the result value as first operand
                // and update the operator and second operand value to null
                firstOperand.setValue(Double.toString(subExpressionResult));
                operator.setValue(value);
                secondOperand.setValue(null);

                //update the calculator view to the result from sub-tree
                calculator.setOutput((Double.parseDouble(new DecimalFormat("##.#####").format(subExpressionResult))) + operator.getValue());
            }

        }
        // This represents a final calculation for a expression like "2+6" in a expression "5-3+6"
        // where 2 is calculation is handeled above when the state is NextOperand
        else if(currentState instanceof CalculateState){
            //calculate the final expression and get the result
            double result = calculateOperation(firstOperand, operator, secondOperand);
            //update the calculator view to the result
            calculator.setOutput(Double.toString(Double.parseDouble(new DecimalFormat("##.#####").format(result))));

            System.out.print("Sending the successful calculation: ");
            for (int i=0;i<trackOperation.size();i++)
                System.out.print(trackOperation.get(i).getValue() + " ");
            System.out.println("to the Server...");

            //create the complete expression tree to send to the server
            // a OperationModel Object is sent to the server
            createExpressionTree();

            //send the operationModel object to the server
            try {
                sendOperationToServer(expressionTree);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        // display an error message if an error state is reached
        // assumes 'C' as 'Reset' option and any other button in calculator as 'Discard' option
        else if(currentState instanceof ErrorState) {
            JOptionPane.showMessageDialog(null, "Error encountered. Please choose 1 of the options:\n" +
                    "1. Hit 'C' to Reset the Calculator\n2. Hit any other key to discard and to go back to your previous calculation");
        }
    }

    // Function handles the general calculation for an operation and return the result
    public double calculateOperation(OperationModel firstOperand, OperationModel operator, OperationModel secondOperand){
        double firstDigit = Double.parseDouble(firstOperand.getValue());
        double secondDigit = Double.parseDouble(secondOperand.getValue());
        double result = 0.0;

        if(operator.getValue() == "+")
            result = firstDigit + secondDigit;
        else if (operator.getValue() == "-")
            result = firstDigit - secondDigit;
        else if (operator.getValue() == "/")
            result = firstDigit / secondDigit;
        else if (operator.getValue() == "*")
            result = firstDigit * secondDigit;

        return result;
    }

    //Function creates a expression tree using composite pattern
    public void createExpressionTree(){
        //expression tree for OperationModel containing no sub-trees like '2+3'
        if(trackOperation.size() == 3){
            expressionTree.setValue(trackOperation.get(1).getValue());
            expressionTree.add(trackOperation.get(0));
            expressionTree.add(trackOperation.get(2));
        }
        //expression tree for Operation model containing subtrees like '2+3*7'
        else {
            for (int i = 1; i < trackOperation.size(); i = i + 2) {
                if (subExpressionTree.getValue() == null) {
                    subExpressionTree.setValue(trackOperation.get(i).getValue());
                    subExpressionTree.add(trackOperation.get(i - 1));
                    subExpressionTree.add(trackOperation.get(i + 1));
                } else {
                    Operator tmp = new Operator(trackOperation.get(i).getValue());
                    tmp.add(subExpressionTree);
                    tmp.add(trackOperation.get(i + 1));
                    subExpressionTree = tmp;
                }
            }
            expressionTree = subExpressionTree;
        }

        //reset the current tracking for operations
        trackOperation= new ArrayList<OperationModel>();
    }

    //Function handles the sending of an OperationModel object from client to server
    public void sendOperationToServer(OperationModel operation ) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        OutputStream os = socket.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(os);

        oos.writeObject(operation);
        os.close();
        oos.close();
        socket.close();
    }

}
