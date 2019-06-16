export UPDATE=$(($(date +'%s * 1000 + %-N / 1000000')))
echo $UPDATE

cd ..

docker build @7 -t @5
docker push @5
pba/refresh.sh $UPDATE

