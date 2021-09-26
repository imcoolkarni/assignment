# assignment
It consists of two services 
1. Service1 
2. Service2


To generate the docker build checkout the code into your local environment and perform below actions

1. checkout the code into IDE of your choice
2. go to terminal/root directory of project
3. run the maven build for the project
   `` mvn clean install -DskipTests=true ``
```

cd service2
docker build -t "vaibhav/service2:1.0" .
cd ../service1
docker build -t "vaibhav/service1:1.0" .


docker run -p 8080:8080 --name service1 -d vaibhav/service1:1.0

docker run -p 9091:9091 --name service2 -d vaibhav/service2:1.0
```

The project supports docker-compose.  
So if the images are once generated as provided by the above steps then run the docker-compose command as below

```
cd Docker
cd docker-compose
docker-compose up -d
```


If you want this project to start in standalone mode then perform the following

1. checkout the code into IDE of your choice
2. go to terminal/root directory of project 
3. run the maven build for the project
    `` mvn clean install -DskipTests=true ``
4. go to service1 > src/main/java/com/vaibhav/service1/Server1ClientApplication.java and run the main function
5. go to service2 > src/main/java/com/vaibhav/assignment/AssignmentApplication.java and run the main function



#CURL documentation for APIs

STORE 
```
curl --location --request POST 'http://localhost:8080/store?fileType=CSV' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Hello",
    "dob": "20-08-2020",
    "salary": "122111241.150",
    "age": "20"
}'
```
UPDATE
```
curl --location --request POST 'http://localhost:8080/update?fileType=XML' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "vaibhav",
    "dob": "20-08-2020",
    "salary": "122111241.150",
    "age": "25"
}'
```
READ
```
curl --location --request GET 'http://localhost:8080/read?fileType=XML'
```