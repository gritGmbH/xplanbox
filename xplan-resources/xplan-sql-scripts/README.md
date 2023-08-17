## SoapUI TestSuites


### Docker Image

Produces a docker image able to initialize, either for the `xplanbox` database or for the `inspireplu` database.

Building image:

```
docker build -t xplanbox/xplan-db .
```

Running image for the `xplanbox` database:
```
docker run -it xplanbox/xplan-db
```

Running image for the `inspireplu` database:
```
docker run -it --env DB_NAME=inspireplu xplanbox/xplan-db
```
