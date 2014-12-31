grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.work.dir = "target"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = null

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
		
		
		
		
		
		mavenRepo "http://nexus.smit/content/groups/public"
		
		
		
		
		
		
        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.

    }

    plugins {

		compile ":build-test-data:2.2.2"


		runtime ":cors:1.1.6"
		compile ":dirserve:0.4"
		compile ":rest-api-doc:0.5"
		runtime ":resources:1.2.13"//needed for rest-api-doc

        // plugins for the build system only
        build ":tomcat:7.0.55"

        // plugins needed at runtime but not for compilation
        //runtime ":hibernate4:4.3.5.5" // or 
		runtime ":hibernate:3.6.10.17"
		
		compile ":scaffold-angular-smit:0.3.26"

    }
}



		grails.war.copyToWebApp = { args ->
			fileset(dir:"") {
				include(name: "angular/client/**")
			}
			fileset(dir:"web-app") {
		        include(name: "restapidoc.json")
				include(name: "WEB-INF/**")
			}
		}
		