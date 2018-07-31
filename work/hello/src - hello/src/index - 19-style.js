import React from 'react';
import { render } from 'react-dom';
import { style } from 'typestyle';


const counter = style({
	backgroundColor: "green",
	fontSize: "1rem",
	padding: "1rem",
	$nest:{
		'&:hover':{
			backgroundColor: "yellow"
		},
		"&>div":{
			backgroundColor: "red"
		}
	}
})

const btn = style({
	backgroundColor: "red",
	fontSize: "1rem"
})

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
				<div className={counter}>
					{count}
					<div> Sentu Biswas </div>
				</div>
				<button className={btn} onClick={this.incrementCounter}> Add Number </button>
			</section>
		)
	}
}

render(<Counter/>, document.getElementById('root'));
