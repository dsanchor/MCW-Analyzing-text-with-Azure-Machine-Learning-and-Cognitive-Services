<!-- se puede cambiar el fromato? -->
<div class="MCWHeader1">
Analyzing text with Azure Machine Learning and Cognitive Services
</div>

<div class="MCWHeader2">
Setup guide
</div>

<div class="MCWHeader3">
March 2022
</div>

Information in this document, including URL and other Internet Web site references, is subject to change without notice. Unless otherwise noted, the example companies, organizations, products, domain names, e-mail addresses, logos, people, places, and events depicted herein are fictitious, and no association with any real company, organization, product, domain name, e-mail address, logo, person, place or event is intended or should be inferred. Complying with all applicable copyright laws is the responsibility of the user. Without limiting the rights under copyright, no part of this document may be reproduced, stored in or introduced into a retrieval system, or transmitted in any form or by any means (electronic, mechanical, photocopying, recording, or otherwise), or for any purpose, without the express written permission of Microsoft Corporation.

Microsoft may have patents, patent applications, trademarks, copyrights, or other intellectual property rights covering subject matter in this document. Except as expressly provided in any written license agreement from Microsoft, the furnishing of this document does not give you any license to these patents, trademarks, copyrights, or other intellectual property.

The names of manufacturers, products, or URLs are provided for informational purposes only and Microsoft makes no representations and warranties, either expressed, implied, or statutory, regarding these manufacturers or the use of the products with any Microsoft technologies. The inclusion of a manufacturer or product does not imply endorsement of Microsoft of the manufacturer or product. Links may be provided to third party sites. Such sites are not under the control of Microsoft and Microsoft is not responsible for the contents of any linked site or any link contained in a linked site, or any changes or updates to such sites. Microsoft is not responsible for webcasting or any other form of transmission received from any linked site. Microsoft is providing these links to you only as a convenience, and the inclusion of any link does not imply endorsement of Microsoft of the site or the products contained therein.

© 2022 Microsoft Corporation. All rights reserved.

Microsoft and the trademarks listed at <https://www.microsoft.com/en-us/legal/intellectualproperty/Trademarks/Usage/General.aspx> are trademarks of the Microsoft group of companies. All other trademarks are property of their respective owners.

**Contents**



- [Cloud Native text analytics platform setup guide](#cloud-native-text-analytics-platform-setup-guide)
  - [Requirements](#requirements)
  - [Setup guide](#setup-guide)
    - [Task 1: Create a resource group](#task-1-create-a-resource-group)
    - [Task 2: Provision a Text Analytics API](#task-2-provision-a-text-analytics-api)
    - [Task 3: Provision an Azure Container Registry](#task-3-provision-an-azure-container-registry)
    - [Task 4: Create an Azure Machine Learning workspace](#task-4-create-an-azure-machine-learning-workspace)
    - [Task 5: Create an Azure Kubernetes Service](#task-5-create-an-azure-kubernetes-service)
    - [Task 6: Create compute instance and configure AKS compute in Azure Machine Learning Studio](#task-6-create-compute-instance-and-configure-aks-compute-in-azure-machine-learning-studio)
    - [Task 7: Deploy a CosmosDB instance](#task-7-deploy-a-cosmosdb-instance)
    - [Task 8: Import the lab notebooks](#task-8-import-the-lab-notebooks)
    - [Task 9: Setup lab environment](#task-9-setup-lab-environment)



# Cloud Native text analytics platform setup guide

## Requirements

1. You will need an Azure subscription with permissions to deploy resource groups and resources into them.

   - Trial subscriptions will not work. You will run into issues with Azure resource quota limits. FIXME: estamos seguros de esto?
   - Subscriptions with access limited to a single resource group will not work. You need the ability to deploy multiple resource groups.

## Setup guide

Duration: 45 minutes FIXME:revisar

In this exercise, you set up your environment for use in the rest of the deployment. You should follow all steps provided in the setup guide.

> **Important**: Many Azure resources require globally unique names. Throughout these steps, the word "SUFFIX" appears as part of resource names. You should replace this with your Microsoft alias, initials, or other value to ensure uniquely named resources. FIXME:revisar

### Task 1: Create a resource group

1. In the [Azure portal](https://portal.azure.com), select **Resource groups** from the Azure services list.

   ![Resource groups are highlighted in the Azure services list.](media/azure-services-resource-groups.png "Azure services")

2. On the Resource groups blade, select **+Add**.

   ![+Add is highlighted in the toolbar on Resource groups blade.](media/resource-groups-add.png "Resource groups")

3. On the Create a resource group **Basics** tab, enter the following:

   - **Subscription**: Select the subscription you are using.
   - **Resource group**: Enter `hands-on-lab` as the name of the new resource group.
   - **Region**: Select the region where you want to deploy your resources.

   ![The values specified above are entered into the Create a resource group Basics tab.](media/create-resource-group.png "Create resource group")

4. Select **Review + Create**.

5. On the **Review + create** tab, ensure the Validation passed message is displayed and then select **Create**.

### Task 2: Provision a Text Analytics API

In this task, you create a Text Analytics API, which will be integrated into your final POC.

1. In the [Azure portal](https://portal.azure.com/), select the **Show portal menu** icon and then choose **+Create a resource** from the menu.

   ![The Show portal menu icon is highlighted, and the portal menu is displayed. Create a resource is highlighted in the portal menu.](media/create-a-resource.png "Create a resource")

2. Select **AI + Machine Learning** in the Azure Marketplace list and then select **Language service, Create** from the featured services list.

    ![In the New resource blade, AI + Machine Learning is selected under the Azure Marketplace and Language service is highlighted under the featured services.](media/create-resource-text-analytics.png "Language service")

3. On the `Select additional features` tab, select **Continue to create your resource**.

4. On the `Create` tab, provide the following:

    Project details:

    - **Subscription**: Select the subscription you are using.
    - **Resource group**: Select the hands-on-lab resource group from the dropdown list.

    Instance Details:

    - **Region**: Select the region you used for the rg-text-analytics-SUFFIX resource group.
    - **Name:** Provide a unique name for this instance, such as ta-SUFFIX.
    - **Pricing tier**: Free F0 (5K Transactions per 30 days).
    - **Legal terms**: Checked
    - **Responsible AI Notice**: Checked

    ![The Create Text Analytics Basics tab is populated with the values specified above.](media/create-text-analytics.png "Create Text Analytics")

5. Select **Review + create**.

6. Ensure validation passes and then select **Create** on the `Review + create` tab.

### Task 3: Provision an Azure Container Registry 

In this task, you provision the Azure Container Registry where docker images will be pushed and pulled from different components of these lab. 

1. Sign into [Azure portal](https://portal.azure.com) by using the credentials for your Azure subscription.

2. In the upper-left corner of the Azure portal, select **+ Create a resource**.

3. Use the search bar to find the **Container Registry**.

4. Select **Container Registry**.

5. In the **Container Registry** pane, select **Create** to begin.

6. Provide the following information to configure your new workspace:

   - **Subscription**: Select the Azure subscription that you want to use.

   - **Resource group**: Use an existing resource group in your subscription or enter a name to create a new resource group. A resource group holds related resources for an Azure solution. In this example, we use **hands-on-lab**.

   - **Registry name**: Enter a unique name that identifies your registry. In this example, we use **mlacr202203**. This name must be globally unique.

   - **Location**: Select the location closest to your users and the data resources to create your workspace.

   - **SKU**: Leave the default one (Standard).

   ![The Azure Container Registry Create form is displayed populated with the aforementioned values. The Review + Create button is highlighted.](media/bhol-acr.png 'Create Azure Container Registry page')


### Task 4: Create an Azure Machine Learning workspace

In this task, you provision the Azure Machine Learning workspace.

1. Sign into [Azure portal](https://portal.azure.com) by using the credentials for your Azure subscription.

2. In the upper-left corner of the Azure portal, select **+ Create a resource**.

3. Use the search bar to find the **Machine Learning**.

4. Select **Machine Learning**.

5. In the **Machine Learning** pane, select **Create** to begin.

   ![The Machine Learning page displays with the Create button selected.](media/bhol-01.png 'Open Create Azure Machine Learning Workspace')

6. Provide the following information to configure your new workspace:

   - **Subscription**: Select the Azure subscription that you want to use.

   - **Resource group**: Use an existing resource group in your subscription or enter a name to create a new resource group. A resource group holds related resources for an Azure solution.

   - **Workspace name**: Enter a unique name that identifies your workspace. In this example, we use **ml-wksp**. Names must be unique across the resource group. Use a name that's easy to recall and to differentiate from workspaces created by others.

   - **Location**: Select the location closest to your users and the data resources to create your workspace.

   - **Container registry**: Select an existing container registry in your subscription (We created on in previous step).

   ![The Machine Learning Create form is displayed populated with the aforementioned values. The Review + Create button is highlighted.](media/bhol-02.png 'Create Azure Machine Learning Workspace page')

7. After you are finished configuring the workspace, select **Review + Create**. Select **Create** after you review the fields you just entered.

    > **Note**: It can take several minutes to create your workspace in the cloud.

    When the process is finished, a deployment success message appears.

8. To view the new workspace, select **Go to resource**.

9. Navigate to the [Azure Machine Learning Studio](https://ml.azure.com) and select the workspace that you created or select **Launch now** under **Try the new Azure Machine Learning studio** in the **Overview** section of your Azure Machine Learning workspace.

   ![The Machine Learning resource page is shown with Overview selected from the left menu, and the Launch now button highlighted in the Overview screen.](media/bhol-03.png 'Launch the Azure Machine Learning studio')

### Task 5: Create an Azure Kubernetes Service

In this task, you will create an Azure Kubernetes Service that it will be used as a compute resource to your Azure Machine Learning workspace and also, the Kubernetes cluster for a set of microservices.


1. Sign into [Azure portal](https://portal.azure.com) by using the credentials for your Azure subscription.

2. In the upper-left corner of the Azure portal, select **+ Create a resource**.

3. Use the search bar to find the **Kubernetes Service**.

4. Select **Kubernetes Service**.

5. In the **Kubernetes Service** pane, select **Create** to begin.

   ![The AKS page displays with the Create button selected.](media/bhol-aks01.png 'Open Create Kubernetes service')

6. Provide the following information to configure your new AKS. Leave defaults if not specified below:

   - **Subscription**: Select the Azure subscription that you want to use.

   - **Resource group**: Use an existing resource group in your subscription or enter a name to create a new resource group. A resource group holds related resources for an Azure solution. In this example, we use **hands-on-lab**.

   - **Cluster preset configuration**: Leave default (**Standard ($$)**).
  
   - **Kubernetes cluster name**: Enter a unique name that identifies your cluster. In this example, we use **mlaks**.

   - **Region**: Select the location closest to your users and the data resources to create your workspace.

   - **SKU**: Leave the default one (Standard).

   - **Node count range**: Set a minimum of 3 nodes.

   ![The Azure Kubernetes Service Create form is displayed populated with the aforementioned values. The Next: Node pools is highlighted.](media/bhol-aks02.png 'Create Azure Kubernetes Service page')

7. Move to **Networking** tab and mark **Enable HTTP application routing**. 

   NOTE: This will create a simple Kubernetes Ingress controller for testing purposes. Do not use this option in a production enviroment. [Learn more.](https://docs.microsoft.com/en-us/azure/aks/http-application-routing)

8. Next, in the **Integrations** tab: 

   - **Container registry**: Select the Azure Container Registry we previously created.
   - **Container Monitoring**: You can keep it enabled, as recommendation for any production cluster. If for this lab you want to disable it to avoid some extra costs, martk the **Disabled** radio button.

   And finally, create the AKS cluster.

   ![The Azure Kubernetes Service final form is displayed populated with the aforementioned values. The Review + Create button is highlighted.](media/bhol-aks03.png 'Create Azure Kubernetes Service page')



### Task 6: Create compute instance and configure AKS compute in Azure Machine Learning Studio

1. In the new Azure Machine Learning studio window, select **Create new** and then select **Compute instance** from the context menu.

   ![Within Azure Machine Learning Studio, Create new is selected and highlighted, and Compute instance is highlighted in the context menu.](media/machine-learning-studio-create-new-compute-instance.png "Create new compute instance")

2. On the create compute instance screen, enter the following information:

   - **Compute name**: Enter csdl-compute-SUFFIX, where SUFFIX is your Microsoft alias, initials, or other value to ensure uniquely named resources.
   - **Virtual machine size**: Standard_DS3_v2

   ![On the create compute instance dialog, CPU is selected for the virtual machine type. Select from recommended options is selected under virtual machine size, and Standard_DS3_v2 is selected and highlighted in the recommended virtual machine sizes.](media/compute-instance-01.png "Create compute instance")

3. Select **Create** and wait for the Compute Instance to be ready. It takes approximately 3-5 minutes for the compute provisioning to complete.

4. Within the Azure Machine Learning studio interface, under the **Manage** section on the left, select **Compute**, and go to **Inference Clusters**.

![Within the Azure Machine Learning studio interface, the sections are highlighted to select](media/attach-aks.png "Go to inference clusters")

5. Click on **+ New** to create a new AKS or use an exisiting one previously created. You will need to assign a unique name within AML that will be used later on to select this specific cluster for deployment. 

### Task 7: Deploy a CosmosDB instance

In this task, you will create an CosmosDb instance with a single database, that will contain a Claims container to hold all the information of future claims.


1. Sign into [Azure portal](https://portal.azure.com) by using the credentials for your Azure subscription.

2. In the upper-left corner of the Azure portal, select **+ Create a resource**.

3. Use the search bar to find the **Cosmos Db**.

4. Select **Azure Cosmos Db**.

5. In the **Azure Cosmos Db** pane, select **Create** to begin.

   ![The Cosmos Db page displays with the Create button selected.](media/bhol-cosmos01.png 'Open Create Azure Cosmos Db service')

6. Then, within the **Select API option**, select **Core (SQL) - Recommended** and **Create**.

   ![The Cosmos Db select API  page displays with the Create button selected.](media/bhol-cosmos02.png 'Open Select API option')

7. Provide the following information to configure your new Cosmos Db instance   :

   - **Subscription**: Select the Azure subscription that you want to use.

   - **Resource group**: Use an existing resource group in your subscription or enter a name to create a new resource group. A resource group holds related resources for an Azure solution. In this example, we use **hands-on-lab**.

   - **Account name**: Set **claimscoresql**.

   - **Location**: Select the location closest to your users and the data resources to create your workspace.

   - **Capacity mode**: To simplify configuration, set **Serverless** mode. To learn more about **Capacity modes**, have a look at the [official documentation](https://docs.microsoft.com/en-us/azure/cosmos-db/throughput-serverless).


   ![The Azure CosmosDb Create form is displayed populated with the aforementioned values.](media/bhol-cosmos03.png 'Create Azure CosmosDb page')

8. Select **Review + Create**.

9. On the **Review + create** tab, ensure the Validation passed message is displayed and then select **Create**.


### Task 8: Import the lab notebooks

TODO EEMPLAZAR LINK CON EL REPO DE DAVID

In this task, you import Jupyter notebooks from GitHub that you will use to complete the exercises in this hands-on lab.

1. Select the Compute Instance, **csdl-compute-SUFFIX**, and then select **Jupyter** link to open Jupyter Notebooks interface.

   ![The Jupyter link is highlighted next to the csdl-compute-SUFFIX compute instance.](media/ml-workspace-compute-instances.png "Compute instances")

2. Check **Yes, I understand** and select **Continue** in the trusted code dialog.

   ![In the Always use trusted code dialog, Yes, I understand is checked, and the continue button is highlighted.](media/trusted-code-dialog.png "Always use trusted code")

3. In the new Jupyter window, select **New** and then select **Terminal** from the context menu.

   ![In the Jupyter notebooks interface, the New dropdown is selected, and Terminal is highlighted in the context menu.](media/jupyter-new-terminal.png "Open new terminal window")
  
4. Run the following commands in order in the terminal window:

   - `mkdir ta-repo-csdl`
   - `cd ta-repo-csdl`
   - `git clone https://github.com/microsoft/MCW-Analyzing-text-with-azure-machine-learning-and-cognitive-services.git`

5. Wait for the `clone` command to finish importing the repo.

### Task 9: Setup lab environment

TODO REVIEW

1. From the terminal window run the following commands (assuming you are in the `ta-repo-csdl` folder):

   - `cd "MCW-Analyzing-text-with-azure-machine-learning-and-cognitive-services/Hands-on lab/notebooks"`
   - `conda activate azureml_py38`
   - `pip install --upgrade pip`
   - `pip install -r requirements.txt`

    > Note: You can safely ignore any dependency errors during installation.

