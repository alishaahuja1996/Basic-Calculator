// By: Fnu Alisha
// SE 311-001
// HW 4

//The class to manage the overall client
public class Client {
    public static void main(String[] args) {
        //create the CalculatorView Subject and ContextController Observer listener
        CalculatorView calculator = new CalculatorView();
        ContextController context = new ContextController(calculator);

        //attach the listener
        calculator.attachListener(context);
    }

}
