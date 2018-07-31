import React from 'react'
import ButtonComponent from './ButtonComponent'
import TextContainerComponent from './TextContainerComponent'
import CarousalCardComponent from './CarousalCardComponent'
import CustomLoader from './CustomLoader'
// const quickReplies = 'quick_replies';
// const attachments = 'attachment';
var noOfButton = 0

const Container = React.createClass({

  // This function Renders the loading Component
  renderLoading () {
    return (
      <CustomLoader />
    )
  },

  // This function renders Quick Reply Component
  renderQuickReplyComponent () {
    let hasMessage = false
    if (this.props.message.length !== 0) {
      hasMessage = true
    }
    return (
      <div>
        <ButtonComponent data={this.props.data}
          isImageRequired={this.props.image}
          noOfButtons={noOfButton}
          shouldShowMessage={hasMessage}
          providerDetails={this.props.prov}
          url={this.props.requestUrl}
          onClick={this.handleClick} />
      </div>
    )
  },

  // This function handles the click event.
  handleClick (title, payload, req) {
    this.props.onClick(title, payload, req)
  },

  // This function handles the RENDERING of TextVIew component.
  renderTextContainerComponent () {
    return (
      <div>
        <TextContainerComponent message={this.props.message} />
      </div>
    )
  },

  // This funciton is PROPS callback.
  updateNoOfButtoninCarousal (slideIndex, tltBtns, indexToBeSelected) {
    this.props.addContact(slideIndex, tltBtns, indexToBeSelected)
  },

  // This function renders CarousalCardComponent
  renderCarosualComponent () {
    let hasMessage = false
    if (this.props.message.length !== 0) {
      hasMessage = true
    }
    return (
      <CarousalCardComponent data={this.props.data}
        message={this.props.message}
        isImageRequired={this.props.image}
        shouldShowMessage={hasMessage}
        addContact={this.updateNoOfButtoninCarousal}
        onClick={this.handleClick}
                               />
    )
  },

  // This is React lifecyle callback called to render any component.
  // Based on the props values, ONE component will be rendered.
  render () {
    return (
      (this.props.updated) ? ((this.props.isQuickReply ? this.renderQuickReplyComponent() : ((this.props.isTextView ? this.renderTextContainerComponent() : this.renderCarosualComponent())))) : this.renderLoading()
    )
  }
})

export default Container
