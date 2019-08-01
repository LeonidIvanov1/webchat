import React from "react";
import "antd/dist/antd.css";
import {Input} from 'antd';
import {Layout} from 'antd';

const {Search} = Input;

class User extends React.Component {
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
            <div className="user">
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