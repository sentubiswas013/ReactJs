import React from 'react';
import ReactDom from 'react-dom'



/*
class HelloWorld extends React.Component{
    render() {
        return <h1> HelloWorld </h1>;
    }
}
*/

class Hello extends React.Component {
    render(){
        return ( <h1> Hello </h1> )
    }
}
class World extends React.Component{
    render(){
        return ( <p> World </p> )   
    }
}

class HellpWorld extends React.Component{
    render(){
        return (
            <div>
                <Hello/>
                <World/>
            </div>
        )
    }
}

ReactDom.render(<HellpWorld/>, document.getElementById("root"));