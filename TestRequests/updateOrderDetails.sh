#!/bin/bash
curl --request POST \
  --url http://localhost:9000/api/detail \
  --header 'content-type: application/json' \
  --data '{
  "detailid": 7,
  "productserialnum": "5AAAAAAA3",
  "productname": "Тест обновления ИМЯ ПРОДУКТА",
  "count": 9,
  "orderid": 5
}'