cd c:/_SOURCES/grails-scaffold-demo/

gvm use grails 2.5.0
[Environment]::SetEnvironmentVariable("JAVA_HOME","C:\Program Files\Java\jdk1.8.0_11", "Process")
Remove-Item -Path scafmo -Force -Recurse
grails create-app scafmo

Copy-Item -Path test-assets\scafmo\* -Destination scafmo -Force -Recurse


cd scafmo
grails compile
grails createDemo --stacktrace 
Copy-Item -Path ..\test-assets\scafmo\grails-app\conf\TestDataConfig.groovy -Destination ..\scafmo\grails-app\conf\TestDataConfig.groovy -Force -Recurse

grails createDemo --stacktrace 


start-process cmd -ArgumentList "/C","mode con:cols=245 lines=1000 && title SCAFMO && grails run-app & pause"
##cp -r test-assets\scafmo\* scafmo
##cp -r ..\test-assets\scafmo\* ..\scafmo
##grails install-dependency grails.plugin:scaffold-angular-smit:0.4.10 --repository=http://nexus.smit/content/groups/public --stacktrace
##grails test-app functional: -Dserver.port=3333  -Dgeb.env=firefox
#start-process cmd -ArgumentList "/C","mode con:cols=145 lines=1000 && title SCAFMO TESTS && grails test-app functional: -Dserver.port=3333 --stacktrace  & pause"

