#!/bin/bash
curl --request PUT \
  --url http://localhost:9000/api/order \
  --header 'content-type: application/json' \
  --data '{
  "customername": "Тестовый заказ",
  "customeraddr": "Тестовый адрес",
  "ordersum": 18002.0
}'