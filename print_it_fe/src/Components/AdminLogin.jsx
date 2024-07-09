import { Link, useNavigate } from "react-router-dom";
import "../Style/adminlogin.css"
import { useState } from "react";
import axios from "axios";

const AdminLogin = () => {
    let[username,setUserName]=useState("")
    let[password,setPassword]=useState("")
    let navigate=useNavigate()
    function adminlogin(e) {
        e.preventDefault()
        if(username.includes('@')){
            axios.post(`http://localhost:8080/api/admins/verify-by-email?email=${username}&password=${password}`)
            .then((res)=>{
                alert("login successfull");
            })
            .catch((err)=>{
                alert("login failed");
            })
        }else{
            axios.post(`http://localhost:8080/api/admins/verify-by-phone?phone=${username}&password=${password}`)
            .then((res)=>{
                alert("login successfull");
                navigate('/admin-home')
            })
            .catch((err)=>{
                alert("login failed");
            })
        }
        
    }

    return ( 
        <div className="adminlogin">
            <form >
             <input type="text" value={username} onChange={(e)=>{setUserName(e.target.value)}} placeholder="please enter phone or email" />
             <input type="text" value={password} onChange={(e)=>{setPassword(e.target.value)}}  placeholder="please enter password"/>
             <p>forgot password?</p>
             <button id="adminloginbutton" onClick={adminlogin}>Log In</button>
             <Link to="/admin-register"><button id="adminregisterbutton">Register</button></Link>
            </form>
        </div>
     );
}
 
export default AdminLogin;