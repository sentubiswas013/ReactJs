import React from 'react'
import {withRouter} from 'react-router-dom'
import ScrollTo from 'scroll-to-element'
import GridCustomItem from './GridCustomItem'
var nextIndex = -1, NUMBER_OF_ITEMS = 0

var GridCustomView = React.createClass({
  componentWillMount () {
    nextIndex = -1
    this.NUMBER_OF_ITEMS = this.props.data.length - 1
    document.addEventListener('keydown', this.handleKeyDown)
  },

  componentWillUnmount () {
    document.removeEventListener('keydown', this.handleKeyDown)
  },

  initializeUI (ui, isUIInitialized) {
    ui.view = document.querySelector('#nav-page')
    ui.menuList = ui.view.querySelector('#nav-page-list')
    isUIInitialized = true
  },

  getDOMElements () {
    var mapping = {
      navPage: '#nav-page-list'
    }
    return Object.keys(mapping).reduce((a, x) => {
      a[x] = document.querySelector(mapping[x])
      return a
    }, {})
  },

  handleKeyDown (e) {
    var ui = {},
      domElems = this.getDOMElements(),
      isUIInitialized = false

    if (!isUIInitialized) {
      this.initializeUI(ui, isUIInitialized)
    }

    if (nextIndex === -1) {
      nextIndex = 0
      var elem = domElems.navPage.children[nextIndex]
      elem.className = 'selected'
      return this
    }

    if (e.key === 'ArrowDown') {
      if (nextIndex === this.NUMBER_OF_ITEMS || (nextIndex === this.NUMBER_OF_ITEMS - 1)) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex === this.NUMBER_OF_ITEMS - 1 ? nextIndex = 0 : nextIndex = 1
        let elem = domElems.navPage.children[nextIndex]
        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      } else {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = nextIndex + 2
        let elem = domElems.navPage.children[nextIndex]
        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      }
    } else if (e.key === 'ArrowUp') {
      if (nextIndex === 0) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = this.NUMBER_OF_ITEMS - 1
        let elem = domElems.navPage.children[nextIndex]
        elem.className = 'selected'
      } else if (nextIndex === 1) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = this.NUMBER_OF_ITEMS
        let elem = domElems.navPage.children[nextIndex]
        elem.className = 'selected'
      } else {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = nextIndex - 2
        let elem = domElems.navPage.children[nextIndex]
        elem.className = 'selected'
      }
    } else if (e.key === 'ArrowRight') {
      if (nextIndex >= this.NUMBER_OF_ITEMS) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = 0
        let elem = domElems.navPage.children[nextIndex]
        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      } else {
        domElems.navPage.children[nextIndex].className = 'unselected'
        let elem = domElems.navPage.children[++nextIndex]
        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      }
    } else if (e.key === 'ArrowLeft') {
      if (nextIndex === 0) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = this.NUMBER_OF_ITEMS
        let elem = domElems.navPage.children[nextIndex]
        elem.className = 'selected'
      } else {
        domElems.navPage.children[nextIndex].className = 'unselected'
        let elem = domElems.navPage.children[--nextIndex]
        elem.className = 'selected'
      }
    } else if (e.key === 'Enter') {
      let val = domElems.navPage.children[nextIndex]
      let selectedbot = [val.id, val.firstElementChild.id]
      this.context.router.history.push('/Chat/' + selectedbot)
    }
    ScrollTo('.selected', {offset: 0, align: 'middle', duration: 3000})
  },

  eachBot (bot) {
    return (
      <GridCustomItem key={bot.id}
        id={bot.nickName}
        displayName={bot.displayName.slice(0, 12)}
        icon={bot.icon} />
    )
  },
  render () {
    return (
      <div className='bots'
        onKeyPress={this.handleKeyDown}
            >
        <section>
          <div id='nav-page' className='help-view'>
            <ul id='nav-page-list' tabIndex='1'>
              {this.props.data.map(this.eachBot)}
            </ul>
          </div>
        </section>
      </div>)
  }
})
GridCustomView.contextTypes = {
  router: React.PropTypes.object
}
export default withRouter(GridCustomView)
