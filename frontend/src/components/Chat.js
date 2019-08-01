
import React from "react";
import "antd/dist/antd.css";
import { Input } from 'antd';
import {Layout} from 'antd';
import Message from "./Message";

const { Search } = Input;

class Chat extends React.Component {
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
            <Layout className="chat">
                <Layout className="message-area">
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                    <Message/>
                </Layout>
            <Search
                placeholder="Input message text..."
                enterButton="Send"
                size="large"
                onSearch={value => console.log(value)}
            />
            </Layout>
        );
    }
}

export default Chat;