const express = require('express')
const httpProxy = require('http-proxy')

const app = express()
const PORT = 8000
const BUCKET_NAME = 'dxployer'
const AWS_REGION = 'ap-south-1'

const BASE_PATH = `https://${BUCKET_NAME}.s3.${AWS_REGION}.amazonaws.com/__outputs`

const proxy = httpProxy.createProxy()

app.use((req, res) => {
    const hostname = req.hostname;
    const subdomain = hostname.split('.')[0];

    //TODO: Custom Domain - DB Query

    const resolvesTo = `${BASE_PATH}/${subdomain}`

    return proxy.web(req, res, { target: resolvesTo, changeOrigin: true })

})

proxy.on('proxyReq', (proxyReq, req, res) => {
    const url = req.url;
    if (url === '/')
        proxyReq.path += 'index.html'

})

app.listen(PORT, () => console.log(`Reverse Proxy Running..${PORT}`))