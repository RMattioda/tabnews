#!/bin/bash

apt-get install -f
apt-get update -y

yes | apt-get install curl

curl_result=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health)

echo "Result status code: " "$curl_result"

while [[ ! $curl_result == "200" ]]; do
  >&2 echo "Config server is not up yet"
  sleep 5
  curl_result=$(curl -s -o /dev/null -I -w "%{http_code}" http://config-server:8888/actuator/health)
done

./cnb/lifecycle/launcher