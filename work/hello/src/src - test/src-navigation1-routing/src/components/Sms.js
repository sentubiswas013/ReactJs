import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';

var elemList,nextIndex=0,selectedElem='',NUMBER_OF_ITEMS;
class Sms extends Component {
  constructor(props) {
    super(props);
	this.handleKeyEvents = this.handleKeyEvents.bind(this);
  }
  componentDidMount() {
	  document.addEventListener("keydown", this.handleKeyEvents);
  }
  handleKeyEvents(e) {
	  //console.log(e.key);
	  switch (e.key) {
		  case 'Backspace':
				this.context.router.history.push("/");
		  break;
		  
		  default:
	  }
  }
  render() {
    return (
      <div className="sms">
		<div id="send-msg-input" className="send-msg-input">
			<div className="send_to">
				 <span id="to-sms">TO:</span>
				 <span id="phone-number">Num</span>
			</div>
			<textarea className="sms-text-area" id="sms-text" placeholder="" name="Text1" cols="30" rows="3" onfocus="this.value = this.value;">
			</textarea>
		</div>
	  </div>
    );
  }
}
//debugger
Sms.contextTypes = {
  router: React.PropTypes.object
  
};
console.log(Sms.contextTypes)
export default withRouter(Sms)
