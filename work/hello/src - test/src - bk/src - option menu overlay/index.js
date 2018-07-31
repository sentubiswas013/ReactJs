import React from 'react'
import {render} from 'react-dom'
import Footer from './Footer';
import OptionMenu from './OptionMenu';
import './App.css'
var count = 0,th;
class App extends React.Component{
	constructor(){
		super()
		this.state = {
			isOpen:false
		}
		this.keyDownHandlerIndex = this.keyDownHandlerIndex.bind(this);
	}
	componentWillMount() {
		th=this;
		  document.addEventListener("keydown", this.keyDownHandlerIndex);
	}
	updateProps(){
		th.setState({
			isOpen:false
		})
		document.addEventListener("keydown", th.keyDownHandlerIndex);
		document.getElementById("optionMenuHome").style.cssText = "position:static; top:0; right:0; bottom:0; left:0";
	}
	keyDownHandlerIndex(e) {
	    var li = document.querySelectorAll(".optionMenuHome li");
		var liTotal = li.length;
		if(e.key === "Shift"){			
			document.removeEventListener("keydown", this.keyDownHandlerIndex);
			console.log("Index");
			this.setState({
				isOpen:!this.state.isOpen
			})
			document.getElementById("optionMenuHome").style.cssText = "position:fixed; top:0; right:0; bottom:0; left:0";
		}if(e.key === "ArrowDown"){
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
			console.log("Index");
		}		
	}	
	render(){
		//console.log(this.state.isOpen);
		return(
		<div className="container">
			<div className="footer-title"> JioAsist </div>
			<div className="optionMenuHome" id="optionMenuHome">
				<ul>
					<li> Menu 1 </li>
					<li> Menu 2 </li>
					<li> Menu 3 </li>
					<li> Menu 4 </li>
					<li> Menu 5 </li>
					<li> Menu 7 </li>
					<li> Menu 8 </li>
					<li> Menu 9 </li>
					<li> Menu 10 </li>
					<li> Menu 11 </li>
					<li> Menu 12 </li>
					<li> Menu 13 </li>
					<li> Menu 14 </li>
					<li> Menu 15 </li>
					<li> Menu 16 </li>
					<li> Menu 17 </li>
				</ul>
			</div>
			<div> 
				{
					this.state.isOpen?
					<OptionMenu isOpenPopup={this.state.isOpen} update={this.updateProps} />:
					<div/>	
				}
			</div>	
			<Footer/>	
		</div>
		)
	}	
}

render(<App/>, document.getElementById("root"));
