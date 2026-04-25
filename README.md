# India Election Process Educational Assistant

An interactive, AI-powered web application designed to help citizens understand the democratic election process in India. This project was built to deliver a highly accessible, dynamic, and educational user experience.

## Chosen Vertical
**Civic Tech / Educational Assistant**
This solution is designed for the persona of a citizen or student who wants to understand the complex steps of a democratic election, including timelines, rules, and procedures, aided by an intelligent assistant.

## Approach and Logic
The application uses a **modern split architecture** to ensure clear separation of concerns, scalability, and security:
1. **Frontend (React + Vite):** Delivers a premium, "Light Mode" user interface with glassmorphism aesthetics. It features an interactive timeline to visualize the election phases and a chat widget.
2. **Backend (Java Spring Boot):** Acts as a secure API gateway and business logic processor.
3. **Database (H2 In-Memory + Spring Data JPA):** Stores the timeline phases dynamically, demonstrating how real-world applications manage state rather than hardcoding data.
4. **AI Integration (Google Gemini API):** The backend communicates securely with the `gemini-2.5-flash` model. It maintains conversation history (Chat Memory) to allow multi-turn context and uses a rigid system prompt to constrain the AI as an "Expert Assistant for the Election Commission of India."

## How the Solution Works
The user is presented with an interactive timeline of the election process fetched from the Spring Boot API. Alongside the timeline, an AI Chat Assistant is available. When the user asks a question, the React app sends the conversation history to the Java backend. The Java backend constructs a structured prompt payload, injects the system instructions, and calls the Google Gemini API. The response is parsed and sent back to the frontend, where it is rendered beautifully using Markdown.

### Prerequisites
- **Java 17 or higher** installed (`javac`).
- **Node.js (v20+)** installed.
- **Google Gemini API Key** (Get a free key from [Google AI Studio](https://aistudio.google.com/app/apikey)).

### Running the Application Locally

**1. Start the Backend:**
```bash
cd backend
export GEMINI_API_KEY="your-api-key-here"
./mvnw spring-boot:run
```
*(The backend will start on port 8080 and automatically seed the database).*

**2. Start the Frontend:**
```bash
cd frontend
npm install
npm run dev
```
*(The frontend will be available at http://localhost:5173).*

## Assumptions Made
1. **API Key Provisioning:** It is assumed the reviewer or user will provide their own Google Gemini API key to test the live chat functionality.
2. **Internet Access:** The application requires active internet access to reach the Google Gemini API endpoints.
3. **Local Environment:** Ports `8080` and `5173` are free on the host machine.

## Technical Highlights for Evaluation
- **Code Quality:** Organized into clean layers (Controllers, Services, Repositories, DTOs).
- **Security:** API keys are injected via environment variables and never exposed to the browser.
- **Efficiency:** Utilizes an in-memory database for lightweight demonstration and React Vite for lightning-fast frontend compilation.
- **Google Services:** Deep integration with Google Gemini LLM for conversational intelligence.
