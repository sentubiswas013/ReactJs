import React from 'react'
import GridCustomView from './Components/GridCustomView'
import ErrorScreen from './Components/ErrorScreen'
import axios from 'axios'
import './assests/css/App.css'
import './assests/css/ReactContexify.min.css'
import Jconsole from './utility/Jconsole'
import { Tab, Tabs, TabList, TabPanel } from 'react-tabs'
import 'react-tabs/style/react-tabs.less'
import icon1 from './assests/icons/Local Search.png'
import icon2 from './assests/icons/entertainment_b.png'
import icon3 from './assests/icons/food_b.png'
import icon4 from './assests/icons/utilities_b.png'
import icon5 from './assests/icons/news_b.png'
import icon6 from './assests/icons/train_b.png'
import icon7 from './assests/icons/sport_b.png'

var requireContext = require.context('./assests/icons', true, /^\.\/.*\.png$/)

var Home = React.createClass({
  // This is callback of react life cycle and its called only once. We initialize all the parameters
  // required to default values.
  getInitialState: function () {
    return {
      bots: {},
      noOfBots: 0,
      uniqueCategory: []
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
    Jconsole.log('Request URL = https://ec2-35-156-49-41.eu-central-1.compute.amazonaws.com:8445/rcsbotsgateway/apps/bots?name=')
    this.serverRequest =
      axios.get('https://ec2-35-156-49-41.eu-central-1.compute.amazonaws.com:8445/rcsbotsgateway/apps/bots?name=')
        .then(function (result) {
          Jconsole.log(result)
          let data = []
          let uniqueNames = []
          let organizeResults = {}

          { result.data.map(function (i) {
          // The below code will add each bot to an object with key as
          // description name and value as bot description.
            if (organizeResults[i.botCategory.description] == undefined) {
              organizeResults[i.botCategory.description] = []
              organizeResults[i.botCategory.description].push(i)
            } else {
              organizeResults[i.botCategory.description].push(i)
            }

          // This will maintain list of unique bot names.
            if (uniqueNames.indexOf(i.botCategory.description) == -1) {
              uniqueNames.push(i.botCategory.description)
            }

            if (i.nickName == '@jiodineout' || i.nickName == '@railenquiry' || i.nickName == '@reminders' || i.nickName == '@asklaila') {
              data.push(i)
            }
          })
          }

          th.setState({
            bots: organizeResults,
            noOfBots: result.length,
            uniqueCategory: uniqueNames
          })
        })
				.catch(function (err) {
  document.removeEventListener('keydown', th.handleKeyDown)
  th.setState({
    data: [],
    updated: true,
    uniqueCategory: []
  })
})
  },

  // This function renders the BOTS
  renderTheBots () {
    let tab = Object.values(this.state.uniqueCategory)

    let images = [
      <img src={icon1} />,
      <img src={icon2} />,
      <img src={icon3} />,
      <img src={icon4} />,
      <img src={icon5} />,
      <img src={icon6} />,
      <img src={icon7} />

    ]
    // console.log('tab', source)
    console.log('images', images)

    const images1 = images.map((tabValue) =>
      <Tab>{tabValue}</Tab>

  )

    // const images = source.map()
    // const tabsName = tab.map((tabValue) =>
    //  <Tab><img src={icon2} /></Tab>
   // )

    // console.log('tabsName', key)
    const eachTabsData = tab.map((tabValue) =>
      <TabPanel>
        <GridCustomView data={this.state.bots[tabValue]} />
      </TabPanel>
)

    // console.log(requireContext.keys().map(requireContext))
    return (
      <Tabs defaultFocus={true} defaultIndex={0} >
        <TabList>
          {images1}
        </TabList>
        {eachTabsData}
      </Tabs>

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
