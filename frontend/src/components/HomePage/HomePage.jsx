import React, { Component } from 'react';
import { getAnalyzes } from '../../api/remote';
import AnalyzesList from './AnalyzesList';

export default class HomePage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            analyzes: []
        };
    }

    componentDidMount() {
        this.getData();
    }


    async getData() {
        const data = await getAnalyzes();
        this.setState({ analyzes: data });
    }


    render() {

        return (
            <div className="container">
                <h1>Home Page</h1>
                <p>Welcome to our site.</p>
                {this.state.analyzes.length === 0 ?
                    <p>Loading &hellip;</p> :
                    <AnalyzesList analyzes={this.state.analyzes}  />}
            </div>
        );
    }
}