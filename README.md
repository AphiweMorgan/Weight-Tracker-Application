# Weight-Tracker-Application

#Software installation requirements

--MySQL open-source relational database management system. https://dev.mysql.com/downloads/installer/  
--Java version 11 or Above
--Java Supported IDE

N.B. I used the latest java version for my project and the only other version i tested it on was java 11,so I am not sure if it will run on older
versions.

PROJECT SETUP
1.Once you a have local copy of the project on your Machine,import the project into your preferred integrated development environment (IDE) and open it 

2.After installing MySQL And opening the MySQL workbench create your password that you will use to login to your localhost server and remember it alongside your username.

3. locate the file Name "Weight-Tracking Script.sql" and run that script  as a query.That would then setup your database and  tables for use.

4.There are 3 classes that you should Open in your IDE, Login_S,SignUp and HomePage in which you need to locate the following connection string:
myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/weighttrackingdb", "root", "Aphiwe@2174");

The only modification that you will then need to make to ensure that the connection string is correctly referencing your localhost database
is change the username "root" to YOUR username and change "Aphiwe@2174" to YOUR password.

RUNNING THE PROJECT
1.Now in order to run the Weight Tracker Application you execute the Login_S Class File and then you would then need to sign up then 
be able to perform entries of weight measurements taken at different dates and also have a graphical visualization of each clients weight
trend of the weeks.


