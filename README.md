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
- If a user is not an admin, then he can choose and solve quizzes. Each quiz has only one correct answer out of 4 possible options, and each correct answer values 1 point, meaning the maximum score is equal with the number of questions for that particular quiz. If the user has solved the quiz for the first time, its score will be inserted in the database and if the user solves the quiz again, his score for that quiz will be updated. Also, each question has a duration in seconds. For each question, a countdown timer will start, showing the remaining time in which the user is allowed to check an answer.
- If one has the role of an admin, he can view the scores of all other users for each quiz they took and also, he can delete accounts of other users.

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

- In a folder .properties there should be 3 String variables: DBNAME, DBUSER and DBPASSWORD used to connect to the postgres database


## Usage
- Once the application has started, you could create an account using the SignUp button, or you could use your credentials in order to SignIn.
- Depending on the role you have (admin, or user) you will be directed to pages accordingly.
- If you are an admin, you could see a table view with all users and their latest scores for all the quizzes they solved or you could delete users (their accounts).
- If you don't have the admin role, you will have to select the category of the quiz you want to take. Then, you can see the available quizzes for the category you chose and you can choose one of them. After choosing a quiz, you have to click the Start button for that particular quiz in order to begin and see the questions.
 
## Documentation
##### HelloApplication Class
-- This class overrides the strat method and calls the launch() function inside main
#### enums
SCENE_IDENTIFIER
-- This enum is used to provide identifiers for the scenes used in the application.  Each enum constant represents a specific scene along with its corresponding FXML file name as label.
#### utils
##### ApplicationHandler
-- This class provides methods for initializing views, starting the application, closing the application, and changing scenes.
-- views is a map containing pairs for each scene with its identifier and the corresponding Pane
-- The getInstance() method provides a singleton instance of the ApplicationHandler class.
##### DBFunctions
-- This class contains methods for working with the database.
##### Environment
-- This class reads and loads in a Properties object field, environment-specific properties related to the database connection (database name, username, and password) from a .properties file located in the application directory.
##### OuizCategory, QuizData and UserData
-- This classes follow the singleton pattern, ensuring that only one instance of the class exists throughout the application. They are used to pass data between scenes.
##### UserQuizScore
-- This class is used in the TableView with the scores.
-- The TableView will be used to display data of type UserQuizScore in tabular format.
-- The fields are username, quizname and the corresponding score.

#### controllers
##### SceneController
-- All controllers inherit this class and its methods: changeScene() and closeApplication().
##### HelloController
-- This is the controller for welcome-page.fxml.
-- It changes the scenes when the Log In or Sign Up buttons were clicked.
##### SignUpController
-- This is the controller for signup-page.fxml.
-- It initializes the roles array with 'admin' and 'user' for the ChoiceBox.
-- When the submit button is clicked, if all the necessary data was completed for a new account, and if the user doesn't exist already, then the new user will be inserted in the database.
##### SignInController
-- This is the controller for signin-page.fxml.
-- If the user if found in the database after clicking the signin button, the user will be directed to the next scene based on its role (admin or user).
##### AdminAfterSignin
-- This is the controller for admin-after-signin.fxml.
-- anchorPane: AnchorPane container for displaying content.
-- labelContainer: VBox container for displaying user labels and delete buttons.
-- tableView: TableView for displaying user quiz scores.
-- onViewScoresClick(): Handles the action when the view scores button is clicked, retrieves user quiz scores from the database and displays them in the tableView.
-- onViewUsersClick(): Handles the action when the view users button is clicked, retrieves all users from the database and displays them in the labelContainer.
-- handleUserButtonClick(String userName): Handles the action when the delete button next to a user's label is clicked, deletes the corresponding user from the database.
##### UserAfterSignin
-- This is the controller for user-after-signin.fxml.
-- onGetCategoriesClick(): Handles the action when the get categories button is clicked, retrieves quiz categories from the database and displays them as buttons in the vbox.
-- handleCategoryButtonClick(String category): Handles the action when a quiz category button is clicked, sets the selected category using the QuizCategory singleton instance, and navigates the user to the quizzes view.
##### QuizzesViewController
-- This is the controller for quizzes-view.fxml.
-- onGetQuizzesClick(): Handles the action when the get quizzes button is clicked, retrieves quizzes from the database and displays them as buttons in the vbox.
-- handleQuizButtonClick(String quizName): Handles the action when a quiz button is clicked, sets the selected quiz name using the QuizData singleton instance, retrieves the quiz ID from the database, sets it in the QuizData instance, and navigates the user to the quiz view.
##### QuizController
-- This is the controller for quiz.fxml.
-- a1, a2, a3, a4: RadioButtons for displaying answer options.
-- toggleGroup: ToggleGroup for managing RadioButton selection.
-- resultSet: ResultSet containing quiz questions retrieved from the database.
-- timer: Timer for managing the countdown timer during each question.
-- onRadioButtonClicked(): Handles the action when a RadioButton representing an answer option is clicked and sets the selected answer.
-- getNextQuestion(ResultSet resultSet): Retrieves the next question from the database and updates the UI. When there are no more questions, meaning that the quiz attempt is over, the score is inserted or updated in the database and the result is displayed.
-- onNextButtonClick(): Handles the action when the next button is clicked, checks if the selected answer is the correct one, proceeds to the next question and resets the timer.
-- DecrementTask: Inner class representing a TimerTask for decrementing the countdown timer during each question. When the time is up for a question, the method onNextButtonClick() is called, to get to the next question.

#### _Class diagram:_
![Quiz-Game drawio](https://github.com/Luisa-Elena/Quiz-Game/assets/143015197/5c3d7643-19fd-4a3c-a529-1e363c84a771)

#### _Database project:_
![Quiz_Game_DB](https://github.com/Luisa-Elena/Quiz-Game/assets/143015197/60081911-7f88-4fc0-93e6-70b9d916e932)
