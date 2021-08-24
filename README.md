# ticketbooker
A backend server for booking movie tickets

### Software 
The backend server is implemented using Spring boot, Java, Hibernate and MySQL as database. <br><br>

### How it works?
This application is designed to solve the use case when multiple users request to a single seat. The method to decide which user the system prefers runs in a separate thread. It repeats in a configured interval (asyncThreadWaitTimein application.properties). It finds the user with maximum number of tickets and adds him to a result hashtable. If multiple users request for the same tickets and same number of tickets then the user is chosen randomly(It is via the hashing algorithm of Hashtable i.e. the user who is the first record in the hash table) is chosen.<br><br>

### Preconditions:<br>
Ensure that you create a database called ticketbooker prior running this application.<br><br>

### Note:<br>
The spring boot application autocreates the required tables. Also some values are loaded to database by default. I have added the schema.sql just in case if it is required. 

### Software Version
Java                 : 1.8<br>
Mysql Server version : 8.0.26-0

