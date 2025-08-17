# Gym Management System

## User Documentation

### Overview of the Application

**Gym Management System** is a console-based Java application that allows gym staff, trainers, and members to manage gym operations efficiently. The system provides:

- **User registration and login** 
with role-based access (Admin, Trainer, Member)

- **Membership management** — purchase, view, and track revenue

**Workout class management** 
— trainers can create/update/delete classes; members can browse

**Gym merchandise management** 
— view, add, update, and delete gym products

- **Logging** 
— all important actions are recorded in a log file for security and auditing  

**PostgreSQL** for storing user, membership, class, and merchandise data, and **BCrypt** for password hashing.

---

### Explanation of All Classes and Their Interactions

1. **`Main`**
   - Entry point of the application.
   - Handles user input and menus.
   - Calls service classes based on user role.

2. **Models (`service.models`)**
   - `User` — base class for all users.
   - `Admin`, `Trainer`, `Member` — extend `User`, define role-specific actions.
   - `WorkoutClass` — stores class ID, type, description, and trainer ID.
   - `Membership` — stores membership details and member association.
   - `GymMerch` — stores merchandise details (name, type, price, quantity).

3. **Data Access Objects (`service.dao`)**
   - `UserDAO` — handles CRUD operations for users.
   - `WorkoutClassDAO` — handles CRUD for workout classes.
   - `MembershipDAO` — handles CRUD for memberships.
   - `GymMerchDAO` — handles CRUD for gym merchandise.

4. **Services (`service.services`)**
   - `UserService` — registration, login, password hashing, validation.
   - `WorkoutClassService` — view/add/update/delete workout classes.
   - `MembershipService` — view/add purchase memberships.
   - `GymMerchService` — view/add/update/delete merchandise.

5. **Utilities (`service.utils`)**
   - `DBConnection` — manages database connection.
   - `LoggerUtil` — logs messages and errors to a text file.

**Interaction Summary:**

- `Main` → handles menus and calls → `Service classes`  
- `Service classes` → call → `DAO classes` → perform database operations → return results  
- `LoggerUtil` logs all key actions  
- `DBConnection` manages all PostgreSQL connectivity  

#### Explanation of Key folders ####. 


dao/ – Handles all database operations (Create, Read, Update, Delete).

models/ – Represents the entities in your system (Users, Memberships, Classes, Merchandise).

services/ – Contains the business logic and orchestrates between DAO and Main UI.

utils/ – Utility/helper classes such as logging and database connections.

Main.java – Entry point of the program; manages user interface and role-based navigation.

##### BUILD PROCESS #### 

Dependency Management
Maven was used to handle all external libraries required by the project.
Key dependencies included:
PostgreSQL JDBC Driver for database connectivity.
BCrypt for password hashing and secure storage.
Optionally, JUnit for unit testing.
All dependencies are declared in the pom.xml file, which ensures they are automatically downloaded and included in the project’s build path. This avoids manual JAR management.

###### How To Setup ######

Install PostgreSQL 

Create a database for the project:

- Open psql or use a GUI like pgAdmin.

** Running The Project** 

clone github - https://github.com/AidanWKeyin/final-java-copy2

make sure maven is installed. 

bash command- mvn clean install

run the main class with maven - mvn exec:java -Dexec.mainClass="service.Main"

###### Contributions #######
- solo 

- Challenges faced. Postgres being unable to install on my laptop. i also struggled with actually tying together the RBAC system in a clean way. couldnt get admin, trainer and member to have access to what was only specified for them. 

