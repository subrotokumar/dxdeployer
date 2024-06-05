# DxDeployer: Platform as a Service
DxDeployer: Developer Experience Deployer is a cutting-edge Platform as a Service (PaaS) designed specifically for deploying React applications seamlessly to production environments.

## Tect Used

<img src="https://skillicons.dev/icons?i=java,spring,aws,docker,bash,kafka,redis,postgres,mongo,prometheus,loki=light">

## Dependency
- JDK 17 LTS +
- Ansible-Avault
- Docker/Podman

## Architecture Design
![](./diagram/dxd_platfrom_as_a_code.png)

## Entity Relationship Diagram
![](./diagram/schema.png)

[Project Link](http://github.com/subrotokumar/dxd-paas)

## Getting Started

1. Start the docker:
   ```bash
   docker-compose up
   ```

## DEVELOPMENT Link:

- pgddmin: http://localhost:5050
- mongo-express: http://localhost:8081
- Maildev: http://localhost:8080
- zipkin: http://localhost:9411
- Eureka: http://localhost:8761
- Docs: http://localhost:3000

- Services Swagger Docs
  - Accounts: http://localhost:7259/api/v1/account/swagger-ui.html
  - Project: http://localhost:7259/api/v1/project/swagger-ui.html
  - Notification: http://localhots:7259/api/v1/project/swagger-ui.html
  - Payment: http://localhost:7259/api/v1/payment/swagger-ui.html

![](./microservice.mp4)