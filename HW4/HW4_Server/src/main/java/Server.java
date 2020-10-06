// By: Fnu Alisha
// SE 311-001
// HW 4

import java.net.*;
import java.util.ArrayList;

//The class to manage the overall Server
public class Server {
    public static void main(String [] args) throws Exception{
        ServerSocket server = new ServerSocket(8080);
        ArrayList<OperationModel> allOperations = new ArrayList<OperationModel>();

        //for each client handle requests
        while(true) {
            Socket client = server.accept();
            RequestHandler rh = new RequestHandler(client, allOperations);
            rh.start();
        }
    }

}
