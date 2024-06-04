---
title: Dependency
sidebar_position: 1
---

# Dependency Requirements

To ensure that dxDeployer operates efficiently and effectively, several dependencies need to be installed and configured. This document outlines the required dependencies and provides brief installation guidelines for each.

## 1. Container Engine (Docker)

### Overview
Docker is essential for creating and managing containers, which encapsulate the application environment for dxDeployer.

### Installation
- **Windows and macOS:** [Docker Desktop](https://www.docker.com/products/docker-desktop)
- **Linux:** Install using the package manager (e.g., `apt` for Debian-based systems or `yum` for Red Hat-based systems).

```bash
# Example for Ubuntu
sudo apt-get update
sudo apt-get install docker-ce docker-ce-cli containerd.io
```

### Verification
```bash
docker --version
```

## 2. Java 21

### Overview
Java 21 is required for running certain backend services within dxDeployer.

### Installation
- **Windows and macOS:** Download from [Oracle's official site](https://www.oracle.com/java/technologies/javase-jdk21-downloads.html).
- **Linux:** Install using the package manager.

```bash
# Example for Ubuntu
sudo apt-get update
sudo apt-get install openjdk-21-jdk
```

### Verification
```bash
java -version
```

## 3. Node.js 21

### Overview
Node.js 21 is required for running the frontend and other JavaScript-based services.

### Installation
- Download and install from the [Node.js official site](https://nodejs.org/).

```bash
# Example using nvm (Node Version Manager)
nvm install 21
nvm use 21
```

### Verification
```bash
node -v
```

## 4. AWS Account

### Overview
An AWS account is required for deploying and managing resources in the cloud.

### Setup
- Sign up at [AWS](https://aws.amazon.com/).
- Configure AWS CLI with your account credentials.

```bash
# Install AWS CLI
pip install awscli

# Configure AWS CLI
aws configure
```

## 5. PostgreSQL

### Overview
PostgreSQL is used as a relational database for storing structured data.

### Installation
- **Windows and macOS:** Download from the [PostgreSQL official site](https://www.postgresql.org/download/).
- **Linux:** Install using the package manager.

```bash
# Example for Ubuntu
sudo apt-get update
sudo apt-get install postgresql postgresql-contrib
```

### Verification
```bash
psql --version
```

## 6. MongoDB

### Overview
MongoDB is used as a NoSQL database for storing unstructured data.

### Installation
- **Windows and macOS:** Download from the [MongoDB official site](https://www.mongodb.com/try/download/community).
- **Linux:** Install using the package manager.

```bash
# Example for Ubuntu
sudo apt-get install -y mongodb
```

### Verification
```bash
mongo --version
```

## 7. Zipkin

### Overview
Zipkin is used for distributed tracing and monitoring of microservices.

### Installation
- Download the latest Zipkin release from [Zipkin GitHub](https://github.com/openzipkin/zipkin).

```bash
# Example using Docker
docker run -d -p 9411:9411 openzipkin/zipkin
```

### Verification
- Access the Zipkin UI at `http://localhost:9411`.

## 8. Kafka

### Overview
Apache Kafka is used for building real-time data pipelines and streaming applications.

### Installation
- Download from the [Apache Kafka site](https://kafka.apache.org/downloads).

```bash
# Example for Linux
wget https://archive.apache.org/dist/kafka/2.13/kafka_2.13-2.7.0.tgz
tar -xzf kafka_2.13-2.7.0.tgz
cd kafka_2.13-2.7.0
```

### Verification
```bash
bin/kafka-topics.sh --version
```

## 9. Zookeeper

### Overview
Apache Zookeeper is a centralized service for maintaining configuration information and providing distributed synchronization.

### Installation
- Download from the [Apache Zookeeper site](https://zookeeper.apache.org/releases.html).

```bash
# Example for Linux
wget https://downloads.apache.org/zookeeper/zookeeper-3.7.0/apache-zookeeper-3.7.0-bin.tar.gz
tar -xzf apache-zookeeper-3.7.0-bin.tar.gz
cd apache-zookeeper-3.7.0-bin
```

### Verification
```bash
bin/zkServer.sh status
```

## 10. Ansible Vault

### Overview
Ansible Vault is used for encrypting sensitive data such as passwords and keys within Ansible playbooks.

### Installation
- **Windows and macOS:** Follow the instructions on the [Ansible official site](https://docs.ansible.com/ansible/latest/installation_guide/intro_installation.html).
- **Linux:** Install using the package manager.

```bash
# Example for Ubuntu
sudo apt-get update
sudo apt-get install ansible
```

### Usage
- Encrypt a file:
  ```bash
  ansible-vault encrypt <filename>
  ```
- Decrypt a file:
  ```bash
  ansible-vault decrypt <filename>
  ```

### Verification
```bash
ansible --version
```

## Conclusion

Ensure all dependencies are installed and configured correctly to provide a smooth and efficient environment for dxDeployer. If any issues arise during installation, refer to the official documentation of each dependency for troubleshooting and additional support.