# Social Media Blog API  

## Project Description  
The **Social Media Blog API** is a backend service for a micro-blogging application. It enables users to register, log in, and manage posts. Users can also view messages posted by all users or filter by specific users. The API handles CRUD operations for accounts and messages, following RESTful principles.

## Technologies Used  
- Java
- Javalin
- MySQL
- Maven
- Postman (for testing)  

## Features  
- **User Registration:** Users can create accounts with a unique username.  
- **User Login:** Validates login with username and password.  
- **Post Messages:** Users can post new messages with timestamps.  
- **View All Messages:** Retrieve all messages from all users.  
- **Retrieve Message by ID:** Fetch a specific message using its ID.  
- **Update Messages:** Users can update their messages.  
- **Delete Messages:** Supports deletion of messages by ID.  
- **View User-Specific Messages:** Retrieve all posts from a particular user.  

### To-Do List:  
- Implement token-based authentication for secure sessions.  
- Add pagination to message retrieval endpoints.  
- Extend functionality for editing user profiles.  

## Getting Started  

### Prerequisites  
- **Java 17** installed  
- **MySQL** installed and running  
- **Maven** installed  

### Installation  
1. Clone the repository:  
   ```bash
   git clone <repository_url>
   cd social-media-blog-api
   ```
2. Setup MySQL database:  
   - Create a database named `social_media_db`.  
   - Run the provided SQL script from `src/main/resources` to set up tables.

3. Update database credentials in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/social_media_db  
   spring.datasource.username=<your_mysql_username>  
   spring.datasource.password=<your_mysql_password>  
   ```

4. Build the project:  
   ```bash
   mvn clean install
   ```

5. Run the application:  
   ```bash
   mvn spring-boot:run
   ```

### API Endpoints  

| Method | Endpoint                           | Description                         |
|--------|------------------------------------|-------------------------------------|
| POST   | `/register`                       | Register a new user                |
| POST   | `/login`                          | User login                         |
| POST   | `/messages`                       | Create a new message               |
| GET    | `/messages`                       | Retrieve all messages              |
| GET    | `/messages/{message_id}`          | Retrieve a specific message by ID  |
| DELETE | `/messages/{message_id}`          | Delete a message by ID             |
| PATCH  | `/messages/{message_id}`          | Update a message by ID             |
| GET    | `/accounts/{account_id}/messages` | Retrieve messages by a user        |

## Usage  
1. Use **Postman** to test API endpoints.  
2. For registration, send a POST request to `/register` with the following JSON:  
   ```json
   {
     "username": "testuser",
     "password": "password123"
   }
   ```
3. For posting a message, use this JSON:  
   ```json
   {
     "posted_by": 1,
     "message_text": "Hello World!"
   }
   ```
4. Retrieve messages using `GET` requests or delete/update messages by their `message_id`.

## Contributors  
This is a solo project by **Peter Marozzi**.

## License  
This project uses the MIT License. See [LICENSE](https://opensource.org/licenses/MIT) for more details.
