import React from 'react';

const TodoForm = (props) =>{
    //return <h1> Todo Form </h1>
    return (
    <form onSubmit={props.addTask}>
        <input type="text" 
            value={props.currentTask} 
            onChange={props.updateTask}
        />
        <button type="submit"> Submit </button>
    </form>
    )
}

export default TodoForm;