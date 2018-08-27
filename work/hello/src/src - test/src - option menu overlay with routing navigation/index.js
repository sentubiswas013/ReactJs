import React from 'react'
import {render} from 'react-dom'
import './App.css'
import {Route,Switch,HashRouter } from 'react-router-dom';
import Home from './home'
import About from './about'
import Services from './services'
import Works from './works'
import Contact from './contact'

class Main extends React.Component{
  constructor(props, context) {
    super();
  }
   render() {
    return (
        <div>
          <Switch>
			  <Route exact path="/" component={Home} />
			  <Route exact path="/About" component={About}/>
			  <Route exact path="/Services" component={Services}/>
			  <Route exact path="/Works" component={Works}/>
			  <Route exact path="/Contact" component={Contact}/>
          </Switch>
        </div>
    );
  }
}

class App extends React.Component{
  render() {
    return (
		<div className="container">
			<div className="footer-title"> JioAsist </div>
		    <HashRouter history={History}>
			  <div className="App">
				 <Route component={Main} />
			  </div>
			</HashRouter>
		</div>
    );
  }
}

render(<App/>, document.getElementById("root"));
