# To-Do App (Grails 2.5.6)

This is a simple To-Do application built with Grails 2.5.6 that demonstrates basic CRUD (Create, Read, Update, Delete) operations. The app is connected to a Microsoft SQL Server (MSSQL) database using the JTDS driver.

## Features
- Create, view, update, and delete tasks
- RESTful routing for `Task` CRUD operations
- MSSQL database integration

## Requirements
- Grails 2.5.6
- Microsoft SQL Server
- IntelliJ IDEA (or any other Grails-compatible IDE)
- JTDS driver for MSSQL

## Setup and Configuration

### 1. Create Grails Application

First, create a new Grails application:

```bash
grails create-app to_do_app
cd to_do_app
```

### 2. Add Database Configuration

In `grails-app/conf/DataSource.groovy`, configure the MSSQL database connection as follows:

```groovy
dataSource {
    pooled = true
    driverClassName = "net.sourceforge.jtds.jdbc.Driver"
    username = "sa"
    password = "Sem2023*"
    url = "jdbc:jtds:sqlserver://192.168.1.48:31433/to_do"
}

environments {w
    development {
        dataSource {
            dbCreate = "update" // Options: 'create', 'update', 'create-drop', 'validate'
        }
    }
    test {
        dataSource {
            dbCreate = "update"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            pooled = true
            properties {
                maxActive = -1
                minEvictableIdleTimeMillis = 1800000
                timeBetweenEvictionRunsMillis = 1800000
                numTestsPerEvictionRun = 3
                testOnBorrow = true
                testWhileIdle = true
                testOnReturn = false
                validationQuery = "SELECT 1"
            }
        }
    }
}
```

### 3. Generate Task Domain Class and CRUD

Create the `Task` domain class:

```bash
grails create-domain-class com.app.Task
```

Define the `Task` attributes in the `grails-app/domain/com/app/Task.groovy` file:

```groovy
package com.app

class Task {
String title
String description
Boolean completed

    static constraints = {
        title blank: false, maxSize: 255
        description nullable: true
        completed defaultValue: false
    }
}
```

Then generate the views and controller for the `Task` domain class:

```bash
grails generate-all com.app.Task
```

### 4. URL Mappings

In `grails-app/conf/UrlMappings.groovy`, define the URL mappings to map the root to the `Task` controller:

```groovy
class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: "task", action: "index")
        "500"(view: '/error')
    }
}
```

### 5. Running the Application

Run the application:

```bash
grails run-app
```

Now, you can access the To-Do app at `http://localhost:8080/to_do_app`.
