import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';

class About extends React.Component{
	constructor(props) {
		super(props);
		this.handleKeyEvents = this.handleKeyEvents.bind(this);
	}
	componentDidMount() {
		  document.addEventListener("keydown", this.handleKeyEvents);
	}
	handleKeyEvents(e) {
	  switch (e.key) {
		  case 'Backspace':
				this.context.router.history.push("/");
		  break;
		  default:
	  }
	}
	render(){
		return(
		<div>
			<p>Page About</p>
			<button> Back To Home </button>
		</div>
		)
	}	
}
About.contextTypes = {
  router: React.PropTypes.object
};

export default withRouter(About)