# Task_Board_Lite

Task_Board_Lite is a simple full-stack task management application built as part of an internship assignment.  
It allows users to create, update, delete, and organize tasks across different statuses using a clean single-screen task board UI.

---

## 🔗 Live Application

- **Frontend (Netlify):**  
  https://task-board-lite.netlify.app/

- **Backend (Render):**  
  https://task-board-lite-5r8i.onrender.com/

---

## ✨ Features

- Create, edit, and delete tasks
- Filter tasks by **status** and **priority**
- Search tasks by title
- Three-column task board:
  - To Do
  - In Progress
  - Done
- Single-screen UI as required
- RESTful backend APIs with validation and proper error handling

---

## 🛠 Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- H2 (in-memory database)
- Docker
- Hosted on Render

### Frontend
- React (Vite)
- Axios
- Plain CSS
- Hosted on Netlify

---

## ▶️ How to Run Locally

### Backend

```bash
cd backend/TaskBoard
./mvnw spring-boot:run

Backend will run at:https://task-board-lite-5r8i.onrender.com

### Frontend

cd frontend/frontend
npm install
npm run dev

Frontend will run at:http://localhost:5173

## ⚖️ Assumptions / Tradeoffs

- Used **H2 in-memory database**, so all data resets when the backend restarts
- Authentication and authorization were not implemented, as they were out of scope
- Focus was kept on clarity, correctness, and clean architecture rather than heavy UI libraries
- Single-screen UI was implemented strictly as per assignment requirements
- Backend and frontend are deployed separately to follow real-world deployment practices

---

## 🚀 What I Would Improve With 1–2 More Hours

- Add drag-and-drop support to move tasks between columns
- Improve mobile responsiveness and layout for smaller screens
- Add a confirmation dialog before deleting a task
- Replace H2 with PostgreSQL for persistent data storage
- Add basic authentication to support multiple users


## ✅ Submission Notes

- The application is fully hosted and accessible via browser
- Backend is deployed on **Render** and frontend on **Netlify**
- Environment variables are used for configuration
- Backend availability is maintained using a scheduled GitHub Action
- All requirements mentioned in the assignment have been implemented
