import React from 'react'
import { render } from 'react-dom'


class Count extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			count: 0
		}
		this.UpdateNumber = this.UpdateNumber.bind(this);
	}
	UpdateNumber(){
		var curVal = this.state.count
		this.setState({
			count:curVal+1
		})
	}
	shouldComponentUpdate(){
		if(this.state.count > 5){
			return false;
		}
	}
	shouldComponentUpdate
	render(){
		return(
			<div> 
				<div> Scrore is: {this.state.count}</div>
				<button onClick={this.UpdateNumber}> Count Here </button>
			</div>
		)
	}	
}

render( <Count/>, document.getElementById('root'));
