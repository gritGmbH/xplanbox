## XPlanDB

### Docker Image

Produces a docker image able to initialize, either for the `xplanbox` database or for the `inspireplu` database.

Building image:

```
docker build -t xplanbox/xplan-db .
```

Running image for the `xplanbox` database:
```
docker run -it --env ... xplanbox/xplan-db
```

Use the docker image xplan-db-updater (maven module xplan-database-scripts) to set up the database with liquibase.

#### Environment variables

Used with `--env`

* XPLAN_DB_NAME: Name of the database to create. Default: xplanbox
* XPLAN_DB_USER: Name of the app user. Default: xplanbox
* XPLAN_DB_PASSWORD: Password of the app user. Default: xplanbox
* XPLAN_DB_INIT_USER: Name of the init user. Default: initxplanbox
* XPLAN_DB_INIT_PASSWORD: Name of the password user. Default: initxplanbox