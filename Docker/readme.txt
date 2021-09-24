cd service2
docker build -t "vaibhav/service2:1.0" .
cd ../service1
docker build -t "vaibhav/service1:1.0" .


docker run -p 8080:8080 --name service1 -d vaibhav/service1:1.0

docker run -p 9091:9091 --name service2 -d vaibhav/service2:1.0