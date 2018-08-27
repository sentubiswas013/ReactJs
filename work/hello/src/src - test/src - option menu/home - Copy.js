import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';

var count = 0;
class Home extends React.Component{
	constructor(props) {
		super(props);
		this.keyDownHandler = this.keyDownHandler.bind(this);
	}
	componentWillMount(){
		document.addEventListener("keydown", this.keyDownHandler);
	}	
	keyDownHandler(e){	
		var li = document.querySelectorAll(".optionMenu li");
		var liTotal = li.length;		
		if(e.key === "Shift"){
			document.removeEventListener("keydown", this.keyDownHandler);
			this.setState({
				isOpenPopup:!this.state.isOpenPopup
			})
			this.props.update();
		}else if(e.key === "ArrowDown"){
			//console.log(count);
			if(count === 0){
				li[count].className = "focus";
				count ++; 
			}else if(count > 0){
				li[count-1].className = "";
				li[count].className = "focus";
				count ++;
				if(count> liTotal-1){
					count = liTotal-1;
				}
			}				
		}else if(e.key === "ArrowUp"){
			if(count > 0){
				li[count-1].className = "focus";
				li[count].className = "";
				count --;
				if(count < -1){
					count = 0;
				}
			}
		}else if(e.key === "Enter"){
			//e.stopImmediatePropagation();
			console.log("Option Menu");
			console.log(count);
			if(count == 0){
				console.log("0");
				//this.context.router.history.push("/About");
			}else if(count == 1){
				console.log("1");
				//this.context.router.history.push("/Contact");
			}
		}		
	}
	render(){
		return(
		<div>
			<div> Page Home </div>
			<div className="optionMenu" id="optionMenu">
				<ul>
					<li>About </li>
					<li>Contact </li>					
				</ul>
			</div>
		</div>
		)
	}	
}

Home.contextTypes = {
  router: React.PropTypes.object
};
export default withRouter(Home)
