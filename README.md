# Grails Audit Logging MongoDB Sample application #
 
This is a sample application to show the functionality of the Grails Audit-Logging plugin in action.
It can be used by users to quickly verify functionality.

The Book domain class implements the audit logging event closures.

The application secured by Spring Security (user: me, password: password)

##Configuration##
 
 - MongoDB as the only DataSource
 - NO Hibernate dependencies
 - Spring-Security-Core to secure the pages and to get the actor in AuditLog
 
## Dependencies ##

 * Grails 2.4.4
 * audit-logging 1.1.0-SNAPSHOT
 * spring-security-core:2.0-RC4
 * cache-ehcache:1.0.5

## Datasource ##
 This example uses a MongoDB Database.
 
 See grails-app/conf/DataSource.groovy for config details


## Starting the application ##
 * Checkout this source branch from GitHub
 * Change into the source-directory
 * Start the application: 
 
```
 grails run-app
```
 * Access http://localhost:8080/audit-log-sample