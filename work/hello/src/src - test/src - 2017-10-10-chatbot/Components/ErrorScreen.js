import React from 'react'

var ErrorScreen = React.createClass({
  // This is React lifecyle callback called to render any component.
  render () {
    return (< div className='errScreen' > {
            this.props.children
        } < /div>)
  }
})

export default ErrorScreen
