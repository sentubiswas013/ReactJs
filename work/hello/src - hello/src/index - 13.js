import React from 'react';
import ReactDom from 'react-dom';
import './index.css';
import TodoItem from './components/TodoItem.js';



class Todos extends React.Component{
    constructor(){
        super();
        this.state = {
            todos: ['I am learning ReactJS', 'I am learing Rails']
        }
    }

    render(){
        const {todos} = this.state;
        return(
        <section>
            <form onSubmit={(e) => {
                const {todos} = this.state;
                //todos.push(this.refs.addTodo.value);
                e.preventDefault();
                this.setState({
                    //todos
                    todos:todos.concat(this.refs.addTodo.value)
                })
                this.refs.addTodo.value = "";
            }}>
            <input type="text" ref="addTodo"/>
            <button type="submit"> Add Todo </button>
            </form>
            <TodoItem todos={todos} />
        </section>
        )
    }
}

ReactDom.render(<Todos/>, document.getElementById("root"));