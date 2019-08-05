import React from "react";
import "antd/dist/antd.css";
import {Badge, Input} from 'antd';


const {Search} = Input;

class User extends React.Component {
    constructor(props) {
        super(props);
        this.state = {userData: props.userData}
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };

    handleClick = e => {
        this.props.onClick(e);
    };

    getUserStatusBadge = () => {
        if (this.state.userData.userStatus === 'ONLINE') {
            return <Badge className={"user-status"} color="#87d068"/>
        } else {
            return <Badge className={"user-status"} color="#f50"/>
        }
    };


    render() {
        return (
            <div className="user" userid={this.state.userData.userId} onClick={this.handleClick}>
                {this.getUserStatusBadge()}
                <div className={"user-data"}>
                    <div className="name">
                        {this.state.userData.fullName}
                    </div>
                </div>
            </div>


        );
    }
}

export default User;