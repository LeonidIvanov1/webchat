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
            messages: [],
            lastIndex: 0
        }
    }

    componentDidMount() {
        this.getMessages();
        this.scrollToBottom();
    }

    getMessages = () => {

        let url = "http://localhost:8080/message?messageIndex=" + this.state.lastIndex;
        Fetch.getData(url)
            .then(Fetch.status)
            .then(Fetch.json)
            .then(data => {
                Array.prototype.push.apply(this.state.messages, data);

                this.setState({
                    lastIndex: (parseInt(this.state.lastIndex) + parseInt(data.length))
                });
            }).then(() => {
            if (sessionStorage.getItem('token') !== null)
                this.getMessages();
        })
    };

    getDisplayMessages = () => {
        let messages = this.state.messages;
        if (messages != null) {
            let displMessages = [];
            for (let i = 0; i < messages.length; i++) {
                displMessages.push(<Message key={messages[i].id} data={messages[i]}/>);
            }
            return displMessages;
        }
    };

    sendMessage = (text) => {
        let data = {
            authorId: sessionStorage.getItem("id"),
            text: text
        };
        let send = document.getElementById("send");
        send.setAttribute("value", "");
        Fetch.postData("http://localhost:8080/message", data)
            .then(Fetch.status);
    };

    scrollToBottom = () => {
        this.messagesEnd.scrollIntoView({behavior: "smooth"});
    };


    componentDidUpdate() {
        this.scrollToBottom();
    }

    render() {
        return (

            <Layout className="chat">
                <div className={"flex-chat"}>
                    <Layout className="message-area">
                        {this.getDisplayMessages()}
                        <div style={{float: "left", clear: "both"}}
                             ref={(el) => {
                                 this.messagesEnd = el;
                             }}>
                        </div>
                    </Layout>
                </div>
                <Search id="send"
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