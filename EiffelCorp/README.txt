****************************************************************************
Project Eiffel Corp:

*****************************************************************************

Please find all the projects. (including the server in the .zip file)

1/ Unzip the file

2/ You will find 6 projects for: 

* First part of the global project (RMI) :

	1- EiffelCorp_application: A web application providing GUI for the Two RMI servers, it's divided into two platforms: 

		* First platform: ADMIN PLATFORM: An admin platform to manage employees, cars, rents, users, waiting lists, and feedbacks. |==> Login: admin, Password: admin

		* Second platform: EMPLOYEE PLATFORM: An employee platform to rent cars.

	2- Serveur_EiffelCrop: RMI server of EiffelCorp including all the methods to manage Employees. !! RUNNING ON PORT 1100!!

	3- Serveur_IfsCars: RMI server of IfsCars including all methods to manage Cars, Rents, Feedbacks and WaitingList. RUNNING ON PORT 1099!! 

	4- EiffelCorp_client: Just a test project you don't necessarily need it.

* Second part of the global project (WebService) :

	5- IfsCarService: A web site for the second part of the project (the web service part) 
	proposing cars to sell for outside clients.

	6- BankService: A web site for the second part of the project (the web service part) 
	proposing an interface for the bank to create accounts and test the scenario after checkout from (IfsCarService project)



**IMPORTANT**: 
*- Please find under the requirements directory a folder containing the server "tomcat 9.0 version" that you need, to implement the web projects(1, 5, and 6) in it !.
*- Please note that the website projects ( 1, 5, and 6) are maven projects.
*- Please note that the website projects (1, 5, and 6) are running with jdk1.8.
*- Please note that the two RMI Servers are running on ports 1099 and 1100. 

__NOTE__: All the other directories besides the 6 projects and requirement directory are optional: *- Some eclipse files(/.project).
*- Some git files( /.git/* , /.gitignore.txt, /.medadata/*).
*- Some server Deployement files( /bin/* , /conf/* , /lib/* , /logs/* , /servers/* ,
/temp/*, /webapps/*, /work/*, /LICENCE, /NOTICE, /RELEASE-NOTES, /RUNNING.txt).
*- Some pdf explaining the project conception (EiffelCorp_project_conception.pdf,
EiffelCorp_application_conception.pdf, Ifscars_application_conception.pdf).

3/ Open you IDE and open the projects ( 1,2,3,5,6)

4/ Create a server (TOMCAT 9.0) please find the folder under the /requirements directory.

5/ Put projects 1,5,6 in the server that you've created, publish, and start the server. 

6/ Now open your bowser, here are the paths for the different applications:

-http://localhost::xxxx/EiffelCorp_application/ (Project 1) To access admin plateform: Login: admin, Password: admin
-http://localhost::xxxx/IfsCarService/ (Project 5)
-http://localhost::xxxx/BankService/ (Project 6)

* Contact: If you ever encounter any issues, please contact me on EMAIL: Skander.marnissi@esprit.tn.