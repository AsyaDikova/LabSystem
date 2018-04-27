const host = 'http://localhost:8000/';

async function register(username, email, password, confirmPassword, firstName, lastName, telephone) {
    const res = await fetch(host + 'employee/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken')
        },
        body: JSON.stringify({
            username,
            email,
            password,
            confirmPassword,
            firstName,
            lastName,
            telephone
        })
    });
    return await res.json();
}

async function login(username, password) {
    const res = await fetch(host + 'login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username,
            password
        })
    });
    return await res.json();
}

function parseJwt (token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
}


async function getAnalyzes() {
    const res = await fetch(host + 'analyzes');
    return await res.json();
}

async function getAnalysesDetails(id) {
    const res = await fetch(host + 'analyzes/' + id, {
        method: 'GET'
    });
    return await res.json();
}

async function addToCart(tripId, date, classType, count){
    const res = await fetch(host + 'cart', {
        method: 'POST',
        headers: {
            'Authorization' : 'Bearer ' + localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            tripId,
            date,
            class: classType,
            count
        })
    });
    return await res.json();
}

async function getCardDetails() {
    const res = await fetch(host + 'cart', {
        method: 'GET',
        headers: {
            'Authorization': 'bearer ' + localStorage.getItem('authToken'),
        }
    });
    return await res.json();
}

async function deleteTicket(ticketId) {
    const res = await fetch(host + `cart/${ticketId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'bearer ' + localStorage.getItem('authToken'),
        }
    });
    return await res.json();
}

async function checkoutCart(){
    const res = await fetch(host + 'cart/checkout', {
        method: 'POST',
        headers: {
            'Authorization' : 'bearer ' + localStorage.getItem('authToken'),
            'Content-Type': 'application/json'
        }
    });
    return await res.json();
}

async function getMyTickets() {
    const res = await fetch(host + 'cart/history', {
        method: 'GET',
        headers: {
            'Authorization': 'bearer ' + localStorage.getItem('authToken'),
        }
    });
    return await res.json();
}

async function deleteCart() {
    const res = await fetch(host + `cart`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'bearer ' + localStorage.getItem('authToken'),
        }
    });
    return await res.json();
}

async function search(origin, destination, date) {
    const res = await fetch(host + `search?origin=${origin}&destination=${destination}&date=${date}`, {
        method: 'GET',
        headers: {
            'Authorization': 'bearer ' + localStorage.getItem('authToken'),
        }
    });
    return await res.json();
}




export { register, login, getAnalyzes, getAnalysesDetails, parseJwt, addToCart , getCardDetails, deleteTicket, checkoutCart, getMyTickets, deleteCart, search};