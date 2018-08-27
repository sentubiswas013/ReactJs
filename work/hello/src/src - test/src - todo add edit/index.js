import React from 'react';
import { render } from 'react-dom';
import './App.css'

class App extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			name:'',
			email:'',
			password:'',
			users:[
			{name:"Sentu",email:"sentu@gmail.com",password:"123"},
			{name:"Pintu",email:"Pintu@gmail.com",password:"321"}
			]
		}
		this.onchange = this.onchange.bind(this);
		this.submitForm = this.submitForm.bind(this);
		this.deleteItem = this.deleteItem.bind(this);
		this.editItem = this.editItem.bind(this);
	}
	onchange(e){
		console.log("onchange");
		this.setState({
			//name : e.target.value
			[e.target.name]:e.target.value
		})		
	}	
	editItem(){
		
	}
	submitForm(e){
		console.log("submitForm");
		e.preventDefault();
		let name = this.state.name;
		let email = this.state.email;
		let password = this.state.password;
		let users = this.state.users;
		users.push({
			name:name,
			email:email,
			password:password
		})
		this.setState({
			name:"",
			email:"",
			password:""
		})
	}
	deleteItem(index){
		console.log("deleteItem");
		console.log(index);
		let users = this.state.users;
		users.splice(index, 1);
		this.setState({
			users
		});		
	}
	render(){
		return(
		<div>
			<form onSubmit={this.submitForm}>
				<p> 
					<input 
						name="name" 
						type="text" 
						placeholder="Name"
						value={this.state.name}
						onChange={this.onchange}
					/> 
				</p>
				<p> 
					<input 
						name="email" 
						type="text" 
						placeholder="Email"
						value={this.state.email}
						onChange={this.onchange}
					/> 
				</p>
				<p> 
					<input 
						name="password" 
						type="text" 
						placeholder="Password"
						value={this.state.password}
						onChange={this.onchange}
					/> 
				</p>
				<p><button> Submit </button> </p>
			</form>
			<ul>
				{
					this.state.users.map((user, index)=>{
						return(
							<UserList
							key={index}
							index={index}
							proUser = {user} 
							deleteItem = {this.deleteItem}
							editItem = {this.editItem}
							/>
						)
					})
				}
			</ul>
		</div>
		)
	}
}

class UserList extends React.Component{	
	constructor(props){
		super(props)
		this.state = {
			isEditing:false
		}
		this.toggleState = this.toggleState.bind(this);
		this.renderForm = this.renderForm.bind(this);
		this.renderItem = this.renderItem.bind(this);
		this.updateForm = this.updateForm.bind(this);
	}
	toggleState(){
		//const {isEditing} this.state;
		this.setState({
			isEditing:!this.state.isEditing
		})
	}
	updateForm(e){
		e.preventDefault();
        //console.log([e.target.name]);
        console.log(this.name.value);
        console.log(this.email.value);
        console.log(this.password.value);
		this.props.editItem(this.props.index, this.name.value);
	}
	
	renderForm(){
		return(
			<form onSubmit={this.updateForm}>
				<span> 
					<input 
						type="text" 
						ref={(value)=>{
							this.name = value
						}}
						value={this.props.proUser.name}
						onChange={this.onchange}
					/> 
				</span>
				<span> 
					<input 
						type="text" 
						ref={(value)=>{
							this.email = value
						}}
						value={this.props.proUser.email}
						onChange={this.onchange}
					/> 
				</span>
				<span> 
					<input 
						type="text" 
						ref={(value)=>{
							this.password = value
						}}
						value={this.props.proUser.password}
						onChange={this.onchange}
					/> 
				</span>
				<span> <button> Update </button> </span>
			</form>
		)
	}
	renderItem(){
		return(
			<li>
				<span>{this.props.proUser.name} | </span>
				<span>{this.props.proUser.email} | </span>
				<span>{this.props.proUser.password} </span>
				<button onClick={()=>{
					this.toggleState()
				}}> Edit</button>
				<button onClick={(e)=>{
					e.preventDefault();
					this.props.deleteItem(this.props.index)
				}}> Delete</button>
			</li>
		)
	}
	render(){
		console.log(this.state.isEditing)
		return(
			<div>
				{
					this.state.isEditing ?
					this.renderForm() :
					this.renderItem()
				}
			</div>
		)
	}
}


render( <App />, document.getElementById('root'));
