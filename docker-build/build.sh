export NAME=nexus.ci.gustavo-arellano.com:5005/pba-service
export FILE=pba-service-0.0.1-SNAPSHOT-fat.jar
export SERVICE=pba-service

cd ..

#mvn -f ../pom.xml clean package
#cp ../target/$FILE .
docker build . -t $NAME
docker push $NAME
pba/refresh.sh $SERVICE $NAME $(date -r $FILE)
