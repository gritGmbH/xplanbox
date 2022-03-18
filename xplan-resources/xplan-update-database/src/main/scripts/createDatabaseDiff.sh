###
# #%L
# xplan-update-database - update of database
# %%
# Copyright (C) 2008 - 2022 lat/lon GmbH, info@lat-lon.de, www.lat-lon.de
# %%
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
# #L%
###
export LIQUIBASE_HOME=/home/lyn/Programme/liquibase-3.6.2

#xml
## XPlan 3
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan3 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan3 > changelog_xplan3.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan3pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan3pre > changelog_xplan3pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan3archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan3archive > changelog_xplan3archive.xml

## XPlan 4.0
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan40 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan40 > changelog_xplan40.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan40pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan40pre > changelog_xplan40pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan40archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan40archive > changelog_xplan40archive.xml

## XPlan 4.1
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan41 > changelog_xplan41.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan41pre > changelog_xplan41pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan41archive > changelog_xplan41archive.xml

## XPlan 4.1 NSM
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41nsm diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan41nsm > changelog_xplan41nsm.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41nsmpre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan41nsmpre > changelog_xplan41nsmpre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41nsmarchive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan41nsmarchive > changelog_xplan41nsmarchive.xml

## XPlan 5.0
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan50 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan50 > changelog_xplan50.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan50pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan50pre > changelog_xplan50pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan50archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan50archive > changelog_xplan50archive.xml

## XPlan 5.1
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan51 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan51 > changelog_xplan51.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan51pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan51pre > changelog_xplan51pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan51archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan51archive > changelog_xplan51archive.xml
 
## XPlan 5.2
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan52 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan52 > changelog_xplan52.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan52pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan52pre > changelog_xplan52pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan52archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan52archive > changelog_xplan52archive.xml
 
## XPlan 5.3
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan53 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan53 > changelog_xplan53.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan53pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan53pre > changelog_xplan53pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan53archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan53archive > changelog_xplan53archive.xml
 
## XPlan 5.4
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan54 diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan54 > changelog_xplan54.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan54pre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan54pre > changelog_xplan54pre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan54archive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplan54archive > changelog_xplan54archive.xml
 
## XPlanSyn
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplansyn diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplansyn > changelog_xplansyn.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplansynpre diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplansynpre > changelog_xplansynpre.xml
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplansynarchive diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplansynarchive > changelog_xplansynarchive.xml

## XPlan Manager
 $LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplanmgr diffChangeLog --referenceUrl=jdbc:postgresql://localhost:5433/xplanbox --referenceUsername=postgres --referencePassword=postgres --referenceDefaultSchemaName=xplanmgr > changelog_xplanmgr.xml

## Remove 'Starte Liquibase...' from XML files
find changelog_xplan* -exec sed -i '/^Starte Liquibase/d' {} \;

#sql
## XPlan 3
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan3 --changeLogFile=changelog_xplan3.xml --outputFile=changelog_xplan3.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan3pre --changeLogFile=changelog_xplan3pre.xml --outputFile=changelog_xplan3pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan3archive --changeLogFile=changelog_xplan3archive.xml --outputFile=changelog_xplan3archive.sql updateSQL

## XPlan 4.0
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan40 --changeLogFile=changelog_xplan40.xml --outputFile=changelog_xplan40.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan40pre --changeLogFile=changelog_xplan40pre.xml --outputFile=changelog_xplan40pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan40archive --changeLogFile=changelog_xplan40archive.xml --outputFile=changelog_xplan40archive.sql updateSQL

## XPlan 4.1
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41 --changeLogFile=changelog_xplan41.xml --outputFile=changelog_xplan41.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41pre --changeLogFile=changelog_xplan41pre.xml --outputFile=changelog_xplan41pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41archive --changeLogFile=changelog_xplan41archive.xml --outputFile=changelog_xplan41archive.sql updateSQL

## XPlan 4.1 NSM
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41nsm --changeLogFile=changelog_xplan41nsm.xml --outputFile=changelog_xplan41nsm.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41nsmpre --changeLogFile=changelog_xplan41nsmpre.xml --outputFile=changelog_xplan41nsmpre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan41nsmarchive --changeLogFile=changelog_xplan41nsmarchive.xml --outputFile=changelog_xplan41nsmarchive.sql updateSQL

## XPlan 5.0
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan50 --changeLogFile=changelog_xplan50.xml --outputFile=changelog_xplan50.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan50pre --changeLogFile=changelog_xplan50pre.xml --outputFile=changelog_xplan50pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan50archive --changeLogFile=changelog_xplan50archive.xml --outputFile=changelog_xplan50archive.sql updateSQL

## XPlan 5.1
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan51 --changeLogFile=changelog_xplan51.xml --outputFile=changelog_xplan51.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan51pre --changeLogFile=changelog_xplan51pre.xml --outputFile=changelog_xplan51pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan51archive --changeLogFile=changelog_xplan51archive.xml --outputFile=changelog_xplan51archive.sql updateSQL

## XPlan 5.2
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan52 --changeLogFile=changelog_xplan52.xml --outputFile=changelog_xplan52.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan52pre --changeLogFile=changelog_xplan52pre.xml --outputFile=changelog_xplan52pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan52archive --changeLogFile=changelog_xplan52archive.xml --outputFile=changelog_xplan52archive.sql updateSQL

## XPlan 5.3
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan53 --changeLogFile=changelog_xplan53.xml --outputFile=changelog_xplan53.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan53pre --changeLogFile=changelog_xplan53pre.xml --outputFile=changelog_xplan53pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan53archive --changeLogFile=changelog_xplan53archive.xml --outputFile=changelog_xplan53archive.sql updateSQL

## XPlan 5.4
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan54 --changeLogFile=changelog_xplan54.xml --outputFile=changelog_xplan54.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan54pre --changeLogFile=changelog_xplan54pre.xml --outputFile=changelog_xplan54pre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplan54archive --changeLogFile=changelog_xplan54archive.xml --outputFile=changelog_xplan54archive.sql updateSQL

## XPlanSyn
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplansyn --changeLogFile=changelog_xplansyn.xml --outputFile=changelog_xplansyn.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplansynpre --changeLogFile=changelog_xplansynpre.xml --outputFile=changelog_xplansynpre.sql updateSQL
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplansynarchive --changeLogFile=changelog_xplansynarchive.xml --outputFile=changelog_xplansynarchive.sql updateSQL

## XPlan Manager
$LIQUIBASE_HOME/liquibase --driver org.postgresql.Driver --classpath=/home/lyn/.m2/repository/org/postgresql/postgresql/42.2.5/postgresql-42.2.5.jar --url=jdbc:postgresql://localhost:5439/xplanbox --username=postgres --password=postgres  --defaultSchemaName=xplanmgr --changeLogFile=changelog_xplanmgr.xml --outputFile=changelog_xplanmgr.sql updateSQL
