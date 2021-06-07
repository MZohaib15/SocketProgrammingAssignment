# Socket Programming Assignment
A Client-Server Application in Java with complete front end and back end implementation.
This Application uses multi-threading to allow multiple clients to interact with a server using sockets. 
Each client can use functionalities like login, signup, change password, or update username.
The server is connected to a MySQL database and is able to handle each client request in simultaneously.
The passwords of each client is encrypted by the server and then stored in the database.

To run the code, install any Java IDE like Eclipse or NetBeans and MySQL. 
To compile the code, clone the repository and open the the project file in IDE.
Since the application is connected to a local database, update the login credentials in the URL of getConnection() method of database in DB_Crud File in order to connect it to your local MySQL.

#### Sign In Interface

<img width="301" alt="Screenshot 2021-06-07 at 10 06 03 PM" src="https://user-images.githubusercontent.com/83071313/121061093-de39e200-c7dc-11eb-8b23-e4a374c90539.png">

#### Sign Up Interface

<img width="301" alt="Screenshot 2021-06-07 at 10 06 11 PM" src="https://user-images.githubusercontent.com/83071313/121061509-5c968400-c7dd-11eb-9fa7-b9ae5d3ea0b2.png">

#### Update Username Interface

<img width="301" alt="Screenshot 2021-06-07 at 10 06 21 PM" src="https://user-images.githubusercontent.com/83071313/121061543-68824600-c7dd-11eb-9d79-81ca9ffe0b56.png">

#### Update Password Interface

<img width="301" alt="Screenshot 2021-06-07 at 10 06 29 PM" src="https://user-images.githubusercontent.com/83071313/121061573-746e0800-c7dd-11eb-8b00-41a9d6c94bed.png">
