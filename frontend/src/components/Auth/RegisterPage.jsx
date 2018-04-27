import React, { Component } from 'react';
import Input from '../common/Input';
import { register } from '../../api/remote';
import { withRouter } from 'react-router-dom';


class RegisterPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            username: '',
            email: '',
            password: '',
            repeat: '',
            firstName: '',
            lastName: '',
            telephone: '',
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

        if(this.state.password !== this.state.repeat){
            this.setState({
                error: "Password not equals!"
            });
            return;
        }

        const res = await register(this.state.username, this.state.email, this.state.password,
            this.state.confirmPassword, this.state.firstName, this.state.lastName, this.state.telephone);

        if(!res.success){
            this.setState({error: res.message});
            return;
        }
        this.props.history.push('/');
    }

    render() {
        const username = localStorage.getItem('username');

        return (
            <div className="container">
                <h1>Register</h1>
                <div>{this.state.error}</div>
                <form onSubmit={this.onSubmitHandler}>
                    <Input
                        name="username"
                        value={this.state.username}
                        onChange={this.onChangeHandler}
                        label="Username"
                    />
                    <Input
                        name="email"
                        value={this.state.email}
                        onChange={this.onChangeHandler}
                        label="E-mail"
                    />
                    <Input
                        name="firstName"
                        value={this.state.firstName}
                        onChange={this.onChangeHandler}
                        label="FirstName"
                    />
                    <Input
                        name="lastName"
                        value={this.state.lastName}
                        onChange={this.onChangeHandler}
                        label="LastName"
                    />
                    <Input
                        name="telephone"
                        value={this.state.telephone}
                        onChange={this.onChangeHandler}
                        label="Telephone"
                    />
                    <Input
                        name="password"
                        type="password"
                        value={this.state.password}
                        onChange={this.onChangeHandler}
                        label="Password"
                    />
                    <Input
                        name="repeat"
                        type="password"
                        value={this.state.repeat}
                        onChange={this.onChangeHandler}
                        label="Repeat password"
                    />
                    <input type="submit" className="btn btn-primary" value="Register" />
                </form>
                <div>{username}</div>
            </div>
        );
    }
}

export default withRouter(RegisterPage);