import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';

class HeadderMaximize extends React.Component{
	render() {
		return (
			<div id="speaker">
				<div id="loader" className="loading-container">
					<div className="loading-back"></div>
					<div className="loading"></div>
				</div>
			</div>
			/*<p class="text-primary" data-l10n-id="hello"></p>
			<p class="text-secondary" data-l10n-id="press_start_for_voice"></p>
			<div class="button-cancel">
				<img src="../images/pngs/BackToVoiceActive.png">
				<p data-l10n-id="cancel_and_retry"></p>
			</div>*/
		)
    }
}

class HeadderMinimize extends React.Component{
	render(){
		return (
			<div className="header-BackToVoice"> Back to Voice </div>
		)
    }
}

class Header extends React.Component{
    render(){
		return (
			<div className="header"> 
				<HeadderMaximize />
			</div>
		)
    }
}

//export default Header;
export default withRouter(Header);


