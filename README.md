
# AI News Aggregator

## Project Overview
The AI News Aggregator is a web application that collects, categorizes, and displays news articles from multiple sources. The system uses AI-based logic to automatically generate categories for news articles when not provided manually. This project is built using **Spring Boot**, **Thymeleaf**, **MySQL**, and **Bootstrap** for frontend styling.

---

## Features

- Add, edit, and delete news articles via web interface.
- AI-generated categories for news based on content.
- Search news articles by title or description.
- Pagination for easy browsing.
- REST API endpoints for programmatic access.
- Responsive and user-friendly frontend.

---

## Technology Stack

- **Backend:** Java, Spring Boot
- **Frontend:** Thymeleaf, Bootstrap
- **Database:** MySQL
- **Version Control:** Git & GitHub
- **Tools:** Eclipse IDE, Postman (for API testing)

---

## Folder Structure

```

news-aggregator/
│
├─ src/
│   ├─ main/java/com/example/demo/       # Java source code
│   └─ main/resources/                  # Application properties, templates
│
|
│   ├─ Frontend/Output                        # Frontend UI screenshots
│  
│
├─ .gitignore
├─ pom.xml                              # Maven dependencies
└─ README.md

````

---

## Database Setup

1. Install MySQL and create a database, e.g., `news_aggregator`.
2. Update `src/main/resources/application.properties` with database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/news_aggregator
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
````

3. The `News` entity table (`ai_news_dataset`) will be auto-created by Hibernate.

---

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/yourusername/news-aggregator.git
```

2. Open the project in Eclipse or your preferred IDE.
3. Build the project using Maven:

```bash
mvn clean install
```

4. Run the Spring Boot application:

```bash
mvn spring-boot:run
```

5. Access the frontend at: `http://localhost:8080/news`

---

## REST API Endpoints

| Endpoint         | Method | Description             |
| ---------------- | ------ | ----------------------- |
| `/news/api`      | GET    | Get paginated news list |
| `/news/api/{id}` | GET    | Get news by ID          |
| `/news/api`      | POST   | Add new news            |
| `/news/api/{id}` | PUT    | Update existing news    |
| `/news/api/{id}` | DELETE | Delete news by ID       |

---

## Screenshots

* **Frontend:** `Frontend/Output`

---

## AI Integration

* News categories are automatically generated using keywords from title and description.
* Example logic:

  * Contains "sports" → `Sports`
  * Contains "politics" → `Politics`
  * Contains "technology" or "AI" → `Technology`
  * Contains "health" → `Health`
  * Contains "entertainment", "movie", "music" → `Entertainment`
  * Otherwise → `General`

---

## Author

**Anusri Chundru**
Email: [chundruanusri6822@gmail.com](mailto:chundruanusri6822@gmail.com)

---

