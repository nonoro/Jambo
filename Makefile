jarBuild:
	./gradlew -x test build

run-local:
	java -jar build/libs/application.jar --spring.profiles.active=gyu

run-aws:
	java -jar build/libs/application.jar --spring.profiles.active=aws

