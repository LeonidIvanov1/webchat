import React from "react";
import "antd/dist/antd.css";
import {Button, Drawer, Form, Input, Layout} from 'antd';
import User from "./User";
import UserData from "./UserData";
import Login from "./Login";


const ButtonGroup = Button.Group;

const {Search} = Input;

const WrappedNormalLoginForm = Form.create({name: 'normal_login'})(UserData);

class Users extends React.Component {

    state = {visible: false};
    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };


    handleClick = e => {
        console.log("click");
        this.setState({
            visible: true,
        });
    };

    onClose = () => {
        this.setState({
            visible: false,
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
                    <User onClick={this.handleClick}/>
                    <User onClick={this.handleClick}/>
                </Layout>
                <Drawer
                    title="User Information"
                    placement="right"
                    closable={false}
                    onClose={this.onClose}
                    visible={this.state.visible}
                >
                    <WrappedNormalLoginForm/>
                </Drawer>
            </Layout>
        );
    }
}

export default Users;