import { useState } from "react";
import { useDispatch } from "react-redux";
import { User, login, logout } from "../../features/user/userSlice";

import styles from './index.module.css';

export function Home() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const dispatch = useDispatch();

  const handleLogin = () => {

    const user: User = {
      firstName,
      lastName,
      email: '',
      token: '',
      isLogged: true,
    }

    dispatch(login(user));
  }

  const handleLogout = () => {
    console.log('Logout');
    setFirstName('');
    setLastName('');
    dispatch(logout());
  }

  return (
    <div>
      <h1>Home Page</h1>
        <input
          aria-label="Set first name"
          placeholder="Nome"
          value={firstName}
          onChange={(e) => setFirstName(e.target.value)}
        />
        <input
          aria-label="Set last name"
          placeholder="Sobrenome"
          value={lastName}
          onChange={(e) => setLastName(e.target.value)}
        />
        <button
          className={styles.button}
          onClick={() => handleLogin()}
        >Login</button>

       <button
          className={styles.button}
          onClick={() => handleLogout()}
        >Logout</button>

    </div>
  );
}

