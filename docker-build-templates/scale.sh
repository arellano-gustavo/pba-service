curl -X PUT \
    https://rancher.ci.gustavo-arellano.com/v3/project/@1/workloads/deployment:@2:@3 \
   -k \
   -H 'Authorization: Bearer @4' \
   -H 'Content-Type: application/json' \
    -d@- <<DATA
    {  
        "scale":$1,
        "containers":[  
            {
            "name":"@3",
            "image":"@5"
            }
        ]
    }
DATA
