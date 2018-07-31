import React from 'react'
import ReactDom from 'react-dom'
import $ from 'jquery';


class HelloWorld extends React.Component{
	constructor() {
		super();
		this.state = {
			users: []
		}
	}
	componentDidMount(){
		$.ajax({
			url:"https://jsonplaceholder.typicode.com/users",
			success: (data) =>{
				console.log(data);
				this.setState({
					users:data
				})
			}
		})
	}
	render(){
		const { users } = this.state;
		return(
			<div> 
				<ul> 
					{
						users.map((user) => {
							return <li key={user.id}> { user.name } </li>
						})	
					}				
				</ul>
			</div>
		)
	}
}

ReactDom.render(<HelloWorld/>, document.getElementById('root'));
