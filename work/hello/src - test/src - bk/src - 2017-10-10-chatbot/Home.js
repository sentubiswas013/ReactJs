import React from 'react'
import GridCustomView from './Components/GridCustomView'
import ErrorScreen from './Components/ErrorScreen'
import axios from 'axios'
import './assests/css/App.css'

var Home = React.createClass({
  // This is callback of react life cycle and its called only once. We initialize all the parameters
  // required to default values.
  getInitialState: function () {
    return {
      bots: [],
      noOfBots: 0
    }
  },
  componentWillUpdate () {
    this.HideStyle = {
						        display: 'block'
						    }
  },

  componentWillMount () {
    this.HideStyle = {
						        display: 'none'
						    }
  },

  // This is a React lifecyle callback. Here, we are making GET requestUrl
  // to receive all the registered BOTS and update the STATE variables.
  componentDidMount: function () {
    // Is there a React-y way to avoid rebinding `this`? fat arrow?
    var th = this
    this.serverRequest =
      axios.get('https://ec2-35-156-49-41.eu-central-1.compute.amazonaws.com:8445/rcsbotsgateway/apps/bots?name=')
        .then(function (result) {
          console.log(result)
          let data = []
          { result.data.map(function (i) {
            if (i.nickName == '@jiodineout' || i.nickName == '@railenquiry' || i.nickName == '@reminders') {
              data.push(i)
            }
          }) }
          th.setState({
            bots: data,
            noOfBots: result.length
          })
        })
				.catch(function (err) {
  document.removeEventListener('keydown', th.handleKeyDown)
  th.setState({
    data: [],
    updated: true
  })
})
  },

  // This function renders the BOTS
  renderTheBots () {
    return (
      <div>
        <GridCustomView data={this.state.bots} />
      </div>
    )
  },

  // This function renders the ErrorScreen
  renderErrorScreen () {
    return (
      <div>
        <ErrorScreen>There are No Bots to show!</ErrorScreen>
      </div>
    )
  },

  // This is React lifecyle callback called to render any component.
  render () {
    return (
      <div>
        <div>
          <h3>Jio Assist</h3>
        </div>
        <div style={this.HideStyle}>
          {(this.state.noOfBots === 0) ? this.renderErrorScreen() : this.renderTheBots()}
        </div>
      </div>
    )
  }
})

export default Home
