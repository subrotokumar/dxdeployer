---
title: How does it work
sidebar_position: 2
---

The system starts with an Angular-based frontend where users interact with the application. Requests from the frontend are routed through an API Gateway, which directs these requests to the appropriate microservices, such as the Accounts, Project, Subscription, and Payment microservices. Each of these microservices is containerized using Docker and orchestrated with AWS's Elastic Container Service (ECS) and Fargate for serverless compute capabilities.

The Accounts Microservice handles user account operations, interacting with its dedicated PostgreSQL database. Similarly, the Project Microservice manages project-related tasks and stores data in its PostgreSQL instance. The Subscription Microservice oversees user subscriptions and coordinates with the Accounts and Project microservices while maintaining its own PostgreSQL database. The Payment Microservice processes transactions and publishes payment confirmation messages to Kafka, a distributed streaming platform.

Kafka plays a critical role in managing asynchronous communication between microservices. It organizes messages into topics, enabling microservices like the Payment and Subscription services to publish events. The Notification Service, which subscribes to these Kafka topics, processes incoming messages and sends out notifications. This service stores notification data in MongoDB, ensuring persistence and traceability.

For monitoring and logging, Prometheus collects metrics from all services, while Grafana visualizes these metrics to provide a clear overview of system health. Loki aggregates logs from various services for centralized logging, simplifying debugging and issue resolution. Service discovery and configuration are handled by a Discovery Server and a Config Server, respectively. The Discovery Server registers all microservices, allowing them to locate each other dynamically, while the Config Server manages configuration settings stored in a version-controlled repository like GitHub.

Zipkin is employed for distributed tracing, offering visibility into the flow of requests across microservices. This tracing capability is crucial for monitoring performance and diagnosing issues within the system. In essence, DxDeployer leverages a combination of containerization, asynchronous messaging, and robust monitoring to create a scalable, reliable, and maintainable microservices architecture, ensuring efficient application development and deployment.