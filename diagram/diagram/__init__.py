# diagram.py
from diagrams import Diagram
from diagrams.aws.compute import ECS, Fargate
from diagrams.aws.storage import S3
from diagrams.onprem.container import Docker
from diagrams.onprem.queue import Kafka
from diagrams.onprem.database import PostgreSQL, MongoDB
from diagrams.programming.framework import Spring, React
from diagrams import Cluster
from diagrams.programming.language import Java
from diagrams.custom import Custom
from diagrams.onprem.monitoring import Prometheus, Grafana
from diagrams.onprem.logging import Loki
from diagrams.onprem.inmemory import Redis
from diagrams.generic.device import Mobile, Tablet
from urllib.request import urlretrieve

with Diagram(name= "DxD Platfrom as a code", show=False):
    urlretrieve("https://cdn.dribbble.com/users/79821/screenshots/1150481/flat-browser-icons_1x.jpg", "image/browser.jpg")
    urlretrieve("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8HYPppZX_HJQGi7YNZrVqsqjbY9IMvlg4nA-NcLlxluBwgO7UoW6mRY28c4iTGvPCEPQ&usqp=CAU", "image/spring-cloud.jpg")
    browser = Custom("Browser", "image/browser.jpg")
    spring_cloud = Custom("Eureka", "image/spring-cloud.jpg")
    storage = S3("Object Storage")

    postgres = PostgreSQL("Database")
    mongo = MongoDB("MongoDB")


    with Cluster("Monitoring"):
        prom = Prometheus("Prometheus")
        graphana = Grafana("Grafana")
        loki = Loki("Loki")
        prom >> graphana
        graphana >> loki

    with Cluster("Microservices"):
        auth_microservice = Spring("Auth\nMicroservice")
        subscription_microservice = Spring("Subscription\nMicroservice")
        project_microservice = Spring("Project\nMicroservice")
        payment_microservice = Spring("Payment\nMicroserive")
        
        microservice = [auth_microservice, subscription_microservice, project_microservice,payment_microservice  ]

    microservice >> prom

    with Cluster("Farget"):
        f1 = Fargate("Fargate")
        f2 = Fargate("Fargate")
        f3 = Fargate("Fargate")
        farget = [
            f1 >> Docker("Builder\nContainer") >> Java("S3 Deployer"),
            f2 >> Docker("Builder\nContainer") >> Java("S3 Deployer"),
            f3 >> Docker("Builder\nContainer") >> Java("S3 Deployer")
        ]


    with Cluster("Output",direction="TB"):
        projects = [
            React("Project 3"),
            React("Project 1"),
            React("Project 2"),
        ]
    

    with Cluster("Strean"):
        kafka = Kafka("Kafka")
        redis = Redis("Redis")
        kafka - redis
   
    
    farget >> kafka 
    redis >> mongo
    # redis >> postgres
    
    spring_cloud >> microservice
    # project_microservice >> postgres

    container_service = ECS("ECS")
    
    project_microservice >> container_service
    container_service >> f1
    container_service >> f2
    container_service >> f3

    farget >> storage 
    storage - projects
    projects >> Spring("Web Proxy\nService") >> browser
    microservice >> postgres
    project_microservice >> mongo
