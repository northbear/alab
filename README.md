# Home Assignment ALab

The project starts a Jenkins server with preinstalled plugins and a predefined job. The job tracks commits/merges in a standalone task repository (https://github.com/northbear/alab-pytask) and execute the task defined in a script `app/main.py`.

## Prerequisites

The project should be run in a Linux OS environment and requires: 
* Docker Engine version 19.x and higher (20.x recommended);
* docker-compose version 2.x or higher (2.11.x recommended). 
* a user that starts the project should be a member of a group `docker`. 

Ensure that a directory `/var/jenkins_home` doesn't exist. It will be used as home/workspace directory of the Jenkins service. If it exists and contains valuable data, please backup it before removing.

## Starting The Project

Clone the project in a local directory, get in the directory and execute linux shell command `docker-compose up`. It builds custom container image of Jenkins Server and starts it. After getting message `Jenkins is fully up and running` the server is available by URL: http://localhost:8081/.

Default Administrator credentials are defined in `jenkins/Dockerfile` and may be redefined in section `environment` of respective service in`docker-compose.yml` file.

## Initiating The Predefined Job

After logging in the Jenkins, you get a standard jenkins GUI with a single job `auroralab-pytask` represented. To initiate the job, please get in the job and press `Build Now` menu point on leftside-menu panel of the job. it cause a pulling and executing Jenkinsfile from a task git repository https://github.com/northbear/alab-pytask. On this stage the Jenkins starts tracking commits on the task repository as it defined in the Jenkinsfile. 

## Comments On The Home Assignment Consideration Points

### Storage 

A significant point of the Jenkins Server is home/workspace directory /var/jenkins_home. It's strongly recommended to use a dedicated FS/Partition mounted to this point. 

It would be good idea to use a FS supportgin snapshotting or (for case of using in a cloud like AWS) Network Block Devices (EBS in terms of AWS) that supporting snapshotting and backups. 

### Security

In the real world the Jenkins Server should be run in a dedicated server/instance/cluster node with limited access. The suggested solution proposes that all task will be run in docker containers. 

The docker containers should be revised regarding of security and stored for further using in a private container image repository (like AWS ECR or Artifactory). 

Another security recommedation is a decoupling of build and deployment processes, but it's mostly about common infrastructure building approach. 

### Maintainability

An approach suggested by this solution is flexable. it intends using a docker engine for execution environments. But it can be adopted to use in K8S cluster in the same way. 

Set of Jenkins plugins are provided in `jenkins/plugins.txt`. Updating the list of plugins requires rebuilding the custom Jenkins container image. 

Set of preconfigured jobs are stored in `jenkins/jobs` directory. It could be a good idea to use Job DSL jenkins plugin for implementing dynamic jobs definitions. The dynamyc jobs defintions can be allocated in standalone git repository. 

## So Thanks... 
