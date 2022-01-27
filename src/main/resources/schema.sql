CREATE TABLE employee
(
    employee_id          serial PRIMARY KEY,
    employee_name        VARCHAR(150),
    contract_information varchar(150),
    age                  integer,
    state varchar (100)
);