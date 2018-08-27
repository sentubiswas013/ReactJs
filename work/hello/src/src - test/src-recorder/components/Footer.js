import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';


class Footer extends React.Component{
    render(){
		return (
			<div className="footer1"> 
				<span className="left">&nbsp;</span>
				<span className="middle">&nbsp;</span>
				<span className="right">Skip</span>
			</div>
		)
    }
}

export default Footer;



