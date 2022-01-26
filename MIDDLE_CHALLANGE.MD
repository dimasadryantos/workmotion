WorkMotion (www.workmotion.com) is a global HR platform enabling companies to hire & onboard their employees internationally, at the push of a button. It is our mission to create opportunities for anyone to work from anywhere. As work is becoming even more global and remote, there has never been a bigger chance to build a truly global HR-tech company.

As a part of our backend engineering team, you will be responsible for building our core platform including an employee management system.

The employees on this system are assigned to different states.

The states (State machine) for A given Employee are:

ADDED
IN-CHECK
APPROVED
ACTIVE
Initially when an employee is added it will be assigned "ADDED" state automatically.

The allowed state transitions are:

ADDED -> IN-CHECK <-> APPROVED -> ACTIVE

Our backend stack is:

Java 11
Spring Framework
Your task is to build Restful API doing the following:

An Endpoint to support adding an employee with very basic employee details including (name, contract information, age, you can decide.) With initial state "ADDED" which incidates that the employee isn't active yet.

Another endpoint to change the state of a given employee to any of the states defined above in the state machine respecting the transition rules

An Endpoint to fetch employee details

Please provide a solution with the above features with the following consideration.

Being simply executable with minimum effort. Ideally using Docker and docker-compose or any similar approach
For state management (State machine) you can use any library or data structure you consider appropriate
Some suggestions from our side (these are only suggestions, feel free to use something else if you want):
ENUM with states
Stateless4j library: https://github.com/stateless4j/stateless4j
Spring state machine: https://projects.spring.io/spring-statemachine/
Please provide testing for your solution
Providing an API Contract e.g. OPENAPI spec. is a big plus