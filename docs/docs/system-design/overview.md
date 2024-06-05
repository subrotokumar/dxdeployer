---
title: Overview
sidebar_position: 1
---

The DxDeployer Platform as a Code architecture is a comprehensive and modular system designed to support scalable, reliable, and efficient application development and deployment. It incorporates microservices, containerization, monitoring tools, databases, and frontend frameworks, ensuring seamless interaction between components and providing end users with a smooth experience.

![HLD](./img/dxd_platfrom_as_a_code.png)

### System Components and Flow

1. **Microservices Layer**:
   - **Auth Microservice**: Manages user authentication.
   - **Subscription Microservice**: Handles user subscription management.
   - **Payment Microservice**: Processes payments.
   - **Project Microservice**: Manages project-related operations.
   - **Eureka**: Service discovery tool used to register and locate microservices.

2. **Database**:
   - **PostgreSQL**: Relational database for storing structured data.
   - **MongoDB**: NoSQL database for unstructured data storage.

3. **Monitoring and Logging**:
   - **Prometheus**: Collects and stores metrics from various services.
   - **Grafana**: Visualizes metrics and provides a dashboard for monitoring.
   - **Loki**: Aggregates logs from different microservices for centralized logging.

4. **Container Management**:
   - **ECS (Elastic Container Service)**: Orchestrates Docker containers in AWS.
   - **Fargate**: Serverless compute engine for running containers without managing servers.
   - **Builder Container**: Environment for building applications.
   - **S3 Deployer**: Deploys applications to AWS S3.

5. **Object Storage**:
   - **AWS S3**: Used for storing static assets, application artifacts, and other objects.

6. **Streaming and Caching**:
   - **Kafka**: Manages real-time data streams between components.
   - **Redis**: In-memory data store used for caching and quick data retrieval.

7. **Frontend Applications**:
   - **Project 1, Project 2, Project 3**: Frontend applications built using React.
   - **Web Proxy Service**: Intermediary that routes client requests to appropriate services.
   - **Browser**: Represents end-user interaction via web browsers.

### Data Flow and Interaction

1. **Microservices Interaction**:
   - Microservices (Auth, Subscription, Payment, Project) are registered with Eureka for service discovery.
   - These microservices communicate with each other directly or through database interactions with PostgreSQL and MongoDB.

2. **Monitoring and Logging**:
   - Prometheus collects metrics from microservices and other components.
   - Grafana provides a dashboard for visualizing these metrics, aiding in monitoring the health and performance of the system.
   - Loki aggregates logs from microservices for centralized logging, simplifying debugging and tracking issues.

3. **Container Management and Deployment**:
   - Applications are built within Builder Containers orchestrated by ECS.
   - Fargate runs these containers serverlessly, handling the underlying infrastructure.
   - Completed builds are deployed to AWS S3 using the S3 Deployer, making them available for use.

4. **Frontend Delivery**:
   - Built frontend applications (Project 1, 2, and 3) are stored in AWS S3.
   - A Web Proxy Service routes requests from users' browsers to the appropriate frontend applications.
   - End users access these applications through their web browsers, ensuring smooth delivery and interaction.

5. **Streaming and Caching**:
   - Kafka manages data streams, allowing real-time data processing and communication between components.
   - Redis caches frequently accessed data, enhancing performance by reducing database load.

6. **Data Storage**:
   - PostgreSQL is used for relational data storage, supporting structured queries and transactions.
   - MongoDB stores unstructured data, providing flexibility for various data types.
   - AWS S3 stores static assets and application artifacts, ensuring durable and scalable storage.

The DxDeployer Platform as a Code architecture leverages modern technologies and best practices to create a robust, scalable, and efficient system for application development and deployment. By integrating microservices, containerization, monitoring, and real-time data processing, the platform ensures reliable and high-performance delivery of applications to end users.