# Using Liquibase

## Configuration

The liquibase maven plugin reads the JDBC connection settings from a properties file like `liquibase.properties`.

More information about the Liquibase Maven plugin can be found here https://docs.liquibase.com/tools-integrations/maven/home.html

## Create changelog

    mvn clean package -Pgenerate-changelog

## Apply changelog to database

    mvn liquibase:update -Dliquibase.propertyFile=./src/test/resources/liquibase-target.properties -Dliquibase.changeLogFile=./src/main/resources/changelog_schemas.yaml 

## Generate SQL migration script

    mvn liquibase:updateSQL -Dliquibase.propertyFile=./src/test/resources/liquibase-target.properties -Dliquibase.changeLogFile=./src/main/resources/changelog_schemas.yaml

The output file is located in the `target/liquibase` folder

