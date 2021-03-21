import React from "react";
import styled from 'styled-components';

const OrderContainer = styled.div`
    margin: 50px 0px 100px 100px;"
`;

const Order = styled.div`
    text-align: left;
    border-radius: 5px;
    border: 1px solid black;
    width: 93%;
    margin: 10px;
`;
const OrderHeader = styled.div`
    background-color: #f6f6f6;
`;

const OrderBody = styled.div`
    border-top: 1px solid black;
`;
const OrderFooter = styled.div`

`;

class Orders extends React.Component {
    state = {
        orders: []
    }
    componentDidMount() {
        fetch(`http://localhost:8080/orders/${this.props.customerId}`)
            .then(res => res.json())
            .then(data => this.setState({ orders: data }));
    }
    render() {
        return (
            <OrderContainer>
                <div>Your Orders</div>
                {this.state.orders && this.state.orders.map(order =>
                    <Order>
                        <OrderHeader>
                            <div>Order Date: {new Date(Date.parse(order.orderDate)).toString()}</div>
                            <div>Order Total: {order.orderTotal}</div>
                        </OrderHeader>
                        <OrderBody>
                            <div>Product Name: {order.product.productName}</div>
                            <div>Order Description:{order.product.description}</div>
                            <div>Quantity: {order.quantity}</div>
                        </OrderBody>
                        <OrderFooter>
                            <div>Customer: {order.customer.firstName}</div>
                            <div>Shipping Address: {order.customer.address}</div>
                        </OrderFooter>
                    </Order>
                )}
            </OrderContainer>
        );
    }
}

export default Orders;