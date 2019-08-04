import React from "react";
import "antd/dist/antd.css";
import Fetch from "./Fetch";
import {Button, Descriptions} from "antd";

class UserData extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            userId: this.props.userID,
            userData: {
                fullName: "",
                email: "",
                userStatus: ""
            }
        }
    }

    componentDidMount = () => {
        let url = "http://localhost:8080/user/" + this.state.userId;
        Fetch.getData(url).then(Fetch.status).then(Fetch.json).then(data => {
            this.setState({userData: data})
        })
    };

    banUser = () => {
        let url = "http://localhost:8080/user/ban/" + this.state.userId;
        Fetch.putData(url, {}).then(Fetch.status).then(Fetch.json).then(data => {
            this.setState({userData: data})
        })
    };

    unbanUser = () => {
        let url = "http://localhost:8080/user/unban/" + this.state.userId;
        Fetch.putData(url, {}).then(Fetch.status).then(Fetch.json).then(data => {
            this.setState({userData: data})
        })
    };
    getBunButton = () => {
        if (sessionStorage.getItem("role") === "ADMINISTRATOR" &&
            sessionStorage.getItem("id") !== this.state.userData.userId)
            if (this.state.userData.userStatus === "BANNED") {
                return <Button onClick={this.unbanUser}>Unban</Button>
            } else {
                return <Button onClick={this.banUser}>Ban user</Button>
            }
    };

    render() {
        return (<div>
                <Descriptions>
                    <Descriptions.Item label="Full name">{this.state.userData.fullName}</Descriptions.Item>
                    <br/><br/>
                    <Descriptions.Item label="Email">{this.state.userData.email}</Descriptions.Item>
                    <br/><br/>
                    <Descriptions.Item label="Status">{this.state.userData.userStatus}</Descriptions.Item>
                    <br/><br/>

                </Descriptions>
                {this.getBunButton()}
            </div>
        );
    }
}

export default UserData;