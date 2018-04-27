import React, { Component } from 'react';
import { Redirect, Route } from 'react-router-dom';


export default class RegistrarRoute extends Component {
    render() {
        if (localStorage.getItem('authToken') === null || !localStorage.getItem('isRegistrar') || !localStorage.getItem('isAdmin')) {
            return <Redirect to="/login" />;
        }

        return (
            <Route {...this.props}>
                this.props.children
            </Route>
        );
    }
}