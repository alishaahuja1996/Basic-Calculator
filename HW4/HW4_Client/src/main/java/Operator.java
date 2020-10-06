// By: Fnu Alisha
// SE 311-001
// HW 4

import java.io.Serializable;
import java.util.ArrayList;

// This class is part of the composite pattern and acts as a Model in MVC architecture.
// It inherits from interface Operation Model and
// It represents the Composite class in the composite pattern. Therefore, it can contain OperationModel: Operator and Operand
public class Operator implements OperationModel, Serializable {
    //--Attributes--
    protected String operatorValue;
    protected ArrayList<OperationModel> operations = new ArrayList<OperationModel>();

    //--Cosntructor--
    Operator(String value){
        operatorValue = value;
    }

    //--Methods--
    public void add(OperationModel op) {
        operations.add(op);
    }

    public void remove(OperationModel op) {
        operations.remove(op);
    }

    public ArrayList<OperationModel> getOperations() {
        return operations;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitOperation(this);
    }

    @Override
    public ArrayList<OperationModel> getOperationChildren() {
        return operations;
    }

    @Override
    public String getValue() {
        return operatorValue;
    }

    @Override
    public void setValue(String value) {
        operatorValue = value;
    }
}
