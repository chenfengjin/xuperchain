docker build -t xuperchain:sdk  -f Dockerfile.ct .
docker stop xchain &&docker rm xchain  && docker run  -it --name xchain -d     xuperchain:sdk  ./xchain
docker exec -it xchain bash  scripts/start.sh