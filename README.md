# address-REST-AI-generated

Example of a locally AI-generated Java Spring Docker REST project.

## About

This project demonstrates a Java Spring Boot REST API generated entirely by a **local AI model** running on consumer hardware — no cloud AI services involved.

**Generation stack:**
- **Model:** Qwen3.6-35B-A3B-MTP-GGUF-Q8_0 
- **Hardware:** AMD Ryzen AI Max+
- **Runtime:** [Lemonade](https://github.com/lemonade-ai)
- **Assistant:** Cline in VS Code

## Tech Stack

- Java + Spring Boot
- Maven (`pom.xml`)
- Docker & Docker Compose

## Project Structure

```
src/                  # Application source code
Dockerfile            # Container build definition
docker-compose.yml    # Multi-container orchestration
pom.xml               # Maven dependencies & build config
TASK.md               # Task prompt used for AI generation
.dockerignore         # Docker build exclusions
.gitignore            # Git exclusions
```

## Getting Started

### Run locally with Maven

```bash
./mvnw spring-boot:run
```

### Run with Docker Compose

```bash
docker-compose up --build
```

The REST API will be available at `http://localhost:8080`.

## Notes

- `TASK.md` contains the original prompt/task description that drove the AI generation.
- This is a reference/demo project rather than a production-ready application.

## Youtube

https://www.youtube.com/watch?v=Y3YBG_dBiSU
