#!/bin/bash
curl --request POST \
  --url http://localhost:9000/api/order \
  --header 'content-type: application/json' \
  --data '{
    "id": 4,
    "customername": "Тест обновления ИМЯ",
    "customeraddr": "Тест обновления АДРЕС",
    "ordersum": 18006.0
  }'