# Team11

## Running Instructions

1. Create **lib** directory under `Team11/Project/`
2. Please download the libraries at the links below and extract all of them into the lib directory (moving them out of their directories).
3. Download and Install Eclipse for Java Developers.
4. Create a new Java Project with the `\Project\` directory as the project folder
5. Go to **Project** > **Properties** > **Code Build Path** > **Libraries** and add all the **External Jars** in `Project/lib/`.
6. Open `/Project/src/app/java/com/view/ui/createAccountViews/Login.java`.
7. **Run** `Login.java` as **Java Application**

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

Libraries Preview:

![alt text](https://github.com/CSCC01/Team11/blob/master/Dependencies.png)

### Installing MySQL
1. Download and Install MySQL Workbench from https://dev.mysql.com/downloads/workbench/

2. Either using workbench directly. (if prefer using command line, go to 3)
  
  Create a new connection using:
  
  Hostname: mathlab.utsc.utoronto,ca
  
  Port: 3306
  
  Username: linjun9
  
  Password: linjun9
  
  Using the cscc43s18_linjun9 database
 
3. Run the following commands:  
  
  mysql -u linjun9 -h mathlab.utsc.utoronto.ca -p linjun9
  
  use cscc43s18_linjun9;


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


