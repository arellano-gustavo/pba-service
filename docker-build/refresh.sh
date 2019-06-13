echo "Se invoca refresh para Rancher"
curl -X PUT \
   https://rancher.ci.gustavo-arellano.com/v3/project/c-v2vwp:p-rw8nc/workloads/deployment:default:$1 \
  -k \
  -H 'Authorization: Bearer token-xg65g:84wwxszfpjp75fpld2nv4rqmk7cg5c856pztdwvzq5bznbcw4xz655' \
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
