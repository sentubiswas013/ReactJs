import React from 'react'
import {withRouter} from 'react-router-dom'
var nextIndex = -1, NUMBER_OF_ITEMS = 0
class Grid extends React.Component {

  constructor (props) {
    super(props)
    this.handleKeyDown = this.handleKeyDown.bind(this)
  }
  componentDidMount () {
    document.addEventListener('keydown', this.handleKeyDown)
    alert(this.props.data.length)
  }
  componentWillMount () {
    alert(this.props.data.length)
    NUMBER_OF_ITEMS = 14 - 1
  }
  componentWillUnmount () {
    document.removeEventListener('keydown', this.handleKeyDown)
  }
  initializeUI (ui, isUIInitialized) {
    ui.view = document.querySelector('#nav-page')
    ui.menuList = ui.view.querySelector('#nav-page-list')
    isUIInitialized = true
  }

  getDOMElements () {
    var mapping = {
      navPage: '#nav-page-list'
    }
    return Object.keys(mapping).reduce((a, x) => {
      a[x] = document.querySelector(mapping[x])
      return a
    }, {})
  }

  handleKeyDown (e) {
    var ui = {},
      domElems = this.getDOMElements(),
      isUIInitialized = false

    if (!isUIInitialized) {
      this.initializeUI(ui, isUIInitialized)
      console.log('redering navingation')
    }

    if (nextIndex === -1) {
      nextIndex = 0
      var elem = domElems.navPage.children[nextIndex]
      elem.className = 'selected'
      console.log(nextIndex)
      return this
    }

    if (e.key === 'ArrowDown') {
      if (nextIndex === this.NUMBER_OF_ITEMS || (nextIndex === this.NUMBER_OF_ITEMS - 1)) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = 0
        let elem = domElems.navPage.children[nextIndex]
        console.log(nextIndex)
        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      } else {
        console.log(nextIndex)
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = nextIndex + 2
        let elem = domElems.navPage.children[nextIndex]
        console.log(nextIndex)
        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      }
    } else if (e.key === 'ArrowUp') {
        // e.preventDefault();
      console.log(nextIndex)
      if (nextIndex === 0) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = this.NUMBER_OF_ITEMS - 2
        let elem = domElems.navPage.children[nextIndex]
        elem.className = 'selected'
      } else if (nextIndex === 1) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = this.NUMBER_OF_ITEMS - 1
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
        console.log(nextIndex)
        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      } else {
        domElems.navPage.children[nextIndex].className = 'unselected'
        let elem = domElems.navPage.children[++nextIndex]
        console.log(nextIndex)

        if (nextIndex <= this.NUMBER_OF_ITEMS) {
          elem.className = 'selected'
        }
      }
    } else if (e.key === 'ArrowLeft') {
      console.log(nextIndex)
      if (nextIndex === 0) {
        domElems.navPage.children[nextIndex].className = 'unselected'
        nextIndex = this.NUMBER_OF_ITEMS - 1
        let elem = domElems.navPage.children[nextIndex]
        elem.className = 'selected'
      } else {
        domElems.navPage.children[nextIndex].className = 'unselected'
        let elem = domElems.navPage.children[--nextIndex]
        elem.className = 'selected'
      }
    } else if (e.key === 'Enter') {
      let val = domElems.navPage.children[nextIndex - 1]
  		    this.context.router.history.push('/Chat/' + val.id)
    }
  }

  // This is React lifecyle callback called to render any component.
  render () {
    const bots = this.props
	 console.log(bots)
    return (
      <div>
        <section>
          <div id='nav-page' className='help-view'>
            <ul id='nav-page-list' tabindex='1'>
              {bots.data.map(function (bot) {
                return (
                  <li id={bot.displayName} className={bot.displayName}>
                    <div id={bot.displayName} className='bot'>
                      <div class='bot'>
                        <div className='botimg'><img src={bot.icon} /></div>
                        <div className='botname'>{bot.displayName}</div>
                      </div>
                    </div>
                  </li>
                               		  )
              })}
            </ul>
          </div>
        </section>
      </div>
    )
  }
}
Grid.contextTypes = {
  router: React.PropTypes.object
}
export default withRouter(Grid)
