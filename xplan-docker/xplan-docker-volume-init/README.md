# xplan-docker-volume-init

Builds a docker image able to initialize the volumes used by the other components.

## Local testing

Build docker image

```
mvn install -Pdocker
```

Create a local folder and run image with this folder as target folder for the initialization.
 
```
mkdir -p target/test-volume

docker run --env XPLANBOX_VOLUMES=/foo --mount type=bind,source="$(pwd)"/target/test-volume,target=/foo -it xplanbox/xplan-docker-volume-init
```

Directory `target/test-volume` will be initialized with the content for the XPlan volumes.


