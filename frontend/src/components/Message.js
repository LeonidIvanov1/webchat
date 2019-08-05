import React from "react";
import "antd/dist/antd.css";
import {Input} from 'antd';
import {Layout} from 'antd';

const {Search} = Input;

class Message extends React.Component {
    constructor(props) {
        super(props);
        this.state = {message: this.props.data}
    }

    render() {
        return (
            <Layout className="message">
                <Layout className="author">
                    {this.state.message.authorFullName}
                </Layout>
                <Layout className="text">
                    {this.state.message.text}
                </Layout>
                <Layout className="date">
                    {this.state.message.sendingTime}
                </Layout>
            </Layout>
        );
    }
}

export default Message;