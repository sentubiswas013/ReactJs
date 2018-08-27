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
		console.log("constructor")
	}
	componentWillMount(){
		console.log("componentWillMount");
	}
	componentDidMount(){
		console.log("componentDidMount");
	}
	shouldComponentUpdate(){
		console.log("shouldComponentUpdate");
	}
	render(){
		console.log("render");
		return(
			<div> 
				asS
			</div>
		)
	}
}

ReactDOM.render(<App />, document.getElementById('root'));
registerServiceWorker();
