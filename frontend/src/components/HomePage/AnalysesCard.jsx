import React from 'react';
import { Link } from 'react-router-dom';

export default function AnalyzesCard({ id, description, name, price }) {
    return (
        <article className="analysesCard">
            <p>Analyses name: {name}</p>
            <p>price: {price}</p>
            <div>description: {description}</div>
            <Link to={'/analyzes/' + id}>View Details</Link>
        </article>
    );
}