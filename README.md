# Java CI/CD Demo with Jenkins, Maven, Docker, and GitHub

A beginner-friendly CI/CD project that automates building, testing, packaging, and containerizing a Java application using Jenkins, Maven, Docker, and GitHub.

## Project Overview

This project demonstrates how code moves from a developer's machine to an automated CI/CD pipeline.

### CI/CD Workflow

1. A developer pushes code changes to GitHub.
2. A GitHub webhook notifies Jenkins.
3. Jenkins checks out the latest source code.
4. Maven compiles the Java application and runs unit tests.
5. Jenkins archives the generated JAR artifact.
6. Jenkins builds a Docker image containing the application.
7. Jenkins reports whether the pipeline succeeded or failed.

```text
Developer
    |
    | git push
    v
GitHub Repository
    |
    | Webhook
    v
Jenkins Pipeline
    |
    +---- Checkout Source Code
    |
    +---- Maven Build and Tests
    |
    +---- Archive JAR Artifact
    |
    +---- Build Docker Image
    |
    v
Pipeline Success / Failure
```

## Tech Stack

- **Java 21** — application development
- **Maven** — build management and testing
- **JUnit 5** — unit testing
- **Jenkins** — CI/CD automation
- **Docker** — application containerization
- **Git and GitHub** — source control and webhook integration

## Project Structure

```text
ci-cd-demo/
├── src/
│   ├── main/java/com/example/
│   │   └── App.java
│   └── test/java/com/example/
│       └── AppTest.java
├── jenkins/
│   └── Dockerfile
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

## Prerequisites

Install the following tools on your machine:

- Git
- Java 21
- Maven
- Docker Desktop
- A GitHub account

Jenkins runs in a Docker container with Maven and the Docker CLI installed.

## Run the Application Locally

Clone the repository:

```bash
git clone https://github.com/Ramana-Vanchanagiri/ci-cd-demo.git
cd ci-cd-demo
```

Build the project and run the tests:

```bash
mvn clean verify
```

Run the generated JAR:

```bash
java -jar target/ci-cd-demo-1.0.0.jar
```

## Build and Run with Docker

Build the application Docker image:

```bash
docker build -t ci-cd-demo:1.0 .
```

Run the container:

```bash
docker run --rm ci-cd-demo:1.0
```

The container executes the Java application and prints its greeting.

## Run Jenkins Locally with Docker

### 1. Build the Jenkins image

From the project root:

```bash
docker build -t jenkins-with-maven -f jenkins/Dockerfile jenkins
```

The custom image includes Jenkins, Java 21, Maven, and the Docker CLI.

### 2. Create a Docker network

```bash
docker network create ci-network
```

If the network already exists, you can continue without creating it again.

### 3. Start Jenkins

On macOS with Docker Desktop, run:

```bash
docker run -d \
  --name jenkins \
  --restart unless-stopped \
  --network ci-network \
  -p 8080:8080 \
  -p 50000:50000 \
  -v jenkins_home:/var/jenkins_home \
  -v "$HOME/.docker/run/docker.sock:/var/run/docker.sock" \
  jenkins-with-maven
```

Open Jenkins in your browser:

http://localhost:8080

The named Docker volume `jenkins_home` preserves Jenkins configuration and job data across container restarts.

> **Security note:** Mounting the Docker socket gives Jenkins broad control over the Docker daemon. This setup is intended for local learning, not an untrusted or production environment.

## Configure the Jenkins Pipeline

Create a Jenkins Pipeline job named `java-ci-demo`.

Configure it as follows:

- **Definition:** Pipeline script from SCM
- **SCM:** Git
- **Repository URL:** `https://github.com/Ramana-Vanchanagiri/ci-cd-demo.git`
- **Branch specifier:** `*/main`
- **Script path:** `Jenkinsfile`

Save the job and run **Build Now** to execute the pipeline manually.

### Pipeline Stages

The `Jenkinsfile` defines these stages:

1. **Checkout from GitHub** — confirms the checked-out commit.
2. **Build and Test** — runs `mvn clean verify`.
3. **Archive JAR Artifact** — archives the generated JAR and fingerprints it.
4. **Build Docker Image** — builds the `ci-cd-demo:1.0` Docker image.
5. **Build Successful** — prints a success message when previous stages pass.

The pipeline stops if a required stage fails.

## Configure GitHub Webhook Automation

To trigger Jenkins automatically when code is pushed:

1. Make sure Jenkins is reachable from GitHub. For local learning, a temporary HTTPS tunnel can provide access.
2. Open the GitHub repository.
3. Go to **Settings → Webhooks → Add webhook**.
4. Set the payload URL to your reachable Jenkins URL followed by `/github-webhook/`.
5. Set the content type to `application/json`.
6. Select **Just the push event**.
7. Keep the webhook active and save it.
8. In Jenkins, enable **GitHub hook trigger for GITScm polling** in the job configuration.

Use your current tunnel URL if you are using a temporary tunnel; tunnel URLs can change. Do not commit credentials, access tokens, or private webhook secrets to this repository.

After configuring the webhook, push a commit to GitHub and verify that Jenkins starts a new build.

## Useful Git Commands

Check your changes:

```bash
git status
```

Stage and commit your changes:

```bash
git add README.md jenkins/Dockerfile
git commit -m "Add project documentation"
```

Push to GitHub:

```bash
git push origin main
```

## Useful Jenkins Commands

View Jenkins logs:

```bash
docker logs -f jenkins
```

Stop Jenkins:

```bash
docker stop jenkins
```

Start Jenkins again:

```bash
docker start jenkins
```

## Troubleshooting

- **Maven build fails:** Check the Java version and inspect Maven's error output.
- **Docker command fails in Jenkins:** Verify that Docker Desktop is running and the Docker socket is accessible.
- **Jenkins is unavailable:** Check `docker ps` and `docker logs jenkins`.
- **GitHub webhook does not trigger a build:** Check the webhook delivery status in GitHub and verify that the Jenkins URL is reachable.
- **JAR artifact is missing:** Confirm that `mvn clean verify` succeeds and that the JAR exists under `target/`.

## Learning Objectives

By completing this project, you will understand:

- How CI differs from CD.
- How GitHub webhooks trigger Jenkins pipelines.
- How Maven compiles, tests, and packages Java applications.
- How Jenkins archives build artifacts.
- How Docker packages an application into an image.
- How a CI/CD pipeline automates repetitive development tasks.

## Future Improvements

- Push Docker images to Docker Hub or another container registry.
- Add automated deployment to a server or cloud platform.
- Publish test reports in Jenkins.
- Add code quality and security scanning.
- Introduce separate development, staging, and production environments.

---

**Author:** Ramana Vanchanagiri

**Repository:** https://github.com/Ramana-Vanchanagiri/ci-cd-demo
