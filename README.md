# Spring-Boot Camel QuickStart

This example demonstrates how you can use Apache Camel with Spring Boot.

The quickstart uses Spring Boot to configure a little application that includes a Camel route that triggers a message every 5th second, and routes the message to a log.

### Building

The example can be built with

    mvn clean install

### Running the example in OpenShift

It is assumed that:
- OpenShift platform is already running, if not you can find details how to [Install OpenShift at your site](https://docs.openshift.com/container-platform/3.3/install_config/index.html).
- Your system is configured for Fabric8 Maven Workflow, if not you can find a [Get Started Guide](https://access.redhat.com/documentation/en/red-hat-jboss-middleware-for-openshift/3/single/red-hat-jboss-fuse-integration-services-20-for-openshift/)

The example can be built and run on OpenShift using a single goal:

    mvn fabric8:deploy

When the example runs in OpenShift, you can use the OpenShift client tool to inspect the status

To list all the running pods:

    oc get pods

Then find the name of the pod that runs this quickstart, and output the logs from the running pods with:

    oc logs <name of pod>

You can also use the OpenShift [web console](https://docs.openshift.com/container-platform/3.3/getting_started/developers_console.html#developers-console-video) to manage the
running pods, and view logs and much more.

### Running via an S2I Application Template

Application templates allow you deploy applications to OpenShift by filling out a form in the OpenShift console that allows you to adjust deployment parameters.  This template uses an S2I source build so that it handle building and deploying the application for you.

First, import the Fuse image streams:

    oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/GA/fis-image-streams.json

Then create the quickstart template:

    oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/GA/quickstarts/spring-boot-camel-template.json

Now when you use "Add to Project" button in the OpenShift console, you should see a template for this quickstart. 


### Installation steps
1. Using Red Hat CodeReady Studio -> New Fuse Integration Project
1. Installing OKD
   1. https://github.com/openshift/origin/blob/v3.11.0/docs/cluster_up_down.md#linux
1. OKD Setup for Fuse 
   1. https://docs.openshift.com/container-platform/3.11/dev_guide/managing_images.html#using-image-pull-secrets
   1. Add secret to connect to Redhat registry
      1. oc create secret docker-registry {name} --docker-server=https://registry.redhat.io --docker-username={redhat email} --docker-password={pass} --docker-email={redhat email} --namespace=openshift
   1. Import Fuse image streams
      1. download https://github.com/jboss-fuse/application-templates/blob/master/fis-image-streams.json
      1. oc create -f fis-image-streams.json -n openshift
1. Login to OKD
   1. oc login https://127.0.01:8443 -u developer
   1. oc project myproject 
1. Deploy
   1. mvn fabric8:deploy
   
Fuse versions : https://maven.repository.redhat.com/ga/org/jboss/redhat-fuse/fabric8-maven-plugin/
