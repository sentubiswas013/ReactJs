import React, { Component } from 'react';
import Parent from './Parent';

class App extends Component {
	state = {
		title: "Place Holder"
	}
	changeTheWorld = (newTitle) =>{
		this.setState({
			title: newTitle
		})
	}	
  render() {
	return (
	  <div>
			<Parent changeComponentFirst={this.changeTheWorld.bind(this, "change Component First")} changeComponentSecond={this.changeTheWorld.bind(this, "change Component Second")} title={this.state.title}/>
	  </div>
	)
  }
}


export default App;