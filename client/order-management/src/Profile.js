import React from "react";
import styled from 'styled-components';

const ProfileContainer = styled.div`
 margin-left: 200px;
 margin-top: 50px;
 margin-bottom: 100px;
`;
const Welcome = styled.div`
 display: flex;
 border: solid 1px lightgrey;
 border-radius: 10px;
 width: 50%;
 padding: 10px 10px 10px 10px;
`;
const CustomerName = styled.div`
 font-size: 18;
 font-weight: bold;
`;
const CustomerDetails = styled.div`
   display: grid;
   text-align: left !important;
   padding-left: 20px;
`;
class Profile extends React.Component {
    state = {
        customer: {}
    }
    componentDidMount() {
        fetch(`http://localhost:8080/customer/${this.props.customerId}`)
            .then(res => res.json())
            .then(data => this.setState({ customer: data }));
    }
    render() {
        return (
            <ProfileContainer>
                <Welcome>
                    <img src={this.props.photo} alt={this.props.customerName} width="100" height="100" />
                    <CustomerDetails>
                        <CustomerName>Welcome {this.state.customer.firstName}</CustomerName>
                        <div>Email: {this.state.customer.email}</div>
                        <div>Phone: {this.state.customer.phone}</div>
                    </CustomerDetails>
                </Welcome>
            </ProfileContainer>
        );
    }
}

export default Profile;