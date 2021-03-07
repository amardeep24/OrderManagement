import React from "react";
import Sidebar from './Sidebar';
import Products from './Products';
import Profile from './Profile';
import Orders from './Orders';
import GoogleLogin from 'react-google-login';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from "react-router-dom";

class App extends React.Component {

  state = {
    customerId: null,
    isLoggedIn: false,
    name: null,
    photo: null,
  }

  onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    const customerId = profile.getId();
    const [firstName, lastName] = profile.getName().split(" ");
    const email = profile.getEmail();
    const address = "Google";
    const phone = 10000;
    fetch(`http://localhost:8080/customer/${customerId}`)
      .then(res => {
        if (res.status == 404) {
          return fetch(`http://localhost:8080/customer`, {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify({
              id: customerId,
              firstName,
              lastName,
              address,
              email,
              phone
            })
          });
        } else {
          return res.json();
        }
      })
      .then(data => {
        this.setState({
          isLoggedIn: true,
          name: profile.getName(),
          photo: profile.getImageUrl(),
          customerId: profile.getId()
        })
      }).catch(err => {
        console.error(err);
      });
  }

  onSignOut() {
    this.setState({
      isLoggedIn: false,
      name: null,
      photo: null,
      customerId: null
    })
  }

  render() {
    return (
      <div className="App">
        {!this.state.isLoggedIn && <GoogleLogin
          clientId="250948059519-isaef6h25pq6fnlm2qcs9negm9t0ff06.apps.googleusercontent.com"
          buttonText="Login"
          onSuccess={this.onSignIn.bind(this)}
          cookiePolicy={'single_host_origin'}
        />}
        <div id="App">
          {this.state.isLoggedIn &&
            <Router>
              <Sidebar pageWrapId={'page-wrap'}
                outerContainerId={'App'}
                onLogout={this.onSignOut.bind(this)} />
              <div id="page-wrap">
                <Switch>
                  <Route exact path="/">
                    <Profile
                      photo={this.state.photo}
                      customerId={this.state.customerId} />
                  </Route>
                  <Route path="/profile">
                    <Products
                      customerId={this.state.customerId} />
                  </Route>
                  <Route path="/orders">
                    <Orders
                      customerId={this.state.customerId} />
                  </Route>
                </Switch>
              </div>
            </Router>
          }
        </div>
      </div>
    );
  }
}

export default App;