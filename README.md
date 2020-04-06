# JavaWebApp
Java enterprise app with Servlet and Jsp

## Prerequisite
- Java 1.8
- Tomcat 9
- Maven 3.3.9
- Eclipse IDE
- MySql DB

In MySql
- create schema 'tutorial' hence your connection url should be like `jdbc:mysql://localhost:3306/tutorial`
- update db.properties file with proper userName and password for MySql connection
- use 'schema.sql' to create tables and entries. 

## To run
- Make sure Java and Maven is set in computer classpath
- Clone repositoriy in hard drive
### Option 1
- Import in eclipse as `Existing maven projects`
- Do maven update to sync dependencies
- Add Tomcat as server in eclipse
- Run the application as `Run on server` by selecting Tomcat
### Option 2
- Open command prompt from project root
- Run `mvn clean install`
- copy war file from `target` folder to Tomcat's `webapps` folder
- Start tomcat via `startup.bat` inside bin

## Access application
[http://localhost:8080/JavaWebApp/](http://localhost:8080/JavaWebApp/)
