import React from 'react';

export default function Timeline({ items }) {
  if (!items || items.length === 0) {
    return <div className="glass-panel timeline-content">Loading timeline...</div>;
  }
  
  return (
    <div className="timeline-container glass-panel">
      <div className="timeline-line"></div>
      {items.map((item) => (
        <div className="timeline-item" key={item.id}>
          <div className="timeline-marker"></div>
          <div className="timeline-content glass-panel">
            <h3>{item.title}</h3>
            <span className="timeline-duration">{item.duration}</span>
            <p>{item.description}</p>
          </div>
        </div>
      ))}
    </div>
  );
}
