// By: Fnu Alisha
// SE 311-001
// HW 4

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class RequestHandler extends Thread {
    //--Attributes--
    private Socket clientSocket = null;
    private ArrayList<OperationModel> successfulCalc;

    //--Constructor--
    public RequestHandler(Socket _clientSocket, ArrayList<OperationModel> _successfulCalc) {
        clientSocket = _clientSocket;
        this.successfulCalc = _successfulCalc;
    }

    public void run() {
        try {
            //get input stream from client, read it,
            // and convert it into appropriate object
            InputStream in = clientSocket.getInputStream();
            ObjectInputStream obi = new ObjectInputStream(in);
            OperationModel expressionTree = (OperationModel) obi.readObject();

            successfulCalc.add(expressionTree);
            System.out.println("Total Number of Successful Calculations: " + successfulCalc.size() + "\n");

            for (int i = 0; i < successfulCalc.size(); i++) {
                try {
                    BufferedWriter out = new BufferedWriter(new FileWriter("SuccessfulOperations.txt"));
                    System.out.println(successfulCalc.get(i).getValue());
                    out.write(successfulCalc.get(i).getValue()+"\n");
                    out.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }

                //access the OperationModel object tree using Visitor
                successfulCalc.get(i).accept(new StringVisitor());
            }
        }
        catch(Exception e) { e.printStackTrace();}
    }

}
