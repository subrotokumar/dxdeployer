accounts=services/config/src/main/resources/configurations/accounts.yaml
project=services/config/src/main/resources/configurations/project.yaml
gateway=services/config/src/main/resources/configurations/gateway.yaml
discovery=services/config/src/main/resources/configurations/discovery.yaml
payment=services/config/src/main/resources/configurations/payment.yaml
notification=services/config/src/main/resources/configurations/notification.yaml
config=services/config/src/main/resources/application.yaml

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
	@./services/config/./mvnw clean package -f ./services/config
	@./services/discovery/./mvnw clean package -f ./services/discovery
	@@./services/gateway/./mvnw clean package -f ./services/gateway

up:
	@docker-compose up
