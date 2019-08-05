import {Button, Form, Icon, Input, message} from 'antd';
import React from "react";
import Fetch from "./Fetch"
import "antd/dist/antd.css";


const errorMessage = (messageText) => {
    message.error(messageText);
};

const successMessage = (messageText) => {
    message.success(messageText);
};
class Register extends React.Component {

    state = {
        confirmDirty: false,
        autoCompleteResult: [],
    };

    handleConfirmBlur = e => {
        const {value} = e.target;
        this.setState({confirmDirty: this.state.confirmDirty || !!value});
    };

    compareToFirstPassword = (rule, value, callback) => {
        const {form} = this.props;
        if (value && value !== form.getFieldValue('password')) {
            callback('Two passwords that you enter is inconsistent!');
        } else {
            callback();
        }
    };

    validateToNextPassword = (rule, value, callback) => {
        const {form} = this.props;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], {force: true});
        }
        callback();
    };

    handleWebsiteChange = value => {
        let autoCompleteResult;
        if (!value) {
            autoCompleteResult = [];
        } else {
            autoCompleteResult = ['.com', '.org', '.net'].map(domain => `${value}${domain}`);
        }
        this.setState({autoCompleteResult});
    };
    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                Fetch.postData("http://localhost:8080/user/register", values)
                    .then(Fetch.status)
                    .then(Fetch.json)
                    .then(data => {
                        successMessage("Register complete! Login please!");
                        this.props.handler({userData: null});
                    })
                    .catch(error => errorMessage(error.message));

            } else {

            }
        });

    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const {autoCompleteResult} = this.state;

        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 8},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 16},
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 24,
                    offset: 0,
                },
                sm: {
                    span: 16,
                    offset: 8,
                },
            },
        };
        return (

            <Form {...formItemLayout} onSubmit={this.handleSubmit}>
                <Form.Item label="Login">
                    {getFieldDecorator('login', {
                        rules: [
                            {
                                required: true,
                                message: 'Please input your login!',
                            },
                        ],
                    })(<Input/>)}
                </Form.Item>
                <Form.Item label="Full name">
                    {getFieldDecorator('fullName', {
                        rules: [
                            {
                                required: true,
                                message: 'Please input your full name!',
                            },
                        ],
                    })(<Input/>)}
                </Form.Item>
                <Form.Item label="E-mail">
                    {getFieldDecorator('email', {
                        rules: [
                            {
                                type: 'email',
                                message: 'The input is not valid E-mail!',
                            },
                            {
                                required: true,
                                message: 'Please input your E-mail!',
                            },
                        ],
                    })(<Input/>)}
                </Form.Item>
                <Form.Item label="Password" hasFeedback>
                    {getFieldDecorator('password', {
                        rules: [
                            {
                                required: true,
                                message: 'Please input your password!',
                            },
                            {
                                validator: this.validateToNextPassword,
                            },
                        ],
                    })(<Input.Password/>)}
                </Form.Item>
                <Form.Item label="Confirm Password" hasFeedback>
                    {getFieldDecorator('confirm', {
                        rules: [
                            {
                                required: true,
                                message: 'Please confirm your password!',
                            },
                            {
                                validator: this.compareToFirstPassword,
                            },
                        ],
                    })(<Input.Password onBlur={this.handleConfirmBlur}/>)}
                </Form.Item>
                <Form.Item {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        Register
                    </Button>
                </Form.Item>
            </Form>
        );
    }
}

export default Register;