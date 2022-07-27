import './App.css';
import { Routes, Route } from 'react-router-dom';
import Home from '../Home/Home';
import Feeds from '../Feed/Feeds';
import TopBar from '../TopBar/TopBar';

export default function App() {
  return(
    <div className='wrapper'>
      {/* <Header />  TODO */}
      <TopBar/>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/feeds" element={<Feeds />} />
      </Routes>
    </div>
  )
}
