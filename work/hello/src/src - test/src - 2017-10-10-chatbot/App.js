import React from 'react'
import {Route, Switch, HashRouter } from 'react-router-dom'
import Home from './Home'
import Chat from './Components/Chat'

class Main extends React.Component {

  render () {
    return (
      <div>
        <Switch>
          <Route exact path='/Chat/:id' component={Chat} />
          <Route exact path='/' component={Home} />
        </Switch>
      </div>
    )
  }
}

class App extends React.Component {

  render () {
    return (
      <HashRouter history={History}>
        <div className='App'>
          <Route component={Main} />
        </div>
      </HashRouter>
    )
  }
}

export default App
