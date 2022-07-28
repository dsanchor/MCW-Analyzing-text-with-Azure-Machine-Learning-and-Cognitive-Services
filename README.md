# Cloud Native Text Analytics Platform

## Building a Cloud Native Text Analytics Platform using Azure Machine Learning, Cognitive Services and Power Apps


In this workshop, you will help Contoso Ltd., an insurance company, build a proof of concept that shows how they can develop a solution that amplifies their agents' claims processing capabilities. 

This repository provides all the necessary resources for developers, data scientists and citizen developers to deploy the required cloud infrastructure and configure all services involved. 

The final solution architecture looks like the following diagram.


![The High-level architectural solution begins with a Claim, which points to Claims submission WebApp. The WebApp then points to Text Analytics, and Containerized Services, which includes a Classification Service and a Summary Service that both processes claim text.](media/new_arch_extended.png "High-level architectural solution")

Let's have a look at the guides you will work through.

1. [Setup Guide](/Setup%20Guide.md)

This guide will help you to deploy the cloud infrastructure to your Azure Subscription before starting with the hands on configurations. You will find all necessary pre-requisites and instructions to prepare your resource group with computing, data, AI and storage capabilities. 

Remember to plan this deployments ahead of time to allow provisioning delays.


2. [Deployment Step by Step](/Deployment%20Step-by-Step.md)

Once the resources are up and running, we can proceed with the hands-on-lab. Here we will find the combination of exercises and tasks to configure a fully functioning cloud native application with cognitive capabilities. 

3. Resource folders

In the remaining folders you will find the remaining code and templates to utilize during the deployment.
