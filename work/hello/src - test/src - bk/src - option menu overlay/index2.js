import React from 'react'
import {render} from 'react-dom'
//import OptionMenu from './OptionMenu';
import './App.css'


class App extends React.Component{
	constructor(){
		super()
		this.state = {
			tasks:[
                {name: "Buy Milk", completed:false},
                {name: "Buy Cheese", completed:false},
                {name: "Buy Bread", completed:false},
                {name: "Buy Sugar", completed:false}
            ],
			isOpen:false
		}		
		this.fnPoupOptions = this.fnPoupOptions.bind(this);
	}
	clickHandler(e){
		console.log("clickHandler "+e);
	}	
	componentWillMount(){
		 document.addEventListener("keydown", this.keyDownHandler);
	}  
	keyDownHandler(e){
		if(e.key === "Shift"){
			//console.log("Hello");
			//fnPoupOptions();
		//console.log(this.state.isOpen)
		this.setState({
            isOpen:!this.state.isOpen
        })
		console.log(this.state.isOpen)
					
		}		
	}
	fnPoupOptions(){
		console.log("---");
		/*console.log(this.state.isOpen)
		this.setState({
            isOpen:!this.state.isOpen
        })
		console.log(this.state.isOpen)
		*/
	}
	
	render(){
		const taskList = this.state.tasks;
		return(
		<div>
			<form className="form">
				<input type="text" value="Type"/>
				<button type="submit"> Add </button>
			</form>
			<ul>			
			{
				taskList.map((task, index)=>{					
					return(
						<TodoItem
							index = {index}
							details = {task}
							key = {index}
							
							clickHandler = {this.clickHandler}
						/>
					)
				})
			}
			</ul>
			<div>
				  dasd
			</div>
		</div>
		)
	}	
}

class TodoItem extends React.Component{
	render(){
		return(
			<li onClick={()=>{
				this.props.clickHandler(this.props.index)
			}}> 
				{ this.props.details.name }
			</li>
		)
	}
}


render(<App/>, document.getElementById("root"));
