import React from 'react';
import ReactDom from 'react-dom';
import './index.css';
import TodoItem from './components/TodoItem.js';


class TodoList extends React.Component {
    constructor() {
        super();
        this.changeStatus = this.changeStatus.bind(this);
        this.state = {
            tasks:[
                {name: "Buy Milk", completed:false},
                {name: "Buy Cheese", completed:false},
                {name: "Buy Bread", completed:false},
                {name: "Buy Sugar", completed:false}
            ]
        }
    }

    changeStatus(index) {
        //console.log(this.state.tasks[index]);
        var tasks = this.state.tasks;
        var task = tasks[index];
        task.completed = !task.completed;
        this.setState({
            tasks:tasks
        });
    }

    render() {
        return (
            <ul>
            {
                this.state.tasks.map((task, index) => {
                   return <TodoItem 
                    key={task.name} 
                    clickHandler={this.changeStatus} 
                    index={index} 
                    details={task} />
                })
            }
            </ul>
        )
    }
} 

ReactDom.render(<TodoList/>, document.getElementById("root"));