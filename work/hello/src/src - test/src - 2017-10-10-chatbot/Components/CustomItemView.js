import React from 'react'
import { Button, Card, Image } from 'semantic-ui-react'
import '../App.css'

var CustomItemView = React.createClass({

  // This function Renders a Plian Card.
  renderNormal () {
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
  renderButtonsForCarousal (title, payload) {
    return (
      <Button id={payload} basic
        key={title}
        onClick={this.handleClick.bind(this, title, payload)}>{title}</Button>
    )
  },

  // This function handles the click event
  handleClick (title, payload) {
    var properPayLoad = this.preparePayload(payload)
    var req = '"request":{"message":{ "text": "' + title + '","quick_reply":{"payload": "' + properPayLoad + '"}}}'
    this.props.onClick(title, properPayLoad, req)
  },

  // This function helps in preparing payload in required format.
  preparePayload (load) {
    return load.replace(/"/g, '\\\"')
  },

  // This function Renders a Card with BUTTON and WITHOUT IMAGE.
  renderSemanticCardWithButtonWithoutImage () {
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
                  th.renderButtonsForCarousal(i.title, load)
              )
            })}
          </div>
        </Card.Content>
      </Card>
    )
  },

  // This function Renders a Card WITH BUTTON and WITH IMAGE.
  renderSemanticCardWithButton () {
    var th = this
    return (
      <Card>
        <Image src={this.props.imageUrl} />
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
                  th.renderButtonsForCarousal(i.title, load)
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
        (this.props.shouldShowImage) ? ((this.props.shouldShowButton) ? ((this.props.imageUrl === '') ? this.renderSemanticCardWithButtonWithoutImage() : this.renderSemanticCardWithButton()) : this.renderSemanticCard()) : this.renderNormal()
    )
  }
})

export default CustomItemView
