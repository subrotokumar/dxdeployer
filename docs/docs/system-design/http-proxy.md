---
title: Http Proxy Server
sidebar_position: 6
---

## Overview

The HTTP Proxy service is a core component of the dxDeployer platform, responsible for routing incoming HTTP requests to the appropriate resources hosted on AWS S3. This service acts as a reverse proxy, forwarding requests to specific subdomains and handling custom domain resolution.

### Key Components

- **Express:** A minimal and flexible Node.js web application framework.
- **http-proxy:** A full-featured HTTP proxy for Node.js.

### Environment Variables and Configuration

The service relies on specific configuration values:

- `PORT`: The port number on which the proxy server listens (default: 8000).
- `BUCKET_NAME`: The name of the S3 bucket where the build artifacts are stored.
- `AWS_REGION`: The AWS region where the S3 bucket is located.
- `BASE_PATH`: Constructed URL for accessing the S3 bucket.

### Functionality

1. **Express Server:**
   - Sets up an Express application to handle incoming HTTP requests.
   - Configures the HTTP proxy middleware to forward requests based on the subdomain.

2. **Subdomain Resolution:**
   - Extracts the subdomain from the request hostname.
   - Constructs the target URL based on the subdomain and the base path.

3. **Proxy Middleware:**
   - Uses `http-proxy` to forward the request to the target URL.
   - Modifies the request path to ensure the correct file (e.g., `index.html`) is served for root requests.

### Detailed Code Analysis

```javascript
const express = require('express');
const httpProxy = require('http-proxy');

const app = express();
const PORT = process.env.PORT;
const BUCKET_NAME = process.env.BUCKET_NAME;
const AWS_REGION = process.env.AWS_REGION;

const BASE_PATH = `https://${BUCKET_NAME}.s3.${AWS_REGION}.amazonaws.com/__outputs`;

const proxy = httpProxy.createProxy();

app.use((req, res) => {
    const hostname = req.hostname;
    const subdomain = hostname.split('.')[0];

    const resolvesTo = `${BASE_PATH}/${subdomain}`;

    return proxy.web(req, res, { target: resolvesTo, changeOrigin: true });
});

proxy.on('proxyReq', (proxyReq, req, res) => {
    const url = req.url;
    if (url === '/') {
        proxyReq.path += 'index.html';
    }
});

app.listen(PORT, () => console.log(`Reverse Proxy Running on port ${PORT}`));
```

### Functionality Breakdown

1. **Dependencies and Setup:**
   - `express`: A web framework for setting up the server.
   - `http-proxy`: A library to create a reverse proxy.

2. **Server Configuration:**
   - `PORT`, `BUCKET_NAME`, and `AWS_REGION`: Configuration values defining the server's behavior and target S3 bucket.

3. **Base Path Construction:**
   - `BASE_PATH`: Constructs the base path to access resources in the S3 bucket based on the provided bucket name and AWS region.

4. **Express Middleware:**
   - `app.use`: Handles all incoming requests, extracts the subdomain, and constructs the target URL.
   - Forwards the request to the constructed target URL using `http-proxy`.

5. **Proxy Request Handling:**
   - `proxy.on('proxyReq')`: Listens to the `proxyReq` event to modify the request path if the root URL is accessed, ensuring that `index.html` is served.

6. **Server Listening:**
   - `app.listen`: Starts the Express server on the specified port and logs a message indicating the server is running.

### Conclusion

The HTTP Proxy service in dxDeployer is designed to efficiently route incoming HTTP requests to the appropriate resources in an S3 bucket based on subdomain resolution. It simplifies the process of serving static files for different projects and provides a flexible architecture for handling custom domain requests. This service is essential for ensuring that deployed React applications are accessible through their respective URLs.