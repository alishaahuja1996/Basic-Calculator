// By: Fnu Alisha
// SE 311-001
// HW 4

import java.util.ArrayList;

// This interface is part of the Composite pattern and acts as a Model in the MVC Architecture
// The purpose of the interface is to create a tree representing an operation of Operator and Operand
public interface OperationModel {
    //--Methods--
    void accept(Visitor visitor);
    ArrayList<OperationModel> getOperationChildren();
    String getValue();
    void setValue(String value);
}

