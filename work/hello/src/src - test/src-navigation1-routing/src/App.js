import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import './App.css';
import Home  from "./components/Home";
import Sms  from "./components/Sms";
import SaveContact  from "./components/saveContact";


class Main extends Component {
  constructor(props, context) {
    super();
  }
   render() {
    return (
        <div>
          <Switch>
		  <Route exact path="/" component={Home} />
		  <Route exact path="/sms" component={Sms}/>
		  <Route exact path="/saveContact" component={SaveContact}/>
          </Switch>
        </div>
    );
  }
}

class App extends Component {
  render() {
	  console.log(this.context);
    return (
      <HashRouter history={History}>
          <div className="App">
             <Route component={Main} />
          </div>
        </HashRouter>
    );
  }
}

export default App;
