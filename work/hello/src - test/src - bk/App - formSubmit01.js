import React, { Component } from 'react';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
import Comments from './comments.js';

class TextBox extends React.Component{
    render(){
        return (
            <input className='form-control' 
                name={this.props.name}
                type='text' 
                value={this.props.value}
                onChange={this.props.onChange}/>
        );
    }
}
class App extends React.Component{
	constructor(){
		super();
		this.state = {
			form: { 
				firstName: 'Ryan', 
				lastName: 'Vice'
			}
		}
		this.onChange = this.onChange.bind(this);
		this.onSubmit = this.onSubmit.bind(this);
	}    
    onChange(event) {
        this.state.form[event.target.name] = event.target.value;
        this.setState({form: this.state.form});
    }
    onSubmit(event) {
        event.preventDefault();
        alert('Form submitted. firstName: ' +
            this.state.form.firstName +
            ', lastName: ' +
            this.state.form.lastName);
    }
    render() {
       var self = this;
       return (
            <form onSubmit={this.onSubmit}>
                <TextBox name='firstName' 
                    value={this.state.form.firstName}
                    onChange={this.onChange}/>
                <TextBox name='lastName'
                    value={this.state.form.lastName}
                    onChange={this.onChange}/>
                <button className='btn btn-success' 
                	type='submit'>Submit</button>
            </form>
        );
    }
};



export default App;
