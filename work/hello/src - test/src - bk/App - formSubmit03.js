import React from 'react';

class App extends React.Component{
	constructor(props){
		super(props);
		this.state = {
			firstName:'',
			lastName:'',
			userName:'',
			email:'',
			password:'',
			tasks:[]
		}
		this.change = this.change.bind(this);
		this.onSubmitForm = this.onSubmitForm.bind(this);
	}
	
	change(e){
		this.setState({
			[e.target.name]:e.target.value
		})
	}
	onSubmitForm(e){
		e.preventDefault();
		//console.log(this.state);
		let tasks = this.state.tasks;
		let cuRfirstName = this.state.firstName;
		let cuRlastName = this.state.lastName;
		let cuRuserName = this.state.userName;
		let cuRemail = this.state.email;
		let cuRpassword = this.state.password;
		tasks.push({
			firstName:cuRfirstName,
			lastName:cuRlastName,
			userName:cuRuserName,
			email:cuRemail,
			password:cuRpassword
		})
		console.log(tasks);
       
		this.setState({
			firstName:'',
			lastName:'',
			userName:'',
			email:'',
			password:''
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
					<p>
					<input 
						type='text'
						name="lastName"
						placeholder="Last name"
						value={this.state.lastName}
						onChange={this.change}
					/>
					</p>
					<p>
					<input 
						type='text'
						name="userName"
						placeholder="User name"
						value={this.state.userName}
						onChange={this.change}
					/>
					</p>
					<p>					
					<input
						type='text'
						name="email"
						placeholder="Email"
						value={this.state.email}
						onChange={this.change}
					/>
					</p>
					<p>
					<input
						type='password'
						name="password"
						placeholder="Password"
						value={this.state.password}
						onChange={this.change}
					/>
					</p>
				</div>
				<div> <button> Submit </button> </div>
			</form>
			{/*<div>
				{
					JSON.stringify(this.state)
				}
			</div>*/}
			<div>
				<ul>
				{
					this.state.tasks.map((item, index)=>{
						return(
							<div key={index} className="infoLine">
							<li>First Name:  {item.firstName} </li>
							<li>Last Name: {item.lastName} </li>
							<li>User Name: {item.userName} </li>
							<li>Email: {item.email} </li>
							<li>Password: {item.password} </li>
							</div>
						)
					})
				}
				</ul>
			</div>
		</div>
		)
	}
}

export default App;
