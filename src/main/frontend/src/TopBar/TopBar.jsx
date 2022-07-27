import "./TopBar.css"
export default function TopBar() {
    return (
        <div className="top">
            <div className="topLeft"></div>
            <div className="topCenter">
                <ul className="topList">
                        <li className="topListItem">Home</li>
                        <li className="topListItem">PlaceHolder</li>
                        <li className="topListItem">Placeholder</li>
                    </ul>
            </div>
            <div className="topRight">User</div>
        </div>
    )
}