export LIQUIBASE_HOME=/home/lgvxplanisk/liquibase-3.6.2

#xml
## XPlan 2
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan2 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan2 > changelog_xplan2.xml
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan2pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan2pre > changelog_xplan2pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan2archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan2archive > changelog_xplan2archive.xml

## XPlan 3
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan3 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan3 > changelog_xplan3.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan3pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan3pre > changelog_xplan3pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan3archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan3archive > changelog_xplan3archive.xml

## XPlan 4.0
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan40 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan40 > changelog_xplan40.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan40pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan40pre > changelog_xplan40pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan40archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan40archive > changelog_xplan40archive.xml

## XPlan 4.1
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan41 > changelog_xplan41.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan41pre > changelog_xplan41pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan41archive > changelog_xplan41archive.xml

## XPlan 4.1 NSM
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41nsm diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan41nsm > changelog_xplan41nsm.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41nsmpre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan41nsmpre > changelog_xplan41nsmpre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41nsmarchive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan41nsmarchive > changelog_xplan41nsmarchive.xml

## XPlan 5.0
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan50 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan50 > changelog_xplan50.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan50pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan50pre > changelog_xplan50pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan50archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplan50archive > changelog_xplan50archive.xml

## XPlanSyn
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplansyn diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplansyn > changelog_xplansyn.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplansynpre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplansynpre > changelog_xplansynpre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplansynarchive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplansynarchive > changelog_xplansynarchive.xml

## XPlan Manager
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplanmgr diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/lgvxplanisk210xplan51 --referenceUsername=lgvxplanisk --referencePassword=lgvxplanisk --referenceDefaultSchemaName=xplanmgr > changelog_xplanmgr.xml

## Remove 'Starte Liquibase...' from XML files
find changelog_xplan* -exec sed -i '/^Starte Liquibase/d' {} \;

#sql
## XPlan 2
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan2 --changeLogFile=changelog_xplan2.xml --outputFile=changelog_xplan2.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan2pre --changeLogFile=changelog_xplan2pre.xml --outputFile=changelog_xplan2pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan2archive --changeLogFile=changelog_xplan2archive.xml --outputFile=changelog_xplan2archive.sql updateSQL

## XPlan 3
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan3 --changeLogFile=changelog_xplan3.xml --outputFile=changelog_xplan3.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan3pre --changeLogFile=changelog_xplan3pre.xml --outputFile=changelog_xplan3pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan3archive --changeLogFile=changelog_xplan3archive.xml --outputFile=changelog_xplan3archive.sql updateSQL

## XPlan 4.0
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan40 --changeLogFile=changelog_xplan40.xml --outputFile=changelog_xplan40.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan40pre --changeLogFile=changelog_xplan40pre.xml --outputFile=changelog_xplan40pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan40archive --changeLogFile=changelog_xplan40archive.xml --outputFile=changelog_xplan40archive.sql updateSQL

## XPlan 4.1
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41 --changeLogFile=changelog_xplan41.xml --outputFile=changelog_xplan41.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41pre --changeLogFile=changelog_xplan41pre.xml --outputFile=changelog_xplan41pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41archive --changeLogFile=changelog_xplan41archive.xml --outputFile=changelog_xplan41archive.sql updateSQL

## XPlan 4.1 NSM
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41nsm --changeLogFile=changelog_xplan41nsm.xml --outputFile=changelog_xplan41nsm.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41nsmpre --changeLogFile=changelog_xplan41nsmpre.xml --outputFile=changelog_xplan41nsmpre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan41nsmarchive --changeLogFile=changelog_xplan41nsmarchive.xml --outputFile=changelog_xplan41nsmarchive.sql updateSQL

## XPlan 5.0
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan50 --changeLogFile=changelog_xplan50.xml --outputFile=changelog_xplan50.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan50pre --changeLogFile=changelog_xplan50pre.xml --outputFile=changelog_xplan50pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplan50archive --changeLogFile=changelog_xplan50archive.xml --outputFile=changelog_xplan50archive.sql updateSQL

## XPlanSyn
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplansyn --changeLogFile=changelog_xplansyn.xml --outputFile=changelog_xplansyn.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplansynpre --changeLogFile=changelog_xplansynpre.xml --outputFile=changelog_xplansynpre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplansynarchive --changeLogFile=changelog_xplansynarchive.xml --outputFile=changelog_xplansynarchive.sql updateSQL

## XPlan Manager
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/tmp/xplan-manager-cli-2.10-SNAPSHOT/repo/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5433/lgvxplanisk29 --username=lgvxplanisk --password=lgvxplanisk  --defaultSchemaName=xplanmgr --changeLogFile=changelog_xplanmgr.xml --outputFile=changelog_xplanmgr.sql updateSQL                                                                          