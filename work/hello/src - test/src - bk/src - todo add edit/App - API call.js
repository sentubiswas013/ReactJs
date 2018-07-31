import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import Comments from './comments.js';

class App extends Component {
  constructor(){
	  super();
	  this.state = {
		  comments:Comments
	  }
	  this.fnChangePage = this.fnChangePage.bind(this);
  }
  fnChangePage(e){
	  console.log(e);
  }
  render() {
	//console.log(this.state.comments)
	//console.log(Comments)
    return (
      <div className="App">
		<ul>{
			Comments.commentList.map((item, index)=> {
				//console.log(item.email)
				return(
					<li key={index}
						//id={item.id}
						onClick={()=>{
							this.fnChangePage(item.id);
						}}
					>
						{item.email}
					</li>
				)
			})
		}
		<li><button> Add More </button></li>
		</ul>
      </div>
    );
  }
}

export default App;
