# ticketbooker
A backend server for booking movie tickets

### How it works?
This application is designed to solve the use case when multiple users request to a single seat. The method to decide which user the system prefers runs in a separate thread. It repeats in a configured interval (asyncThreadWaitTimein application.properties). It finds the user with maximum number of tickets and adds him to a result hashtable. If multiple users request for the same tickets and same number of tickets then the user is chosen randomly(It is via the hashing algorithm of Hashtable i.e. the user who is the first record in the hash table) is chosen.<br><br>

### Preconditions:<br>
Ensure that you create a database called ticketbooker prior running this application.<br><br>

### Note:<br>
The spring boot application autocreates the required tables. I have added the schema.sql just in case if it is required. 
