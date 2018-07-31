import React from 'react'
import {render} from 'react-dom'
import './App.css'
import {Route,Switch,HashRouter } from 'react-router-dom';
import Links from './links'
import Home from './home'
import About from './about'


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


class Links extends React.Component{
	render(){
		return(	
			<div className="optionMenuHome" id="optionMenuHome">
				<ul>
					<li><Link to="/"> Home </Link></li>
					<li><Link to="/About"> About </Link></li>
				</ul>
			</div>
		)
	}	
}

class Home extends React.Component{
	render(){
		return(	
			<h1> Home </h1>
		)
	}
}
class About extends React.Component{
	render(){
		return(	
			<h1> About </h1>
		)
	}
}


render(<App/>, document.getElementById("root"));
