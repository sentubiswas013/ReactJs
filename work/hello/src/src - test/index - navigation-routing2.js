import React from 'react'
import { render } from 'react-dom'
//import './index.css';
import { BrowserRouter as Router, Route, Link, NavLink } from 'react-router-dom'

const Home =()=>(
  <h1> Home </h1>
)
const About =()=>(
  <h1> About </h1>
)
const Content =()=>(
  <div>
  <ul className="sidemenu">
    <li>
      <NavLink exact activeClassName="selected" to="/Content/Menu1"> Menu1 </NavLink>
      <NavLink activeClassName="selected" to="/Content/Menu2">  Menu2 </NavLink>
    </li>
  </ul>
  <switch>
    <Route path="/Content/Menu1" component={Menu1} />
    <Route path="/Content/Menu2" component={Menu2} />
  </switch>
  </div>
)
const Links=()=>(
  <div>
    <NavLink exact activeClassName="selected" to="/"> Home </NavLink>
    <NavLink activeClassName="selected" to="/About">  About </NavLink>
    <NavLink activeClassName="selected" to="/Content">  Content </NavLink>
  </div>
)

const Menu1 =()=>(
  <div> Menu1 </div>
)
const Menu2 =()=>(
  <div> Menu2 </div>
)

const App = (e) => (
  <Router>
    <switch>
      <Links />
      <Route exact path="/" component={Home}/>
      <Route path="/About" component={About}/>
      <Route path="/Content" component={Content}/>
    </switch>
  </Router>
)

render(<App />, document.getElementById('root'));


