// By: Fnu Alisha
// SE 311-001
// HW 4

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// This class acts as a Subject in the Observer Pattern and as a View in the MVC Architecture
// The purpose of the class is to create a GUI calculator and
// attach the listeners to the buttons, and set the output result for a successful calculation in the text field
// Reference for calculator GUI: https://www.geeksforgeeks.org/java-swing-simple-calculator/
public class CalculatorView extends JFrame {
    //Attribute
    private TextField outputDisplay;
    private JPanel outputPanel;
    private JPanel buttonPanel;

    //constructor
    public CalculatorView(){
        JFrame f= new JFrame("Calculator");

        //create the output display panel and add it to JFrame
        //add text field to the output display panel
        outputPanel = new JPanel();
        f.add(outputPanel, BorderLayout.NORTH);

        outputDisplay = new TextField("",30);
        outputDisplay.setEditable(false);
        outputDisplay.setForeground(Color.blue);
        outputPanel.add(outputDisplay);

        //create the button panel and add it to JFrame
        //add the buttons to the button panel
        buttonPanel = new JPanel();
        f.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(4,4));

        String[] buttons = {"1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "*", "0", "=", "C", "/"};

        for(String btn: buttons){
            buttonPanel.add(new JButton(btn));
        }

        //display the calculator
        f.setSize(300,300);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    //--Methods---

    // To set the calculation output in the text field
    public void setOutput(String value) {
        outputDisplay.setText(value);
    }

    public String getOutput(){
        return outputDisplay.getText();
    }

    // To add the listener for each button
    public void attachListener(ActionListener  l) {
        for(Component btn: buttonPanel.getComponents()) {
            ((JButton)btn).addActionListener(l);
        }
    }

}
