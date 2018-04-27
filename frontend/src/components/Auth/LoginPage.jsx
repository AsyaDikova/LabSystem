import React, { Component } from 'react';
import Input from '../common/Input';
import { login } from '../../api/remote';
import {withRouter} from "react-router-dom";
import decode from 'jwt-decode';

class LoginPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            password: '',
            error: false
        };

        this.onChangeHandler = this.onChangeHandler.bind(this);
        this.onSubmitHandler = this.onSubmitHandler.bind(this);
    }

    onChangeHandler(e) {
        this.setState({ [e.target.name]: e.target.value });
    }

    async onSubmitHandler(e) {
        e.preventDefault();
        const res = await login(this.state.username, this.state.password);

        localStorage.setItem('authToken', res.Authorization.toString().replace("Bearer", ""));
        localStorage.setItem('user', decode(res.Authorization.toString().replace("Bearer", "")).sub);
        localStorage.setItem('isAdmin', decode(res.Authorization.toString().replace("Bearer", "")).isAdmin);
        localStorage.setItem('isRegistrar', decode(res.Authorization.toString().replace("Bearer", "")).isRester);

        this.props.history.push('/');
    }

    render() {
        return (
            <div className="container">
                <h1>Login</h1>
                <div>{this.state.error}</div>
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        name="username"
                        value={this.state.username}
                        onChange={this.onChangeHandler}
                        label="Username"
                    />
                    <Input
                        name="password"
                        type="password"
                        value={this.state.password}
                        onChange={this.onChangeHandler}
                        label="Password"
                    />
                    <input type="submit" className="btn btn-primary" value="Login" />
                </form>
            </div>
        );
    }
}

export default withRouter(LoginPage);