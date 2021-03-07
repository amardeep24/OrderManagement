import React from 'react';
import { slide as Menu } from 'react-burger-menu';
import { GoogleLogout } from 'react-google-login';
import {
    Link
} from "react-router-dom";
import styled from 'styled-components';;

class Sidebar extends React.Component {
    render() {
        return (
            <Menu>
                <div className="menu-item">
                    <Link to="/" style={{ textDecoration: 'none', color: 'white' }}> Home</Link>
                </div>
                <div className="menu-item">
                    <Link to="/profile" style={{ textDecoration: 'none', color: 'white' }}> Shop</Link>
                </div>
                <div className="menu-item" style={{ textDecoration: 'none' }}>
                    <Link to="/orders" style={{ textDecoration: 'none', color: 'white' }}> Orders</Link>
                </div>
                <GoogleLogout
                    clientId="250948059519-isaef6h25pq6fnlm2qcs9negm9t0ff06.apps.googleusercontent.com"
                    buttonText="Logout"
                    onLogoutSuccess={this.props.onLogout}
                />
            </Menu>
        );
    }
};

export default Sidebar;