import React, { useEffect, useState } from 'react';
import Timeline from './components/Timeline';
import AssistantChat from './components/AssistantChat';
import './App.css';

function App() {
  const [timelineData, setTimelineData] = useState([]);

  useEffect(() => {
    // Fetch timeline data from the Spring Boot backend
    fetch('http://localhost:8080/api/v1/timeline')
      .then(res => res.json())
      .then(data => setTimelineData(data))
      .catch(err => console.error("Error fetching timeline:", err));
  }, []);

  return (
    <div className="app-container">
      <header className="header">
        <h1>India Election Journey</h1>
        <p>Interactive guide to understanding the world's largest democratic exercise</p>
      </header>
      
      <main className="main-content">
        <Timeline items={timelineData} />
        <AssistantChat />
      </main>
    </div>
  );
}

export default App;
