import React from 'react';
import { render } from 'react-dom';
import './App.css'

class App extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			firstName:'',
			tasks:[
				{ firstName:"Sentu"},
				{ firstName: "Pintu"}
			],
			isEdit:false
		}
		this.change = this.change.bind(this);
		this.onSubmitForm = this.onSubmitForm.bind(this);
		this.deleteItem = this.deleteItem.bind(this);
		this.editItem = this.editItem.bind(this);
	}
	change(e){
		this.setState({
			[e.target.name]:e.target.value
		})
	}
	editItem(index){
		//console.log(index);
		//let tasks = this.state.tasks;
		//console.log(tasks);
		this.setState({
			isEdit:true
		})
	}
	deleteItem(index){
		//e.preventDefault();
		console.log(index);
		//console.log("deleteItem");
		let tasks = this.state.tasks;
		tasks.splice(index, 1)
		this.setState({
			tasks
		})
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
						<button> Submit </button>
					</p>
				</div>
			</form>
			<div>
				<ul>
				{
					this.state.tasks.map((item, index)=>{
						return(
							<li key={index}>
								
							{
								this.state.isEdit?
								<input 
									type='text'
									name="firstName"
									placeholder="First name"
									value={item.firstName}
									onChange={this.change}
								/> 
								:
								item.firstName
							}
															
								<button 
									onClick={(evt)=>{
										evt.stopPropagation();
										this.deleteItem(index)
									}}
								> Delete </button>
								<button 
									onClick={(evt)=>{
										evt.stopPropagation();
										this.editItem(index)
									}}
								> Edit </button>
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


render(
	<App />, document.getElementById('root'));
