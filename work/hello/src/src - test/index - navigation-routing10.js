import React from 'react'
import { render } from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import { BrowserRouter as Router, Route, Link, NavLink, Prompt, Switch  } from 'react-router-dom';
//import './index.css'


const Home =() => (
  <h1> Home </h1>
)

const About =() =>(
  <h1> About </h1>
)

const NotFound =() => (
  <h1> Page not fould </h1>
)

class Form extends React.Component{
  constructor(){
    super();
    this.state = {
      isChanged: false
    }
  }
  render(){
    return(
      <div> 
        <Prompt
          when={this.state.isChanged}
          message="Are you sure you want to leave?"
        />
        <input onChange={()=> {
          this.setState({
            isChanged:true
          })
        }} type="text" /> 
      </div>
    )
  }
}

const Links =() =>(
  <div className="list-group"> 
    <NavLink className="list-group-item" exact activeClassName="active" to="/"> Home </NavLink>
    <NavLink className="list-group-item" activeClassName="active" to="/about"> About </NavLink>
    <NavLink className="list-group-item" activeClassName="active" to="/content"> content </NavLink>
    <NavLink className="list-group-item" activeClassName="active" to="/form"> Form </NavLink>
  </div>
)


const Content =() =>(
  <div>
    <NavLink className="list-group-item" exact activeClassName="active" to="/content/city"> City </NavLink>
    <NavLink className="list-group-item" exact activeClassName="active" to="/content/sports"> Sports </NavLink>
    <Route path="/content/:contentName" component={ContentDetails} />
  </div>
)

const ContentDetails = (props) =>(
  
  <div>
    {
      props.match.params.contentName ? 
      <div> <img src={'http://lorempixel.com/400/200/'+props.match.params.contentName+'/1'} /> </div> 
      : null
    }
  </div>  
)

const App = (e) => (
  <Router>
    <div className="row">
      <section className="col-sm-4">
        <Links />
      </section>
      <section className="col-sm-8">
        <Switch>
          <Route exact path="/" component={Home} />
          <Route path="/about" component={About} />
          <Route path="/content" component={Content} />
          <Route path="/form" component={Form} />
          <Route render={() => <h1> Not found casdasdas</h1> } />
        </Switch>
      </section>
    </div>
  </Router>
)

render((
  <BrowserRouter>
    <App />
  </BrowserRouter>
), document.getElementById('root'));
