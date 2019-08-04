import React from "react";
import "antd/dist/antd.css";
import {Button, Drawer, Form, Input, Layout} from 'antd';
import User from "./User";
import UserData from "./UserData";
import Login from "./Login";
import Fetch from "./Fetch";


const ButtonGroup = Button.Group;

const {Search} = Input;

class Users extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            visible: false,
            users: null,
            userStatus: "ONLINE"
        };
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };


    handleClick = (e) => {
        this.setState({
            visible: true,
            userId: e.currentTarget.getAttribute("userid")
        });
    };

    onClose = () => {
        this.setState({
            visible: false,
        });
    };

    componentDidMount = () => {
        Fetch.getData("http://localhost:8080/user")
            .then(Fetch.status)
            .then(Fetch.json)
            .then(data => {
                this.setState({users: data});
            })
    };


    getDisplayUsers = () => {
        let users = this.state.users;
        if (users != null) {
            let displUsers = [];
            for (let i = 0; i < users.length; i++) {
                if (this.state.userStatus === "ALL") {
                    displUsers.push(<User key={users[i].userId}  userData={users[i]} onClick={this.handleClick}/>)
                } else {
                    if (users[i].userStatus === "ONLINE") {
                        displUsers.push(<User key={users[i].userId} userData={users[i]} onClick={this.handleClick}/>)
                    }
                }
            }
            return displUsers;
        }
    };

    showOnlineUsers = () => {
        this.setState({userStatus: "ONLINE"})
    };
    showAllUsers = () => {
        this.setState({userStatus: "ALL"})
    };

    render() {
        return (
            <Layout className="users" width="300">
                <ButtonGroup>
                    <Button onClick={this.showOnlineUsers}>Online</Button>
                    <Button onClick={this.showAllUsers}>All</Button>
                </ButtonGroup>
                <Layout className="users-area">
                    {this.getDisplayUsers()}
                </Layout>
                <Drawer
                    title="User Information"
                    placement="right"
                    closable={false}
                    onClose={this.onClose}
                    visible={this.state.visible}
                >
                    <UserData key={this.state.userId} userID={this.state.userId}/>
                </Drawer>
            </Layout>
        );
    }
}

export default Users;