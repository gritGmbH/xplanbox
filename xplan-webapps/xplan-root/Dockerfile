FROM httpd:2.4.58-alpine

RUN apk add -U tzdata
ENV TZ=Europe/Berlin

COPY target/xplan-root-*-default /usr/local/apache2/htdocs/