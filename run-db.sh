#!/bin/bash
sudo pkill -u postgres
sudo docker run --network="host" -it reservago-mvc-db
