import React from 'react';
import ReactDom from 'react-dom';
import './index.css';


/* class HellpWorld extends React.Component {
    render(){
        return (
            <div>
                <section className="foo">
                    Hello 
                </section>
                <section>
                    World
                </section>
            </div>
        )
    }
} */

/* class HellpWorld extends React.Component {
    getName (){
        return "Sentu Biswas";
    }
    render(){
        return (
            <div>
               { 9 + 9 } <br/>
               { this.getName() }
            </div>
        )
    }
}  */

class HellpWorld extends React.Component{
    constructor(){
        super();
        this.name = "Sentu Biswas";
    }
    render(){
        return (
            <div>
               { this.name }
            </div>
        )
    }
} 




ReactDom.render(<HellpWorld/>, document.getElementById("root"));