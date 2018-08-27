import React from 'react';
import ReactDom from 'react-dom';
import './index.css';


/* class TodoList extends React.Component{
    constructor() {
        super();
        this.state = {
            firstname : "Sentu"
        }
    }
    render(){
        return (
            <ul>
               { this.state.firstname }
            </ul>
        )
    }
}*/


/* class TodoList extends React.Component{
    constructor() {
        super();
        this.state = {
            firstname : "Sentu"
        }
    }
    render(){
        return (
            <ul>
               <TodoItem/>
            </ul>
        )
    }
} 

class TodoItem extends React.Component{
    render(){
        return (
            <li> Hello world </li>
        )
    }
} */

/* class TodoList extends React.Component{
    constructor() {
        super();
        this.state = {
            firstname : "Sentu"
        }
    }
    render(){
        return (
            <ul>
               <TodoItem detail = {this.state.firstname}/>
            </ul>
        )
    }
} 

class TodoItem extends React.Component{
    render(){
        return (
            <li> {this.props.detail} </li>
        )
    }
} */

class TodoList extends React.Component{
    constructor() {
        super();
        this.state = {
            names : ["Sentu", "Pintu", "Ranku", "Diko"]
        }
    }
    render(){
        return (
            <ul>
            {
                this.state.names.map(function(name){
                   return <TodoItem key={name} detail = {name}/>
                })
            }
            </ul>
        )
    }
} 

class TodoItem extends React.Component{
    render(){
        return (
            <li> {this.props.detail} </li>
        )
    }
}

ReactDom.render(<TodoList/>, document.getElementById("root"));


