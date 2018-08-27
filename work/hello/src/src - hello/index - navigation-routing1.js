import React from 'react'
import { render } from 'react-dom'
//import './index.css';
import { BrowserRouter as Router, Route, Link, NavLink } from 'react-router-dom'


const Home=()=>(
  <h1> Home </h1>
)
const About=()=>(
  <h1> About </h1>
)
const Links=()=>(
  /*<ul>
    <li><Link to="/"> Home </Link></li>
    <li><Link to="/About">  About </Link></li>
  </ul>*/
  <ul>
    <li><NavLink exact activeClassName="selected" to="/"> Home </NavLink></li>
    <li><NavLink activeClassName="selected" to="/About">  About </NavLink></li>
  </ul>
)

const App = (e) => (
  <Router>
    <switch>
      <Links />
      <Route exact path="/" component={Home}/>
      <Route path="/About" component={About}/>
    </switch>
  </Router>
)

render(<App />, document.getElementById('root'));


