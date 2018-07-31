import React from 'react';

class TodoItem extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isEditing: false
        }
        this.renderForm = this.renderForm.bind(this);
        this.renderItem = this.renderItem.bind(this);
        this.toggleState = this.toggleState.bind(this);
    }
    toggleState() {
        const { isEditing } = this.state;
        this.setState({
            isEditing:!isEditing
        })
    }
    renderItem() {
        return (
            <li onClick={ () => {
                this.props.clickHandler(this.props.index);
            }} className={this.props.details.completed ? 'completed' : ''}>
                    { this.props.details.name }
                    <button onClick={(evt)=>{
                        evt.stopPropagation();
                        this.props.deleteTask(this.props.index)
                    }}> Delete </button>

                    <button onClick={(evt)=>{
                        evt.stopPropagation();
                        this.toggleState()
                    }}> Edit Item </button>
            </li>
        )
    }
    renderForm (){
        return(
            <form>
                <input type="text" defaultValue={ this.props.details.name } />
                <button type="submit"> Update Item</button>
            </form>
         )
    }
    render(){
        //const isEditing = this.state.isEditing;
        const { isEditing } = this.state;
        return(

            <section>
            {
                isEditing ? this.renderForm() : this.renderItem()
            }
        </section>

            /* <section>
                {
                    isEditing?
                    <form>
                        <input type="text" defaultValue={ this.props.details.name } />
                        <button type="submit"> Update Item</button>
                    </form> 
                    :
                    <li onClick={ () => {
                    this.props.clickHandler(this.props.index);
                }} className={this.props.details.completed ? 'completed' : ''}>
                    { this.props.details.name }
                    <button onClick={(evt)=>{
                            evt.stopPropagation();
                            this.props.deleteTask(this.props.index)
                        }}> Delete </button>
                </li>
                }               
            </section> */
        )
    }
}

export default TodoItem;


