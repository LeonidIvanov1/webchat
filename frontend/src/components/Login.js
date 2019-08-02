import {Button, Form, Icon, Input, message} from 'antd';
import React from "react";
import Fetch from "./Fetch"
import "antd/dist/antd.css";

const {URL} = "http://localhost:8080/auth/login";

const errorMessage = (messageText) => {
    message.error(messageText);
};

class Login extends React.Component {


    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                Fetch.postData("http://localhost:8080/auth/login", values)
                    .then(Fetch.status)
                    .then(Fetch.json)
                    .then(data => {
                        sessionStorage.setItem('token', data.token);
                        sessionStorage.setItem('role', data.userRole);
                        sessionStorage.setItem('id', data.userID);
                        this.props.handler(data);
                    })
                    .catch(error => errorMessage(error.message));

            } else {

            }
        });

    };

    render() {
        const {getFieldDecorator} = this.props.form;
        return (

            <Form onSubmit={this.handleSubmit} className="login-form">
                <Form.Item>
                    {getFieldDecorator('username', {
                        rules: [{
                            required: true,
                            message: 'Please input your username!'
                        }],
                    })(
                        <Input
                            prefix={<Icon type="user"
                                          style={{color: 'rgba(0,0,0,.25)'}}/>}
                            placeholder="Username"
                        />,
                    )}
                </Form.Item>
                <Form.Item>
                    {getFieldDecorator('password', {
                        rules: [{
                            required: true,
                            message: 'Please input your Password!'
                        }],
                    })(
                        <Input
                            prefix={<Icon type="lock"
                                          style={{color: 'rgba(0,0,0,.25)'}}/>}
                            type="password"
                            placeholder="Password"
                        />,
                    )}
                </Form.Item>
                <Form.Item>

                    <Button type="primary" htmlType="submit"
                            className="login-form-button">
                        Log in
                    </Button>

                </Form.Item>
            </Form>
        );
    }
}

export default Login;