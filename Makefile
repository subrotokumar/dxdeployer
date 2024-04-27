auth=microservices/authentication/src/main/resources/application.yaml
project=microservices/project/src/main/resources/application.properties
subscription=microservices/subscription/src/main/resources/application.properties
payment=microservices/payment/src/main/resources/application.properties

encrypt:
	@ansible-vault encrypt ${auth}

decrypt:
	@ansible-vault decrypt ${auth}

commit:
	./scripts/commit.sh

git-commit:
	@make encrypt
	@make commit
