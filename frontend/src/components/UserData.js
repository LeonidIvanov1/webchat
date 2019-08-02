import {Button, Form, Icon, Input} from 'antd';
import React from "react";
import "antd/dist/antd.css";

class UserData extends React.Component {
    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        return (

            <Form onSubmit={this.handleSubmit} className="login-form">
                <Form.Item label="Full name">
                    {getFieldDecorator('username', {
                        rules: [{
                            required: true,
                            message: 'Please input your full name!'
                        }],
                    })(
                        <Input
                            prefix={<Icon type="user"
                                          style={{color: 'rgba(0,0,0,.25)'}}/>}
                            placeholder="Full name"
                        />,
                    )}
                </Form.Item>
                <Form.Item label="Email">
                    {getFieldDecorator('username', {
                        rules: [{
                            required: true,
                            message: 'Please input your email!'
                        }],
                    })(
                        <Input
                            prefix={<Icon type="user"
                                          style={{color: 'rgba(0,0,0,.25)'}}/>}
                            placeholder="Full name"
                        />,
                    )}
                </Form.Item>
                <Form.Item>

                    <Button>Kick</Button>
                </Form.Item>
                <Form.Item>

                    <Button>Ban</Button>
                </Form.Item>
            </Form>
        );
    }
}

export default UserData;