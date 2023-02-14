import {Routes, Route, Navigate } from "react-router-dom";

import './App.css';

function App() {
  return (
    <div className="App">
        <Routes>
          <Route path="/" element={<Navigate to="/main" replace />} />
        </Routes>
    </div>
  );
}

export default App;
