// By: Fnu Alisha
// SE 311-001
// HW 4

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

// This class inherits from the Visitor interface(on client side) and is part of the Visitor pattern
// The purpose of the class is to access the composite tree and print the tree to the console and save it to a text file
public class StringVisitor implements Visitor {
    //--Method--
    @Override
    public void visitOperation(OperationModel operation) {
        ArrayList<OperationModel> children = operation.getOperationChildren();

        File file = new File("SuccessfulOperations.txt");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), true));

            out.write("|\\\n");
            System.out.println("|\\");

            for (OperationModel o : children) {
                out.write(o.getValue() + "  ");
                System.out.print(o.getValue() + "  ");
            }

            out.write("\n");
            System.out.println("");
            out.close();

            for (OperationModel op : children) {
                if (op.getOperationChildren() != null && !op.getOperationChildren().isEmpty()) {
                    visitOperation(op);
                }
            }

            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
