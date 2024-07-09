import "../Style/adminhomepage.css"
import AdminDashBord from "./AdminDashBord";
import AdminNavBar from "./AdminNavBar";
const AdminHomePage = () => {
    return ( 
        <div className="adminhomepage">
            <AdminNavBar/>
            <AdminDashBord/>
        </div>
     );
}
 
export default AdminHomePage;