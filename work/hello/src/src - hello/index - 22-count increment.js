/*import React from 'react';
import ReactDom from 'react-dom';
import './index.css';


class Counter extends React.Component{
	constructor(){
		super();
		this.incrementCounter = this.incrementCounter.bind(this);
		this.state = {
			count:0
		}		
	}
	incrementCounter(){
		this.setState({
			count:this.state.count + 1
		}, () => {
			console.log(this.state.count);
		})		
	}

	render(){
		const { count } = this.state;
		return(
			<section>
					{count}
				<button onClick={this.incrementCounter}> Add Number </button>
			</section>
		)
	}
}


ReactDom.render(<Counter/>, document.getElementById("root"));

*/

import React from 'react';
import ReactDom from 'react-dom';
import './index.css';

class Counter extends React.Component{
	constructor(){
		super();
		this.incrementCounter = this.incrementCounter.bind(this);
		this.state = {
			count:0
		}		
	}
	incrementCounter(){
		this.setState((prevState) => {
			return {
				count:prevState.count + 1
			}
		})		
	}

	render(){
		const { count } = this.state;
		return(
			<section>
					{count}
				<button onClick={this.incrementCounter}> Add Number </button>
			</section>
		)
	}
}


ReactDom.render(<Counter/>, document.getElementById("root"));

