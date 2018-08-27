import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';

class SaveContact extends Component {
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
      <div className="saveContact">
	  <div id="view-saveContact">
			<div id="add-contact">
			<h3>New Contact</h3>
			<div id="firstname">
				<div id="first">First Name</div>
			<textarea id="LN" rows="2" cols="30"></textarea>
			</div>

			<div id="lastname">
				<div id="last">Last Name</div>
			<textarea id="LN" rows="2" cols="30"></textarea>
			</div>

			<div id="phonenum">
				<div id="phone">Mobile</div>
			<textarea id="MN" rows="2" cols="30"></textarea>
			</div>
			</div>
		</div>
      </div>
    );
  }
}
//debugger
SaveContact.contextTypes = {
  router: React.PropTypes.object
};

export default withRouter(SaveContact)
