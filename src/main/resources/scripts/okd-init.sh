#!/usr/bin/env bash
echo "Usage okd-init.sh docker-registry-name kerberos-username kerberos-password"
oc cluster up
oc login -u system:admin
echo "params : " $1 $2 $3
oc create secret docker-registry $1 --docker-server=https://registry.redhat.io --docker-username=$2 --docker-password=$3 --docker-email=$2 --namespace=openshift
oc create -f https://raw.githubusercontent.com/jboss-fuse/application-templates/master/fis-image-streams.json -n openshift
oc create -n openshift -f https://raw.githubusercontent.com/jboss-fuse/application-templates/application-templates-2.1.fuse-730065-redhat-00002/fis-console-namespace-template.json
oc login -u developer
oc project myproject
cd ../../..
mvn fabric8:deploy