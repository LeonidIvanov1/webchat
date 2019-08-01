import React from "react";
import "antd/dist/antd.css";
import {Input} from 'antd';
import {Layout} from 'antd';
import User from "./User";

import {Button, Icon} from 'antd';

const ButtonGroup = Button.Group;

const {Search} = Input;

class Users extends React.Component {
    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };

    render() {
        return (
            <Layout className="users">
                <ButtonGroup>
                    <Button>Online</Button>
                    <Button>All</Button>
                </ButtonGroup>
                <Layout className="users-area">
                    <User/>
                    <User/>
                    <User/>
                    <User/>
                    <User/>
                    <User/>
                    <User/>
                    <User/>
                </Layout>
            </Layout>
        );
    }
}

export default Users;