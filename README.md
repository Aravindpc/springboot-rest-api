# Springboot Rest API
 Basic REST API project using Springboot and Maven with MYSQL database.

## How To Run
   This application is embedded with Tomcat 8 so there is no need to install tomcat on local machine.
   
   1. Clone the project into local repository.
   2. Make sure you are having JDK 1.8 and Maven 3.6.3.
   3. You can build the project by running ```mvn install -DskipTests``` by skipping the test cases.
   4. Once installed you can find ```springboot-rest-api-0.0.1-SNAPSHOT.jar``` inside the target folder.
   5. Run the application by ```java -jar springboot-rest-api-0.0.1-SNAPSHOT.jar``` command.
   6. On complete execution you can see something like 
```
2021-06-10 12:55:13.614  INFO 3756 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8095 (http) with context path ''
2021-06-10 12:55:13.618  INFO 3756 --- [           main] c.j.m.SpringBootGraphqlMysqlApplication  : Started SpringBootGraphqlMysqlApplication in 7.68 seconds (JVM running for 8.568)
```

#### Features:
  - REST API allows the devloper to access the data from the server using endpoints.
  - This spring application provide end points for accessing data from MYSQL database ```patientdb1```.
  - The MYSQL database ```patientdb1``` contains two tables as ```employee``` and ```schedule```.
  - Employee and Schedule has bidirectional One-To-Many relationship.
 ##### Main End Points:
 The main end points for the employee and schedule are given as
```
http://localhost:8095/employee
http://localhost:8095/schedule
```

#### End points of Employee

###### Get All employee
 
```
Get method
http://localhost:8095/employee/getall
```
- This end point will return the employees as json 
```
[
    {
        "employeeid": "abc@gmail.com",
        "schedule": [
            {
                "scheduleid": 2,
                "startDate": "09 Jun 2021",
                "endDate": "23 Jun 2021",
                "time": "10:03",
                "duration": 20,
                "isrepeated": false,
                "frequency": null
            }
        ]
    },
    {
        "employeeid": "abcd@gmail.com",
        "schedule": [
            {
                "scheduleid": 1,
                "startDate": "11 Jun 2021",
                "endDate": "22 Jun 2021",
                "time": "10:43",
                "duration": 50,
                "isrepeated": true,
                "frequency": "MONTHLY"
            },
            {
                "scheduleid": 5,
                "startDate": "09 Jun 2021",
                "endDate": "22 Jun 2021",
                "time": "10:53",
                "duration": 20,
                "isrepeated": false,
                "frequency": null
            }
        ]
    }
]
```

###### Get one employee
 
```
Get method
http://localhost:8095/employee/get/abcd@gmail.com
```
- This end point will return the employee who has employee id ```abcd@gmail.com``` 
```
{
    "employeeid": "abcd@gmail.com",
    "schedule": [
        {
            "scheduleid": 1,
            "startDate": "11 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:43",
            "duration": 50,
            "isrepeated": true,
            "frequency": "MONTHLY"
        },
        {
            "scheduleid": 5,
            "startDate": "09 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:53",
            "duration": 20,
            "isrepeated": false,
            "frequency": null
        }
    ]
}
```

###### Create employee
 
```
Post method
http://localhost:8095/employee/create
```
Request Body
```
{
    "employeeid": "abcde@gmail.com",
    "schedule": [
        {
            "scheduleid": 11,
            "startDate": "08 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:34",
            "duration": 20,
            "isrepeated": true,
            "frequency": "WEEKLY"
        },
        {
            "scheduleid": 12,
            "startDate": "08 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:34",
            "duration": 20,
            "isrepeated": true,
            "frequency": "WEEKLY"
        },
        {
            "scheduleid": 13,
            "startDate": "09 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:34",
            "duration": 20,
            "isrepeated": false,
            "frequency": null
        }
    ]
}
```
- This end point will save the employee and schedules given in the request body to employee table and schedule table and maps them accordingly. 
```
{
    "employeeid": "abcde@gmail.com",
    "schedule": [
        {
            "scheduleid": 11,
            "startDate": "08 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:34",
            "duration": 20,
            "isrepeated": true,
            "frequency": "WEEKLY"
        },
        {
            "scheduleid": 12,
            "startDate": "08 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:34",
            "duration": 20,
            "isrepeated": true,
            "frequency": "WEEKLY"
        },
        {
            "scheduleid": 13,
            "startDate": "09 Jun 2021",
            "endDate": "22 Jun 2021",
            "time": "10:34",
            "duration": 20,
            "isrepeated": false,
            "frequency": null
        }
    ]
}
```

###### Update employee
```
Put method
http://localhost:8095/employee/update/abc@gmail.com
```
Request Body 
```
{
    "employeeid": "abc@gmail.com",
    "schedule": [
        {
            "scheduleid": 1,
            "startDate": "22 Jun 2021",
            "endDate": "23 Jun 2021",
            "time": "11:13",
            "duration": 20,
            "isrepeated": true,
            "frequency": "DAILY"
        },
        {
            "scheduleid": 2,
            "startDate": "20 Jun 2021",
            "endDate": "23 Jun 2021",
            "time": "11:13",
            "duration": 20,
            "isrepeated": true,
            "frequency": "DAILY"
        }
    ]
}
```
This end point is capable to modify schedule of the employee with employee id ```abc@gmail.com```.
Response message will be updated entity
```
{
    "employeeid": "abc@gmail.com",
    "schedule": [
        {
            "scheduleid": 1,
            "startDate": "22 Jun 2021",
            "endDate": "23 Jun 2021",
            "time": "11:13",
            "duration": 20,
            "isrepeated": true,
            "frequency": "DAILY"
        },
        {
            "scheduleid": 2,
            "startDate": "20 Jun 2021",
            "endDate": "23 Jun 2021",
            "time": "11:13",
            "duration": 20,
            "isrepeated": true,
            "frequency": "DAILY"
        }
    ]
}
```

###### Delete employee
```
Delete method
http://localhost:8095/employee/delete/abcd@gmail.com
```
This method deletes employee and the schedules associated with it.

On succesful deletion the delete method returns true.
```
true
```

###### Cancel schedules of an employee
```
Delete method
http://localhost:8095/employee/cancelschedules/abc@gmail.com
```
This method deletes only the schedules associated with the employee with id ```abc@gmail.com```.

On succesful deletion the delete method returns true.
```
true
```
There are similar methods for the schedule.


#### End points of Schedule

###### Get All schedule
 
```
Get method
http://localhost:8095/schedule/getall
```
- This end point will return all the schedules as json 
```
[
    {
        "scheduleid": 1,
        "startDate": "10 Jun 2021",
        "endDate": "15 Jun 2021",
        "time": "09:46",
        "duration": 20,
        "isrepeated": false,
        "frequency": null,
        "employee": {
            "employeeid": "abcdef@gmail.com"
        }
    },
    {
        "scheduleid": 2,
        "startDate": "10 Jun 2021",
        "endDate": "15 Jun 2021",
        "time": "09:46",
        "duration": 20,
        "isrepeated": false,
        "frequency": null,
        "employee": {
            "employeeid": "abcdef@gmail.com"
        }
    }
]
```

###### Get one schedule
 
```
Get method
http://localhost:8095/schedule/get/1
```
- This end point will return the employee who has schedule id as ```1``` 
```
{
    "scheduleid": 1,
    "startDate": "10 Jun 2021",
    "endDate": "15 Jun 2021",
    "time": "09:46",
    "duration": 20,
    "isrepeated": false,
    "frequency": null,
    "employee": {
        "employeeid": "abcdef@gmail.com"
    }
}
```

###### Create schedule for existing employee
 
```
Post method
http://localhost:8095/schedule/create
```
Request Body
```
{
    "scheduleid": 3,
    "startDate": "02 Jun 2021",
    "endDate": "15 Jun 2021",
    "time": "09:55",
    "duration": 20,
    "isrepeated": true,
    "frequency": "WEEKLY",
    "employee": {
        "employeeid": "abcdef@gmail.com"
    }
}
```
- This end point will save the schedule given in the request body to schedule table and maps it with employee accordingly. 
```
{
    "scheduleid":3,
    "startDate": "02 Jun 2021",
    "endDate": "15 Jun 2021",
    "time": "09:55",
    "duration": 20,
    "isrepeated": true,
    "frequency": "WEEKLY",
    "employee": {
        "employeeid": "abcdef@gmail.com"
    }
}
```

###### Update schedule
```
Put method
http://localhost:8095/schedule/update/1
```
Request Body 
```
{
    "scheduleid": 1,
    "startDate": "01 May 2021",
    "endDate": "25 May 2021",
    "time": "09:55",
    "duration": 20,
    "isrepeated": false,
    "frequency": null,
    "employee": {
        "employeeid": "abcdef@gmail.com"
    }
}
```
Response message will be updated entity
```
{
    "scheduleid": 1,
    "startDate": "01 May 2021",
    "endDate": "25 May 2021",
    "time": "09:55",
    "duration": 20,
    "isrepeated": false,
    "frequency": null,
    "employee": {
        "employeeid": "abcdef@gmail.com"
    }
}
```

###### Delete schedule
```
Delete method
http://localhost:8095/schedule/delete/1
```
This method deletes the schedule.

On succesful deletion the delete method returns true.
```
true
```
