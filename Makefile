accounts=microservices/config/src/main/resources/configurations/accounts.yaml
project=microservices/config/src/main/resources/configurations/project.yaml
gateway=microservices/config/src/main/resources/configurations/gateway.yaml
discovery=microservices/config/src/main/resources/configurations/discovery.yaml
payment=microservices/config/src/main/resources/configurations/payment.yaml
config=microservices/config/src/main/resources/application.yaml

encrypt:
	@ansible-vault encrypt ${accounts} ${project} ${gateway} ${discovery} ${payment}

decrypt:
	@ansible-vault decrypt ${accounts} ${project} ${gateway} ${discovery} ${payment}

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
