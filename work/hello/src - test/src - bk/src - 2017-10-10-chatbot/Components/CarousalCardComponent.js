import React from 'react'
import ReactDOM from 'react-dom'
import Carousel from 'nuka-carousel'
import CustomItemView from './CustomItemView'
window.React = React
var noOfButton = -1, hasButton = false

const CarousalCardComponent = React.createClass({
  mixins: [Carousel.ControllerMixin],

  getInitialState () { return { slideIndex: 0 } },

  // This function removes the EMOJIS in texts
  removeEmojis (string) {
    if (typeof (string) !== 'undefined') {
      return (string.replace(/(?:[\u2700-\u27bf]|(?:\ud83c[\udde6-\uddff]){2}|[\ud800-\udbff][\udc00-\udfff]|\ufe0f?\u20e3|\u3299|\u3297|\u303d|\u3030|\u24c2|\ud83c[\udd70-\udd71]|\ud83c[\udd7e-\udd7f]|\ud83c\udd8e|\ud83c[\udd91-\udd9a]|\ud83c[\udde6-\uddff]|[\ud83c[\ude01-\ude02]|\ud83c\ude1a|\ud83c\ude2f|[\ud83c[\ude32-\ude3a]|[\ud83c[\ude50-\ude51]|\u203c|\u2049|[\u25aa-\u25ab]|\u25b6|\u25c0|[\u25fb-\u25fe]|\u00a9|\u00ae|\u2122|\u2139|\ud83c\udc04|[\u2600-\u26FF]|\u2b05|\u2b06|\u2b07|\u2b1b|\u2b1c|\u2b50|\u2b55|\u231a|\u231b|\u2328|\u23cf|[\u23e9-\u23f3]|[\u23f8-\u23fa]|\ud83c\udccf|\u2934|\u2935|[\u2190-\u21ff])/g, '')
      )
    }
  },

  // Click event for the button in CarousalCardComponent is handled here.
  handleClick (title, payload, req) {
    this.props.onClick(title, payload, req)
  },

  componentWillMount () {
    if (this.props.shouldShowMessage) {
      this.TitleStyle = {
        display: 'block',
        textAlign: 'center',
        marginTop: '20px',
        fontSize: '12px',
        backgroundColor: 'white'
      }
    } else {
      this.TitleStyle = {
        display: 'none',
        textAlign: 'center',
        marginTop: '20px',
        fontSize: '12px',
        backgroundColor: 'white'
      }
    }
  },

  componentDidUpdate () {
    if (hasButton && this.props.isImageRequired) {
      if (this.props.data[this.state.slideIndex].buttons == undefined) {
        this.props.addContact(this.state.slideIndex, 0, -1)
      } else {
        let indexToBeSelected = this.calculateNextindex(this.state.slideIndex)
        this.props.addContact(this.state.slideIndex, this.props.data[this.state.slideIndex].buttons.length, indexToBeSelected)
      }
    }
  },

  // This function calculates the index of the button to be selected based on Slide Index.
  calculateNextindex (slideInx) {
    let nextIndex = 0
    if (slideInx === 0) {
      nextIndex = 0
    }
    for (let count = 0; count < slideInx; count++) {
      if (this.props.data[count].buttons != undefined) {
        nextIndex += this.props.data[count].buttons.length
      }
    }
    return nextIndex
  },

  // This function renders the actual Carousel component. This is autamatically called by react.
  render () {
    let th = this
    return (
      <div style={{width: '220px', margin: 'auto', background: '#ebebe0'}}>
        <Carousel
          ref='carousel'
          data={this.setCarouselData.bind(this, 'carousel')}
          slideIndex={this.state.slideIndex}
          afterSlide={newSlideIndex => this.setState({ slideIndex: newSlideIndex })}>

          {this.props.data.map(function (i) {
            let sub = th.removeEmojis(i.subtitle.slice(0, 115))
            hasButton = false
            if (i.buttons !== undefined) {
              hasButton = true
              noOfButton = i.buttons.length
            }
            return (
              <CustomItemView
                key={i.title}
                imageUrl={i.image_url}
                title={i.title}
                subtitle={sub}
                shouldShowImage={th.props.isImageRequired}
                shouldShowButton={hasButton}
                onClick={th.handleClick}
                buttons={i.buttons} />
            )
          })}
        </Carousel>
      </div>
    )
  }

})

export default CarousalCardComponent
