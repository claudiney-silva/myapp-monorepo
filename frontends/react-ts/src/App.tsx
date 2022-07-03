import React from 'react';
import { Home } from './pages/home';
import { Blog } from './pages/blog';
import { About } from './pages/about';
import { Counter } from './features/counter/Counter';
import { Header } from './components/header';
import {
  BrowserRouter as Router,
  Routes,
  Route
} from "react-router-dom";
import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <Header />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/blog' element={<Blog />} />
          <Route path='/counter' element={<Counter />} />
          <Route path='/about' element={<About />} />
        </Routes>
    </Router>
    </div>
  );
}

export default App;
