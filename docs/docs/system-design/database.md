---
title: Database Schema
sidebar_position: 5
---


## Overview

This documentation provides an overview of the database schema used in the dxDeployer platform. The schema is designed to manage users, their projects, deployments, subscriptions, and transactions. The following sections describe each table, its fields, and relationships with other tables.

### Enums

#### `project_status`

- `running`
- `failed`
- `deployed`

#### `plan`

- `free`
- `paid`
- `enterprice`

### Tables

#### User Table

The `user` table stores information about the users of the dxDeployer platform.

| Column     | Type      | Constraints                     |
|------------|-----------|---------------------------------|
| `id`       | bigserial | Primary Key, Auto Increment     |
| `username` | varchar   | Not Null                        |
| `email`    | varchar   | Unique                          |
| `createdAt`| timestamp | Default: 'now()'                |

#### Project Table

The `project` table stores information about the projects created by users.

| Column     | Type          | Constraints                     |
|------------|---------------|---------------------------------|
| `id`       | bigserial     | Primary Key                     |
| `userId`   | bigint        | Foreign Key, References `user.id`|
| `name`     | varchar       | Not Null                        |
| `url`      | varchar       | Not Null                        |
| `status`   | project_status|                                 |
| `createAt` | timestamp     | Default: 'now()'                |

#### Deployments Table

The `deployments` table stores information about the deployments associated with projects.

| Column         | Type     | Constraints                     |
|----------------|----------|---------------------------------|
| `id`           | bigserial| Primary Key                     |
| `deploymentId` | uuid     |                                 |
| `projectId`    | bigint   | Foreign Key, References `project.id`|

#### Subscription Table

The `subscription` table stores information about the subscriptions of users.

| Column         | Type     | Constraints                     |
|----------------|----------|---------------------------------|
| `id`           | bigserial| Primary Key                     |
| `userId`       | bigint   | Foreign Key, References `user.id`|
| `current_plan` | plan     | Default: 'free'                 |
| `expiry`       | timestamp|                                 |

#### Transaction Table

The `transaction` table stores information about the transactions made by users.

| Column        | Type      | Constraints                     |
|---------------|-----------|---------------------------------|
| `id`          | bigserial | Primary Key                     |
| `userId`      | bigint    | Foreign Key, References `user.id`|
| `status`      | varchar   | Not Null                        |
| `time`        | timestamp |                                 |
| `plan_choosed`| plan      |                                 |
| `amount`      | decimal   |                                 |

## Relationships

- **User and Project**: One-to-Many relationship. A user can have multiple projects. The `project` table references the `user` table via `userId`.
- **Project and Deployments**: One-to-Many relationship. A project can have multiple deployments. The `deployments` table references the `project` table via `projectId`.
- **User and Subscription**: One-to-One relationship. A user can have only one subscription. The `subscription` table references the `user` table via `userId`.
- **User and Transaction**: One-to-Many relationship. A user can have multiple transactions. The `transaction` table references the `user` table via `userId`.

## Schema

```sql
-- Enums
CREATE TYPE project_status AS ENUM ('running', 'failed', 'deployed');
CREATE TYPE plan AS ENUM ('free', 'paid', 'enterprice');

-- User Table
CREATE TABLE user (
    id bigserial PRIMARY KEY,
    username varchar NOT NULL,
    email varchar UNIQUE,
    createdAt timestamp DEFAULT 'now()'
);

-- Project Table
CREATE TABLE project (
    id bigserial PRIMARY KEY,
    userId bigint REFERENCES user(id),
    name varchar NOT NULL,
    url varchar NOT NULL,
    status project_status,
    createAt timestamp DEFAULT 'now()'
);

-- Deployments Table
CREATE TABLE deployments (
    id bigserial PRIMARY KEY,
    deploymentId uuid,
    projectId bigint REFERENCES project(id)
);

-- Subscription Table
CREATE TABLE subscription (
    id bigserial PRIMARY KEY,
    userId bigint REFERENCES user(id),
    current_plan plan DEFAULT 'free',
    expiry timestamp
);

-- Transaction Table
CREATE TABLE transaction (
    id bigserial PRIMARY KEY,
    userId bigint REFERENCES user(id),
    status varchar NOT NULL,
    time timestamp,
    plan_choosed plan,
    amount decimal
);
```

## Conclusion

This database schema is designed to efficiently manage the key entities and their relationships within the dxDeployer platform. It ensures data integrity through the use of foreign keys and provides a scalable structure for storing user, project, deployment, subscription, and transaction data.