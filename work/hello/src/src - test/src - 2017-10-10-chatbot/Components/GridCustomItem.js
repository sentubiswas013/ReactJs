import React from 'react'

var GridCustomItem = React.createClass({

  // This is React lifecyle callback called to render any component.
  render () {
    return (
      <li id={this.props.id}>
        <div id={this.props.displayName} className='bot'>
          <div className='bot'>
            <div className='botimg'>
              <img src={this.props.icon} />
            </div>
            <div className='botname'>
              {this.props.displayName}
            </div>
          </div>
        </div>
      </li>
    )
  }
})

export default GridCustomItem
