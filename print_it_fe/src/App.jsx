import {BrowserRouter,Routes,Route} from "react-router-dom";
import './App.css';
import LandingPage from "./Components/LandingPage";
import AdminLogin from "./Components/AdminLogin";
import UserLogin from "./Components/UserLogin";
import AdminRegister from "./Components/AdminRegister";
import UserRegister from "./Components/UserRegister";
import AdminHomePage from "./Components/AdminHomePage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <Routes>
        <Route path="/" element={<LandingPage/>}/>
        <Route path="/admin-login" element={<AdminLogin/>}/>
        <Route path="/user-login" element={<UserLogin/>}/>
        <Route path="/admin-register" element={<AdminRegister/>}/>
        <Route path="/user-register" element={<UserRegister/>}/>
        <Route path="/admin-home"  element={<AdminHomePage/>}/>
      </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
