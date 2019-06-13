echo "Se invoca refresh para Rancher"
curl -X PUT \
   https://10.17.23.44/v3/project/c-mclh8:p-l9nx6/workloads/deployment:default:$1 \
  -k \
  -H 'Authorization: Bearer token-f9c2n:5gjcps9fs6ltl6hkxbf6nh2q9jds6524rp5m5jvts8mbcvzdtwbf7c' \
  -H 'Content-Type: application/json' \
   -d@- <<DATA
   {  
   "annotations":{
       "dt":"$3"
   },
   "containers":[  
      {  
         "name":"$1",
         "image":"$2"
      }
   ]
   }
DATA
