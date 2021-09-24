 grpcurl --plaintext -d '{"FileData":"{ name:“Hello”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"CSV"}' localhost:9091 com.vaibhav.assignment.Service2.Store


  grpcurl --plaintext -d '{"FileData":"{ name:“Hi”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"CSV"}' localhost:9091 com.vaibhav.assignment.Service2.Update



  grpcurl --plaintext -d '{"FileData":"{ name:“Hi”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"XML"}' localhost:9091 com.vaibhav.assignment.Service2.Store


  grpcurl --plaintext -d '{"FileData":"{ name:“Hi”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"XML"}' localhost:9091 com.vaibhav.assignment.Service2.Update

  grpcurl --plaintext -d '{"FileFormat":"XML"}' localhost:9091 com.vaibhav.assignment.Service2.ReadFile


curl --location --request POST 'http://localhost:8080/store?fileType=CSV' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Hello",
    "dob": "20-08-2020",
    "salary": "122111241.150",
    "age": "20"
}'


curl --location --request POST 'http://localhost:8080/update?fileType=XML' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "vaibahv",
    "dob": "20-08-2020",
    "salary": "122111241.150",
    "age": "25"
}'

curl --location --request GET 'http://localhost:8080/read?fileType=XML'