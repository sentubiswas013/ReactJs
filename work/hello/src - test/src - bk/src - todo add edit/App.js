import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter as Router, Route, Link, NavLink, Prompt, Switch  } from 'react-router-dom';
import './App.css'

class AddItem extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			firstName:'',
			tasks:[]
		}
		this.change = this.change.bind(this);
		this.onSubmitForm = this.onSubmitForm.bind(this);
		this.deleteItem = this.deleteItem.bind(this);
	}
	change(e){
		this.setState({
			[e.target.name]:e.target.value
		})
	}
	deleteItem(){
		console.log("deleteItem");
	}
	onSubmitForm(e){
		e.preventDefault();
		let cuRfirstName = this.state.firstName;
		let tasks = this.state.tasks;
		tasks.push({
			firstName:cuRfirstName
		})
		console.log(tasks);
		this.setState({
			firstName:''
		})
	}
	
	render(){
		return(
		<div>
			<form onSubmit={this.onSubmitForm}>
				<div> 
					<p>
						<input 
							type='text'
							name="firstName"
							placeholder="First name"
							value={this.state.firstName}
							onChange={this.change}
						/> 
					</p>
				</div>
				<div> <button> Submit </button> </div>
			</form>
			<div>
				<ul>
				{
					this.state.tasks.map((item, index)=>{
						return(
							<li key={index}>
								<Link 
									to={`/${index}`} 
									onClick={this.deleteItem}
								> 
									{item.firstName} 
								</Link>
							</li>
						)
					})
				}
				</ul>
			</div>
		</div>
		)
	}
}

const Details =(props)=>{
	return(
		<div>
			<div>
				<p> Name:  </p>
				<p> Index:  </p>
			</div>
			<div> <Link to="/"> Back to Home </Link></div> <br/>
			<div className="columnContent">
				<div> 1 </div>
				<div> 2 </div>
				<div> 3 </div>
			</div>
		</div>
	)
}

const App =()=> {
	return(
		<Router history={History}>
			<switch>
				<Route exact path='/' component={AddItem}/>
				<Route path='/:index' component={Details} />
			</switch>
		</Router>
	)
}

render(
	<App />, document.getElementById('root'));
