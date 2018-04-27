import React, { Component } from 'react';
import { getAnalysesDetails } from '../../api/remote';

export default class DetailsPage extends Component {
    constructor(props) {
        super(props);

        this.state = {
            analyses: false,
            error: false
        };
    }

    componentDidMount() {
        this.getData();
    }

    async getData() {
        const res = await getAnalysesDetails(this.props.match.params.id);
        this.setState({ analyses: res });
    }


    render() {
        let main = <p>Loading &hellip;</p>;
        if (this.state.analyses) {
            const analyses = this.state.analyses;
            main = (
                <div className="analysesCard">
                    <h2>Name: {analyses.name}</h2>
                    <h3>price {analyses.price}</h3>
                    <div>description: {analyses.description}</div>
                </div>
            );
        }

        return (
            <div className="container">
                <h1>Details Page</h1>
                {main}
            </div>
        );
    }
}