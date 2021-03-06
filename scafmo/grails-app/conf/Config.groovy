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
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

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
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

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
log4j.main = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
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
}
grails.plugin.scaffold.core.folders = [frontendCasAngulr:null, backendRestCasGrailsApp:null, backendRestCasSrc:null,
                                       backendRestCasTest:null]


environments {
	development {
		grails.serverURL = "http://localhost:8080/scafmo"
	}
	test {
		grails.serverURL = "http://localhost:3333/scafmo"
	}
	production {
		grails.serverURL = "http://www.changeme.com"
	}
}
grails.resources.resourceLocatorEnabled = true
/**
 * Rest Api doc config
 */

//generate api: grails rest-api-doc
//view api: http://.../restApiDoc/?doc_url=http://.../restApiDoc/api#
grails.plugins.restapidoc.outputFileGeneration = "web-app/WEB-INF/restapidoc.json"
grails.plugins.restapidoc.outputFileReading = "WEB-INF/restapidoc.json"

environments {
    development {
		grails.plugins.restapidoc.outputFileReading = "web-app/WEB-INF/restapidoc.json"
	}
}
		
/**
 * Spring Security Config
 */
			
grails.plugin.springsecurity.active = true
environments {
    development {
        grails.plugin.springsecurity.active = false
    }
}
	
grails.plugin.springsecurity.filterChain.chainMap = [
    '/ng/**': 'nonAuthFilter',
    '/static/**': 'nonAuthFilter',
    '/css/**': 'nonAuthFilter',
    '/js/**': 'nonAuthFilter',
    '/index.gsp': 'nonAuthFilter',
    '/index': 'nonAuthFilter',
    '/': 'nonAuthFilter',
    '/login/**': 'nonAuthFilter',
    '/restApiDoc/**': 'nonAuthFilter', //disable in production
    '/**/**': 'JOINED_FILTERS'
]

grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.interceptUrlMap = [
      '/**':                  ['isFullyAuthenticated()']
]
			
			
/**
 * Spring Security Rest Config
 */


grails.plugin.springsecurity.rest.token.rendering.usernamePropertyName = 'login'
grails.plugin.springsecurity.rest.token.rendering.tokenPropertyName = 'access_token'
grails.plugin.springsecurity.rest.token.rendering.authoritiesPropertyName = 'permissions'

grails.plugin.springsecurity.rest.token.storage.useGrailsCache = true

grails.cache.config = {
    cache {
       name 'userTokens'
    }
}
grails.plugin.springsecurity.rest.token.storage.grailsCacheName = "userTokens"

grails.plugin.springsecurity.rest.login.active=true
grails.plugin.springsecurity.rest.login.useJsonCredentials = true
grails.plugin.springsecurity.rest.login.usernamePropertyName = "username"
grails.plugin.springsecurity.rest.login.passwordPropertyName = "password"

grails.plugin.springsecurity.rest.login.endpointUrl = "/api/login"
grails.plugin.springsecurity.rest.login.failureStatusCode = 401

grails.plugin.springsecurity.rest.token.validation.useBearerToken = true
grails.plugin.springsecurity.rest.token.validation.headerName = 'Authorization'
grails.plugin.springsecurity.rest.token.validation.active = true
grails.plugin.springsecurity.rest.token.validation.endpointUrl = "/api/validate"

/*----------------------------------------------------------------------*/
		
/**
 * Scaffold Angular plugin Config
 */

grails{
	plugin{
		scaffold{
			core{
				overwrite = true // false = Ask before replacing file
				ignoreStatic = false // Don't generate static files again
				ignoreFileNames = ['TestDataGeneratorService.groovy', 'TestDataConfig.groovy', 'config.json', '.gitignore']
				// don't generate files or menu for domains
				ignoreDomainNames = []
			}
		}
	}
}
grails.plugin.scaffold.core.displayNames = ['Tag':['id', 'name'], 'Task':['id', 'timeSpent', 'summary', 'status'], 'Owner':['id', 'telephone', 'lastName', 'firstName'], 'Person':['id', 'lastName', 'firstName'], 'Pet':['id', 'name', 'birthDate'], 'PetType':['id', 'name'], 'Speciality':['id', 'name'], 'Vet':['id', 'lastName', 'firstName'], 'Visit':['id', 'description', 'date'], 'DivisionCollection':['id', 'name'], 'DivisionCollectionless':['id', 'name'], 'PersonCollection':['id', 'name', 'age'], 'PersonCollectionless':['id', 'name', 'age'], 'TestNumber':['id', 'longNr', 'integerNrUnique', 'integerNrRange'], 'TestOther':['id', 'testEnum', 'testDate', 'booleanNullable'], 'TestString':['id', 'urlStr', 'uniqueStr', 'sizeStr'], 'Role':['id', 'authority'], 'User':['id', 'username', 'passwordExpired', 'enabled'], 'UserRole':['id'], 'Classifier':['id', 'propertyname', 'description', 'constant']]
			grails.databinding.dateFormats = ["yyyy-MM-dd'T'hh:mm:ssZ"]
grails.spring.bean.packages = ['defpackage.aop']