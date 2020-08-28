#!/bin/bash
curl --request PUT \
  --url http://localhost:9000/api/detail \
  --header 'content-type: application/json' \
  --data '{
    "productserialnum": "5CCC6",
    "productname": "Test",
    "count": 8,
    "orderid": 5
  }'