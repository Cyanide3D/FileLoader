docker run -d --network file-network --rm --name one -p 127.0.0.1:8080:8080 fileuploader
docker run -d --network file-network --rm --name two --env SERVER_PORT=8081 -p 127.0.0.1:8081:8081 fileuploader
docker run -d --network file-network --rm --name three --env SERVER_PORT=8082 -p 127.0.0.1:8082:8082 fileuploader
docker run -d --network file-network --rm --name four --env SERVER_PORT=8083 -p 127.0.0.1:8083:8083 fileuploader
docker run -d --network file-network --rm --name five --env SERVER_PORT=8084 -p 127.0.0.1:8084:8084 fileuploader