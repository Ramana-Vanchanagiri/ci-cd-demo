FROM jenkins/jenkins:lts-jdk21

USER root

RUN apt-get update \
    && apt-get install -y --no-install-recommends \
       curl ca-certificates \
       maven \
    && curl -fsSL https://download.docker.com/linux/static/stable/aarch64/docker-28.3.0.tgz \
       -o /tmp/docker.tgz \
    && tar -xzf /tmp/docker.tgz -C /tmp \
    && install -m 0755 /tmp/docker/docker /usr/local/bin/docker \
    && rm -rf /tmp/docker /tmp/docker.tgz /var/lib/apt/lists/*

USER jenkins