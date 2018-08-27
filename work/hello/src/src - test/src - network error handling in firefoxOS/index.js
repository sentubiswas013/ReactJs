import React from 'react';
import ReactDom from 'react-dom';
import './App.css';
import $ from 'jquery';

class App extends React.Component {
    constructor(props){
        super(props);
        this.state ={
            users:[]
        }    
    }
    componentWillMount(){
        console.log("Will mount");
    }
    shouldComponentUpdate(){
        if(this.state.count > 10){
            return false;
        }
        return true;
    }
    componentDidMount(){
		if (navigator.onLine) {
			//console.log('online');	
			alert("Online");
			$.ajax({
				url:'https://jsonplaceholder.typicode.com/users',
				success: (data) =>{
					this.setState({
						users:data
					})
				}
			})
		} else {
			//console.log('offline');
			alert("Please check your internet");
		}
		
    }

    render(){
        const { users } = this.state;         
        return(
            <ul>
                {
                    users.map((user) => {
                        return <li key={user.id}>{ user.name}</li>
                    })  
                 }  
            </ul>
        )
    }
}

ReactDom.render(<App/>, document.getElementById("root"));