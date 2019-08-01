import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import {Form} from "antd";
import Login from "./components/Login"
import {Layout} from 'antd';
import Chat from "./components/Chat";
import Users from "./components/Users";

const WrappedNormalLoginForm = Form.create({name: 'normal_login'})(Login);


const {Header, Footer, Sider, Content} = Layout;

ReactDOM.render(
    <Layout className="app">
        <Header className="header">
            Web Chat v.1.0
        </Header>
        <Layout>
            <Sider className="left-side">
                <Users/>
            </Sider>
            <Content className="central-content">
                {/*<WrappedNormalLoginForm/>*/}
                <Chat/>
            </Content>
            <Sider className="right-side"></Sider>
        </Layout>

    </Layout>
    , document.getElementById('root'));
