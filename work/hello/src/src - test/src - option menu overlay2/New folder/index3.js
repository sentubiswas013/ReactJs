import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import axios from 'axios';
import registerServiceWorker from './registerServiceWorker';

class App extends React.Component {
	constructor(props){
		super(props)
		this.state = {
            userInfo:[]
        } 
		//console.log("constructor")
	}
	componentWillMount(){
		//console.log("componentWillMount");
		axios.get('https://jsonplaceholder.typicode.com/users')
		  .then(function (response) {
			//console.log(response);
			this.setState({
				userInfo:response
			})
		  })
		  .catch(function (error) {
			console.log (error);
		  });
	}
	componentDidMount(){
		//console.log("componentDidMount");
	}
	shouldComponentUpdate(){
		//console.log("shouldComponentUpdate");
	}
	render(){
		//console.log("render");
		console.log(this.state.userInfo);
		return(
			<div> 
				{
					response.map((user)=>{
						return(
							<div> {user.name} </div>
						)
					})
				}
			</div>
		)
	}
}

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
