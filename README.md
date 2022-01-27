# 🚀 Coding Challange Workmotion-Middle 🚀

## 🏃👾 To run the Application

First, you may need to configure this:

```
JAVA_HOME=/Library/Java/JavaVirtualMachines/{your jdk 11 here}/Contents/Home/ 

For mac you can install sdkman

Install docker go to : https://www.docker.com

(Don't forget to setup you IDE java version if you use intellij go to file -> project structure -> project -> java version to java 11)

Environtment version i used :
java: 11.0.13-librca
maven: 3.6.3


DB Credential Set up : 
POSTGRES_USER: dimas
POSTGRES_PASSWORD: password
POSTGRES_DB: employee

```

##  🦾 Here are step by step to Start test the application :
```
1.Run 'docker compose up' in directory where docker-compose.yaml file located

2.Set up DB connection with credentials above and run all test

3.Run mvn install -DskipTests in project directory cd workmotion/ (without test)

4.to run springboot app Go to project and cd to target folder then execute application jar : java -jar hrapplication-0.0.1-SNAPSHOT.jar(result build from mvn install above)

5.there are two way to test the endpoints first using postman and using integration test that i have provided 

6.Please note if you found the integration test Error it's because the data that need to be provided before hand 

7.if table not generated try to delete postgre container and run docker compose up again
```

## 🏗 Here are some API you need to know :

```
💰 POST : localhost:8080/employees (end point to save new Employee)

💰 POST Response = success , status = 201 (created)

METHOD BODY : 
{
    "employeeName" : "andy",
    "contractInformation" :"CONTRACT",
    "age":25
}



🙌🏻 GET : localhost:8080/employees (endpoint to fecth employees detail)

🙌🏻 GET Response = "employees": [
                                    {
                                        "employeeId": 27,
                                        "employeeName": "dimas",
                                        "contractInformation": "CONTRACT",
                                        "age": 30,
                                        "state": "IN_CHECK" , 

                                    }
                               ]  
       status = 200 (Ok)



🧜🏻‍♂️ PUT : localhost:8080/edit (To update the state you need to provide employeeId that have been created with POST above)

🧜🏻‍♂️ PUT Response = success , status 200(Ok)

I assume we update the state based on employeeId , and there are many ways to update the state ,would be great if i can have discussion like a team

METHOD BODY  : 

{
    "employeeId":30
}

```


## 🧑‍💻 🤖 About the challenge
```
I see the challange is excellent , design logic to change the statemachine , maintain data , some state validation 🧑‍🔬
I'have applied Domain driven design with Clean Architecture which is sepration of concern is important. Also I have followed TDD
```

🙏🏻 **BTW, a big thank you to the one who created the documentation and guidelines , they were super helpful!!** 🍦


## 🙌🏻 This project using [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

* [Core](/core) Module to store business logic and abstraction to manipulate the data.
* [Persistence](/persistence) Module to implement persistence abstraction given in Core.
* [Service](/service) Main application which orchestrate how components interact to fulfill main objective.
* [Client](/client) request response interaction for REST


## 🧑‍🔬 Why Clean Architecture  :
🦾 to compose design hierarchy so the application will be separated into several modules, enhancing independence,easy to test and modularity. 


### 🏗 Infrastructure

In this project You will:

- **🧮 Unit And Integration test:** all layer covered , with 90 to 100% coverages


- **🧮 Configuration:** all beans creation.


- **🕹 Controllers(MVCP):** one per use case, why? because Easy to test and mock and clean dependency injection.

- *🚑 Postgre:** with employee table has been created 


- **🗂 Docker:** to compose postgre



## 🔁 Feedback 🚀
````
As I mentioned, I see the problem challenging, and I've enjoyed solving and coding it a lot.
these are things that i need to improve if i have more time :

- I'd like to create clientId as far as i know WorkMotion have multi client and i think using clientId separation would help

- In order to maintain state i would like to create more flexibility using yaml config and java generic to validate event and transition

- Since I'm using GitHub maybe it could be helpful for reviewers going to the repo
  instead of opening my .zip submitted.

Thank you!
Kind Regards , Dimas Adriyanto s
```

