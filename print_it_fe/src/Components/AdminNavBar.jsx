import { Link } from "react-router-dom";
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import "../Style/adminnavbar.css"
const AdminNavBar = () => {
    return ( 
        <div className="adminnavbar">
            <div className="adminnavlogo">
                <h1>Print<span>IT</span></h1>
            </div>
            <div className="adminmenu">
                <Link to="#">Home</Link>
                <Link to="#">Catagory</Link>
                <Link to="#">Products</Link>
                <Link to="#">Orders</Link>
                <Link to="#"><AccountCircleIcon/></Link>

            </div>
        </div>
     );
}
 
export default AdminNavBar;