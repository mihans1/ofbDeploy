sudo docker image rmi $(sudo docker image ls -f='dangling=true' -q) -f || true
docker-compose -f docker-compose-databases-deploy-light.yml down
#docker-compose -p ofbiz -f docker-compose-databases-deploy-light.yml build
docker-compose -p ofbiz -f docker-compose-databases-deploy-light.yml up -d
#docker-compose -f docker-compose-databases.yml restart master
#docker-compose -p ofbiz -f docker-compose-ofbiz.yml build
#docker-compose -p ofbiz -f docker-compose-ofbiz.yml up
#sudo docker image rmi $(sudo docker image ls -f='dangling=true' -q) -f || true
sudo ./gradlew cleanAll loadAll ofbiz
