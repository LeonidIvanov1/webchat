import React from "react";
import "antd/dist/antd.css";
import {Input} from 'antd';
import {Layout} from 'antd';

const {Search} = Input;

class Message extends React.Component {
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
            <Layout className="message">
                <Layout className="author">
                    Иван Иванов
                </Layout>
                <Layout className="text">
                    Важно знать количество букв или всех символов в тексте.
                    Подчас то, что вы пишете, менее важно чем то, как вы пишете
                    и сколько символов при этом используете. Поэтому многие
                    интернет-пользователи
                    беспокоятся о количестве символов.!!!!!!!!!!!!!!!!!!!!!!!!!
                </Layout>
                <Layout className="date">
                    01.09.19 20:00
                </Layout>
            </Layout>
        );
    }
}

export default Message;