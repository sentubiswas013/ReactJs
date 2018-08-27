import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';

class App extends React.Component {
	constructor(props){
		super(props)
		this.state = {
            name:"",
			phone:"",
			users:[]
        } 
		this.onChange = this.onChange.bind(this);
		this.formSubmit = this.formSubmit.bind(this);
	}
	onChange(e){
		this.setState({
			[e.target.name]:e.target.value
		})
	}
	formSubmit(e){
		e.preventDefault();
		//console.log("formSubmit")
		let users = this.state.users;
		let name = this.state.name;
		let phone = this.state.phone;
		users.push({
			name:name,
			phone:phone
		})
		this.setState({
			name:"",
			phone:""
		})
	}
	render(){
		//console.log("render");
		console.log(this.state.userInfo);
		return(
			<div> 
				<Form
					formSubmit={this.formSubmit}
					onChange={this.onChange}
					name={this.state.name}
					phone={this.state.phone}
				/>
				<List
					users={this.state.users}
				/>
			</div>
		)
	}
}

class Form extends React.Component{
	render(){
		return(
			<div> 
				<form onSubmit={this.props.formSubmit}>
					<div> 
						<input 
							type="text" 
							name="name"
							value={this.props.name}
							placeholder="Name"
							onChange={this.props.onChange}
						/>
					</div>
					<div> 
						<input 
							type="text" 
							name="phone"
							value={this.props.phone}
							placeholder="Phone"
							onChange={this.props.onChange}
						/>
					</div>
					<div>
						<button> Add </button>
					</div>
				</form>
			</div>
		)
	}
}

class List extends React.Component{
	render(){
		return(
			<div> 
			{
				this.props.users.map((user, index)=>{
					return(
						<div> {user.name} {user.phone} </div>
					)
				})
			}
			</div>
		)
	}
}

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
