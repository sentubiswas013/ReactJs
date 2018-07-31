/*import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter as Router, Route, Link } from 'react-router-dom';


const Home =() => (
	<h1> Home </h1>
)

const About =() =>(
	<h1> About </h1>
)

const Links =() =>(
	<ul> 
		<li><Link to="/"> Home </Link></li> 
		<li><Link to="/about"> About </Link></li>
	</ul>
)


const App = (e) => (
	<Router>
		<swtich>
			<Links />
			<Route exact path="/" component={Home} />
			<Route path="/about" component={About} />
			</swtich>
	</Router>
)
*/

import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter as Router, Route, Link, NavLink } from 'react-router-dom';


const Home =() => (
	<h1> Home </h1>
)

const About =() =>(
	<h1> About </h1>
)

const Links =() =>(
	<ul> 
		<li><NavLink exact activeClassName="selected" to="/"> Home </NavLink></li> 
		<li><NavLink activeClassName="selected" to="/about"> About </NavLink></li>
	</ul>
)


const App = (e) => (
	<Router>
		<swtich>
			<Links />
			<Route exact path="/" component={Home} />
			<Route path="/about" component={About} />
			</swtich>
	</Router>
)

export default App;