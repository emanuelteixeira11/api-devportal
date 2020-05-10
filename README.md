#API developer portal

**Run ArangoDb and devportal modules**

From root folder execute following commands:
```
./scripts/start.sh
```

**Stop ArangoDb and devportal modules**
```
./scripts/stop.sh
```

**Start _just_ ArangoDb (debug purpose)**
```
./scripts/start-arango.sh
```

##Access ArangoDb instance
**Solution instance:**
```
http://localhost:7376/
``` 
**ArangoDb instance (debug purpose):**
```
http://localhost:8529/
``` 
##Access devportal-manager instance
**Solution instance:**
```
http://localhost:8927/
```
**devportal-manager instance (debug purpose):**
```
http://localhost:8080/
``` 
###Test devportal-manager
```
curl http://localhost:8080/api/v1/metadata/
``` 
###SwaggerUI devportal-manager
```
tbd
``` 