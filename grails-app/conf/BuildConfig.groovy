grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.work.dir = "target"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
	// configure settings for the test-app JVM, uses the daemon by default
	test: [maxMemory: 1768, minMemory: 64, debug: false, maxPerm: 768, daemon:true],
	// configure settings for the run-app JVM
	run: [maxMemory: 1768, minMemory: 64, debug: false, maxPerm: 768, forkReserve:true],
	// configure settings for the run-war JVM
	war: [maxMemory: 1768, minMemory: 64, debug: false, maxPerm: 768, forkReserve:false],
	// configure settings for the Console UI JVM
	console: [maxMemory: 1768, minMemory: 64, debug: false, maxPerm: 768]
]

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
		test "org.gebish:geb-spock:0.9.2"
    }

    plugins {

		test ":geb:0.9.2"
		compile ":rest-client-builder:2.0.3"
		compile ":spring-security-core:2.0-RC3"
		compile ":spring-security-rest:1.4.1.RC2", {
    		excludes: 'spring-security-core'
		}



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
		
		compile ":scaffold-angular-smit:latest.integration"// latest.integration"//run "grails list-plugin" before
		compile ':cache:1.1.8'

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
		