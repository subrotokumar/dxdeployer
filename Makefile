accounts=microservices/config/src/main/resources/configurations/accounts.yaml
project=microservices/config/src/main/resources/configurations/project.yaml
gateway=microservices/config/src/main/resources/configurations/gateway.yaml
discovery=microservices/config/src/main/resources/configurations/discovery.yaml
payment=microservices/config/src/main/resources/configurations/payment.yaml
notification=microservices/config/src/main/resources/configurations/notification.yaml
config=microservices/config/src/main/resources/application.yaml

encrypt:
	@ansible-vault encrypt ${accounts} ${project} ${gateway} ${discovery} ${payment} ${notification}

decrypt:
	@ansible-vault decrypt ${accounts} ${project} ${gateway} ${discovery} ${payment} ${notification}

commit:
	./scripts/commit.sh

git-commit:
	@make encrypt
	@make commit

build:
	@./microservices/config/./mvnw clean package -f ./microservices/config
	@./microservices/discovery/./mvnw clean package -f ./microservices/discovery
	@@./microservices/gateway/./mvnw clean package -f ./microservices/gateway

up:
	@docker-compose up
