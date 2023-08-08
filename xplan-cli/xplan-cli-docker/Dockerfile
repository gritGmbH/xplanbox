ARG XPLANBOX_VERSION=latest
ARG XPLANBOX_IMAGE_NAME_PREFIX=xplanbox

FROM ${XPLANBOX_IMAGE_NAME_PREFIX}/xplan-docker-volume-init:$XPLANBOX_VERSION as volumeInitBuilder 


FROM eclipse-temurin:11.0.20_8-jdk-alpine

RUN apk --no-cache add bash

COPY maven/xplan-manager-cli-* /xplanbox/xplan-manager-cli/
COPY maven/xplan-transform-cli-* /xplanbox/xplan-transform-cli/
COPY maven/xplan-update-data-* /xplanbox/xplan-update-data/

RUN mkdir /xplanbox/xplan-manager-cli/logs \
	&& mkdir /xplanbox/xplan-transform-cli/logs \
	&& mkdir /xplanbox/xplan-update-data/logs \
	&& chmod a+xw /xplanbox/xplan-manager-cli/logs /xplanbox/xplan-transform-cli/logs /xplanbox/xplan-update-data/logs

COPY --from=volumeInitBuilder /xplan-volume-init/ ./xplan-volume-init
COPY resynthesize.sh /xplanbox/

ENTRYPOINT ["/bin/bash"]
CMD ["/xplanbox/resynthesize.sh"]
