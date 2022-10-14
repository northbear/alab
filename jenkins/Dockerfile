FROM jenkins/jenkins:lts
USER root

RUN apt-get update && \
    apt-get -y install apt-transport-https ca-certificates curl gnupg2 software-properties-common && \
    curl -fsSLo /usr/share/keyrings/docker-repo.gpg https://download.docker.com/linux/debian/gpg && \
    apt-key add /usr/share/keyrings/docker-repo.gpg && \
    add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/debian bullseye stable" && \
    apt-get update && apt-get -y install docker-ce

COPY ./jenkins-plugins.txt /usr/share/jenkins/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/plugins.txt

ENV JENKINS_USER admin
ENV JENKINS_PASS !Q2w3e4r

ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

COPY default-user.groovy /usr/share/jenkins/ref/init.groovy.d/

VOLUME /var/jenkins_home

USER jenkins
