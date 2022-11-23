# Using Liquibase

## Configuration

Detailed information how to install and setup the Liquibase Maven plugin can be found here https://docs.liquibase.com/tools-integrations/maven/home.html

The liquibase maven plugin reads the JDBC connection settings from a properties file.
This module includes two files: 
- `src/main/resources/liquibase.properties` - with the source database connection 
- `src/test/resources/liquibase-target.properties` - with the target or reference database connection

## Create changelog

To create a changelog for the source database configure the JDBC connection in `liquibase.properties` and run: 

    mvn clean package -Pgenerate-changelog

The changelog files are written into the directory `target/generated-resources/`.

> **IMPORTANT**: To put the generated changelog files under source control, move them into the `src/main/resources` folder.

## Apply changelog to database

This step applies an existing changelog to an empty database.

First configure the JDBC connection in the `liquibase-target.properties` and create the database with pgAdmin or psql `CREATE DATABASE <DATABASE_NAME>`. Then run the goal:  

    mvn clean package
    mvn liquibase:update -Dliquibase.propertyFile=./target/test-classes/liquibase-target.properties -Dliquibase.changeLogFile=./target/classes/changelog_xplandb.yaml 

## Generate SQL migration script

This step generates SQL migration scripts for the given changelog and can be used to migrate an existing database without liquibase.

    mvn clean package
    mvn liquibase:updateSQL -Dliquibase.propertyFile=./target/test-classes/liquibase-target.properties -Dliquibase.changeLogFile=./target/classes/changelog_xplandb.yaml 

The output files are located in the `target/liquibase` folder.

## Create diff changelog

To create a changelog with a diff between different database versions configure the target and reference JDBC connection in `liquibase-diff.properties` and run:

    mvn clean package -Pdiff

The changelog files are written into the directory `target/generated-resources/`.

## Workarounds

### Missing Functions and Triggers

Functions and Triggers are ignore by liquibase (free, s. https://forum.liquibase.org/t/updating-triggers-and-functions/7370). Functions and triggers must be added manually to the changelog or sql.  

### Wrong index method 

Currently liquibase generates the wrong [index method](https://www.postgresql.org/docs/13/sql-createindex.html) for spatial objects, see issue https://github.com/liquibase/liquibase/issues/1134.
As long as this issue is not fixed the method for creating the index has to be done by SQL:

```yaml
    changes:
#     - createIndex:
#         columns:
#         - column:
#             name: xplan_position
#         indexName: spidx_xplan_so_linienobjekt_xplan_position
#         schemaName: xplansyn
#         tableName: xplan_so_linienobjekt
      - sql: CREATE INDEX IF NOT EXISTS spidx_xplan_so_linienobjekt_xplan_position ON xplansyn.xplan_so_linienobjekt USING gist (xplan_position);
```

To apply this to the changelog by keeping the changeSet ID the following scripts can be used. In addition, some the indices for the columns xplan_geltungsbereich, xplan_raeumlichergeltungsbereich, xplan_raeumlichergeltungsbereich, xplan_geltungsbereichaenderung must be fixed manually.

For `changelog_xplansyn.yaml`:

```shell
sed -i -e 's/^    - createIndex:/# &/' changelog_xplansyn.yaml
sed -i -e '/^#     - createIndex:/{ n; s/.*/# &/g }' changelog_xplansyn.yaml
sed -i -e '/^#     - createIndex:/{ n; n; s/.*/# &/g }' changelog_xplansyn.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; s/.*/# &/g }' changelog_xplansyn.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; s/.*/# &/g }' changelog_xplansyn.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; n; s/.*/# &/g }' changelog_xplansyn.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; n; n; s/.*/# &/g }' changelog_xplansyn.yaml
sed -i -e '/^#         tableName:/{ p; s/^#         tableName: /      - sql: CREATE INDEX IF NOT EXISTS spidx_/g }' changelog_xplansyn.yaml
sed -i -e '/^      - sql: CREATE INDEX IF NOT EXISTS spidx_.*/s/.*\(xplan_.*\)/&_xplan_position ON xplansyn.\1 USING gist (xplan_position);/g' changelog_xplansyn.yaml
```

For `changelog_xplansynarchive.yaml`:

```shell
sed -i -e 's/^    - createIndex:/# &/' changelog_xplansynarchive.yaml
sed -i -e '/^#     - createIndex:/{ n; s/.*/# &/g }' changelog_xplansynarchive.yaml
sed -i -e '/^#     - createIndex:/{ n; n; s/.*/# &/g }' changelog_xplansynarchive.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; s/.*/# &/g }' changelog_xplansynarchive.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; s/.*/# &/g }' changelog_xplansynarchive.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; n; s/.*/# &/g }' changelog_xplansynarchive.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; n; n; s/.*/# &/g }' changelog_xplansynarchive.yaml
sed -i -e '/^#         tableName:/{ p; s/^#         tableName: /      - sql: CREATE INDEX IF NOT EXISTS spidx_/g }' changelog_xplansynarchive.yaml
sed -i -e '/^      - sql: CREATE INDEX IF NOT EXISTS spidx_.*/s/.*\(xplan_.*\)/&_xplan_position ON xplansynarchive.\1 USING gist (xplan_position);/g' changelog_xplansynarchive.yaml
```

For `changelog_xplansynpre.yaml`:

```shell
sed -i -e 's/^    - createIndex:/# &/' changelog_xplansynpre.yaml
sed -i -e '/^#     - createIndex:/{ n; s/.*/# &/g }' changelog_xplansynpre.yaml
sed -i -e '/^#     - createIndex:/{ n; n; s/.*/# &/g }' changelog_xplansynpre.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; s/.*/# &/g }' changelog_xplansynpre.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; s/.*/# &/g }' changelog_xplansynpre.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; n; s/.*/# &/g }' changelog_xplansynpre.yaml
sed -i -e '/^#     - createIndex:/{ n; n; n; n; n; n; s/.*/# &/g }' changelog_xplansynpre.yaml
sed -i -e '/^#         tableName:/{ p; s/^#         tableName: /      - sql: CREATE INDEX IF NOT EXISTS spidx_/g }' changelog_xplansynpre.yaml
sed -i -e '/^      - sql: CREATE INDEX IF NOT EXISTS spidx_.*/s/.*\(xplan_.*\)/&_xplan_position ON xplansynpre.\1 USING gist (xplan_position);/g' changelog_xplansynpre.yaml
```