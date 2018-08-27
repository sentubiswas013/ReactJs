import React, { Component } from 'react'
import '../App.css'
import icon from '../assests/icons/Home.png'
import icon1 from '../assests/icons/menu.png'

class Footer extends Component {
  // This is React lifecyle callback called to render any component.
  render () {
    return (
      <footer className='App-footer'>
        <div className='textmsg'>
          <div className='lsk'><img onClick={this.handleClick.bind(this)} src={icon} /></div>
          <input id='footerMsg' placeholder='Press # to type msg or * to speak' className='input_feild' />
          <div id='rsk' className='rsk'><img id='img' onClick={this.handleClick.bind(this)} src={icon1} /></div>
        </div>
      </footer>
    )
  }

  // This function handles the click event.
  handleClick (e) {
    if (e.target.parentElement.className == 'lsk') {
      document.getElementById('footerMsg').blur()
      document.getElementById('footerMsg').value = ''
      this.props.onClick(undefined, undefined, req)
    } else if (e.target.parentElement.className == 'rsk') {
      document.getElementById('rsk').style.border = ''
      /* click event for the date selection is handled here */
      document.getElementById('footerMsg').removeAttribute('type')
      document.getElementById('footerMsg').blur()
      var txt = document.getElementById('footerMsg').value
      document.getElementById('footerMsg').value = ''
      if (txt.length > 1) {
        var req = '"request":{"message":{ "text": "' + txt + '"}}'
        this.props.onClick(undefined, undefined, req)
      }
    }
    document.getElementById('footerMsg').removeAttribute('type')
  }

}

export default Footer
