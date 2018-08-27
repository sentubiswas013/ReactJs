import React from 'react';
import ReactDom from 'react-dom';
import './index.css';

class App extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            count : 0
        }
        console.log("constructor");
    }
    incCount  =() =>{
        this.setState({
            count:this.state.count + 1
        })
    }
    componentWillMount() {
        console.log("Will mount");
    }
    shouldComponentUpdate(){
		console.log("Update mount");
        if(this.state.count > 10) {
            return false;
        }
        return true;
    }
    componentDidMount() {
        console.log("Did mount");
    }
    render(){
        console.log("render");
        return(
            <section>
                    {this.state.count}
                   <button onClick={this.incCount}> Increment </button>
            </section>
        )
    }
}

ReactDom.render(<App/>, document.getElementById("root"));