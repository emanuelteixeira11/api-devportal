#!/bin/sh
docker-compose -f docker/arangodb/docker-compose.yml down --remove-orphans
docker-compose -f docker/arangodb/docker-compose.yml up -d