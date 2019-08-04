import React from "react";
import "antd/dist/antd.css";
import {Input} from 'antd';
import {Layout} from 'antd';
import Message from "./Message";
import Fetch from "./Fetch";

const {Search} = Input;

class Chat extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            messages: null
        }
    }

    componentDidMount() {
        this.getMessages();
    }

    getMessages = () => {
        Fetch.getData("http://localhost:8080/message")
            .then(Fetch.status)
            .then(Fetch.json)
            .then(data => {
                this.setState({messages: data});
            })
    };

    getDisplayMessages = () => {
        let messages = this.state.messages;
        if (messages != null) {
            let displMessages = [];
            for (let i = 0; i < messages.length; i++) {
                displMessages.push(<Message data={messages[i]}/>);
            }
            return displMessages;
        }
    };

    sendMessage = (text) => {
        let data = {
            authorId: sessionStorage.getItem("id"),
            text: text
        };
        Fetch.postData("http://localhost:8080/message", data)
            .then(Fetch.status)
            .then(Fetch.json)
            .then(data => {
                this.getMessages();
                text="";
            })
    };

    render() {
        return (
            <Layout className="chat">
                <Layout className="message-area">
                    {this.getDisplayMessages()}
                </Layout>
                <Search
                    placeholder="Input message text..."
                    enterButton="Send"
                    size="large"
                    onSearch={this.sendMessage}
                />
            </Layout>
        );
    }
}

export default Chat;