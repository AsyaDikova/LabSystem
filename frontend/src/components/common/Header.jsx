import React, { Component } from 'react';
import { NavLink } from 'react-router-dom';

export default class Header extends Component {
    render() {
        const {loggedIn, isAdmin, logout, user} = this.props;

        return (
            <header>
                <NavLink exact to="/" activeClassName="active">Home</NavLink>
                {loggedIn && <span>Welcome, {user} !</span>}
                {loggedIn && <a href="javascript:void(0)" onClick={logout}>Logout</a>}
                {!loggedIn && <NavLink to="/login" activeClassName="active">Login</NavLink>}
                {isAdmin && <NavLink to="/employee/register" activeClassName="active">Register</NavLink>}
            </header>
        );
    }
}