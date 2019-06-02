FROM jboss/wildfly:16.0.0.Final
MAINTAINER Olivier Truong <olivier@nadeva.fr>

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-full.xml", "-b", "0.0.0.0"]
ADD target/bookstore.war bookstore.war
RUN mv bookstore.war /opt/jboss/wildfly/standalone/deployments/bookstore.war
