 grpcurl --plaintext -d '{"FileData":"{ name:“Hello”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"CSV"}' localhost:9091 com.vaibhav.assignment.Service2.Store



  grpcurl --plaintext -d '{"FileData":"{ name:“Hi”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"CSV"}' localhost:9091 com.vaibhav.assignment.Service2.Update



  grpcurl --plaintext -d '{"FileData":"{ name:“Hi”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"XML"}' localhost:9091 com.vaibhav.assignment.Service2.Store


  grpcurl --plaintext -d '{"FileData":"{ name:“Hi”, dob:“20-08-2020”, salary:“122111241.150”, age:20 }","FileFormat":"XML"}' localhost:9091 com.vaibhav.assignment.Service2.Update