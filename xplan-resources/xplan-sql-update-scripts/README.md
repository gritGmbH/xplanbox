# Using Liquibase

## Configuration

Detailed information how to install and setup the Liquibase Maven plugin can be found here https://docs.liquibase.com/tools-integrations/maven/home.html

The liquibase maven plugin reads the JDBC connection settings from a properties file.
This module includes two file: 
- `src/main/resources/liquibase.properties` - with the source database connection 
- `src/test/resources/liquibase-target.properties` - this the target or reference database connection

## Create changelog

To create a changelog for the source database configure the JDBC connection in `liquibase.properties` and run: 

    mvn clean package -Pgenerate-changelog

The changelog files are written into the directory `target/generated-resources/`.

> **IMPORTANT**: To put the generated changelog files under source control, move them into the `src/main/resources` folder.

## Apply changelog to database

This step applies an existing changelog to an empty database.

First configure the JDBC connection in the `liquibase-target.properties` and create the database with pgAdmin or psql `CREATE DATABASE <DATABASE_NAME>`. Then run the goal:  

    mvn liquibase:update -Dliquibase.propertyFile=./src/test/resources/liquibase-target.properties -Dliquibase.changeLogFile=./src/main/resources/changelog_v60.yaml 

## Generate SQL migration script

This step generates SQL migration scripts for the given changelog and can be used to migrate an existing database without liquibase.

    mvn liquibase:updateSQL -Dliquibase.propertyFile=./src/test/resources/liquibase-target.properties -Dliquibase.changeLogFile=./src/main/resources/changelog_v60.yaml

The output files are located in the `target/liquibase` folder.

