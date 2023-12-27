# Quiz-Game

#### _A JavaFX quiz-game application_

### Contents of this file
- Description
- Installation
- Usage
- Documentation

## Description

- This is a college project for Object-Oriented Programming and Databases.
- It is a JavaFX application where users can take quizzes from different categories. 
- All quizzes are stored in a PostgreSQL database.
- A user has a name, password and a role assigned.
- If a user is not an admin, then he could choose and solve quizzes. Each quiz has only one correct answer out of 4 possible options, and each correct answer values 1 point, meaning the maximum score is equal with the number of questions for that particular quiz. If the user has solved the quiz for the first time, its score will be inserted in the database and if the user solves the quiz again, his score for that quiz will be updated. Also, each question has a duration in seconds. For each question, a countdown timer will start, showing the remaining time in which the user is allowed to check an answer.
- If one has the role of an admin, he could view the scores of all other users for each quiz they took.

## Installation

Here are the steps that you need to follow to clone this repository and run it with IntelliJ IDEA

1. Install Git:
Make sure you have Git installed on your machine. You can download and install Git from https://git-scm.com/ if you haven't already.

2. Open IntelliJ IDEA:
Open IntelliJ IDEA and ensure you have the Git plugin installed. You can install it from the JetBrains Plugin Repository if it's not already installed.

3. Clone the Repository:
Click on "Get from Version Control" on the welcome screen or go to VCS > Get from Version Control in the menu.
In the "Get from Version Control" dialog, enter the URL of the GitHub repository you want to clone.
Select the directory where you want to clone the repository.

4. Configure JavaFX SDK:
If the JavaFX project has specific JavaFX libraries or SDK dependencies, you need to configure them in IntelliJ IDEA. Follow these steps:

5. Open the project in IntelliJ IDEA.
Go to File > Project Structure.
In the Project Structure dialog, navigate to Project > Project.
Set the Project SDK to a version of Java that is compatible with your JavaFX project.
If JavaFX is not detected automatically, you may need to add it manually. Go to Libraries and add the JavaFX library. Make sure to include the necessary JAR files.

6. Run the JavaFX Application:
Locate the main class of your JavaFX application.
Right-click on the main class file and select Run <YourMainClass>.

## Usage
- Once the application has started, you could create an account using the SignUp button, or you could use your credentials in order to SignIn.
- Depending on the role you have (admin, or user) you will be directed to pages accordingly.
- If you are an admin, you could see a table view with all users and their latest scores for all the quizzes they solved.
- If you don't have the admin role, you will have to select the category of the quiz you want to take. Then, you can see the available quizzes for the category you chose and you can choose one of them. After choosing a quiz, you have to click the Start button for that particular quiz in order to begin and see the questions.
 
## Documentation
#### _Class diagram:_

- In a folder .properties there should be 3 String variables: DBNAME, DBUSER and DBPASSWORD used to connect to the postgres database

#### _Database project:_
![Quiz_Game_DB](https://github.com/Luisa-Elena/Quiz-Game/assets/143015197/60081911-7f88-4fc0-93e6-70b9d916e932)
