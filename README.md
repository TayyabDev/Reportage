# Team11: Ctrl-Alt-Elite
Members: Leo Chan, Jun Lin, Vishwa Patel, Tayyab Waqar, Angela Zavaketa Bernuy

## Running Instructions

1. Create **lib** directory under `Team11\Project\`
2. Please download the libraries at the links below and extract all of them into the lib directory (moving them out of their directories).
3. Download and Install Eclipse for Java Developers.
4. Create a new Java Project with the `\Project\` directory as the project folder
5. Go to **Project** > **Properties** > **Code Build Path** > **Libraries** and add all the **External Jars** in `Project\lib\`.
6. Open `\Project\src\app\java\com\view\ui\createAccountViews\Run.java`.
7. **Run** `Run.java` as **Java Application**
8. Login to the application using Username: `root` and Password `root`.

### Required Libraries

Apache POI - https://poi.apache.org/download.html#POI-4.0.0
- download the latest binary distribution

Apache Commons Compress - https://commons.apache.org/proper/commons-compress/download_compress.cgi
- download the latest binaries

MYSQL Connector Java - https://dev.mysql.com/downloads/connector/j/8.0.html
- select 'Platform Independent' under 'Select Operating System'
- press 'Download' next to 'Platform Independent (Architecture Independent), Compressed TAR Archive'
- press 'No, just start my download'

JCalendar - https://toedter.com/jcalendar/
- download latest version

Python - https://www.python.org/downloads/
- download Python 3.7.1 and when installing, at the end click Add Python to PATH

MatPlotLib - https://matplotlib.org/users/installing.html
- issue the following commands into Terminal (for Mac) or Command Prompt (for Windows): 
    - python -m pip install -U pip
    - python -m pip install -U matplotlib

Libraries Preview:

![alt text](https://github.com/CSCC01/Team11/blob/master/Dependencies.png)

### Installing MySQL
Download and Install MySQL Workbench from https://dev.mysql.com/downloads/workbench/
#### Using our database
1. Either using workbench directly. (if prefer using command line, go to 2)
  - Create a new connection using:
  - Hostname: mathlab.utsc.utoronto.ca
  - Port: 3306
  - Username: linjun9
  - Password: linjun9
  - Using the cscc43s18_linjun9 database
2. Run the following commands:  
  - mysql -u linjun9 -h mathlab.utsc.utoronto.ca -plinjun9
  - use cscc43s18_linjun9;
#### Using your own database
1. Create a new local database using:
  - Hostname: 127.0.0.1
  - Port: 3306
  - Username: root
  - Password: 
2. Open the *create.sql* script in the `\Project\create.sql` directory in MySQL Workbench
3. Modify the first 2 lines to run *create schema cscc01* and *use cscc01
4. Run the script
5. This is initialize the local database to be ready for the application.
6. Open the java source file *ConnectDatabase.java* under `/Project/src/app/java/com/model/database/api/` and change CONNECTION_URL, USERNAME, and PASSWORD accordingly.

### Running Unit Tests

The JUnit 5 and hamcrest libraries must be in the build path of the project:
1. Go to **Project** > **Properties** > **Java Build Path** > **Add Library**
2. Select **JUnit** > **JUnit 5** > **Finish**
3. The location of the Eclipse project must be the **Project** directory, and the source folder must be **Project\src**.
4. This must be performed for the unit tests to run as their relative directory to the root of the project matters.


## Deliverable 5 - November 26th, 2018

The Project Code is located in the `\Project\` directory in the master branch.

Automated Unit Tests and Integration Tests are located in the `\Project\src\test\java\com\`directory in the master branch.

The Product Backlog and the User Acceptance Tests are located in the `\Deliverable5\` directory in the master branch.

Code Review Strategy and Summary Documents are located in the `\Deliverable5\Code Review\` directory in the master branch.

The Sprint Backlogs are located in the `\Deliverable5\Sprint Backlogs\` directory in the master branch.

The Project Presentation Demo Video is located at: https://www.youtube.com/watch?v=egHQNFicY7M&feature=youtu.be

### New Features Released:

1. Added multi-sheet feature to allow users to upload Excel files with multiple sheets and select which sheet they want to upload as a template.
2. Added ability to create and view a trend report regarding the Immigration trends based on the information submitted by various agencies for new clients.
3. Added permissions for different accounts to differentiate between TEQ and third-party agencies.
4. Implemented registration feature to allow new accounts created by TEQ to be able to register on the first login with the application.
5. Introduced the editing feature to the database while viewing the user data of the templates from the database for TEQ staff.
6. Implemented feature to handle conflicts during uploading template such as DuplicateKeyException and InvalidException and allow users to resolve errors that cannot be automatically fixed by the system.
7. New Templates can be created using an Excel file where users can choose the columns that can uniquely identify rows in the template.
8. Users can search for an specific account to see the details including account type and if it is register.

#### Using your own database
2. Create a new local database using:
  
  - Hostname: 127.0.0.1
  - Port: 3306
  - Username: root
  - Password: 
 
3. Open the *create.sql* script in the `\Project\create.sql` directory in MySQL Workbench, modify the first 2 lines to run *create schema cscc01* and *use cscc01*, then run the script. This is initialize the local database to be ready for the application.

4. Open the java source file *ConnectDatabase.java* under `/Project/src/app/java/com/model/database/api/` and change CONNECTION_URL, USERNAME, and PASSWORD accordingly.

## Deliverable 5 - November 26th, 2018

The Project Code is located in the `\Project\` directory in the master branch.

Automated Unit Tests and Integration Tests are located in the `\Project\src\test\java\com\`directory in the master branch.

The Product Backlog and the User Acceptance Tests are located in the `\Deliverable5\` directory in the master branch.

Code Review Strategy and Summary Documents are located in the `\Deliverable5\Code Review\` directory in the master branch.

The Sprint Backlogs are located in the `\Deliverable5\Sprint Backlogs\` directory in the master branch.

### New Features Released :



## Deliverable 4 - November 12th, 2018

The Project Code is located in the `\Project\` directory in the master branch.

Automated Unit Tests and Integration Tests are located in the `\Project\src\test\java\com\`directory in the master branch.

The Sprint Backlogs and the User Acceptance Tests are located in the `\Deliverable4\` directory in the master branch. The Product Backlog is unchanged.

Code Review Strategy and Summary Documents and Debrief Meeting Link in the `\Deliverable4\CodeReview\` directory in the master branch.

### New Features Released :

1. Able to upload CSV file to create a new template and upload user data.

2. Created accounts with with different logins. Able to create new accounts for different user types

3. Select a template to view to user data to manually check errors in the user data.

4. Generate Custom Reports into a CSV files by running an SQL select query.

5. Custom Reports can now be generated using an SQL query and the report with be stored in the user’s  directory. Default locations are **Windows**: `C:\Users\[user_name]\Desktop\`, **macOS**: `Users\[user_name]\Desktop\`, **Linux**: `home\[user_name]\Desktop\`

6. Custom Reports can now also be generated using the application where users can select options.


## Deliverable 3 - October 29th, 2018

The Project Code is located in the \Project\ directory in the master branch.

The Sprint Backlogs and the Product Backlog are located in the \Deliverable3\ directory in the master branch.

### New Features Released :

1. User can select an Excel file and create a template in the database.

2. User can input a raw SQL query command which can be run directly into the database.


## Deliverable 2 - October 15th, 2018

The Personas and User Stories are located in the \Deliverable2\ directory in the master branch.


## Deliverable 1 - October 1st, 2018

The Team Report and Team Agreement are located in the \Deliverable1\ directory in the master branch.


