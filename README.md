# Grails Audit Logging Sample Applications #
 
This repository holds sample audit-logging applications in different branches to show the functionality of the Grails Audit-Logging plugin in action.
They can be used by users to quickly verify functionality.

The applications are secured by Spring Security (user: me, password: password)

The Book domain class implements the audit logging event closures.

## master branch: single datasource ##

 * One common H2 Datasource for all domain classes 

## mongoDbOnlyDatasource branch: MongoDB as only DataSource ##
For the MongoDB as single datasource example project, see branch [mongoDbOnlyDatasource](https://github.com/robertoschwald/grails-audit-logging-plugin-examples/tree/mongoDbOnlyDatasource)

##Configuration##
 
 - H2 as the only DataSource
 - Hibernate dependencies
 - Spring-Security-Core to secure the pages and to get the actor in AuditLog
 - database-migration to setup the schema
 
## Dependencies ##

 * Grails 2.4.4
 * audit-logging 1.1.0-SNAPSHOT
 * spring-security-core:2.0-RC4
 * hibernate4
 * cache-ehcache:1.0.5

## Datasource ##
 This example uses a H2 Database.
 
 See grails-app/conf/DataSource.groovy for config details


## Starting the application ##
 * Checkout this source branch from GitHub
 * Change into the source-directory
 * Start the application: 
 
```
 grails run-app
```
 * Access http://localhost:8080/audit-log-sample