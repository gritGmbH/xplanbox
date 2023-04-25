FROM alpine:3.16.2

RUN apk --no-cache add bash curl unzip
# TODO: update

RUN mkdir -p /xplan-volume-init
ADD target/xplan-docker-volume-init-*.tgz /xplan-volume-init/
ADD setupVolumes.sh /xplan-volume-init/

COPY src/main/resources/xplan-inspireplu-workspaces/webapps.properties /xplan-volume-init/xplan-inspirepluvalidator-workspaces/
COPY src/main/resources/xplan-validator-workspaces/webapps.properties /xplan-volume-init/xplan-validator-workspaces/
COPY src/main/resources/xplan-workspaces/webapps.properties /xplan-volume-init/xplan-workspaces/

CMD ["/xplan-volume-init/setupVolumes.sh"]
