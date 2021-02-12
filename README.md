# Web-service-Project

A distributed architecture project done with JEE, RMI and wsdl webservices.

The company EiffelCorp has just acquired the company IfCars, a company that is specialized in car rental and by doing that, they aim to provide their employees with that service at a preferential price and to reach that objective, a platform will be needed.

That platform will revolve around managing car rental operations and organizing them and therefore, enhance EiffelCorp’s vehicle base and allow them to sell them to the outside world.

It will consist in an E-commerce website based on JAVA and the use of Web Services that would:

● Allow Employees to rent cars for a specific duration of time.

● Allow Employees to rate and leave a note based on their appreciation after returning the car.

● Generate a Waiting List for every car that is requested for rent but already rented so when the car is available, the Employee will be notified and will be able to rent the car based on the "first come, first served" principle.

● Allow outside Customers to be able to access a catalog of previously rented cars.

● Show all car prices in Euros.

● Allow outside Customers to be able to buy previously rented cars.

● Accept all currencies for payment through conversion.

*Note: For more information go check the Project_Report.pdf file.*

## Installation

Open terminal and type the following commands: 

```bash
git clone https://github.com/SkanderMarnissi/Web-service-Project/
```
After downloading:

```bash
cd Web-service-Project

```
Then follow the steps:

### Step 1: Install JEE IDLE(eclipse, netbeans...).

### Step 2: Install Tomcat 9.0 server.

### Step 3: Install all the dependencies located in the Pom.xml file.

**Note:You can just run the maven Pom for step 3 under each project directory.**


## You will find six projects for: 

### First part of the global project (RMI) :

#### 1- EiffelCorp_application: A web application providing GUI for the Two RMI servers, it's divided into two platforms: 

##### First platform: 
ADMIN PLATFORM: An admin platform to manage employees, cars, rents, users, waiting lists, and feedbacks. 
Login: admin, Password: admin.

##### Second platform:
EMPLOYEE PLATFORM: An employee platform to rent cars.

#### 2- Serveur_EiffelCrop: 
RMI server of EiffelCorp including all the methods to manage Employees. 
**Note: The server is running on port 1100!.**

#### 3- Serveur_IfsCars: 
RMI server of IfsCars including all methods to manage Cars, Rents, Feedbacks and WaitingList.
**Note: The server is running on port 1099!.**

#### 4- EiffelCorp_client: 
Just a test project you don't necessarily need it.

### Second part of the global project (WebService) :

#### 5- IfsCarService: 
A web site for the second part of the project (the web service part) proposing cars to sell for outside clients.

#### 6- BankService:
A web site for the second part of the project (the web service part) proposing an interface for the bank to create accounts and test the scenario after checkout from (IfsCarService project)

### IMPORTANT: 

**- Please find under the requirements directory a folder containing the server "tomcat 9.0 version" that you need, to implement the web projects(1, 5, and 6) in it !.**
**- Please note that the website projects (1, 5, and 6) are maven projects.**
**- Please note that the website projects (1, 5, and 6) are running with jdk1.8.**
**- Please note that the two RMI Servers are running on ports 1099 and 1100.** 


**Note: Some pdf explaining the project conception(EiffelCorp_project_conception.pdf,
EiffelCorp_application_conception.pdf, Ifscars_application_conception.pdf).**

## Usage: 

### Step 1: Open you IDEL and open the projects. ( 1,2,3,5,6)

### Step 2: Create a server (TOMCAT 9.0).

### Step 3: Put projects 1,5,6 in the server that you've created, publish, and start the server. 

### Step 4: Now open your bowser, here are the paths for the different applications:

#### Project 1:

On http://localhost::xxxx/EiffelCorp_application/
To access admin plateform: Login: admin, Password: admin

#### Project 5:

On http://localhost::xxxx/IfsCarService/

#### Project 6:
On http://localhost::xxxx/BankService/ (Project 6)

**Note: Replace xxxx with the port number that you have choosen.**

*Contact: If you ever encounter any issues, please contact me on EMAIL: Skander.marnissi@esprit.tn.*
*SKANDER MARNISSI COPYRIGHT © 2020 - ALL RIGHTS RESERVED*