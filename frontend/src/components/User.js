import React from "react";
import "antd/dist/antd.css";
import {Input} from 'antd';


const {Search} = Input;

class User extends React.Component {
    constructor(props) {
        super(props);
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


    render() {
        return (
            <div className="user" onClick={this.handleClick}>
                <div className="name">
                    Иван Иванов
                </div>
                <div className="email">
                    ivanov@email.com
                </div>
            </div>


        );
    }
}

export default User;