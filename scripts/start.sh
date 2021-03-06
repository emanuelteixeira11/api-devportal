#!/bin/sh
echo "Solution going to be package!"
mvn clean package -Dmaven.test.skip=true
echo "Package done!"

docker build api-devportal-manager/ -t api-manager:latest
docker-compose -f scripts/docker/devportal/docker-compose.yml down --remove-orphans
docker-compose -f scripts/docker/devportal/docker-compose.yml up -d