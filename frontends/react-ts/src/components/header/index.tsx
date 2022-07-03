import { useSelector } from "react-redux";
import { NavLink } from "react-router-dom";
import { selectUser } from "../../features/user/userSlice";
import './index.css';

export function Header() {
  const user = useSelector(selectUser);

  return (
        <header className="header">
          <nav>
            <ul>
                <li>
                  <NavLink to="/"  className={({ isActive }) => isActive ? 'active' : ''}>Home</NavLink>
                </li>
                <li>
                  <NavLink to="/blog" className={({ isActive }) => isActive ? 'active' : ''}>Blog</NavLink>
                </li>
                <li>
                  <NavLink to="/counter" className={({ isActive }) => isActive ? 'active' : ''}>Counter</NavLink>
                </li>
                <li>
                  <NavLink to="/about" className={({ isActive }) => isActive ? 'active' : ''}>About</NavLink>
                </li>
            </ul>
            <div>
              Usuário: {user.firstName} {user.lastName}
            </div>
          </nav>
        </header>
    );
  }
  


