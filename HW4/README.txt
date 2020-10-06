Fnu Alisha
SE 311-001
HW4


README

Note: I did the extra credit for HW4 so my Visitor is applied on the server side and 
the successful operation is stored in the text file

The HW4 has the following projects/files:

	1. HW4-componentUML.pdf: It is a pdf UML component diagram for HW4
	2. HW4-classUML.pdf: It is a pdf UML class diagram for HW4
	3. README
	4. HW4-video: the 10 min demo video for hw4
	5. HW4_Client: It is a project which contains all the client side code and its jar file.

		5.1 To view all the project code for client, go to
		HW4_Client --> src --> main --> java --> { all files for java code }
		The following are the files containing the java code:			CalculateState.java			CalculatorView.java			Client.java: contains runnable main 			ContextController.java			ErrorState.java			FirstOperandState.java			NextOperandState.java			Operand.java			OperationModel.java			Operator.java			SecondOperandState.java			StartState.java			State.java			Visitor.java
		5.2 To access it's jar file, go to HW4_Client --> target
		
	6. HW4_Server: It is a project which contains all the server side code.

		6.1 To view all the project code for server, go to
		HW4_Server --> src --> main --> java --> { all files for java code }
		The following are the files containing the java code:
			RequestHandler.java			Server.java: contains runnable main			StringVisitor.java
			---------------------
			Visitor.java	     |
			OperationModel.java  |  --> These files are copied from the client side
			Operand.java         |	    in the server side project so the 2 projects
			Operator.java        |      can run on maven independently
			---------------------
		
		6.22 To access it's jar file, go to HW4_Client --> target

		6.3 successfulOperations.txt: it an output text file created for saving the successful 
			calculation received by the Server from client. If it doesn't exist,
			then it's created, otherwise, it's overwritten with next successful 
			calculation received by the server.
		

All the project is packed in maven and you. Just have maven installed on your computer.
To compile/ run the code for each project:
	1. Open Terminal
	2. go to each directory: HW4_Client/HW4_Server
	3. Type the following commands:
		mvn clean
		mvn compile
		mvn package
		mvn exec:java

	This will compile and build the code and would start running.
	
	For the client side, it will show a GUI calculator where you can perform calculations. 
	Every successful calculation is sent to the server and a message is also displayed on
	client side console. For example,

		Sending the successful calculation: 5 - 6 + 3 to the Server...
		Sending the successful calculation: 9 * 4 + 3 - 6 to the Server...

	For server side, for any successful calculation it receives, it displays the total successful calculations, 
	the expression tree and save the successful expression tree received from he client side in
	'successfulOperations.txt' file. For example, the following is displayed on the server console
	
		Total Number of Successful Calculations: 1

		+
		|\
		-  3  
		|\
		5  6  


		Total Number of Successful Calculations: 2

		+
		|\
		-  3  
		|\
		5  6  


		-
		|\
		+  6  
		|\
		*  3  
		|\
		9  4  

	
	and the successfulOperations.txt file will have the current saved operation in form of tree structure.