import { Link } from "react-router-dom";
import AdminPanelSettingsIcon from '@mui/icons-material/AdminPanelSettings';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import "../Style/landingpage.css"

const LandingPage = () => {
    return ( 
        <div className="landingpage">
        <Link to="/admin-login"><AdminPanelSettingsIcon/> ADMIN</Link>
        <Link to="/user-login"><AccountCircleIcon/> USER</Link>
        </div>
     );
}
 
export default LandingPage;