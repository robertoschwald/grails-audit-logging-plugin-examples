import grails.plugin.springsecurity.SpringSecurityUtils
import grails.example.User

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }


grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [ // the first one is the default format
                      all          :'*/*', // 'all' maps to '*' or the first available format in withFormat
                      atom         :'application/atom+xml',
                      css          :'text/css',
                      csv          :'text/csv',
                      form         :'application/x-www-form-urlencoded',
                      html         :['text/html', 'application/xhtml+xml'],
                      js           :'text/javascript',
                      json         :['application/json', 'text/json'],
                      multipartForm:'multipart/form-data',
                      rss          :'application/rss+xml',
                      text         :'text/plain',
                      xml          :['text/xml', 'application/xml'],
                      // added by roos
                      hal          :['application/hal+json', 'application/hal+xml'],
                      png          :'image/png',
                      jpg          :'image/jpg',
                      pdf          :'application/pdf'

]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.excludes = ['/WEB-INF/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
  views {
    gsp {
      encoding = 'UTF-8'
      htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
      codecs {
        expression = 'html' // escapes values inside ${}
        scriptlet = 'html' // escapes output from scriptlets in GSPs
        taglib = 'none' // escapes output from taglibs
        staticparts = 'none' // escapes output from static template parts
      }
    }
    // escapes all not-encoded output at final stage of outputting
    // filteringCodecForContentType.'text/html' = 'html'
  }
}

grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = true

grails.assets.excludes = ["bootstrap3/*.less"]
grails.assets.less.compiler = 'less4j'

// database migration plugin. See grails-app/migration/ dir
grails.plugin.databasemigration.updateOnStartFileNames = ['changelog.groovy']
grails.plugin.databasemigration.updateOnStart = true
grails.plugin.databasemigration.dropOnStart = true
grails.plugin.databasemigration.autoMigrateScripts = ['RunApp', 'TestApp']


// cache
// special config with named cacheManager to prevent "Another unnamed Cache Manager already exists" errors
// see http://www.first8.nl/blog/fix-cachemanager-with-same-name-grails-cache-ehcache/
grails{
  cache {
    order = 2000 // higher than default (1000) and plugins, usually 1500
    enabled = true
    clearAtStartup=true // reset caches when redeploying
    ehcache {
      // ehcacheXmlLocation = 'classpath:ehcache.xml' // no custom xml config location (no xml at all)
      reloadable = false
    }
  }
}
def uniqueCacheManagerName = appName + "ConfigEhcache-" + System.currentTimeMillis()
grails.cache.ehcache.cacheManagerName = uniqueCacheManagerName

// Spring Security Core plugin
grails {
  plugin {
    springsecurity {
      // Added by the Spring Security Core plugin:
      userLookup.userDomainClassName = 'grails.example.User'
      userLookup.authorityJoinClassName = 'grails.example.UserRole'
      authority.className = 'grails.example.Role'
      password.algorithm = "bcrypt" // do not use digests anymore. See spring-security-plugin documentation for bcrypt.
      password.bcrypt.logrounds = 10
      securityConfigType = "InterceptUrlMap"
      portMapper.httpPort = 80
      portMapper.httpsPort = 443
      rejectIfNoRule = true
      auth.forceHttps = true
      useSessionFixationPrevention = "true" // Create new Cookie on login. OWASP security A3
      //secureChannel.definition = ['/**':'REQUIRES_SECURE_CHANNEL']
      interceptUrlMap = [
        '/'                   :['ROLE_USER', 'IS_AUTHENTICATED_REMEMBERED'],
        '/cache/**'           :['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/static/**'          :['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/js/**'              :['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/assets/**'          :['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/login/**'           :['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/logout/**'          :['IS_AUTHENTICATED_ANONYMOUSLY'],
        '/**'                 :['ROLE_USER', 'IS_AUTHENTICATED_REMEMBERED']
      ]

      // New in SpringSecurity 2.x plugin:
      // When mapping URLs for controllers that are mapped in UrlMappings.groovy, you need to secure the un-url-mapped URLs.
      // For example if you have a FooBarController that you map to /foo/bar/$action, you must register that in
      // controllerAnnotations.staticRules as /foobar/**.
      // This is different than the mapping you would use for the other two approaches and is necessary because
      // controllerAnnotations.staticRules entries are treated as if they were annotations on the corresponding controller.
      controllerAnnotations.staticRules = [
        '/index':                         ['permitAll'],
        '/index.gsp':                     ['permitAll'],
        '/**/js/**':                      ['permitAll'],
        '/**/css/**':                     ['permitAll'],
        '/**/images/**':                  ['permitAll'],
        '/**/favicon.ico':                ['permitAll']
      ]

      //successHandler.defaultTargetUrl =
      logout.afterLogoutUrl = "/login/auth?logoutByUser"

      useSecurityEventListener = true
      onInteractiveAuthenticationSuccessEvent = { e, appCtx ->
        // update lastLogin.
        User.withSession {
          User user = User.findById(appCtx.springSecurityService.principal.id)
          user.lastLogin = new Date()
          user.save(flush:true)
        }
      }
    }
  }
}

// AuditLog Plugin config
auditLog {
  auditDomainClassName = "grails.example.AuditLogEvent"
  verbose = true // verbosely log all changed values to db by default
  nonVerboseDelete = true // do not log attributes on delete
  logIds = true  // log db-ids of associated objects
  largeValueColumnTypes = true // use large column db types for oldValue/newValue.
  TRUNCATE_LENGTH = 4000
  //idMapping = [generator:"uuid2", type:"string", length:36]
  replacementPatterns = ["org.codehaus.grails.plugin.":""] // replacement patterns for logging values
  actorClosure = { request, session ->
    // Starting with SpringSecurity Core 1.1.2, principal is a String when ANONYMOUS.
    if (request.applicationContext.springSecurityService.principal instanceof String) {
      return request.applicationContext.springSecurityService.principal
    }
    String username = request.applicationContext.springSecurityService.principal?.username
    if (SpringSecurityUtils.isSwitched()) {
      username = SpringSecurityUtils.switchedUserOriginalUsername + " AS " + username
    }
    return username
  }
}

grails.cache.config = {
  provider {
    updateCheck false
    monitoring 'on'
    dynamicConfig false
    // unique name when configuring caches
    name uniqueCacheManagerName
  }
  defaultCache {
    maxElementsInMemory 10000
    eternal false
    timeToIdleSeconds 120
    timeToLiveSeconds 120
    overflowToDisk false // no disk use, this would require more config
    maxElementsOnDisk 10000000
    diskPersistent false
    diskExpiryThreadIntervalSeconds 120
    memoryStoreEvictionPolicy 'LRU' // least recently used gets kicked out
  }
}

environments {
  development {
    grails.logging.jul.usebridge = true
  }
  production {
    grails.logging.jul.usebridge = false
    // TODO: grails.serverURL = "http://www.changeme.com"
  }
}


// log4j configuration
log4j = {
  // Example of changing the log pattern for the default console appender:
  //
  // appenders {
  //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
  //}

  error 'org.codehaus.groovy.grails.web.servlet',        // controllers
      'org.codehaus.groovy.grails.web.pages',          // GSP
      'org.codehaus.groovy.grails.web.sitemesh',       // layouts
      'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
      'org.codehaus.groovy.grails.web.mapping',        // URL mapping
      'org.codehaus.groovy.grails.commons',            // core / classloading
      'org.codehaus.groovy.grails.plugins',            // plugins
      'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
      'org.springframework',
      'org.hibernate',
      'net.sf.ehcache.hibernate'

  trace 'org.codehaus.groovy.grails.plugins.orm.auditable'
  debug 'grails.plugin.mongodbcreatedrop'
  trace 'org.springframework.security'
  info "grails.app"
  error stdout: "StackTrace" // stacktraces to console

  debug 'org.codehaus.grails.plugin.auditlog'

  root {
    info 'stdout'
    additivity = true
  }


}


