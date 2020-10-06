// By: Fnu Alisha
// SE 311-001
// HW 4

import java.io.Serializable;
import java.util.ArrayList;

// This class is part of the composite pattern and acts as a Model in MVC architecture.
// It inherits from interface Operation Model and
// It represents the leaf class in the composite pattern.
public class Operand implements OperationModel, Serializable {
    //--Attribute--
    protected String operandValue;

    //--Cosntructor--
    Operand(String value){
        operandValue = value;
    }

    //--Methods--

    @Override
    public void accept(Visitor visitor) {
        visitor.visitOperation(this);
    }

    @Override
    public ArrayList<OperationModel> getOperationChildren() {
        return null;
    }

    @Override
    public String getValue() {
        return operandValue;
    }

    @Override
    public void setValue(String value) {
        operandValue = value;
    }
}
