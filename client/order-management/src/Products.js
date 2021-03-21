import React from "react";
import styled from 'styled-components';

const ProductContainer = styled.div`
 display:flex;
 margin-left: 200px;
 margin-top: 50px;
 margin-bottom: 100px;
`;
const Product = styled.div`
 box-shadow: 1px 1px 5px #9E9E9E;
 margin: 10px 10px 10px 10px;
 padding: 10px;
 border-radius: 5px;
 transition: all 200ms ease-in;
 &:hover{
    box-shadow: 1px 3px 20px #9E9E9E;
    transform: translateZ(50px);
 }
`;
const ProductButton = styled.div`
 background-color: #febd69;
 border-radius: 10px;
 border: 2px solid black; 
 cursor: pointer;
 width: 50%;
 margin: auto;
 margin-top: 10px;
 margin-bottom: 10px;
`;

const NewOrder = styled.div`
    background-color: light-green;
    border: 2px solid green;
    border-radius: 10px;
`;
class Products extends React.Component {
    state = {
        products: null,
        isOrdered: false,
        productName: null
    }

    componentDidMount() {
        fetch(`http://localhost:8080/products`)
            .then(res => res.json())
            .then(data => this.setState({ products: data }));
    }

    placeOrder(productId, productName) {
        const orderPayload = {
            productId,
            customerId: this.props.customerId,
            quantity: 1
        }
        fetch(`http://localhost:8080/order`, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(orderPayload)
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    isOrdered: true,
                    productName: productName
                });
            })

    }

    render() {
        if (this.state.products) {
            return (
                <>
                 {this.state.isOrdered && <NewOrder>{this.state.productName} has been ordered successfully</NewOrder>}
                 <ProductContainer>
                    {this.state.products.map(product => (<Product>
                        <div> {product.productName} </div>
                        <div> {product.manufacturer} </div>
                        <div> {product.description} </div>
                        <ProductButton onClick={this.placeOrder.bind(this, product.id, product.productName)}> Place order </ProductButton>
                    </Product>)
                    )}
                </ProductContainer>
                </>);
        }
        return null;
    }
}

export default Products;