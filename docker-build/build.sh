export NAME=nexus.ci.gustavo-arellano.com:5005/pba-service
export FILE=pba-service-0.0.1-SNAPSHOT-fat.jar
export SERVICE=pba-service
export RUTA=/home/ubuntu/pba/
export UPDATE=echo $(($(date +'%s * 1000 + %-N / 1000000')))

echo $UPDATE

cd ..

docker build $RUTA -t $NAME
docker push $NAME
pba/refresh.sh $SERVICE $NAME $UPDATE

