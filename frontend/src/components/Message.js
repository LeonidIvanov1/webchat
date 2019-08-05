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
     convert(timestamp){
         let months_arr = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
         let date = new Date(timestamp);
         let year = date.getFullYear();
         let month = months_arr[date.getMonth()];
         let day = date.getDate();
         let hours = date.getHours();
         let minutes = "0" + date.getMinutes();
         let seconds = "0" + date.getSeconds();
         return  month+'-'+day+'-'+year+' '+hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);

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
                    {this.convert(this.state.message.sendingTime)}
                </Layout>
            </Layout>
        );
    }
}

export default Message;