import React, { Component } from 'react';
import {Route,Switch,HashRouter } from 'react-router-dom';
import {withRouter} from 'react-router-dom';

var count = 0;
class OptionMenu extends React.Component{
	constructor(props){
		super(props)
		this.state = {
			isOpenPopup:props.isOpenPopup
		}
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
			if(count === 0){
				li[count].className = "active";
				count ++;
			}else if(count > 0){
				li[count-1].className = "";
				li[count].className = "active";
				count ++;
				if(count> liTotal-1){
					count = liTotal-1;
				}
			}				
		}else if(e.key === "ArrowUp"){
			if(count > 0){
				li[count-1].className = "active";
				li[count].className = "";
				count --;
				if(count < -1){
					count = 0;
				}
			}
		}else if(e.key === "Enter"){
			//e.stopImmediatePropagation();
			console.log("Option Menu");
			var selectedLi = document.querySelector(".active");
			var selectedLiText = document.querySelector(".active").innerHTML;
			console.log(selectedLiText);
			if(selectedLiText == 'About Us'){
				this.context.router.history.push("About");
			}else if(selectedLiText == 'Services'){
				this.context.router.history.push("/Services");
			}else if(selectedLiText == 'Our Works'){
				this.context.router.history.push("/Works");
			}else if(selectedLiText == 'Contact Us'){
				this.context.router.history.push("/Contact");
			}			
		}		
	}  
	
  render(){
	//console.log("-" +this.state.isOpenPopup)
    return (		
      <div id="optionPopup" className={this.state.isOpenPopup?'optionPopupActive':'optionPopup'}>
			<div className="optionMenu">
				<ul>
					<li>About Us</li>
					<li>Services</li>
					<li>Our Works</li>
					<li>Contact Us</li>
				</ul>
			</div>
		</div>
    )
	
  }
}

OptionMenu.contextTypes = {
  router: React.PropTypes.object
};
export default withRouter(OptionMenu)