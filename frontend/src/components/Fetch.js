class Fetch {
    static postData(url, data) {
        return fetch(url, {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'include',
            headers: this.getHeaders(),
            redirect: 'follow',
            referrer: 'no-referrer',
            body: JSON.stringify(data),
        });
    }
    static putData(url, data) {
        return fetch(url, {
            method: 'POST',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'include',
            headers: this.getHeaders(),
            redirect: 'follow',
            referrer: 'no-referrer',
            body: JSON.stringify(data),
        });
    }
    static getData(url) {
        return fetch(url, {
            method: 'GET',
            mode: 'cors',
            cache: 'no-cache',
            credentials: 'include',
            headers: this.getHeaders(),
            redirect: 'follow',
            referrer: 'no-referrer',
        });
    }

    static getJwtToken() {
        if (sessionStorage.getItem('token') != null) {
            return 'Bearer_' + sessionStorage.getItem('token');
        }

    }
    static getHeaders() {
        return new Headers({
            'Authorization': this.getJwtToken(),
            'Content-Type': 'application/json'
        })
    }

    static status(response) {
        if (response.status >= 200 && response.status < 300) {
            return Promise.resolve(response);
        } else if (response.status === 422) {
            return Promise.reject(new Error("Username and password do not match"));
        } else {
            return Promise.reject(new Error(response));
        }

    }

    static json(response) {
        return response.json()
    }
}

export default Fetch;