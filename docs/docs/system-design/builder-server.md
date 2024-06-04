---
title: Builder Server
sidebar_position: 2
---

## Overview

The builder service is a crucial component of the dxDeployer platform, responsible for automating the build and deployment process of React applications. This service handles the compilation of the project, uploads the build artifacts to AWS S3, and logs the entire process for monitoring and debugging purposes.

### Key Components

- **Environment Variables:** Used for configuration and authentication.
- **AWS S3 Client:** Handles the upload of build artifacts.
- **Logging:** Publishes logs to a console (and potentially Redis for centralized logging).

### Environment Variables

The service relies on several environment variables for configuration:

- `REDIS_URL`: The URL for connecting to the Redis instance (commented out in the script but used for potential future logging).
- `AWS_REGION`: The AWS region where the S3 bucket is located.
- `ACCESS_KEY`: The AWS access key for authenticating with S3.
- `SECRET_ACCESS_KEY`: The AWS secret access key for authenticating with S3.
- `PROJECT_ID`: A unique identifier for the project being deployed.
- `BUCKET_NAME`: The name of the S3 bucket where build artifacts will be uploaded.

### Initialization

The `init` function is the main entry point of the builder service. It performs the following tasks:

1. **Start the Build Process:**
   - Changes the directory to the output folder.
   - Installs necessary dependencies using `npm install`.
   - Runs the build script using `npm run build`.

2. **Logging:**
   - Logs various stages of the build process to the console.
   - Publishes logs (potentially to Redis) for monitoring purposes.

3. **Upload to S3:**
   - Reads the contents of the `dist` folder.
   - Uploads each file to a designated S3 bucket.
   - Sets the appropriate MIME type for each file using the `mime-types` library.

### Detailed Code Analysis

```javascript
const { exec } = require('child_process');
const path = require('path');
const fs = require('fs');
const { S3Client, PutObjectCommand } = require('@aws-sdk/client-s3');
const mime = require('mime-types');
const Redis = require('ioredis');

const REDIS_URL = process.env.PROJECT_ID;
const AWS_REGION = process.env.AWS_REGION;
const ACCESS_KEY = process.env.ACCESS_KEY;
const SECRET_ACCESS_KEY = process.env.SECRET_ACCESS_KEY;
const PROJECT_ID = process.env.PROJECT_ID;
const BUCKET_NAME = process.env.BUCKET_NAME;
// const publisher = new Redis(REDIS_URL);

const s3Client = new S3Client({
    region: AWS_REGION,
    credentials: {
        accessKeyId: ACCESS_KEY,
        secretAccessKey: SECRET_ACCESS_KEY
    }
});

function publishLog(log) {
    console.log(log);
    // publisher.publish(`logs:${PROJECT_ID}`, JSON.stringify({ log }));
}

async function init() {
    console.log('Executing script.js');
    publishLog('Build Started...');
    const outDirPath = path.join(__dirname, 'output');

    const p = exec(`cd ${outDirPath} && npm install && npm run build`);

    p.stdout.on('data', function (data) {
        console.log(data.toString());
        publishLog(data.toString());
    });

    p.stdout.on('error', function (data) {
        console.log('Error', data.toString());
        publishLog(`error: ${data.toString()}`);
    });

    p.on('close', async function () {
        console.log('Build Complete');
        publishLog(`Build Complete`);
        console.log("dirname", __dirname);
        const distFolderPath = path.join(__dirname, 'output', 'dist');
        const distFolderContents = fs.readdirSync(distFolderPath, { recursive: true });

        publishLog(`Starting to upload`);
        for (const file of distFolderContents) {
            const filePath = path.join(distFolderPath, file);
            if (fs.lstatSync(filePath).isDirectory()) continue;

            console.log('uploading', filePath);
            publishLog(`uploading ${file}`);

            const command = new PutObjectCommand({
                Bucket: BUCKET_NAME,
                Key: `__outputs/${PROJECT_ID}/${file}`,
                Body: fs.createReadStream(filePath),
                ContentType: mime.lookup(filePath)
            });

            await s3Client.send(command);
            publishLog(`uploaded ${file}`);
            console.log('uploaded', filePath);
        }
        publishLog(`Done`);
        console.log('Done...');
    });
}
```

### Functionality Breakdown

1. **Dependencies and Setup:**
   - `child_process`, `path`, `fs`: Node.js modules for executing commands, handling file paths, and reading/writing files.
   - `@aws-sdk/client-s3`: AWS SDK for S3 interactions.
   - `mime-types`: Library to determine MIME types of files.
   - `ioredis`: Redis client for potential logging.

2. **Environment Variables:**
   - Configuration values are fetched from environment variables to keep sensitive information secure and make the service configurable.

3. **AWS S3 Client:**
   - Configures an S3 client using the provided AWS credentials and region.

4. **Logging Function:**
   - `publishLog`: Logs messages to the console (and potentially to Redis).

5. **Main Function (`init`):**
   - Logs the start of the build process.
   - Executes build commands in the `output` directory.
   - Captures and logs stdout and errors from the build process.
   - Upon build completion, reads files from the `dist` directory.
   - Uploads each file to S3, setting the correct MIME type.
   - Logs the upload progress and completion.

### Conclusion

The builder service is designed to automate the deployment of React applications by compiling the project and uploading the build artifacts to AWS S3. It provides robust logging for monitoring the build and deployment process, ensuring transparency and ease of debugging.