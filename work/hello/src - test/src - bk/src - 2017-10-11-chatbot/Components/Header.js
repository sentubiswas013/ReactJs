import React from 'react'
import '../App.css'
import Jconsole from '../utility/Jconsole';

class Header extends React.Component {
  constructor (props) {
    super(props)
    Jconsole.log(this.props)
  }
  // This is React lifecyle callback called to render any component.
  render () {
    return (
      <div className='App'>
        <div className='App-header'>
          <span >{this.props.data}</span>
        </div>
      </div>
    )
  }
}

export default Header
