import { createSlice } from '@reduxjs/toolkit';
import { RootState } from '../../app/store';

export interface User {
    firstName?: String;
    lastName?: String;
    email?: String;
    token?: String;
    isLogged: Boolean;
}

const initialState: User = {
    firstName: '',
    lastName: '',
    email: '',
    token: '',
    isLogged: true
};

export const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
      login: (state, {payload}) => {
        console.log(payload)
        return { ...state, ...payload, isLogged: true }
      },
      logout: (state) => {
        console.log('logout', state)
        return { ...state, firstName: '', lastName: '', email: '', token: '', isLogged: false }
      },
    },
});

export const { login, logout } = userSlice.actions;

export const selectUser =  (state: RootState) => state.user;

export default userSlice.reducer;