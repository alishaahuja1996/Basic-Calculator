// By: Fnu Alisha
// SE 311-001
// HW 4

// This interface is part of the Visitor pattern
// The purpose of the interface is to access the composite tree representing an operation of Operator and Operand
public interface Visitor {
    //--Method--
    void visitOperation(OperationModel operation);
}
