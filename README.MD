# simplewallet
this is sample code how to setup axon framework.. 

  * t0 - [Java8] - newest java with lambda and stream
  * t1 - [Maven] - for dependencies management
  * t2 - [Spring Boot - Spring data] - framework used
  * t3 - [Axon Framework] - CQRS

## How To Run 
export DOCKER_KAFKA_HOST=$(ipconfig getifaddr en0)

docker-compose up -d (will bring up postgres )

mvn spring-boot:run

## DATABASE
 psql -p 5431 -h 127.0.0.1 -U simple
 
select *,
 convert_from(lo_get(payload), 'utf-8')::xml payload_printed,
  convert_from(lo_get(meta_data), 'utf-8')::xml metadata_printed 
from domain_event_entry;

## CURL
curl --request POST \
  --url http://localhost:8080/create-wallet \
  --header 'cache-control: no-cache' \
  --header 'postman-token: de1a5730-c814-1fd7-8aae-5070dd9bb014' \
  --data data
  
curl --request POST \
  --url http://localhost:8080/deposit-wallet \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: 64707a71-3629-ea83-2235-60d4148c3278' \
  --data '{\n"walletId": "07c70540-3f95-4575-ad3f-e4f911659e1e",\n"amount": 100\n}'
  
curl --request POST \
  --url http://localhost:8080/deduct-wallet \
  --header 'cache-control: no-cache' \
  --header 'content-type: application/json' \
  --header 'postman-token: 20bfa9d6-722e-e1ba-128c-c989a289c444' \
  --data '{\n"walletId": "72c9ba58-0923-4f6b-b41c-2ea485dc6afe",\n"amount": 100\n}'    