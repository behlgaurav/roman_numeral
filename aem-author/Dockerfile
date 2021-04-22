# Use the previously created aem-base-image
FROM aem-baseimage

ENV MAVEN_OPTS "-Xms1024M -Xmx2048M"

COPY packages/* ./crx-quickstart/install/

# Expose AEM author in port 4502
EXPOSE 4502

# Make the container start always in Author mode with Port 4502
ENTRYPOINT ["java", "-Xms1024M", "-Xmx2048M", "-jar", "cq-quickstart.jar", "-nofork", "-pt", "CHILD", "-use-control-port", "-Dsling.run.modes=author", "-p", "4502", "-gui", "-verbose"]
