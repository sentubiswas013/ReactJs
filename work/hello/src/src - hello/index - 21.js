/*
import React from 'react';
import ReactDom from 'react-dom';
import './index.css';

class App extends React.Component{
	render(){
		return(
			<section>
				{ this.props.children }
			</section>
		)
	}
}


ReactDom.render(
	<App> 
		<h1> Hello World </h1>
		<h1> Hello World </h1>
		<h1> Hello World </h1>
		<h1> Hello World </h1>
	</App>, document.getElementById("root"));

*/

/*import React from 'react';
import ReactDom from 'react-dom';
import './index.css';

class App extends React.Component{
	render(){
		return(
			<section>
				{ React.Children.only(this.props.children)}
			</section>
		)
	}
}

ReactDom.render(
	<App> 
		<div>
			<h1> Hello World </h1>
			<h1> Hello World </h1>
			<h1> Hello World </h1>
			<h1> Hello World </h1>
		</div>
</App>, document.getElementById("root"));
*/


import React from 'react';
import ReactDom from 'react-dom';
import './index.css';
import $ from 'jquery';

class Fetch extends React.Component{
	constructor(){
		super();
		this.state = {
			content: []
		}
	}
	componentDidMount(){
		$.ajax({
			url : this.props.url,
			success: (data) =>{
				this.setState({
					content:data
				})
			},
			error: (err) => {
				console.log("err", err);
			}
		})
	}
	render(){
		return(
			<section>
				{ this.props.children(this.state.content) }
			</section>
		)
	}
}

class App extends React.Component{
	render(){
		return(
			<section>
				<Fetch url="https://jsonplaceholder.typicode.com/posts"> 
					{(data) => {
						return data.map((value, index) => {
							return <li key={index}> {value.title} </li>
						})
					}}
				</Fetch>
				<h2> users </h2>
				<Fetch url="https://jsonplaceholder.typicode.com/users"> 
					{(data) => {
						return data.map((value, index) => {
							return <li key={index}> {value.name} </li>
						})
					}}
				</Fetch>
			</section>
		)
	}
}


ReactDom.render(<App/>, document.getElementById("root"));

