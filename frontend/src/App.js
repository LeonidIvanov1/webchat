import React from "react";
import "antd/dist/antd.css";
import {Button, Form, Input} from 'antd';
import {Layout} from 'antd';
import Chat from "./components/Chat";
import Users from "./components/Users";
import Login from "./components/Login";
import Fetch from "./components/Fetch";

const WrappedNormalLoginForm = Form.create({name: 'normal_login'})(Login);


const {Header, Footer, Sider, Content} = Layout;
const {Search} = Input;

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {userData: null};
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };

    getAdminRenderData() {
        console.log(sessionStorage.getItem('userData'));
        if (sessionStorage.getItem('role') === 'ADMINISTRATOR') {
            return <Users/>
        }
    }

    setUserData = (data) => {
        this.setState({userData: data});
    };

    getRenderData() {
        if (sessionStorage.getItem('token') === null) {
            return <Layout>
                <Content className="central-content">
                    <WrappedNormalLoginForm handler={this.setUserData}/>
                </Content>
            </Layout>
        } else {
            return <Layout>
                <Content className="central-content">
                    {this.getAdminRenderData()}
                    <Chat/>
                </Content>
            </Layout>

        }
    }

    getHeaderButtons() {
        if (sessionStorage.getItem('token') === null) {
            return <div>
                <Button type="primary">Register</Button>
            </div>
        } else {
            return <div>
                <Button type="primary" onClick={this.logout}>Logout</Button>
            </div>
        }
    }

    logout = () => {
        Fetch.postData("http://localhost:8080/auth/logout", {}).then(res => {
            sessionStorage.clear();
            this.setState(null);
        });

    };

    render() {
        return (
            <Layout className="app">
                <Header className="header">
                    Web Chat v.1.0
                    {this.getHeaderButtons()}
                </Header>
                {this.getRenderData()}
                <Footer>
                    EPAM Spring WEB application
                </Footer>

            </Layout>
        );
    }
}

export default App;