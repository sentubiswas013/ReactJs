import React, { Component} from 'react'
import { ContextMenu, Item, Separator, IconFont } from 'react-contexify'
var nextIndex = -1, NUMBER_OF_ITEMS = 0

function onClick (targetNode, ref, data) {
}

// create your menu first
const ContextMenuComponent = React.createClass({

  onClick (targetNode, ref, data) {
  },

  componentWillMount () {

  },

  componentDidMount () {
    window.MutationObserver = window.MutationObserver
    || window.WebKitMutationObserver
    || window.MozMutationObserver

    var target = document.querySelector('.react-contexify-empty')

    var th = this

    var observer = new MutationObserver(function (mutation) {
        let value = (document.querySelector('.react-contexify-empty') ? false : true)
        th.props.keyEvntsCallback(value)
        if (value) {
          document.addEventListener('keydown', th.handleKeyDown)
          th.NUMBER_OF_ITEMS = document.querySelector('.react-contexify-populated').children.length - 1;
        } else {
          document.removeEventListener('keydown', th.handleKeyDown)
          th.resetNavigationIndexValues();
        }

     /** this is the callback where you
         do what you need to do.
         The argument is an array of MutationRecords where the affected attribute is
         named "attributeName". There is a few other properties in a record
         but I'll let you work it out yourself.
      **/
      }),

// configuration of the observer:
      config = {
        childList: true, characterData: true, subtree: true // this is to watch for attribute changes.
      }

    observer.observe(target, config)
  },

  resetNavigationIndexValues () {
    nextIndex = -1
    NUMBER_OF_ITEMS = 0
  },

  componentDidUpdate(prevProps, prevState) {
    window.MutationObserver = window.MutationObserver
    || window.WebKitMutationObserver
    || window.MozMutationObserver

    var target = document.querySelector('.react-contexify-empty')

    var th = this

    var observer = new MutationObserver(function (mutation) {
        let value = (document.querySelector('.react-contexify-empty') ? false : true)
        th.props.keyEvntsCallback(value)
        if (value) {
          document.addEventListener('keydown', th.handleKeyDown)
          th.NUMBER_OF_ITEMS = document.querySelector('.react-contexify-populated').children.length - 1;
        } else {
          document.removeEventListener('keydown', th.handleKeyDown)
          th.resetNavigationIndexValues();
        }

     /** this is the callback where you
         do what you need to do.
         The argument is an array of MutationRecords where the affected attribute is
         named "attributeName". There is a few other properties in a record
         but I'll let you work it out yourself.
      **/
      }),

// configuration of the observer:
      config = {
        childList: true, characterData: true, subtree: true // this is to watch for attribute changes.
      }

    observer.observe(target, config)
  },

  handleKeyDown (e) {
    if (nextIndex === -1 && (e.key === 'ArrowDown' || e.key === 'ArrowUp')) {
      nextIndex = 0
      var elem = document.querySelectorAll('.react-contexify-populated')[0].children
      if (elem === null || elem === undefined) {
        return this
      }
      elem[nextIndex].className += ' menu_selected'
      return this
    } else if (e.key === "ArrowDown") {
      if (nextIndex === this.NUMBER_OF_ITEMS) {
        var elem = document.querySelectorAll('.react-contexify-populated')[0].children
        let clsNameUnselected = elem[nextIndex].className;
        clsNameUnselected = clsNameUnselected.replace(' menu_selected', '');
        elem[nextIndex].className = clsNameUnselected;
        elem[nextIndex].className += " menu_unselected"
        nextIndex = 0;
        if(nextIndex <= this.NUMBER_OF_ITEMS) {
          let clsNameSelected = elem[nextIndex].className;
          clsNameSelected = clsNameSelected.replace(' menu_unselected', '');
          elem[nextIndex].className = clsNameSelected
          elem[nextIndex].className += " menu_selected";
        }
      } else {
        var elem = document.querySelectorAll('.react-contexify-populated')[0].children
        let clsNameUnselected = elem[nextIndex].className;
        clsNameUnselected = clsNameUnselected.replace(' menu_selected', '');
        elem[nextIndex].className = clsNameUnselected;
        elem[nextIndex].className += " menu_unselected"
        nextIndex = nextIndex + 1;
        if(nextIndex <= this.NUMBER_OF_ITEMS) {
          let clsNameSelected = elem[nextIndex].className;
          clsNameSelected = clsNameSelected.replace(' menu_unselected', '');
          elem[nextIndex].className = clsNameSelected
          elem[nextIndex].className += " menu_selected";
        }
      }
    } else if (e.key === "ArrowUp"){
      if(nextIndex === 0) {
        var elem = document.querySelectorAll('.react-contexify-populated')[0].children
        let clsNameUnselected = elem[nextIndex].className;
        clsNameUnselected = clsNameUnselected.replace(' menu_selected', '');
        elem[nextIndex].className = clsNameUnselected;
        elem[nextIndex].className += " menu_unselected"
        nextIndex = this.NUMBER_OF_ITEMS;
        let clsNameSelected = elem[nextIndex].className;
        clsNameSelected = clsNameSelected.replace(' menu_unselected', '');
        elem[nextIndex].className = clsNameSelected
        elem[nextIndex].className += " menu_selected";
      } else {
        var elem = document.querySelectorAll('.react-contexify-populated')[0].children
        let clsNameUnselected = elem[nextIndex].className;
        clsNameUnselected = clsNameUnselected.replace(' menu_selected', '');
        elem[nextIndex].className = clsNameUnselected;
        elem[nextIndex].className += " menu_unselected"
        nextIndex = nextIndex -1;
        let clsNameSelected = elem[nextIndex].className;
        clsNameSelected = clsNameSelected.replace(' menu_unselected', '');
        elem[nextIndex].className = clsNameSelected
        elem[nextIndex].className += " menu_selected";
      }
    } else if (e.key === "Enter") {
      let elem = document.querySelector(".menu_selected");
      var properPayLoad = this.preparePayload(elem.id);
      var req = '"request":{"message":{ "text": "'+elem.value+'","quick_reply":{"payload": "' + properPayLoad + '"}}}';
      elem.click();
      this.props.keyEvntsCallback(false)
      this.props.onClick(elem.title, properPayLoad, req);
    }
  },

  // Payload is modified according to required format.
  preparePayload(load) {
    return load.replace(/"/g, '\\\"');
  },

  render () {
    let menu = this.props.contextMenuData
    const menuData = menu.map((menuValue) =>
      <Item key = {menuValue.title }
            id = {menuValue.payload}
            leftIcon={<IconFont className='fa fa-plus' />}
            onClick={onClick}>
        {menuValue.title}
      </Item>
    )
    return (
      <ContextMenu id='menu_id'>
        {menuData}
      </ContextMenu>
    )
  }
})

export default ContextMenuComponent
