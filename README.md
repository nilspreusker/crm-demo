crm-demo
========

This is a simple showcase to show how to build web-apps using Angular.js and Java EE 6 (JAX-RS and JPA 2.0).

To deploy, just copy the war to the "standalone/deployments" directory of a 
Jboss AS 7.1. You can also create a file named build.properties and add a line 
with "deploy.dir=.../standalone/deployments" where the three dots should be 
replaced with the path to your application server. Afterwards you can just run
Ant, which will in turn execute the maven build and deploy the application for 
you. 

Have fun! 