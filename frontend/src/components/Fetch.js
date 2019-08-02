class Fetch {
    static postData(url, data) {
        return fetch(url, {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'same-origin',
            headers: this.getHeaders(),
            redirect: 'follow',
            referrer: 'no-referrer',
            body: JSON.stringify(data),
        });
    }

    static getJwtToken() {
        if (sessionStorage.getItem('token') != null) {
            console.log(sessionStorage.getItem('token'));
            return 'Bearer_' + sessionStorage.getItem('token');
        }

    }
    static getHeaders() {
        return new Headers({
            'Authorization': "blabla",
            'Content-Type': 'application/json'
        })
    }

    static status(response) {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response);
        } else if (response.status === 422) {
            return Promise.reject(new Error("Username and password do not match"));
        } else {
            return Promise.reject(new Error(response.body));
        }

    }

    static json(response) {
        return response.json()
    }
}

export default Fetch;