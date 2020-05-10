#!/bin/sh
docker-compose -f scripts/docker/arangodb/docker-compose.yml down --remove-orphans
docker-compose -f scripts/docker/arangodb/docker-compose.yml up -d