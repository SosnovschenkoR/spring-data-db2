# spring-data-db2
### Overview
This Maven project contains the Java code for Spring Data DB2 entities and repositories.


### Working with the Code
The project was developed and tested using Java 8 and also include
- Spring Cache
- Spring Validation
- MapStruct
- Liquibase


### DB2
- db2 "select schemaname from syscat.schemata"
- db2 list db directory
- db2 connect to testdb
- db2 list tables for schema db2inst1

### Docker
- docker run -h db2server --name db2server --restart=always --detach --privileged=true -p 50000:50000 --env-file .env_list -v /Docker:/database ibmcom/db2
- docker exec -ti db2server bash
- docker exec -ti db2server /bin/su - db2inst1