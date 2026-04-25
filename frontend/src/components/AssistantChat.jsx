import React, { useState } from 'react';
import ReactMarkdown from 'react-markdown';

export default function AssistantChat() {
  const [messages, setMessages] = useState([
    { role: 'assistant', text: 'Namaste! I am your Election Assistant. Ask me anything about the Indian election process (e.g., "What is EVM?", "How do I register to vote?").' }
  ]);
  const [input, setInput] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const sendMessage = async () => {
    if (!input.trim() || isLoading) return;
    
    const userMessage = input.trim();
    setInput('');
    
    // Add user message to state
    const newMessages = [...messages, { role: 'user', text: userMessage }];
    setMessages(newMessages);
    setIsLoading(true);

    try {
      // Send the entire conversation history (excluding the very first greeting if you want to save tokens, but here we'll send it all)
      const response = await fetch('http://localhost:8080/api/v1/chat', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ messages: newMessages })
      });
      
      if (!response.ok) throw new Error('API Error');
      
      const data = await response.json();
      setMessages(prev => [...prev, { role: 'assistant', text: data.reply }]);
    } catch (error) {
      setMessages(prev => [...prev, { role: 'assistant', text: 'Sorry, I am having trouble connecting to the server. Is the backend running?' }]);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="chat-container glass-panel">
      <div className="chat-header">
        <span className="chat-header-icon">🤖</span>
        <h2>AI Election Guide</h2>
      </div>
      
      <div className="chat-messages">
        {messages.map((msg, idx) => (
          <div key={idx} className={`message ${msg.role}`}>
            <ReactMarkdown>{msg.text}</ReactMarkdown>
          </div>
        ))}
        {isLoading && <div className="message assistant">Thinking...</div>}
      </div>
      
      <div className="chat-input-container">
        <input 
          type="text" 
          className="chat-input"
          placeholder="Ask a question..." 
          value={input}
          onChange={(e) => setInput(e.target.value)}
          onKeyDown={(e) => e.key === 'Enter' && sendMessage()}
        />
        <button className="btn btn-primary" onClick={sendMessage} disabled={isLoading}>
          Send
        </button>
      </div>
    </div>
  );
}
