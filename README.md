# Grails Audit Logging Sample Applications #
 
This repository holds sample audit-logging applications in different branches to show the functionality of the Grails Audit-Logging plugin in action.
They can be used by users to quickly verify functionality.

* The Domain actions are mostly performed in the Boostrap class. You can then use your browser to check the auditController list of events logged.
* Most of the applications are secured by Spring Security (user: me, password: password)
* Some Domain classes implement the audit event hooks as well.

## master branch: single datasource

 * One common H2 Datasource for all domain classes 

## mongoDbOnlyDatasource branch: MongoDB as only DataSource
For the MongoDB as single datasource example project, see branch [mongoDbOnlyDatasource](https://github.com/robertoschwald/grails-audit-logging-plugin-examples/tree/mongoDbOnlyDatasource)

## Configuration
 
 - H2 as the only DataSource
 - Hibernate dependencies
 - Spring-Security-Core to secure the pages and to get the actor in AuditLog
 - database-migration to setup the schema
 
## Dependencies
 * See the branches build.gradle / BuildConfig.groovy

## Datasource
 This examples use a H2 Database.
 
 See grails-app/conf/DataSource.groovy for config details


## Starting the application
 * Checkout this source branch from GitHub
 * Change into the source-directory
 * Start the application: 
 
```
 grails run-app
```
 * Access http://localhost:8080/audit-log-sample


## grails_3.1.x branches
 * single H2 db DataSource
 * Auditing domains in Bootstrap.groovy

```
 grails run-app
```
