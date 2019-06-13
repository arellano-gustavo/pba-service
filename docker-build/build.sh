export NAME=kebblar/count-factorial
export FILE=pba-service-0.0.1-SNAPSHOT-fat.jar
export SERVICE=factorial-srv

mvn -f ../pom.xml clean package
cp ../target/$FILE .
docker build . -t $NAME
# docker run -it -p 7654:8080 $NAME
docker push $NAME
./refresh.sh $SERVICE $NAME $(date -r $FILE)


