import React, { Component } from 'react';
import { Redirect, Route } from 'react-router-dom';


export default class AdminRoute extends Component {
    render() {
        if (localStorage.getItem('authToken') === null ||
            localStorage.getItem('isAdmin') === null ||
            localStorage.getItem('isAdmin') === 'false') {

            return <Redirect to="/login" />;
        }

        return (
            <Route {...this.props}>
                this.props.children
            </Route>
        );
    }
}