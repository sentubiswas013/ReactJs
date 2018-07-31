import React from 'react';
import { render } from 'react-dom';

class App extends React.Component{
	constructor(){
		super();
		this.state = {
			name: '',
			age:'',
			list:[]
		}
		this.submitForm = this.submitForm.bind(this);
	}	
	submitForm(){
		console.log("Hello");
	}
	render(){
		return(
			<div> 
				<form onsubmit={this.submitForm}>
					<div> <input type="text"  /> </div>
					<div> <input type="text" /> </div>
					<button> Submit </button>
				</form>
			</div>
		)
	}
}


render(
	<App />, document.getElementById('root'));
