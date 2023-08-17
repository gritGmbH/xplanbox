FROM liquibase/liquibase:4.23

# Install PSQL (probably not really needed) 
USER root
RUN apt-get update -y \
  && apt-get install --no-install-recommends -y postgresql-client \
  && rm -rf /var/lib/apt/lists/*

# Return to liquibase user space
USER liquibase

COPY src/main/resources /liquibase/changelog
