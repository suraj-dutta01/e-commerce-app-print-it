import { useState } from "react";
import "../Style/adminregistr.css"
import axios from "axios";
const AdminRegister = () => {
    let[name,setName]=useState("")
    let[email,setEmail]=useState("")
    let[phone,setPhone]=useState("")
    let[password,setPassword]=useState("")
    function registerAdmin(e) {
        e.preventDefault()
        let data={name,email,phone,password}
        axios.post(`http://localhost:8080/api/admins`,data)
        .then((res)=>{
            console.log(res);
            alert(res.data.message)
        })
        .catch((err)=>{
            console.log(err);
            alert(err.message)
        })
    }
    return (
        <div className="adminregistr">
            <form onSubmit={registerAdmin}>
                <input type="text" value={name} onChange={(e)=>{setName(e.target.value)}} placeholder="enter the name"/>
                <input type="email" value={email} onChange={(e)=>{setEmail(e.target.value)}} placeholder="enter the email"/>
                <input type="text" value={phone} onChange={(e)=>{setPhone(e.target.value)}} placeholder="enter the phone" />
                <input type="password" value={password} onChange={(e)=>{setPassword(e.target.value)}}  placeholder="enter the password" />
                <button >Register</button>
            </form>
        </div>
      );
}
 
export default AdminRegister;