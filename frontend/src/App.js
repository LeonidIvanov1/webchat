import React from "react";
import "antd/dist/antd.css";
import {Button, Form, Input} from 'antd';
import {Layout} from 'antd';
import Chat from "./components/Chat";
import Users from "./components/Users";
import Login from "./components/Login";
import Fetch from "./components/Fetch";
import Register from "./components/Register"

const WrappedNormalLoginForm = Form.create({name: 'normal_login'})(Login);
const WrappedNormalRegisterForm = Form.create({name: 'normal_register'})(Register);


const {Header, Footer, Sider, Content} = Layout;
const {Search} = Input;

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            userData: null,
            register: false
        };
    }

    handleSubmit = e => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Received values of form: ', values);
            }
        });
    };


    setUserData = (data) => {
        this.setState({userData: data});
    };

    getRenderData() {
        if (sessionStorage.getItem('token') == null) {
            if (!this.state.register) {
                return <Layout>
                    <Content className="central-content">
                        <WrappedNormalLoginForm handler={this.setUserData}/>
                    </Content>
                </Layout>
            } else {
                return <Layout>
                    <Content className="central-content">
                        <WrappedNormalRegisterForm className={"register-form"} handler={this.setUserData}/>
                    </Content>
                </Layout>
            }
        } else {
            return <Layout>
                <Content className="central-content">
                    <Users/>
                    <Chat/>
                </Content>
            </Layout>

        }
    }

    showRegister = () => {
        this.setState({register: true});
    };

    showLogin = () => {
        this.setState({register: false});
    };

    getHeaderButtons = () => {
        if (this.state.userData == null && !this.state.register) {
            return <div>
                <Button type="primary" onClick={this.showRegister}>Register</Button>
            </div>
        } else if (this.state.register) {
            return <div><Button type="primary" onClick={this.showLogin}>Login</Button></div>;
        } else {
            return <div>
                <Button type="primary" onClick={this.logout}>Logout</Button>
            </div>
        }
    };

    logout = () => {
        Fetch.postData("http://localhost:8080/auth/logout", {}).then(res => {
            sessionStorage.clear();
            this.setState({userData: null});
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
                <Footer className={"footer"}>
                    ©EPAM Spring WEB application
                </Footer>

            </Layout>
        );
    }
}

export default App;