
#!bin/sh

echo "Docker Execute!!!"

DOCKER_PATH=$(pwd)

echo "ROOT 디렉토리로 이동"

cd ..

echo "./gradlew 권한 추가"

chmod 755 ./gradlew

echo " Execute './gradlew clean build"

./gradlew clean build

echo "스크립트 디렉토리로 이동"

cd $DOCKER_PATH

echo "docker-compose.yml 파일 실행"

docker-compose up


