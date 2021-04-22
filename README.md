# Sample AEM project template

This is a Number to Roman Numeral conversion project for AEM-based applications. This README is work-in-progress

## Modules

The main parts are:

* myapp: AEM archetype based project that contains the source code.
* docker-compose.yaml: Configuration to setup container services 
* aem-author: AEM author based on 'aem-baseimage' with SP7
* aem-baseimage: Ubuntu based AEM 6.5.0 base image

## Building
### AEM base image
```
docker build -t aem-baseimage .
```

All at once using the docker compose command or one by one
```
docker-compose up
```

### AEM author image
```
docker build -t aem-author .

docker run -p 4502:4502 author or docker run -i -d -p 4502:4502 aem-author to run in background
```

## Creating the app
### With docker
```
docker run -it --rm --name myapp -v "$(pwd)/myapp":/usr/src/workdir -w /usr/src/workdir -v "$(pwd)/myapp"/.m2:/root/.m2 maven:3.6.2-jdk-8 mvn clean install -Dmaven.test.skip=true -Daem.port=4502 -PautoInstallPackage -PautoInstallBundle
```

### Without docker
```
mvn clean install -Dmaven.test.skip=true -Daem.port=4502 -PautoInstallPackage -PautoInstallBundle
```

### Integration tests

* Set up integration tests using Teleporter rules - Pending

## Methodology

### Engineering
* AEM Architeype 27
* Path based sling servlet to expose the endpoint
* OSGI DS Annotations
* OSGI Service to perform the number to roman numeral conversion logic
* Custom exception as a placeholder for all scenarios
* Minimal complexity in logic

### Testing
* JUnit for Service
* Mocks for Servlet (pending)

## Packaging layout
* Standard AEM project with optional Docker setup

## Dependency Attribution
* Refer to license-maven-plugin report

## FIXME/Nice-to-haves:
 * Set up publish and dispatcher as applicable
 * Set up replication agent configuration in case publish is required
 * Set up script to upgrade to latest service pack
 * Update docker port number in docker-compose.yaml as applicable
 * Update dispatcher configs for filters, url shortener, farm & cache configurations, as applicable 
 * Automate using scripts
 * CentOS+vagrant alternative