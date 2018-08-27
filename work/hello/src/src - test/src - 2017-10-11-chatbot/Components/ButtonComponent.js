import React from 'react'
import ReactDOM from 'react-dom'
import { Button } from 'semantic-ui-react'
import '../App.css'
import axios from 'axios'
import Jconsole from '../utility/Jconsole'
import Utility from '../utility/Utility'
import Constants from '../utility/Constants'

const ButtonComponent = React.createClass({
  // This function removes the EMOJIS in texts
  removeEmojis (string) {
    if (typeof (string) !== 'undefined') {
      return (string.replace(/(?:[\u2700-\u27bf]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff]|\ufe0f?\u20e3|\u3299|\u3297|\u303d|\u3030|\u24c2|\ud83c[\udd70-\udd71]|\ud83c[\udd7e-\udd7f]|\ud83c\udd8e|\ud83c[\udd91-\udd9a]|\ud83c[\udde6-\uddff]|[\ud83c[\ude01-\ude02]|\ud83c\ude1a|\ud83c\ude2f|[\ud83c[\ude32-\ude3a]|[\ud83c[\ude50-\ude51]|\u203c|\u2049|[\u25aa-\u25ab]|\u25b6|\u25c0|[\u25fb-\u25fe]|\u00a9|\u00ae|\u2122|\u2139|\ud83c\udc04|[\u2600-\u26FF]|\u2b05|\u2b06|\u2b07|\u2b1b|\u2b1c|\u2b50|\u2b55|\u231a|\u231b|\u2328|\u23cf|[\u23e9-\u23f3]|[\u23f8-\u23fa]|\ud83c\udccf|\u2934|\u2935|[\u2190-\u21ff])/g, '')
      )
    }
  },

  // This function renders the actual Button component. This is autamatically called by react.
  render () {
    // alert(this.props.shouldShowMessage);
    Jconsole.log('Data for Button Componenet = ' + JSON.stringify(this.props.data))
    let buttons
    if (typeof this.props.data.quick_replies !== 'undefined') {
      buttons = this.props.data.quick_replies
    } else if (typeof this.props.data.buttons !== 'undefined') {
      buttons = this.props.data.buttons
    }
    return (
      <div id='btn-id'>
        <div style={this.TitleStyle}>
          <span>{this.removeEmojis(this.props.data['text'])}</span>
        </div>
        <div style={{textAlign: 'center'}}>
          {buttons.map(this.eachButton)}
        </div>
      </div>
    )
  },

// click event for the date selection is handled here
  eachButton (button) {
    if (button.content_type == 'location') {
      button.title = Constants.vars.location_title
    } else if (button.content_type == 'datepicker') {
      button.title = Constants.vars.datepicker
    }
    return (
      <Button className='quick-reply'
        id={button.payload}
        key={button.title}
        onClick={this.handleClick.bind(this, button.title, button.payload)}>
        {this.removeEmojis(button.title)}
      </Button>

    )
  },

  // Click event for the button is handled here.
  handleClick (title, payload) {
    if (title == Constants.vars.location_title && typeof payload === 'undefined') {
      Jconsole.log('Inside handle Click for location')
      Utility.getCurrentLocation(this.handleClickWithLocation)
    } else if (title == Constants.vars.datepicker && typeof payload === 'undefined') {
      Jconsole.log('Inside handle Click for date picker')
      document.getElementById('footerMsg').setAttribute('type', 'date')
      document.getElementById('footerMsg').focus()
    } else {
      var properPayLoad1 = this.preparePayload(payload)
      var enc = unescape(encodeURIComponent(title));
      var encPayload = unescape(encodeURIComponent(properPayLoad1));
      var req = '"request":{"message":{ "text": "' + enc + '","quick_reply":{"payload": "' + encPayload + '"}}}'
      this.props.onClick(title, payload, req)
    }
  },

// This function will handle the click event for the "Send Location Buttons".
  handleClickWithLocation (lat, long) {
    Jconsole.log('handleClickWithLocation')
    var req = '"request":{ "message":{ "attachments":[ { "title": "' + Constants.vars.location_title + '", "type":"location", "payload":{ "coordinates":{"lat":' + lat.toFixed(6) + ',"long":' + long.toFixed(6) + '}}}]}}'
    this.props.onClick(Constants.vars.location_title, '', req)
  },

  componentWillMount () {
    if (this.props.shouldShowMessage) {
      this.TitleStyle = {
        display: 'block',
        textAlign: 'center',
        marginTop: '5%'
      }
    } else {
      this.TitleStyle = {
        display: 'none',
        textAlign: 'center',
        marginTop: '5%'
      }
    }
  },
  componentDidMount () {
    this.props.default()
  },

  defaultProps () {
    noOfButtons: 0
    data: []
    providerDetails: ''
    url: ''
  },

  // Payload is modified according to required format.
  preparePayload (load) {
    if (load) {
      return load.replace(/"/g, '\\\"')
    } else {
      return ''
    }
  }

})

export default ButtonComponent
