import React from 'react'
import ReactDOM from 'react-dom'
import { Button } from 'semantic-ui-react'
import '../App.css'
import axios from 'axios'

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
    return (
      <div id='btn-id'>
        <div style={this.TitleStyle}>
          <span>{this.removeEmojis(this.props.data['text'])}</span>
        </div>
        <div style={{textAlign: 'center'}}>
          {this.props.data.quick_replies.map(this.eachButton)}
        </div>
      </div>

    )
  },

  eachButton (button) {
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
    var properPayLoad1 = this.preparePayload(payload)
    var req = '"request":{"message":{ "text": "' + title + '","quick_reply":{"payload": "' + properPayLoad1 + '"}}}'
    this.props.onClick(title, payload, req)
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

  defaultProps () {
    noOfButtons: 0
    data: []
    providerDetails: ''
    url: ''
  },

  // Payload is modified according to required format.
  preparePayload (load) {
    return load.replace(/"/g, '\\\"')
  }

})

export default ButtonComponent
