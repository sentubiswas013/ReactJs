import React from 'react';
import { render } from 'react-dom';
import { BrowserRouter, Switch, Route, Link } from 'react-router-dom';
//import PlayerAPI from './api';
///import axios from 'axios';

const App = () => (
  <div>
    <Switch>      
      <Route exact path='/' component={UserList}/>
      <Route path='/:id' component={User}/>
    </Switch>
  </div>
)

const UserApi = {
  users: [
    {id:1, name:"Sentu"},
    {id:2, name:"Diko"},
    {id:3, name:"Pintu"},
    {id:4, name:"Ranku"},
    {id:5, name:"Bisu"}
  ],
  get: function(id){
    //const isUser = user => user.id === id;
    function isUser(user) {
      return user.id === id;
    }
    //console.log(isUser);
    return this.users.find(isUser);
  }
}

const UserList =() => (
  <div> 
    <ul>
      {
        UserApi.users.map((user)=>{
          return(
            <li key={user.id}>
              <Link to={`/${user.id}`}> {user.name} </Link>
            </li>
          )
        })
      }
    </ul>
  </div>
)

const User =(props) => {
  const User = UserApi.get(parseInt(props.match.params.id, 10));
  //const User = parseInt(props.match.params.id);
  console.log(User);
  if(!User){ return <div> Sorry I didn't find anything </div> }
  return(
    <div> 
      <h3> {User.name} </h3>
      <Link to="/"> Home </Link>
    </div>
  )
}
  


render((
  <BrowserRouter>
    <App />
  </BrowserRouter>
), document.getElementById('root'));
