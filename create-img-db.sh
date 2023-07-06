#!/bin/bash
sudo docker build -t reservago-mvc-db --target db-img-mvc .
sudo docker build -t reservago-mvc-redis --target redis-img-mvc .
