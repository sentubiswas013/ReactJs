import React from 'react'
import { Button, Card, Image } from 'semantic-ui-react'
import '../App.css'
import Jconsole from '../utility/Jconsole'
import Utility from '../utility/Utility'
import Constants from '../utility/Constants'

var CustomItemView = React.createClass({

  // This function Renders a Plian Card.
  renderNormal () {
    Jconsole.log('renderNormal')
    return (
      <div>
        <Card
          header={this.props.title}
          description={this.props.subtitle}
          centered />
      </div>
    )
  },

  // This function Renders a Plian Card with IMAGE.
  renderSemanticCard () {
    Jconsole.log('renderSemanticCard')
    return (
      <Card>
        <Image src={this.props.imageUrl} />
        <Card.Content>
          <Card.Header>{this.props.title}</Card.Header>
          <Card.Description>{this.props.subtitle}</Card.Description>
        </Card.Content>
      </Card>
    )
  },

  // This function HElps in rendering BUTTONS for Cards
  renderButtonsForCarousal (title, payload, type, url) {
    Jconsole.log('renderButtonsForCarousal')
    return (
      <Button id={payload} basic
        key={title}
        onClick={this.handleClick.bind(this, title, payload, type, url)}>{title}</Button>
    )
  },

  // This function handles the click event
  handleClick (title, payload, type, url) {
    if (type == Constants.vars.call_type) {
      Jconsole.log('Inside handle Click for phone_number')
      Utility.makePhoneCall(payload)
    } else if (type == Constants.vars.browser) {
      Jconsole.log('Inside handle Click for web_url')
      Utility.openWebUrl(url)
    } else {
      var properPayLoad = this.preparePayload(payload)
      var req = '"request":{"message":{ "text": "' + title + '","quick_reply":{"payload": "' + properPayLoad + '"}}}'
      this.props.onClick(title, properPayLoad, req)
    }
  },

  // This function helps in preparing payload in required format.
  preparePayload (load) {
    return load.replace(/"/g, '\\\"')
  },

  // This function Renders a Card with BUTTON and WITHOUT IMAGE.
  renderSemanticCardWithButtonWithoutImage () {
    Jconsole.log('renderSemanticCardWithButtonWithoutImage')
    var th = this
    return (
      <Card>
        <Card.Content>
          <Card.Header>{this.props.title}</Card.Header>
          <Card.Description>{this.props.subtitle}</Card.Description>
        </Card.Content>
        <Card.Content extra>
          <div className='ui basic vertical buttons'>
            {this.props.buttons.map(function (i) {
              let load = i.payload
              if (load === undefined) {
                load = ''
              }
              return (
                  th.renderButtonsForCarousal(i.title, load, i.type, i.url)
              )
            })}
          </div>
        </Card.Content>
      </Card>
    )
  },

  // This function Renders a Card WITH BUTTON and WITH IMAGE.
  renderSemanticCardWithButton () {
    Jconsole.log('renderSemanticCardWithButton')
    var th = this
    return (
      <Card>
        <Image src={this.props.imageUrl} />
        <Card.Content>
          <Card.Header>{this.props.title}</Card.Header>
          <marquee><Card.Description>{this.props.subtitle}</Card.Description></marquee>
        </Card.Content>
        <Card.Content extra>
          <div className='ui basic vertical buttons'>
            {this.props.buttons.map(function (i) {
              let load = i.payload
              if (load === undefined) {
                load = ''
              }
              return (
                  th.renderButtonsForCarousal(i.title, load, i.type, i.url)
              )
            })}
          </div>
        </Card.Content>
      </Card>
    )
  },

  // This is React lifecyle callback called to render any component.
  // Based on the props values, ONE component will be rendered.
  render () {
    return (
        (this.props.shouldShowImage) ? ((this.props.shouldShowButton) ? ((this.props.imageUrl === '') ? this.renderSemanticCardWithButtonWithoutImage() : this.renderSemanticCardWithButton()) : ((this.props.imageUrl === '') ? this.renderNormal() : this.renderSemanticCard())) : this.renderNormal()
    )
  }
})

export default CustomItemView
