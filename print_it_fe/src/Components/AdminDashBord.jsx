import "../Style/admindashbord.css"
const AdminDashBord = () => {
    return ( 
        <div className="admindashbord">
            <div className="allboxes">
                <div className="totaluser">
                    <h2>39</h2>
                    <p>Total Users</p>
                </div>
                <div className="totalorder">
                    <h2>88</h2>
                    <p>Total Orders</p>
                </div>
                <div className="totalsales">
                    <h2>₹ 56,390</h2>
                    <p>Total Sales</p>
                </div>
                <div className="thismontsales">
                    <h2>₹ 19,200</h2>
                    <p>This Month Sales</p>
                </div>
            </div>
        </div>
     );
}
 
export default AdminDashBord;