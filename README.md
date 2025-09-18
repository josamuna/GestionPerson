# GestionPerson Project

Managing a sample project using 🔗[Jira](https://www.atlassian.com/software/jira) as a tracker from **requirements** 📜 to **software testing** 🛠️.

## 🎯 Keys Points

- ✅ Software feasibility study.
- ✅ Software requirement Analysis.
- ✅ Software design.
- ✅ Software development.
- ✅ Software testing.
- ✅ Tracking the project on Jira.

### 🔔 Sofware feasibility study

> This stage assesses the necessity for the expected software. **Why is it so crucial to develop software?** Because software development is difficult, achieving high-quality software is challenging.

> The answer to this question requires a good understanding of the user's needs. This main question guides the remaining steps.

> In our case, the expected software helps to manage persons' information such as `firstname`, `lastname`, `age`, `email`, and `password` via a console application. The UI can be added later. The App should be able to _`save`_, _`update`_, _`delete`_, _`seach`_ and _`list`_ person.

### 📜 Software requirement Analysis

> This stage formulate functional and non-functional requirement to be cheched before deploying the software and used by client / users.

#### Functional requirements

- The App should add a new person. ✔️
- The App should update the person's information. ✔️
- The App should delete a person. ✔️
- The App should retrieve a person by some criteria (ID or firstname). ✔️
- The App should list all saved persons. ✔️

### Non-functional requirements

- Security: User access is restricted by login and password.
- Efficiency: Common actions (save, update, delete, search, and list) have a response time of less than 2 seconds.
- Maintainability: The source code must be modular, testable, and extensible.
- Portability: Allows the software to run on multiple platforms, such as Windows, Linux, and macOS.

### 🎨 Software design

> The design assists in modeling and producing a few useful diagrams to make the developers' task easier. In a particular way, it reduces the complexity of the software by illustrating how it will function and act. Some UML diagrams are used, including the **Use case diagram** and the **Class diagram**.

#### Use case Diagram

![Image](https://github.com/user-attachments/assets/0f883317-6dc2-4d6c-93db-7361da422c69)

#### Class Diagram

![Image](https://github.com/user-attachments/assets/0d8f166c-19e5-4e39-85d2-a37ef1f662a0)

### 👨🏻🧑🏽‍💻 Software development

> During the software development stage, source code is written in a specific programming language (in this case, [Java](https://www.oracle.com/java/)). Most of the time, this is done with the help of IDEs ([Eclipse](https://www.eclipse.org/downloads/) or [VSCode](https://code.visualstudio.com/) in our case) to improve productivity. These tools format code, refactor it, etc., and provide IntelliSense features.

In this project, the source code has breaking down in _`five java packages`_, which acts as _`modules`_:

- `com.josamuna.project.accessdata`: Contains classes to access the Database, `MySQL PersonDAO` for MySQL, `SQL Server PersonDAO` for SQL Server (Not implemented in this project, but easy to add by implementing the `PersonDAO` interface), etc.
- `com.josamuna.project.application`: Contains the main class (`MainPerson`) to execute the project.
- `com.josamuna.project.model`: Contains all required elements related to the model: `Person` class and `PersonDAO` interface, which acts as the DAO.
- `com.josamuna.project.service`: Contains business logic and access to the model through `PersonService` class.
- `com.josamuna.project.test`: Contains software testing, particularly Unit and Integration testing. System tests are used to test the general functionality of the product, comparable to user acceptance tests, which must match user requirements formulated at an earlier stage.

#### 🛢 MySQL Database Script

```sql
CREATE DATABASE IF NOT EXISTS gestion_person;

USE gestion_person;

CREATE TABLE person(
    id INT AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    age INT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    CONSTRAINT pk_person PRIMARY KEY(id)
);
```

#### Project execution on Eclipse IDE

![Image](https://github.com/user-attachments/assets/fda15c85-6778-4b5b-852d-df4cc00d992a)

### 🛠️ Software testing

> Testing begins when the development phase reaches a certain point or is done (depending on the team and project). This project employs automated testing with [JUnit](https://junit.org). The user guide of **JUnit v5.13.4** is available [on this link](https://docs.junit.org/current/user-guide/junit-user-guide-5.13.4.pdf) is available on this link.

> Before beginning the testing process, a comprehensive **Testing Plan** should be created and followed to ensure that everything works as planned. This was not included in this project, but a [**Test case sheet**](https://github.com/josamuna/GestionPerson/tree/main/docs/TestCaseSheet.xlsx) was produced for the test cases that were completed. Also, the UI is unavailable because everything is done in console mode.

#### Unit testing ✔️

![Image](https://github.com/user-attachments/assets/df540b91-b3d2-404f-92c6-64e121e7ec4d)

#### Integration testing ✔️

![Image](https://github.com/user-attachments/assets/492fb368-e7e4-4385-801b-135c1759ce9e)

### 🎫. Tracking the project on Jira

> Jira is one of the best tools for tracking software projects and collaborating with all team members while increasing efficiency. This can monitor all stages, including _'Requirement'_, _'Design'_, _'Development'_, _'Testing'_, _'Deployment'_, and _'User Feedback'_. For this project, we have only added a few of them, not all of them.

#### Requirement ☑️

![Image](https://github.com/user-attachments/assets/6e07c6b1-ae68-40b8-8914-b1cc63c5439c)

#### Design ☑️

![Image](https://github.com/user-attachments/assets/2076696b-cae1-4273-9d7e-4843a99b3ab8)

#### Devlopement ☑️

![Image](https://github.com/user-attachments/assets/2137ea2a-9a9c-46f6-a88d-4dcfa2ad18dd)

#### Testing ☑️

![Image](https://github.com/user-attachments/assets/3abb0e9e-76ba-4e3f-bd30-97b0e6c63078)

![Image](https://github.com/user-attachments/assets/410e4b16-515e-46ea-84f5-5bed489bec88)

### 💬. Suggestions

> If you have any suggestions or concerns, please make a pull request.
