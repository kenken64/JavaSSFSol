## Workshop 1

- Run the spring boot app using `./mvnw spring-boot:run`
- `./mvnw spring-boot:run -Dspring-boot.run.arguments=--port=8081`
- `sudo snap install --classic heroku`
- Login to heroku `heroku login`

```
git init
git add .
git commit -m "first commit"
heroku create
git push heroku master
```

## Workshop 2

- Run the spring boot app using `./mvnw spring-boot:run`
- Login to heroku `heroku login`

```
git init
git add .
git commit -m "first commit"
heroku create
git push heroku master
```

## Workshop 3

- Run the spring boot app using `./mvnw spring-boot:run -Dspring-boot.run.arguments=--dataDir=/opt/tmp/data`
- Add this entry to the pom.xml

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
```

- Run ./mvnw package to run test cases.

## Workshop 4

- Run the spring boot app using `./mvnw spring-boot:run`
- Add this entry to the pom.xml for redis data persistency

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-cache</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<dependency>
	<groupId>redis.clients</groupId>
	<artifactId>jedis</artifactId>
</dependency>
```

- Run ./mvnw package to run test cases
- Connect to the redis cloud service

```
$ redis-cli -h redis-19763.c252.ap-southeast-1-1.ec2.cloud.redislabs.com -p 19763 -a dEnC2q43NOxxghtlcWxI56n3aS8lRZw1

$ redis-19763.c252.ap-southeast-1-1.ec2.cloud.redislabs.com:19763> keys *

```
