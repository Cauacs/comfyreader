import { ThemeProvider } from '@emotion/react';
import { createTheme, CssBaseline } from '@mui/material';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import SignIn from './Login/SignIn';
import Navbar from './Navbar/Navbar';
import Posts from './Posts/Posts';
import SignUp from './Register/SignUp';
import Urls from './Urls/Urls';


const theme = createTheme({
  palette : {
    mode: "dark",
  }
})

function App() {
  const currentUser = localStorage.getItem('user')
  return (
    <>
      <ThemeProvider theme={theme}>
        <CssBaseline/>
        
        <BrowserRouter>
        <Navbar/>
          <Routes>
            <Route path='/' element={<Posts />} />
            <Route path='login' element={<SignIn/>}/>
            <Route path='signup' element={<SignUp/>}/>
            <Route path='urls' element={<Urls />} />
          </Routes>
        </BrowserRouter>
      </ThemeProvider>
    </>
  );
}

export default App;
