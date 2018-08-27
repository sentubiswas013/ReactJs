import React from 'react';
var count = 0;
class OptionMenu extends React.Component {
	constructor(props) {
		super(props)
		this.state = {
			isOpenPopup:props.isOpenPopup
		}
		this.keyDownHandler = this.keyDownHandler.bind(this);
	}
	componentWillMount() {
		document.addEventListener("keydown", this.keyDownHandler);
	}	
	keyDownHandler(e) {	
		var li = document.querySelectorAll(".optionMenu li");
		var liTotal = li.length;		
		if(e.key === "Shift") {
			document.removeEventListener("keydown", this.keyDownHandler);
			this.setState({
				isOpenPopup:!this.state.isOpenPopup
			})
			this.props.update();
		}else if(e.key === "ArrowDown"){
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
		}		
	}  
	
  render(){
	//console.log("-" +this.state.isOpenPopup)
    return (		
      <div id="optionPopup" className={this.state.isOpenPopup?'optionPopupActive':'optionPopup'}>
			<div className="optionMenu">
				<ul>
					<li> Menu 1 </li>
					<li> Menu 2 </li>
					<li> Menu 3</li>
					<li> Menu 4</li>
				</ul>
			</div>
		</div>
    )
	
  }
}

export default OptionMenu