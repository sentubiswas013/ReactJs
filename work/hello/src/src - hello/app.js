/*import React, {Component} from 'react';


export default class App extends Component{
	handleSubmit =() =>(
			console.log(this._name.value)
		)

	render(){
		return(
			<div><in
			put type="text" ref={(input) => {
				return this._name =input
			}} /></div>

			<div>
				<input type="text" ref={(input) => this._name =input } />
				<button onClick={this.handleSubmit}>Submit</button>
			</div>
		)
	}
}

*/


import React, {Component} from 'react';
export default class App extends Component{
	constructor(){
		super();
		this.state = {
			name:""
		}
	}
	onInputChange =(event) => {
		this.setState ({
			name:event.target.value
		})
	}
	handleSubmit  =() => {
			console.log(this._name.value)
			console.log("Hello")
	}
	render(){
		return(
			<div>
				<input type="text" value={this.state.name} onChange={this.onInputChange} />
				<button onClick={this.handleSubmit}> Submit </button>
			</div>
		)
	}
}

