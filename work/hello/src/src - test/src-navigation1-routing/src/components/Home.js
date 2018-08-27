import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';

var elemList,nextIndex=0,selectedElem='',NUMBER_OF_ITEMS;
class Home extends Component {
  constructor(props) {
    super(props);
	this.handleKeyEvents = this.handleKeyEvents.bind(this);
  }
  componentDidMount() {
	  document.addEventListener("keydown", this.handleKeyEvents);
	  nextIndex=0;
  }
  handleKeyEvents(e) {
    //console.log(e.key);
	elemList=document.querySelectorAll('.nav-page-list');
	NUMBER_OF_ITEMS=elemList.length;
	//selectedElem=document.querySelectorAll('.focus');;
	//console.log(elemList);
	switch (e.key) {
        case 'ArrowUp':
          this.moveUp(e);
          break;
        case 'ArrowDown':
          this.moveDown(e);
          break;
		case 'Enter':
		  var elementSelect=document.querySelector('.focus');
		  if(elementSelect.innerHTML =="1. Save Contact")
			  this.context.router.history.push("/saveContact");
		  else 
			 this.context.router.history.push("/sms");
		 e.stopImmediatePropagation();
          break;
        case '1':
            this.context.router.history.push("/saveContact");
		break;
		case '2':
			this.context.router.history.push("/sms");
            
		break;
		default:
	}
		
  }
  moveUp(e){
	  var elem;
		if(nextIndex === 0){
			selectedElem=elemList[nextIndex];
			elemList[nextIndex].classList.add('focus');
			nextIndex = 1;
		}else{
			//console.log('element' +' ' +nextIndex);
			if(selectedElem)
				selectedElem.classList.remove('focus');
			elem = elemList[--nextIndex];
			selectedElem=elem;
			selectedElem.classList.add('focus');
		}
  }
  moveDown(e){
	  var elem;
		//console.log(nextIndex);
		if(nextIndex >= NUMBER_OF_ITEMS){
			
		}else if(nextIndex===0){
			elem = elemList[nextIndex++];
			selectedElem=elem;
			selectedElem.classList.add('focus');
		}
		else{
			
			if(selectedElem.classList.contains('focus'))
				selectedElem.classList.remove('focus');
			
			nextIndex>=NUMBER_OF_ITEMS-1?NUMBER_OF_ITEMS-1:nextIndex++;
			elem = elemList[nextIndex];
			selectedElem=elem;
			selectedElem.classList.add('focus');
		} 
  }
  render() {
    return (
      <div className="Home">
	  <div id="view-home">
	  <div id="header">
	  <h3>Voice Assistant</h3>
	  </div>
       <div id="nav-page" className="list help-view">
			<section>
				<ul id="nav-page-list" tabindex="1">
					<li className="nav-page-list">1. Save Contact</li>
					<li className="nav-page-list">2. Send SMS</li>
				</ul>
			</section>
		</div>
		</div>
      </div>
    );
  }
}

Home.contextTypes = {
  router: React.PropTypes.object
};

export default withRouter(Home)
